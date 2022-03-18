package top.hendrixshen.bilibilidanmaku.util.bilibili;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import top.hendrixshen.bilibilidanmaku.config.Configs;

import java.util.regex.Matcher;

public class MessageHandler {
    public static String handleDanmaku(JsonObject data) {
        JsonArray info = data.getAsJsonArray("info");
        JsonArray user = info.get(2).getAsJsonArray();

        String userName = user.get(1).getAsString();
        String danmaku = info.get(1).getAsString();
        boolean isAdmin = user.get(2).getAsInt() == 1;
        boolean isGuard = StringUtils.isNotBlank(user.get(7).getAsString());

        if (Configs.blockWords.getStrings().stream().noneMatch((danmaku::contains))) {
            if (isAdmin) {
                return String.format(Configs.formatChatAdmin.getStringValue()
                        .replaceAll("&([0-9a-fk-or])", "§$1")
                        .replaceAll("%\\{user}", Matcher.quoteReplacement("%1$s"))
                        .replaceAll("%\\{danmaku}", Matcher.quoteReplacement("%2$s")), userName, danmaku);
            } else if (isGuard) {
                return String.format(Configs.formatChatDanmakuGuard.getStringValue()
                        .replaceAll("&([0-9a-fk-or])", "§$1")
                        .replaceAll("%\\{user}", Matcher.quoteReplacement("%1$s"))
                        .replaceAll("%\\{danmaku}", Matcher.quoteReplacement("%2$s")), userName, danmaku);
            } else {
                return String.format(Configs.formatChatDanmakuNormal.getStringValue()
                        .replaceAll("&([0-9a-fk-or])", "§$1")
                        .replaceAll("%\\{user}", Matcher.quoteReplacement("%1$s"))
                        .replaceAll("%\\{danmaku}", Matcher.quoteReplacement("%2$s")), userName, danmaku);
            }
        }
        return null;
    }

    public static String handleGift(JsonObject data) {
        JsonObject jsonObject = data.getAsJsonObject("data");
        String userName = jsonObject.get("uname").getAsString();
        String action = jsonObject.get("action").getAsString();
        String giftName = jsonObject.get("giftName").getAsString();
        int num = jsonObject.get("num").getAsInt();

        if (Configs.blockGifts.getStrings().stream().noneMatch(giftName::contains)) {
            return String.format(Configs.formatChatGifts.getStringValue()
                    .replaceAll("&([0-9a-fk-or])", "§$1")
                    .replaceAll("%\\{user}", Matcher.quoteReplacement("%1$s"))
                    .replaceAll("%\\{action}", Matcher.quoteReplacement("%2$s"))
                    .replaceAll("%\\{gift}", Matcher.quoteReplacement("%3$s"))
                    .replaceAll("%\\{num}", Matcher.quoteReplacement("%4$s")), userName, action, giftName, num);
        }
        return null;
    }

    public static String handleWelcomeNormal(JsonObject data) {
        JsonObject jsonObject = data.getAsJsonObject("data");
        String userName = jsonObject.get("uname").getAsString();

        return String.format(Configs.formatChatWelcomeNormal.getStringValue()
                .replaceAll("&([0-9a-fk-or])", "§$1")
                .replaceAll("%\\{user}", Matcher.quoteReplacement("%1$s")), userName);
    }

