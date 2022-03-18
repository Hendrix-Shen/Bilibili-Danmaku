package top.hendrixshen.bilibilidanmaku.event;

import fi.dy.masa.malilib.hotkeys.IHotkey;
import fi.dy.masa.malilib.hotkeys.IKeybindManager;
import fi.dy.masa.malilib.hotkeys.IKeybindProvider;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmaku;

import java.util.List;
import java.util.stream.Collectors;

public class InputHandler implements IKeybindProvider {
    private static final List<IHotkey> ALL_CUSTOM_HOTKEYS = BilibiliDanmaku.cm.getAllConfigOptionStream().
            filter(option -> option instanceof IHotkey).
            map(option -> (IHotkey) option).
            collect(Collectors.toList());

    @Override
    public void addKeysToMap(IKeybindManager manager) {
        ALL_CUSTOM_HOTKEYS.forEach(iHotkey -> manager.addKeybindToMap(iHotkey.getKeybind()));
    }

    @Override
    public void addHotkeys(IKeybindManager manager) {
    }
}
