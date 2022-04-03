package top.hendrixshen.bilibilidanmaku.event;

import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuConfigGui;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuReference;
import top.hendrixshen.bilibilidanmaku.config.Configs;
import top.hendrixshen.bilibilidanmaku.util.websocket.WebSocketManager;
import top.hendrixshen.magiclib.config.Option;

public class CallBacks {
    public static boolean openConfigGuiCallback(KeyAction keyAction, IKeybind keybind) {
        GuiBase.openGui(BilibiliDanmakuConfigGui.getInstance());
        return true;
    }

    public static void enableCallback(Option option) {
        if (Configs.enable) {
            if (WebSocketManager.getWebSocketClient() != null) {
                WebSocketManager.close();
            }
            WebSocketManager.open();
            return;
        }
        WebSocketManager.close();
    }

    public static void roomIdCallback(Option option) {
        if (Configs.enable) {
            if (WebSocketManager.getWebSocketClient() != null) {
                WebSocketManager.close();
            }
            WebSocketManager.open();
            return;
        }
        WebSocketManager.close();
    }

    public static void debugModeCallBack(Option option) {
        Configurator.setLevel(BilibiliDanmakuReference.getModId(), Level.toLevel(Configs.debugMode ? "DEBUG" : "INFO"));
        if (option != null) {
            BilibiliDanmakuConfigGui.getInstance().reDraw();
        }
    }
}
