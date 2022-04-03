package top.hendrixshen.bilibilidanmaku;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.hendrixshen.bilibilidanmaku.config.Configs;
import top.hendrixshen.magiclib.config.ConfigHandler;
import top.hendrixshen.magiclib.config.ConfigManager;

public class BilibiliDanmaku implements ModInitializer {
    private static final Logger logger = LogManager.getLogger(BilibiliDanmakuReference.getModId());
    private static final Minecraft minecraftClient = Minecraft.getInstance();

    public static ConfigManager cm = ConfigManager.get(BilibiliDanmakuReference.getModId());

    public static Minecraft getMinecraftClient() {
        return minecraftClient;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public void onInitialize() {
        cm.parseConfigClass(Configs.class);

        ConfigHandler.register(new ConfigHandler(BilibiliDanmakuReference.getModId(), cm, BilibiliDanmakuReference.getConfigVersion(), null, null));
        Configs.initCallbacks(cm);

        logger.info(String.format("[%s]: Mod initialized - Version: %s (%s)", BilibiliDanmakuReference.getModName(), BilibiliDanmakuReference.getModVersion(), BilibiliDanmakuReference.getModVersionType()));
    }
}
