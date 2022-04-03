package top.hendrixshen.bilibilidanmaku;

import fi.dy.masa.malilib.util.StringUtils;
import top.hendrixshen.bilibilidanmaku.config.ConfigCategory;
import top.hendrixshen.magiclib.config.ConfigManager;
import top.hendrixshen.magiclib.gui.ConfigGui;

public class BilibiliDanmakuConfigGui extends ConfigGui {
    private static final BilibiliDanmakuConfigGui INSTANCE;

    public BilibiliDanmakuConfigGui(String identifier, String defaultTab, ConfigManager configManager) {
        super(identifier, defaultTab, configManager, StringUtils.translate("bilibili-danmaku.gui.title", BilibiliDanmakuReference.getModVersion(), StringUtils.translate(String.format("bilibili-danmaku.misc.versionType.%s", BilibiliDanmakuReference.getModVersionType()))));
    }

    static {
        INSTANCE = new BilibiliDanmakuConfigGui(BilibiliDanmakuReference.getModId(), ConfigCategory.GENERIC, BilibiliDanmaku.cm);
    }

    public static BilibiliDanmakuConfigGui getInstance() {
        return INSTANCE;
    }
}
