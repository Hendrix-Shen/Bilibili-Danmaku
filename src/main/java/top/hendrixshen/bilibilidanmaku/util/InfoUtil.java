package top.hendrixshen.bilibilidanmaku.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.TextComponent;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmaku;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuReference;
import top.hendrixshen.magiclib.untils.language.I18n;

public class InfoUtil {
    private static final Minecraft minecraft = BilibiliDanmaku.getMinecraftClient();

    public static void sendClientMessage(String string) {
        sendClientMessage(new TextComponent(string));
    }

    public static void sendClientMessage(TextComponent component) {
        LocalPlayer player = minecraft.player;
        if (player != null) {
            player.sendMessage(component, player.getUUID());
        }
    }

    public static String getMessage(String node, Object... objects) {
        return I18n.translate(String.format("%s.messages.%s", BilibiliDanmakuReference.getModId(), node), objects);
    }
}
