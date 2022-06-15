package top.hendrixshen.bilibilidanmaku.compat.modmenu;

import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuConfigGui;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuReference;
import top.hendrixshen.magiclib.compat.modmenu.ModMenuCompatApi;

public class ModMenuApiImpl implements ModMenuCompatApi {
    @Override
    public ConfigScreenFactoryCompat<?> getConfigScreenFactoryCompat() {
        return (screen) -> {
            BilibiliDanmakuConfigGui gui = BilibiliDanmakuConfigGui.getInstance();
            gui.setParentGui(screen);
            return gui;
        };
    }

    @Override
    public String getModIdCompat() {
        return BilibiliDanmakuReference.getCurrentModId();
    }
}