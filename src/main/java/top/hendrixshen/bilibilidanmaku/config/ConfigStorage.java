package top.hendrixshen.bilibilidanmaku.config;

import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.dy.masa.malilib.config.ConfigUtils;
import fi.dy.masa.malilib.config.IConfigBase;
import fi.dy.masa.malilib.config.IConfigHandler;
import fi.dy.masa.malilib.config.options.ConfigBooleanHotkeyed;
import fi.dy.masa.malilib.util.FileUtils;
import fi.dy.masa.malilib.util.JsonUtils;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmaku;
import top.hendrixshen.bilibilidanmaku.BilibiliDanmakuReference;

import java.io.File;
import java.util.List;

public class ConfigStorage implements IConfigHandler {
    private static final String CONFIG_FILE_NAME = BilibiliDanmakuReference.getModId() + ".json";

    public String getToggleLabel(String category) {
        return String.format("%s_toggle", category);
    }

    public String getHotkeyLabel(String category) {
        return String.format("%s_hotkey", category);
    }

    public List<ConfigBooleanHotkeyed> getCoveredList(List<IConfigBase> category) {
        List<ConfigBooleanHotkeyed> list = Lists.newArrayList();
        for (IConfigBase iConfigBase : category) {
            if (iConfigBase instanceof ConfigBooleanHotkeyed) {
                list.add((ConfigBooleanHotkeyed) iConfigBase);
            }
        }
        return list;
    }

    public void loadFromFile() {
        File configFile = new File(FileUtils.getConfigDirectory(), CONFIG_FILE_NAME);

        if (configFile.exists() && configFile.isFile() && configFile.canRead()) {
            JsonElement element = JsonUtils.parseJsonFile(configFile);

            if (element != null && element.isJsonObject()) {
                JsonObject root = element.getAsJsonObject();
                ConfigUtils.readConfigBase(root, ConfigCategory.GENERIC, BilibiliDanmaku.cm.getConfigsByCategory(ConfigCategory.GENERIC));
                ConfigUtils.readConfigBase(root, ConfigCategory.TOGGLE_CHAT, BilibiliDanmaku.cm.getConfigsByCategory(ConfigCategory.TOGGLE_CHAT));
                ConfigUtils.readConfigBase(root, ConfigCategory.FORMAT_CHAT, BilibiliDanmaku.cm.getConfigsByCategory(ConfigCategory.FORMAT_CHAT));
                ConfigUtils.readConfigBase(root, ConfigCategory.DEBUG, BilibiliDanmaku.cm.getConfigsByCategory(ConfigCategory.FORMAT_CHAT));
            }
        }
    }

    public void saveToFile() {
        File dir = FileUtils.getConfigDirectory();

        if ((dir.exists() && dir.isDirectory()) || dir.mkdirs()) {
            JsonObject root = new JsonObject();
            ConfigUtils.writeConfigBase(root, ConfigCategory.GENERIC, BilibiliDanmaku.cm.getConfigsByCategory(ConfigCategory.GENERIC));
            ConfigUtils.writeConfigBase(root, ConfigCategory.TOGGLE_CHAT, BilibiliDanmaku.cm.getConfigsByCategory(ConfigCategory.TOGGLE_CHAT));
            ConfigUtils.writeConfigBase(root, ConfigCategory.FORMAT_CHAT, BilibiliDanmaku.cm.getConfigsByCategory(ConfigCategory.FORMAT_CHAT));
            ConfigUtils.writeConfigBase(root, ConfigCategory.DEBUG, BilibiliDanmaku.cm.getConfigsByCategory(ConfigCategory.DEBUG));
            JsonUtils.writeJsonToFile(root, new File(dir, CONFIG_FILE_NAME));
        }
    }

    @Override
    public void load() {
        loadFromFile();
    }

    @Override
    public void save() {
        saveToFile();
    }
}
