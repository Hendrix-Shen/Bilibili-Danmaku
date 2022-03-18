package top.hendrixshen.bilibilidanmaku.mixin.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.hendrixshen.bilibilidanmaku.util.websocket.WebSocketManager;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    @Inject(
            method = "clearLevel(Lnet/minecraft/client/gui/screens/Screen;)V",
            at = @At(
                    value = "HEAD"
            )
    )
    private void onDisconnect(Screen screen, CallbackInfo ci) {
        WebSocketManager.close();
    }
}
