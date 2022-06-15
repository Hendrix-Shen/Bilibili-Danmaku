package top.hendrixshen.bilibilidanmaku.compat.modmenu;

import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuReference;

public class WrapperModMenuApiImpl extends ModMenuApiImpl {
    @Override
    public String getModIdCompat() {
        return BilibiliDanmakuReference.getModId();
    }
}