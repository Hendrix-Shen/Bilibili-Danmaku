package top.hendrixshen.bilibilidanmaku;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import top.hendrixshen.bilibilidanmaku.config.ConfigHandler;
import top.hendrixshen.bilibilidanmaku.config.Configs;

public class BilibiliDanmaku implements ClientModInitializer {
    private static final Logger logger = LogManager.getLogger(BilibiliDanmakuReference.getModId());
    @NotNull
    private static final Minecraft minecraftClient = Minecraft.getInstance();

    public static @NotNull Minecraft getMinecraftClient() {
        return minecraftClient;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public void onInitializeClient() {
        ConfigHandler configHandler = BilibiliDanmakuReference.getConfigHandler();
        configHandler.configManager.parseConfigClass(Configs.class);
        ConfigHandler.register(configHandler);
        Configs.initCallbacks(configHandler.configManager);

        logger.info("[{}]: Mod initialized - Version: {} ({})", BilibiliDanmakuReference.getModName(), BilibiliDanmakuReference.getModVersion(), BilibiliDanmakuReference.getModVersionType());
    }
}
