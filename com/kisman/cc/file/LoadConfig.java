//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.file;

import com.kisman.cc.*;
import com.kisman.cc.module.*;
import java.util.*;
import java.nio.file.*;
import com.kisman.cc.settings.*;
import org.lwjgl.input.*;
import java.io.*;
import com.google.gson.*;
import com.kisman.cc.hud.hudmodule.*;

public class LoadConfig
{
    public static void init() {
        try {
            Kisman.initDirs();
            loadModules();
            loadEnabledModules();
            loadVisibledModules();
            loadEnabledHudModules();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void loadModules() throws IOException {
        for (final Module module : Kisman.instance.moduleManager.modules) {
            try {
                final boolean settings = Kisman.instance.settingsManager.getSettingsByMod(module) != null && !Kisman.instance.settingsManager.getSettingsByMod(module).isEmpty();
                loadModuleDirect("kisman.cc/Modules/", module, settings);
            }
            catch (IOException e) {
                System.out.println(module.getName());
                e.printStackTrace();
            }
        }
    }
    
    private static void loadModuleDirect(final String moduleLocation, final Module module, final boolean settings) throws IOException {
        if (!Files.exists(Paths.get(moduleLocation + module.getName() + ".json", new String[0]), new LinkOption[0])) {
            return;
        }
        final InputStream inputStream = Files.newInputStream(Paths.get(moduleLocation + module.getName() + ".json", new String[0]), new OpenOption[0]);
        JsonObject moduleObject;
        try {
            moduleObject = new JsonParser().parse((Reader)new InputStreamReader(inputStream)).getAsJsonObject();
        }
        catch (IllegalStateException e) {
            return;
        }
        if (moduleObject.get("Module") == null) {
            return;
        }
        final JsonObject settingObject = moduleObject.get("Settings").getAsJsonObject();
        final JsonElement keyObject = settingObject.get("key");
        if (settings) {
            for (final Setting setting : Kisman.instance.settingsManager.getSettingsByMod(module)) {
                final JsonElement dataObject = settingObject.get(setting.getName());
                final JsonElement[] colour = { settingObject.get(setting.getName() + "H"), settingObject.get(setting.getName() + "S"), settingObject.get(setting.getName() + "B"), settingObject.get(setting.getName() + "A"), settingObject.get(setting.getName() + "RainBow"), settingObject.get(setting.getName() + "Syns") };
                try {
                    if (dataObject == null || !dataObject.isJsonPrimitive()) {
                        continue;
                    }
                    if (setting.isCheck()) {
                        setting.setValBoolean(dataObject.getAsBoolean());
                    }
                    if (setting.isCombo()) {
                        setting.setValString(dataObject.getAsString());
                    }
                    if (setting.isSlider()) {
                        setting.setValDouble(dataObject.getAsDouble());
                    }
                    if (!setting.isColorPicker()) {
                        continue;
                    }
                    setting.getColorPicker().setColor(0, colour[0].getAsFloat());
                    setting.getColorPicker().setColor(1, colour[1].getAsFloat());
                    setting.getColorPicker().setColor(2, colour[2].getAsFloat());
                    setting.getColorPicker().setColor(3, colour[3].getAsFloat());
                    setting.setRainbow(colour[4].getAsBoolean());
                    setting.setSyns(colour[5].getAsBoolean());
                }
                catch (NumberFormatException e2) {
                    System.out.println(setting.getName() + " " + module.getName());
                    System.out.println(dataObject);
                }
            }
        }
        if (keyObject != null && keyObject.isJsonPrimitive()) {
            module.setKey(Keyboard.getKeyIndex(keyObject.getAsString()));
        }
        inputStream.close();
    }
    
    private static void loadEnabledModules() throws IOException {
        final String enabledLocation = "kisman.cc/Main/";
        if (!Files.exists(Paths.get(enabledLocation + "Toggle.json", new String[0]), new LinkOption[0])) {
            return;
        }
        final InputStream inputStream = Files.newInputStream(Paths.get(enabledLocation + "Toggle.json", new String[0]), new OpenOption[0]);
        final JsonObject moduleObject = new JsonParser().parse((Reader)new InputStreamReader(inputStream)).getAsJsonObject();
        if (moduleObject.get("Modules") == null) {
            return;
        }
        final JsonObject settingObject = moduleObject.get("Modules").getAsJsonObject();
        for (final Module module : Kisman.instance.moduleManager.modules) {
            final JsonElement dataObject = settingObject.get(module.getName());
            if (dataObject != null && dataObject.isJsonPrimitive()) {
                try {
                    module.setToggled(dataObject.getAsBoolean());
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        inputStream.close();
    }
    
    private static void loadVisibledModules() throws IOException {
        final String enabledLocation = "kisman.cc/Main/";
        if (!Files.exists(Paths.get(enabledLocation + "Visible.json", new String[0]), new LinkOption[0])) {
            return;
        }
        final InputStream inputStream = Files.newInputStream(Paths.get(enabledLocation + "Visible.json", new String[0]), new OpenOption[0]);
        final JsonObject moduleObject = new JsonParser().parse((Reader)new InputStreamReader(inputStream)).getAsJsonObject();
        if (moduleObject.get("Modules") == null) {
            return;
        }
        final JsonObject settingObject = moduleObject.get("Modules").getAsJsonObject();
        for (final Module module : Kisman.instance.moduleManager.modules) {
            final JsonElement dataObject = settingObject.get(module.getName());
            if (dataObject != null && dataObject.isJsonPrimitive()) {
                try {
                    module.visible = dataObject.getAsBoolean();
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        inputStream.close();
    }
    
    private static void loadEnabledHudModules() throws IOException {
        final String enabledLocation = "kisman.cc/Main/";
        if (!Files.exists(Paths.get(enabledLocation + "HudToggle.json", new String[0]), new LinkOption[0])) {
            return;
        }
        final InputStream inputStream = Files.newInputStream(Paths.get(enabledLocation + "HudToggle.json", new String[0]), new OpenOption[0]);
        final JsonObject moduleObject = new JsonParser().parse((Reader)new InputStreamReader(inputStream)).getAsJsonObject();
        if (moduleObject.get("Modules") == null) {
            return;
        }
        final JsonObject settingObject = moduleObject.get("Modules").getAsJsonObject();
        for (final HudModule module : Kisman.instance.hudModuleManager.modules) {
            final JsonElement dataObject = settingObject.get(module.getName());
            if (dataObject != null && dataObject.isJsonPrimitive()) {
                try {
                    module.setToggled(dataObject.getAsBoolean());
                }
                catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
        inputStream.close();
    }
}
