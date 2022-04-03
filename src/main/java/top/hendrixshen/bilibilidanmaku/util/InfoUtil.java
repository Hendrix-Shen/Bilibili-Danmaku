package top.hendrixshen.bilibilidanmaku.util;

import fi.dy.masa.malilib.util.StringUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.TextComponent;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmaku;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuReference;

public class InfoUtil {
    private static final Minecraft minecraft = BilibiliDanmaku.getMinecraftClient();

    public static void sendClientMessage(String string) {
        sendClientMessage(new TextComponent(string));
    }

    public static void sendClientMessage(TextComponent component) {
        LocalPlayer player = minecraft.player;
        if (player != null) {
            player.displayClientMessage(component, false);
        }
    }

    public static String getMessage(String node, Object... objects) {
        return StringUtils.translate(String.format("%s.messages.%s", BilibiliDanmakuReference.getModId(), node), objects);
    }
}
