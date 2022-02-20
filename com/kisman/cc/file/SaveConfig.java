//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.file;

import com.kisman.cc.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import com.kisman.cc.module.*;
import java.util.*;
import java.nio.charset.*;
import java.io.*;
import com.kisman.cc.settings.*;
import org.lwjgl.input.*;
import com.google.gson.*;
import com.kisman.cc.hud.hudmodule.*;

public class SaveConfig
{
    public static void init() {
        try {
            Kisman.initDirs();
            saveModules();
            saveEnabledModules();
            saveVisibledModules();
            saveEnabledHudModules();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Kisman.LOGGER.info("Saved Config!");
    }
    
    private static void registerFiles(final String location, final String name) throws IOException {
        if (Files.exists(Paths.get("kisman.cc/" + location + name + ".json", new String[0]), new LinkOption[0])) {
            new File("kisman.cc/" + location + name + ".json").delete();
        }
        else {
            Files.createFile(Paths.get("kisman.cc/" + location + name + ".json", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
        }
    }
    
    private static void saveModules() throws IOException {
        for (final Module module : Kisman.instance.moduleManager.getModuleList()) {
            try {
                saveModuleDirect(module, Kisman.instance.settingsManager.getSettingsByMod(module) != null && !Kisman.instance.settingsManager.getSettingsByMod(module).isEmpty());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    private static void saveModuleDirect(final Module module, final boolean settings) throws IOException {
        registerFiles("Modules/", module.getName());
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final OutputStreamWriter fileOutputStreamWriter = new OutputStreamWriter(new FileOutputStream("kisman.cc/Modules/" + module.getName() + ".json"), StandardCharsets.UTF_8);
        final JsonObject moduleObject = new JsonObject();
        final JsonObject settingObject = new JsonObject();
        moduleObject.add("Module", (JsonElement)new JsonPrimitive(module.getName()));
        if (settings) {
            if (!Kisman.instance.settingsManager.getSettingsByMod(module).isEmpty()) {
                for (final Setting setting : Kisman.instance.settingsManager.getSettingsByMod(module)) {
                    if (setting != null) {
                        if (setting.isCheck()) {
                            settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive(Boolean.valueOf(setting.getValBoolean())));
                        }
                        if (setting.isCombo()) {
                            settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive(setting.getValString()));
                        }
                        if (!setting.isSlider()) {
                            continue;
                        }
                        settingObject.add(setting.getName(), (JsonElement)new JsonPrimitive((Number)setting.getValDouble()));
                    }
                }
            }
            settingObject.add("key", (JsonElement)new JsonPrimitive(Keyboard.getKeyName(module.getKey())));
        }
        moduleObject.add("Settings", (JsonElement)settingObject);
        final String jsonString = gson.toJson(new JsonParser().parse(moduleObject.toString()));
        fileOutputStreamWriter.write(jsonString);
        fileOutputStreamWriter.close();
    }
    
    private static void saveEnabledModules() throws IOException {
        registerFiles("Main/", "Toggle");
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final OutputStreamWriter fileOutputStreamWriter = new OutputStreamWriter(new FileOutputStream("kisman.cc/Main/Toggle.json"), StandardCharsets.UTF_8);
        final JsonObject moduleObject = new JsonObject();
        final JsonObject enabledObject = new JsonObject();
        for (final Module mod : Kisman.instance.moduleManager.modules) {
            enabledObject.add(mod.getName(), (JsonElement)new JsonPrimitive(Boolean.valueOf(mod.isToggled())));
        }
        moduleObject.add("Modules", (JsonElement)enabledObject);
        final String jsonString = gson.toJson(new JsonParser().parse(moduleObject.toString()));
        fileOutputStreamWriter.write(jsonString);
        fileOutputStreamWriter.close();
    }
    
    private static void saveVisibledModules() throws IOException {
        registerFiles("Main/", "Visible");
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final OutputStreamWriter fileOutputStreamWriter = new OutputStreamWriter(new FileOutputStream("kisman.cc/Main/Visible.json"), StandardCharsets.UTF_8);
        final JsonObject moduleObject = new JsonObject();
        final JsonObject enabledObject = new JsonObject();
        for (final Module mod : Kisman.instance.moduleManager.modules) {
            enabledObject.add(mod.getName(), (JsonElement)new JsonPrimitive(Boolean.valueOf(mod.visible)));
        }
        moduleObject.add("Modules", (JsonElement)enabledObject);
        final String jsonString = gson.toJson(new JsonParser().parse(moduleObject.toString()));
        fileOutputStreamWriter.write(jsonString);
        fileOutputStreamWriter.close();
    }
    
    private static void saveEnabledHudModules() throws IOException {
        registerFiles("Main/", "HudToggle");
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final OutputStreamWriter fileOutputStreamWriter = new OutputStreamWriter(new FileOutputStream("kisman.cc/Main/HudToggle.json"), StandardCharsets.UTF_8);
        final JsonObject moduleObject = new JsonObject();
        final JsonObject enabledObject = new JsonObject();
        for (final HudModule mod : Kisman.instance.hudModuleManager.modules) {
            enabledObject.add(mod.getName(), (JsonElement)new JsonPrimitive(Boolean.valueOf(mod.isToggled())));
        }
        moduleObject.add("Modules", (JsonElement)enabledObject);
        final String jsonString = gson.toJson(new JsonParser().parse(moduleObject.toString()));
        fileOutputStreamWriter.write(jsonString);
        fileOutputStreamWriter.close();
    }
}