    public static String handleWelcomeGuard(JsonObject data) {
        JsonObject jsonObject = data.getAsJsonObject("data");
        String userName = jsonObject.get("username").getAsString();
        int level = jsonObject.get("guard_level").getAsInt();
        switch (level) {
            case 1:
                if (Configs.chatWelcomeGuardLv1Enable.getBooleanValue()) {
                    return String.format(Configs.formatChatWelcomeGuardLv1.getStringValue()
                            .replaceAll("&([0-9a-fk-or])", "§$1")
                            .replaceAll("%\\{user}", Matcher.quoteReplacement("%1$s")), userName);
                }
            case 2:
                if (Configs.chatWelcomeGuardLv2Enable.getBooleanValue()) {
                    return String.format(Configs.formatChatWelcomeGuardLv2.getStringValue()
                            .replaceAll("&([0-9a-fk-or])", "§$1")
                            .replaceAll("%\\{user}", Matcher.quoteReplacement("%1$s")), userName);
                }
            case 3:
                if (Configs.chatWelcomeGuardLv3Enable.getBooleanValue()) {
                    return String.format(Configs.formatChatWelcomeGuardLv3.getStringValue()
                            .replaceAll("&([0-9a-fk-or])", "§$1")
                            .replaceAll("%\\{user}", Matcher.quoteReplacement("%1$s")), userName);
                }
            default:
                return null;
        }
    }

    public static String handleComboGift(JsonObject data) {
        JsonObject jsonObject = data.getAsJsonObject("data");
        String userName = jsonObject.get("uname").getAsString();
        String action = jsonObject.get("action").getAsString();
        String giftName = jsonObject.get("gift_name").getAsString();
        int num = jsonObject.get("total_num").getAsInt();

        if (Configs.blockGifts.getStrings().stream().noneMatch(giftName::contains)) {
            return String.format(Configs.formatChatGifts.getStringValue()
                    .replaceAll("&([0-9a-fk-or])", "§$1")
                    .replaceAll("%\\{user}", Matcher.quoteReplacement("%1$s"))
                    .replaceAll("%\\{action}", Matcher.quoteReplacement("%2$s"))
                    .replaceAll("%\\{gift}", Matcher.quoteReplacement("%3$s"))
                    .replaceAll("%\\{num}", Matcher.quoteReplacement("%4$s")), userName, action, giftName, num);
        }

        return null;
    }

    public static String handleBuyGuard(JsonObject data) {
        JsonObject jsonObject = data.getAsJsonObject("data");
        String userName = jsonObject.get("username").getAsString();
        int level = jsonObject.get("guard_level").getAsInt();
        switch (level) {
            case 1:
                if (Configs.chatGuardBuyLevel1Enable.getBooleanValue()) {
                    return String.format(Configs.formatChatBuyGuardLv1.getStringValue()
                            .replaceAll("&([0-9a-fk-or])", "§$1")
                            .replaceAll("%\\{user}", Matcher.quoteReplacement("%1$s")), userName);
                }
            case 2:
                if (Configs.chatGuardBuyLevel2Enable.getBooleanValue()) {
                    return String.format(Configs.formatChatBuyGuardLv2.getStringValue()
                            .replaceAll("&([0-9a-fk-or])", "§$1")
                            .replaceAll("%\\{user}", Matcher.quoteReplacement("%1$s")), userName);
                }
            case 3:
                if (Configs.chatGuardBuyLevel3Enable.getBooleanValue()) {
                    return String.format(Configs.formatChatBuyGuardLv3.getStringValue()
                            .replaceAll("&([0-9a-fk-or])", "§$1")
                            .replaceAll("%\\{user}", Matcher.quoteReplacement("%1$s")), userName);
                }
            default:
                return null;
        }

    }

    public static String handleSuperChat(JsonObject data) {
        JsonObject jsonObject = data.getAsJsonObject("data");
        String userName = jsonObject.getAsJsonObject("user_info").get("uname").getAsString();
        String message = jsonObject.get("message").getAsString();
        int price = jsonObject.get("price").getAsInt();

        return String.format(Configs.formatChatSuperChat.getStringValue()
                .replaceAll("&([0-9a-fk-or])", "§$1")
                .replaceAll("%\\{user}", Matcher.quoteReplacement("%1$s"))
                .replaceAll("%\\{msg}", Matcher.quoteReplacement("%2$s"))
                .replaceAll("%\\{price}", Matcher.quoteReplacement("%3$s")), userName, message, price);
    }
}
