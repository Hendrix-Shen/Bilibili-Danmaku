package top.hendrixshen.bilibilidanmaku.compat.modmenu;

import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screens.Screen;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuConfigGui;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuReference;

import java.util.function.Function;

public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public String getModId() {
        return BilibiliDanmakuReference.getModId();
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return (screen) -> {
            BilibiliDanmakuConfigGui gui = BilibiliDanmakuConfigGui.getInstance();
            gui.setParent(screen);
            return gui;
        };
    }
}
