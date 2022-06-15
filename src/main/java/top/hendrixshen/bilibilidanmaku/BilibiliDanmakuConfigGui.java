package top.hendrixshen.bilibilidanmaku;

import top.hendrixshen.bilibilidanmaku.config.ConfigCategory;
import top.hendrixshen.bilibilidanmaku.util.StringUtil;
import top.hendrixshen.magiclib.config.ConfigManager;
import top.hendrixshen.magiclib.gui.ConfigGui;

public class BilibiliDanmakuConfigGui extends ConfigGui {
    private final static BilibiliDanmakuConfigGui INSTANCE = new BilibiliDanmakuConfigGui(BilibiliDanmakuReference.getModId(), ConfigCategory.GENERIC, BilibiliDanmakuReference.getConfigHandler().configManager);

    public BilibiliDanmakuConfigGui(String identifier, String defaultTab, ConfigManager configManager) {
        super(identifier, defaultTab, configManager, () -> StringUtil.tr("gui.title", BilibiliDanmakuReference.getModVersion(), StringUtil.tr(String.format("misc.versionType.%s", BilibiliDanmakuReference.getModVersionType()))));
    }

    public static BilibiliDanmakuConfigGui getInstance() {
        return INSTANCE;
    }
}
