package top.hendrixshen.bilibilidanmaku.config;

import top.hendrixshen.bilibilidanmaku.BilibiliDanmaku;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuReference;
import top.hendrixshen.magiclib.untils.language.I18n;
import top.hendrixshen.magiclib.util.malilib.ConfigManager;

public class ConfigGui extends top.hendrixshen.magiclib.impl.malilib.ConfigGui {
    private static final ConfigGui INSTANCE;

    public ConfigGui(String identifier, String defaultTab, ConfigManager configManager) {
        super(identifier, defaultTab, configManager, I18n.translate("bilibili-danmaku.gui.title", BilibiliDanmakuReference.getModVersion(), I18n.translate(String.format("bilibili-danmaku.misc.versionType.%s", BilibiliDanmakuReference.getModVersionType()))));
    }

    static {
        INSTANCE = new ConfigGui(BilibiliDanmakuReference.getModId(), ConfigCategory.GENERIC, BilibiliDanmaku.cm);
    }

    public static ConfigGui getInstance() {
        return INSTANCE;
    }
}
