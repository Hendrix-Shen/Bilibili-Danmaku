package top.hendrixshen.bilibilidanmaku.event;

import fi.dy.masa.malilib.config.options.ConfigBoolean;
import fi.dy.masa.malilib.config.options.ConfigInteger;
import fi.dy.masa.malilib.gui.GuiBase;
import fi.dy.masa.malilib.hotkeys.IKeybind;
import fi.dy.masa.malilib.hotkeys.KeyAction;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.config.Configurator;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuReference;
import top.hendrixshen.bilibilidanmaku.config.ConfigGui;
import top.hendrixshen.bilibilidanmaku.config.Configs;
import top.hendrixshen.bilibilidanmaku.util.websocket.WebSocketManager;
import top.hendrixshen.magiclib.util.FabricUtil;

import static top.hendrixshen.bilibilidanmaku.BilibiliDanmaku.cm;

public class CallBacks {
    public static boolean openConfigGuiCallback(KeyAction keyAction, IKeybind keybind) {
        GuiBase.openGui(ConfigGui.getInstance());
        return true;
    }

    public static void enableCallback(ConfigBoolean configBoolean) {
        if (configBoolean.getBooleanValue()) {
            if (WebSocketManager.getWebSocketClient() != null) {
                WebSocketManager.close();
            }
            WebSocketManager.open();
            return;
        }
        WebSocketManager.close();
    }

    public static void roomIdCallback(ConfigInteger configInteger) {
        if (Configs.enable.getBooleanValue()) {
            if (WebSocketManager.getWebSocketClient() != null) {
                WebSocketManager.close();
            }
            WebSocketManager.open();
            return;
        }
        WebSocketManager.close();
    }

    public static void debugModeCallBack(ConfigBoolean configBoolean) {
        Configurator.setLevel(BilibiliDanmakuReference.getModId(), Level.toLevel(configBoolean.getBooleanValue() ? "DEBUG" : "INFO"));
        cm.setHideDisabled(!configBoolean.getBooleanValue());
        cm.setHideDebug(!configBoolean.getBooleanValue());
        if (FabricUtil.isDevelopmentEnvironment()) {
            cm.setHideDevOnly(!configBoolean.getBooleanValue());
        }
        ConfigGui.getInstance().reDraw();
    }
}
