package top.hendrixshen.bilibilidanmaku.util.bilibili;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.apache.commons.io.IOUtils;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmaku;
import top.hendrixshen.bilibilidanmaku.config.Configs;
import top.hendrixshen.bilibilidanmaku.util.InfoUtil;
import top.hendrixshen.bilibilidanmaku.util.Zlib;
import top.hendrixshen.bilibilidanmaku.util.websocket.WebSocketClient;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bilibili {
    private static final Bilibili INSTANCE = new Bilibili();

    private static final long HEART_BEAT_INTERVAL = 30 * 1000;

    private static final int HEADER_LENGTH = 16;
    private static final int SEQUENCE_ID = 1;

    private static final int PACKET_LENGTH_OFFSET = 0;
    private static final int PROTOCOL_VERSION_OFFSET = 6;
    private static final int OPERATION_OFFSET = 8;
    private static final int BODY_OFFSET = 16;

    private static final int JSON_PROTOCOL_VERSION = 0;
    private static final int POPULAR_PROTOCOL_VERSION = 1;
    private static final int BUFFER_PROTOCOL_VERSION = 2;

    private static final int HEART_BEAT_OPERATION = 2;
    private static final int POPULAR_OPERATION = 3;
    private static final int MESSAGE_OPERATION = 5;
    private static final int ENTER_ROOM_OPERATION = 7;

    private final Gson gson = new GsonBuilder().registerTypeAdapter(String.class, new JsonDeserializer()).create();

    private static final Pattern SPLIT = Pattern.compile("}.{16}\\{\"cmd\"");
    private static final Pattern EXTRACT_ROOM_ID = Pattern.compile("\"room_id\":(\\d+),");

    public static Bilibili getInstance() {
        return INSTANCE;
    }

    public String getUri() {
        return Configs.websocketUri.getStringValue();
    }

    public String getInitApiUrl() {
        return Configs.initApiUrl.getStringValue();
    }

    public int getRealRoomId(int id) {
        String realRoomId = null;
        try {
            URL url = new URL(String.format("%s?id=%s", this.getInitApiUrl(), id));
            String data = IOUtils.toString(url, StandardCharsets.UTF_8);
            Matcher matcher = EXTRACT_ROOM_ID.matcher(data);
            return matcher.find() ? Integer.parseInt(matcher.group(1)) : -1;
        } catch (IOException e) {
            BilibiliDanmaku.getLogger().error(e);
        }
        return -1;
    }

    public void initMessage(WebSocketClient client) {
        int id = this.getRealRoomId(Configs.roomId.getIntegerValue());
        if (id > -1) {
            InfoUtil.sendClientMessage(InfoUtil.getMessage("getRealRoomID.successful", id));
            byte[] message = String.format("{\"roomid\": %d}", id).getBytes(StandardCharsets.UTF_8);
            ByteBuf buf = Unpooled.buffer();
            buf.writeInt(HEADER_LENGTH + message.length);
            buf.writeShort(HEADER_LENGTH);
            buf.writeShort(BUFFER_PROTOCOL_VERSION);
            buf.writeInt(ENTER_ROOM_OPERATION);
            buf.writeInt(SEQUENCE_ID);
            buf.writeBytes(message);
            client.sendMessage(buf);
        } else {
            InfoUtil.sendClientMessage(InfoUtil.getMessage("getRealRoomID.failed"));
        }
    }

    public long getHeartBeatInterval() {
        return HEART_BEAT_INTERVAL;
    }

    public ByteBuf getHeartBeatBuffer() {
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(HEADER_LENGTH);
        buf.writeShort(HEADER_LENGTH);
        buf.writeShort(BUFFER_PROTOCOL_VERSION);
        buf.writeInt(HEART_BEAT_OPERATION);
        buf.writeInt(SEQUENCE_ID);
        return buf;
    }

    public void handMessage(WebSocketFrame webSocketFrame) throws Exception {
        if (webSocketFrame instanceof BinaryWebSocketFrame) {
            ByteBuf data = webSocketFrame.content();
            int protocol = data.getShort(PROTOCOL_VERSION_OFFSET);
            switch (protocol) {
                case JSON_PROTOCOL_VERSION:
                case POPULAR_PROTOCOL_VERSION:
                    return;
                case BUFFER_PROTOCOL_VERSION:
                    this.handleBufferMessage(data);
                    return;
                default:
            }
        }
    }

    private void handleBufferMessage(ByteBuf data) throws Exception {
        int packetLength = data.getInt(PACKET_LENGTH_OFFSET);
        int operation = data.getInt(OPERATION_OFFSET);

        if (operation == MESSAGE_OPERATION) {
            byte[] uncompressedData = new byte[packetLength - BODY_OFFSET];
            data.getBytes(BODY_OFFSET, uncompressedData);
            byte[] decompressData = Zlib.decompress(uncompressedData);
            byte[] msgBytes = Arrays.copyOfRange(decompressData, BODY_OFFSET, decompressData.length);
            String[] message = this.split(IOUtils.toString(msgBytes, StandardCharsets.UTF_8.toString()));
            for (String msg : message) {
                handleStringMessage(msg);
            }
        }
    }

    private void handleStringMessage(String message) {
        BilibiliDanmaku.getLogger().debug(message);
        try {
            String str = gson.fromJson(message, String.class);
            if (str != null) {
                InfoUtil.sendClientMessage(str);
            }
        } catch (JsonSyntaxException ignore) {
        }
    }

    public String[] split(String msg) {
        msg = msg.replaceAll("\n", "\u0020").replaceAll("\r", "\u0020");
        String[] split = SPLIT.split(msg);
        if (split.length > 1) {
            for (int i = 0; i < split.length; i++) {
                if (i == 0) {
                    split[0] = split[0] + "}";
                } else if (i == split.length - 1) {
                    split[i] = "{\"cmd\"" + split[i];
                } else {
                    split[i] = "{\"cmd\"" + split[i] + "}";
                }
            }
        }
        return split;
    }
}
