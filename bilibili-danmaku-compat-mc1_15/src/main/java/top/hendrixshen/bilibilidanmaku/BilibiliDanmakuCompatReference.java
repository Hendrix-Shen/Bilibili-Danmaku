package top.hendrixshen.bilibilidanmaku;

import net.fabricmc.loader.api.FabricLoader;
import top.hendrixshen.bilibilidanmaku.util.VersionParser;

public class BilibiliDanmakuCompatReference {
    private static final String MOD_COMPAT_VERSION = "MC 1.16.x";
    private static final String MOD_ID = "bilibili-danmaku-compat-mc1_16";
    private static final String MOD_NAME = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getName();
    private static final String MOD_VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
    private static final String MOD_VERSION_TYPE = VersionParser.getVersionType(MOD_VERSION);

    public static String getCompatVersion() {
        return MOD_COMPAT_VERSION;
    }

    public static String getModId() {
        return MOD_ID;
    }

    public static String getModName() {
        return MOD_NAME;
    }

    public static String getModVersion() {
        return MOD_VERSION;
    }

    public static String getModVersionType() {
        return MOD_VERSION_TYPE;
    }
}
