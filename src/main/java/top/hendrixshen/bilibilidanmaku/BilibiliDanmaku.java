package top.hendrixshen.bilibilidanmaku;

import fi.dy.masa.malilib.event.InitializationHandler;
import fi.dy.masa.malilib.event.InputEventHandler;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.hendrixshen.bilibilidanmaku.config.ConfigStorage;
import top.hendrixshen.bilibilidanmaku.config.Configs;
import top.hendrixshen.bilibilidanmaku.event.InputHandler;
import top.hendrixshen.magiclib.untils.language.I18n;
import top.hendrixshen.magiclib.util.malilib.ConfigManager;

public class BilibiliDanmaku implements ModInitializer {
    private static final Logger logger = LogManager.getLogger(BilibiliDanmakuReference.getModId());
    private static final Minecraft minecraftClient = Minecraft.getInstance();

    public static ConfigManager cm = new ConfigManager(BilibiliDanmakuReference.getModId());

    public static Minecraft getMinecraftClient() {
        return minecraftClient;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public void onInitialize() {
        InitializationHandler.getInstance().registerInitializationHandler(() -> {
            cm.parseConfigClass(Configs.class);
            fi.dy.masa.malilib.config.ConfigManager.getInstance().registerConfigHandler(BilibiliDanmakuReference.getModId(), new ConfigStorage());
            InputEventHandler.getKeybindManager().registerKeybindProvider(new InputHandler());
            Configs.initCallbacks();

            logger.info(String.format("[%s]: Mod initialized - Version: %s (%s)", BilibiliDanmakuReference.getModName(), BilibiliDanmakuReference.getModVersion(), BilibiliDanmakuReference.getModVersionType()));
        });

        I18n.getInstance().register(BilibiliDanmakuReference.getModId(), "en_us");
        I18n.getInstance().register(BilibiliDanmakuReference.getModId(), "zh_cn");
    }
}
