package top.hendrixshen.bilibilidanmaku.config;

import com.google.common.collect.ImmutableList;
import fi.dy.masa.malilib.config.options.*;
import top.hendrixshen.bilibilidanmaku.event.CallBacks;
import top.hendrixshen.magiclib.api.malilib.annotation.Config;

import static top.hendrixshen.bilibilidanmaku.BilibiliDanmaku.cm;

public class Configs {
    // Generic configs
    @Config(category = ConfigCategory.GENERIC)
    public static final ConfigBoolean enable = cm.createBoolean("enable", false);

    @Config(category = ConfigCategory.GENERIC)
    public static final ConfigStringList blockGifts = cm.createStringList("blockGifts", ImmutableList.of("辣条"));

    @Config(category = ConfigCategory.GENERIC)
    public static final ConfigStringList blockWords = cm.createStringList("blockWords", ImmutableList.of("Fuck"));

    @Config(category = ConfigCategory.GENERIC)
    public static final ConfigHotkey openConfigGui = cm.createHotkey("openConfigGui", "B,C");

    @Config(category = ConfigCategory.GENERIC)
    public static final ConfigInteger roomId = cm.createInteger("roomId", 0, 0, Integer.MAX_VALUE);

    // Chat toggle configs
    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static final ConfigBoolean chatDanmakuEnable = cm.createBoolean("chatDanmakuEnable", true);

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static final ConfigBoolean chatGiftEnable = cm.createBoolean("chatGiftEnable", true);

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static final ConfigBoolean chatGuardBuyEnable = cm.createBoolean("chatGuardBuyEnable", true);

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static final ConfigBoolean chatGuardBuyLevel1Enable = cm.createBoolean("chatGuardBuyLevel1Enable", true);

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static final ConfigBoolean chatGuardBuyLevel2Enable = cm.createBoolean("chatGuardBuyLevel2Enable", true);

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static final ConfigBoolean chatGuardBuyLevel3Enable = cm.createBoolean("chatGuardBuyLevel3Enable", true);

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static final ConfigBoolean chatSuperChatEnable = cm.createBoolean("chatSuperChatEnable", true);

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static final ConfigBoolean chatWelcomeEnable = cm.createBoolean("chatWelcomeEnable", true);

    @Config(category = ConfigCategory.TOGGLE_CHAT, devOnly = true)
    public static final ConfigBoolean chatWelcomeGuardEnable = cm.createBoolean("chatWelcomeGuardEnable", false);

    @Config(category = ConfigCategory.TOGGLE_CHAT, devOnly = true)
    public static final ConfigBoolean chatWelcomeGuardLv1Enable = cm.createBoolean("chatWelcomeGuardLv1Enable", false);

    @Config(category = ConfigCategory.TOGGLE_CHAT, devOnly = true)
    public static final ConfigBoolean chatWelcomeGuardLv2Enable = cm.createBoolean("chatWelcomeGuardLv2Enable", false);

    @Config(category = ConfigCategory.TOGGLE_CHAT, devOnly = true)
    public static final ConfigBoolean chatWelcomeGuardLv3Enable = cm.createBoolean("chatWelcomeGuardLv3Enable", false);

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static final ConfigBoolean chatWelcomeNormalEnable = cm.createBoolean("chatWelcomeNormalEnable", true);

    // Chat format configs
    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static final ConfigString formatChatAdmin = cm.createString("formatChatAdmin", "&4[房] &d<%{user}> &f%{danmaku}");

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static final ConfigString formatChatBuyGuardLv1 = cm.createString("formatChatBuyGuardLv1", "&4%{user} 开通了主播的总督");

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static final ConfigString formatChatBuyGuardLv2 = cm.createString("formatChatBuyGuardLv2", "&6%{user} 开通了主播的提督");

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static final ConfigString formatChatBuyGuardLv3 = cm.createString("formatChatBuyGuardLv3", "&3%{user} 开通了主播的舰长");

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static final ConfigString formatChatDanmakuGuard = cm.createString("formatChatDanmakuGuard", "&6[舰] &2<%{user}> &f%{danmaku}");

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static final ConfigString formatChatDanmakuNormal = cm.createString("formatChatDanmakuNormal", "&7[普] &b<%{user}> &f%{danmaku}");

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static final ConfigString formatChatGifts = cm.createString("formatChatGifts", "&7%{user} %{action} [%{gift}] × %{num}");

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static final ConfigString formatChatSuperChat = cm.createString("formatChatSuperChat", "&4%{user} > %{msg} [¥%{price}]");

    @Config(category = ConfigCategory.FORMAT_CHAT, devOnly = true)
    public static final ConfigString formatChatWelcomeGuardLv1 = cm.createString("formatChatWelcomeGuardLv1", "&4欢迎总督 %{user} 进入直播间");

    @Config(category = ConfigCategory.FORMAT_CHAT, devOnly = true)
    public static final ConfigString formatChatWelcomeGuardLv2 = cm.createString("formatChatWelcomeGuardLv2", "&6欢迎提督 %{user} 进入直播间");

    @Config(category = ConfigCategory.FORMAT_CHAT, devOnly = true)
    public static final ConfigString formatChatWelcomeGuardLv3 = cm.createString("formatChatWelcomeGuardLv3", "&3欢迎舰长 %{user} 进入直播间");

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static final ConfigString formatChatWelcomeNormal = cm.createString("formatChatWelcomeNormal", "&7欢迎 %{user} 进入直播间");

    // Debug configs
    @Config(category = ConfigCategory.DEBUG)
    public static final ConfigBoolean debugMode = cm.createBoolean("debugMode", false);

    @Config(category = ConfigCategory.DEBUG, debug = true)
    public static final ConfigString initApiUrl = cm.createString("initApiUrl", "https://api.live.bilibili.com/room/v1/Room/room_init");

    @Config(category = ConfigCategory.DEBUG, debug = true)
    public static final ConfigString websocketUri = cm.createString("websocketUri", "wss://broadcastlv.chat.bilibili.com:443/sub");

    public static void initCallbacks() {
        // Generic config callback
        enable.setValueChangeCallback(CallBacks::enableCallback);
        openConfigGui.getKeybind().setCallback(CallBacks::openConfigGuiCallback);
        roomId.setValueChangeCallback(CallBacks::roomIdCallback);

        // Debug config callback
        debugMode.setValueChangeCallback(CallBacks::debugModeCallBack);
    }
}
