package top.hendrixshen.bilibilidanmaku.mixin.event;

import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundLoginPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.hendrixshen.bilibilidanmaku.config.Configs;
import top.hendrixshen.bilibilidanmaku.util.websocket.WebSocketManager;

@Mixin(ClientPacketListener.class)
public class MixinClientPacketListener {
    @Inject(
            method = "handleLogin",
            at = @At(
                    value = "RETURN"
            )
    )
    private void afterLogin(ClientboundLoginPacket clientboundLoginPacket, CallbackInfo ci) {
        if (Configs.enable.getBooleanValue()) {
            WebSocketManager.open();
        }
    }
}
