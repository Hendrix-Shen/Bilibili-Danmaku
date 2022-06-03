package top.hendrixshen.bilibilidanmaku;

import net.fabricmc.loader.api.FabricLoader;
import top.hendrixshen.bilibilidanmaku.util.VersionParser;

public class BilibiliDanmakuReference {
    private static final String MOD_ID = "bilibili-danmaku";
    private static final String MOD_NAME = "Bilibili Danmaku";
    private static final String MOD_VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
    private static final String MOD_VERSION_TYPE = VersionParser.getVersionType(MOD_VERSION);

    private static final int CONFIG_VERSION = 1;

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

    public static int getConfigVersion() {
        return CONFIG_VERSION;
    }
}
