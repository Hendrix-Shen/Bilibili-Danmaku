package top.hendrixshen.bilibilidanmaku.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import top.hendrixshen.bilibilidanmaku.config.ConfigGui;

public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {

        return (screen) -> {
            ConfigGui gui = ConfigGui.getInstance();
            gui.setParent(screen);
            return gui;
        };
    }
}