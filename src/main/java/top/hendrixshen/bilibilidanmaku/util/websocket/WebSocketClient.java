package top.hendrixshen.bilibilidanmaku.util.websocket;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.EmptyHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import top.hendrixshen.bilibilidanmaku.util.bilibili.Bilibili;

import java.net.URI;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class WebSocketClient {
    public static final ScheduledExecutorService SERVICE = Executors.newSingleThreadScheduledExecutor();
    public ScheduledFuture<?> HEART_BEAT_TASK = null;
    private final URI uri = URI.create(Bilibili.getInstance().getUri());
    private Channel channel;

    public void open() throws Exception {
        Bootstrap bootstrap = new Bootstrap();
        SslContext sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
        EventLoopGroup group = new NioEventLoopGroup();
        WebSocketClientHandshaker handshaker = WebSocketClientHandshakerFactory.newHandshaker(uri, WebSocketVersion.V13,
                null, false, EmptyHttpHeaders.INSTANCE);
        WebSocketClientHandler handler = new WebSocketClientHandler(handshaker);

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ChannelPipeline pipe = ch.pipeline();
                        pipe.addLast(
                                sslCtx.newHandler(ch.alloc(), uri.getHost(), uri.getPort()),
                                new HttpClientCodec(),
                                new HttpObjectAggregator(8192),
                                handler
                        );
                    }
                });

        this.channel = bootstrap.connect(this.uri.getHost(), this.uri.getPort()).sync().channel();
        handler.handshakeFuture().sync();

        Bilibili.getInstance().initMessage(this);
        this.HEART_BEAT_TASK = SERVICE.scheduleAtFixedRate(() -> sendMessage(Bilibili.getInstance().getHeartBeatBuffer()),
                Bilibili.getInstance().getHeartBeatInterval(), Bilibili.getInstance().getHeartBeatInterval(), TimeUnit.MILLISECONDS);
    }

    public void close() throws InterruptedException {
        this.channel.writeAndFlush(new CloseWebSocketFrame());
        this.channel.closeFuture().sync();
        this.HEART_BEAT_TASK.cancel(true);
    }

    public void sendMessage(final ByteBuf binaryData) {
        this.channel.writeAndFlush(new BinaryWebSocketFrame(binaryData));
    }
}
