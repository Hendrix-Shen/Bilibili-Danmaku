package top.hendrixshen.bilibilidanmaku.compat.modmenu;

import io.github.prospector.modmenu.api.ConfigScreenFactory;
import io.github.prospector.modmenu.api.ModMenuApi;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuConfigGui;

public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (screen) -> {
            BilibiliDanmakuConfigGui gui = BilibiliDanmakuConfigGui.getInstance();
            gui.setParent(screen);
            return gui;
        };
    }
}
