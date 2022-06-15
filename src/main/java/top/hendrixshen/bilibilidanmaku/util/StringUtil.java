package top.hendrixshen.bilibilidanmaku.util;

import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuReference;
import top.hendrixshen.magiclib.language.I18n;

public class StringUtil {
    public static String tr(String key, Object... objects) {
        return I18n.get(String.format("%s.%s", BilibiliDanmakuReference.getModId(), key), objects);
    }
}
