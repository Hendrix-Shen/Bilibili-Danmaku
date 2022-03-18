package top.hendrixshen.bilibilidanmaku.util.bilibili;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import top.hendrixshen.bilibilidanmaku.config.Configs;

import java.lang.reflect.Type;

public class JsonDeserializer implements com.google.gson.JsonDeserializer<String> {
    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonObject()) {
            JsonObject data = json.getAsJsonObject();
            String type = data.get("cmd").getAsString();
            switch (type) {
                case "DANMU_MSG":
                    if (Configs.chatDanmakuEnable.getBooleanValue()) {
                        return MessageHandler.handleDanmaku(data);
                    }
                    break;
                case "SEND_GIFT":
                    if (Configs.chatGiftEnable.getBooleanValue()) {
                        return MessageHandler.handleGift(data);
                    }
                    break;
                case "COMBO_SEND":
                    if (Configs.chatGiftEnable.getBooleanValue()) {
                        return MessageHandler.handleComboGift(data);
                    }
                    break;
                case "INTERACT_WORD":
                case "WELCOME": // Not tested, may no longer work.
                    if (Configs.chatWelcomeEnable.getBooleanValue() && Configs.chatWelcomeNormalEnable.getBooleanValue()) {
                        return MessageHandler.handleWelcomeNormal(data);
                    }
                    break;
                case "WELCOME_GUARD": // Not tested, may no longer work.
                    if (Configs.chatWelcomeEnable.getBooleanValue() && Configs.chatWelcomeGuardEnable.getBooleanValue()) {
                        return MessageHandler.handleWelcomeGuard(data);
                    }
                    break;
                case "GUARD_BUY": // Not tested!
                    if (Configs.chatGuardBuyEnable.getBooleanValue()) {
                        return MessageHandler.handleBuyGuard(data);
                    }
                    break;
                case "SUPER_CHAT_MESSAGE": // Not tested!
                    if (Configs.chatSuperChatEnable.getBooleanValue()) {
                        return MessageHandler.handleSuperChat(data);
                    }
                    break;
                default:
            }
        }
        return null;
    }
}
