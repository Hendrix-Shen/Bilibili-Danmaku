package top.hendrixshen.bilibilidanmaku.util;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmaku;
import top.hendrixshen.magiclib.compat.minecraft.network.chat.ComponentCompatApi;

public class InfoUtil {
    private final static Minecraft mc = BilibiliDanmaku.getMinecraftClient();

    public static void displayClientMessage(Component component, boolean useActionBar) {
        if (mc.player != null) {
            mc.player.displayClientMessage(component, useActionBar);
        }
    }

    public static void displayClientMessage(String string, boolean useActionBar) {
        InfoUtil.displayClientMessage(ComponentCompatApi.literal(string), useActionBar);
    }

    public static void displayActionBarMessage(Component component) {
        InfoUtil.displayClientMessage(component, true);
    }

    public static void displayActionBarMessage(String string) {
        InfoUtil.displayClientMessage(string, true);
    }

    public static void displayChatMessage(Component component) {
        InfoUtil.displayClientMessage(component, false);
    }

    public static void displayChatMessage(String string) {
        InfoUtil.displayClientMessage(string, false);
    }
}
