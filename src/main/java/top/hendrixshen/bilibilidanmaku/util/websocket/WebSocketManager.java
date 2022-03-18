package top.hendrixshen.bilibilidanmaku.util.websocket;

import top.hendrixshen.bilibilidanmaku.BilibiliDanmaku;
import top.hendrixshen.bilibilidanmaku.util.InfoUtil;

public class WebSocketManager {
    private static WebSocketClient webSocketClient = null;

    public static WebSocketClient getWebSocketClient() {
        return webSocketClient;
    }

    public static void open() {
        if (webSocketClient == null) {
            webSocketClient = new WebSocketClient();
            try {
                webSocketClient.open();
                InfoUtil.sendClientMessage(InfoUtil.getMessage("websocket.open.successful"));
            } catch (Exception e) {
                webSocketClient = null;
                InfoUtil.sendClientMessage(InfoUtil.getMessage("websocket.open.failed"));
                BilibiliDanmaku.getLogger().error(e);
            }
        }
    }

    public static void close() {
        if (webSocketClient != null) {
            try {
                webSocketClient.close();
            } catch (InterruptedException e) {
                BilibiliDanmaku.getLogger().error(e);
            } finally {
                InfoUtil.sendClientMessage(InfoUtil.getMessage("websocket.close"));
                webSocketClient = null;
            }
        }
    }
}
