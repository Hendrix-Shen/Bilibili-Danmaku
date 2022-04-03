package top.hendrixshen.bilibilidanmaku;

import net.fabricmc.api.ModInitializer;

public class BilibiliDanmakuCompat implements ModInitializer {
    @Override
    public void onInitialize() {
        BilibiliDanmaku.getLogger().info("[{}]: Compat module for {} loaded. Starting compat api...", BilibiliDanmakuCompatReference.getModName(), BilibiliDanmakuCompatReference.getCompatVersion());

        BilibiliDanmaku.getLogger().info("[{}]: Compat initialized - Version: {} ({})", BilibiliDanmakuCompatReference.getModName(), BilibiliDanmakuCompatReference.getModVersion(), BilibiliDanmakuCompatReference.getModVersionType());
    }
}
