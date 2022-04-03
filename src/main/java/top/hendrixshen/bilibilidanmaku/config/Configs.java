package top.hendrixshen.bilibilidanmaku.config;

import com.google.common.collect.Lists;
import fi.dy.masa.malilib.config.options.ConfigHotkey;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuPredicates;
import top.hendrixshen.bilibilidanmaku.event.CallBacks;
import top.hendrixshen.magiclib.config.ConfigManager;
import top.hendrixshen.magiclib.config.annotation.Config;
import top.hendrixshen.magiclib.config.annotation.Hotkey;
import top.hendrixshen.magiclib.config.annotation.Numeric;
import top.hendrixshen.magiclib.dependency.Predicates;

import java.util.ArrayList;

public class Configs {
    // Generic configs
    @Config(category = ConfigCategory.GENERIC)
    public static boolean enable = false;

    @Config(category = ConfigCategory.GENERIC)
    public static ArrayList<String> blockGifts = Lists.newArrayList("辣条");

    @Config(category = ConfigCategory.GENERIC)
    public static ArrayList<String> blockWords = Lists.newArrayList("Fuck");

    @Hotkey(hotkey = "B,C")
    @Config(category = ConfigCategory.GENERIC)
    public static ConfigHotkey openConfigGui;

    @Numeric(minValue = 0, maxValue = Integer.MAX_VALUE)
    @Config(category = ConfigCategory.GENERIC)
    public static int roomId;

    // Chat toggle configs
    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static boolean chatDanmakuEnable = true;

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static boolean chatGiftEnable = true;

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static boolean chatGuardBuyEnable = true;

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static boolean chatGuardBuyLevel1Enable = true;

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static boolean chatGuardBuyLevel2Enable = true;

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static boolean chatGuardBuyLevel3Enable = true;

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static boolean chatSuperChatEnable = true;

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static boolean chatWelcomeEnable = true;

    @Config(category = ConfigCategory.TOGGLE_CHAT, predicate = Predicates.DevOptionPredicate.class)
    public static boolean chatWelcomeGuardEnable = false;

    @Config(category = ConfigCategory.TOGGLE_CHAT, predicate = Predicates.DevOptionPredicate.class)
    public static boolean chatWelcomeGuardLv1Enable = false;

    @Config(category = ConfigCategory.TOGGLE_CHAT, predicate = Predicates.DevOptionPredicate.class)
    public static boolean chatWelcomeGuardLv2Enable = false;

    @Config(category = ConfigCategory.TOGGLE_CHAT, predicate = Predicates.DevOptionPredicate.class)
    public static boolean chatWelcomeGuardLv3Enable = false;

    @Config(category = ConfigCategory.TOGGLE_CHAT)
    public static boolean chatWelcomeNormalEnable = true;

    // Chat format configs
    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static String formatChatAdmin = "&4[房] &d<%{user}> &f%{danmaku}";

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static String formatChatBuyGuardLv1 = "&4%{user} 开通了主播的总督";

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static String formatChatBuyGuardLv2 = "&6%{user} 开通了主播的提督";

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static String formatChatBuyGuardLv3 = "&3%{user} 开通了主播的舰长";

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static String formatChatDanmakuGuard = "&6[舰] &2<%{user}> &f%{danmaku}";

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static String formatChatDanmakuNormal = "&7[普] &b<%{user}> &f%{danmaku}";

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static String formatChatGifts = "&7%{user} %{action} [%{gift}] × %{num}";

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static String formatChatSuperChat = "&4%{user} > %{msg} [¥%{price}]";

    @Config(category = ConfigCategory.FORMAT_CHAT, predicate = Predicates.DevOptionPredicate.class)
    public static String formatChatWelcomeGuardLv1 = "&4欢迎总督 %{user} 进入直播间";

    @Config(category = ConfigCategory.FORMAT_CHAT, predicate = Predicates.DevOptionPredicate.class)
    public static String formatChatWelcomeGuardLv2 = "&6欢迎提督 %{user} 进入直播间";

    @Config(category = ConfigCategory.FORMAT_CHAT, predicate = Predicates.DevOptionPredicate.class)
    public static String formatChatWelcomeGuardLv3 = "&3欢迎舰长 %{user} 进入直播间";

    @Config(category = ConfigCategory.FORMAT_CHAT)
    public static String formatChatWelcomeNormal = "&7欢迎 %{user} 进入直播间";

    // Debug configs
    @Config(category = ConfigCategory.DEBUG)
    public static boolean debugMode = false;

    @Config(category = ConfigCategory.DEBUG, predicate = BilibiliDanmakuPredicates.DebugOptionPredicate.class)
    public static String initApiUrl = "https://api.live.bilibili.com/room/v1/Room/room_init";

    @Config(category = ConfigCategory.DEBUG, predicate = BilibiliDanmakuPredicates.DebugOptionPredicate.class)
    public static String websocketUri = "wss://broadcastlv.chat.bilibili.com:443/sub";

    public static void initCallbacks(ConfigManager cm) {
        // Generic config callback
        cm.setValueChangeCallback("enable", CallBacks::enableCallback);
        openConfigGui.getKeybind().setCallback(CallBacks::openConfigGuiCallback);
        cm.setValueChangeCallback("roomId", CallBacks::roomIdCallback);

        // Debug config callback
        cm.setValueChangeCallback("debugMode", CallBacks::debugModeCallBack);

        CallBacks.debugModeCallBack(null);
    }
}
