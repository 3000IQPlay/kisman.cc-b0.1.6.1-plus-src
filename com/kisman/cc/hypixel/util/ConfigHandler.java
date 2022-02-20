//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.util;

import java.io.*;
import net.minecraftforge.common.config.*;
import com.kisman.cc.*;

public class ConfigHandler
{
    public static Configuration config;
    private static String file;
    public static int threads;
    
    public static void init() {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
    }
    
    public static void removeConfig(final String category) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            if (ConfigHandler.config.hasCategory(category)) {
                ConfigHandler.config.removeCategory(new ConfigCategory(category));
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
    }
    
    public static void removeConfig(final String category, final String key) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            if (ConfigHandler.config.getCategory(category).containsKey(key)) {
                ConfigHandler.config.getCategory(category).remove((Object)key);
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
    }
    
    public static int getInt(final String category, final String key) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            if (ConfigHandler.config.getCategory(category).containsKey(key)) {
                return ConfigHandler.config.get(category, key, 0).getInt();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
        return 0;
    }
    
    public static double getDouble(final String category, final String key) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            if (ConfigHandler.config.getCategory(category).containsKey(key)) {
                return ConfigHandler.config.get(category, key, 0.0).getDouble();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
        return 0.0;
    }
    
    public static float getFloat(final String category, final String key) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            if (ConfigHandler.config.getCategory(category).containsKey(key)) {
                return (float)ConfigHandler.config.get(category, key, 0.0).getDouble();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
        return 0.0f;
    }
    
    public static String getString(final String category, final String key) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            if (ConfigHandler.config.getCategory(category).containsKey(key)) {
                return ConfigHandler.config.get(category, key, "").getString();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
        return "";
    }
    
    public static short getShort(final String category, final String key) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            if (ConfigHandler.config.getCategory(category).containsKey(key)) {
                return (short)ConfigHandler.config.get(category, key, 0).getInt();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
        return 0;
    }
    
    public static byte getByte(final String category, final String key) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            if (ConfigHandler.config.getCategory(category).containsKey(key)) {
                return (byte)ConfigHandler.config.get(category, key, 0).getInt();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
        return 0;
    }
    
    public static boolean getBoolean(final String category, final String key) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            if (ConfigHandler.config.getCategory(category).containsKey(key)) {
                return ConfigHandler.config.get(category, key, false).getBoolean();
            }
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
        return false;
    }
    
    public static void writeConfig(final String category, final String key, final String value) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            ConfigHandler.config.get(category, key, value).getString();
            ConfigHandler.config.getCategory(category).get(key).set(value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
            Kisman.LOGGER.error(e.getMessage(), (Throwable)e);
        }
        finally {
            ConfigHandler.config.save();
        }
    }
    
    public static void writeConfig(final String category, final String key, final int value) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            ConfigHandler.config.get(category, key, value).getInt();
            ConfigHandler.config.getCategory(category).get(key).set(value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
    }
    
    public static void writeConfig(final String category, final String key, final boolean value) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            ConfigHandler.config.get(category, key, value).getBoolean();
            ConfigHandler.config.getCategory(category).get(key).set(value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
    }
    
    public static void writeConfig(final String category, final String key, final double value) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            ConfigHandler.config.get(category, key, value).getDouble();
            ConfigHandler.config.getCategory(category).get(key).set(value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
    }
    
    public static void writeConfig(final String category, final String key, final short value) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            ConfigHandler.config.get(category, key, (int)value).getInt();
            ConfigHandler.config.getCategory(category).get(key).set((int)value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
    }
    
    public static void writeConfig(final String category, final String key, final byte value) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            ConfigHandler.config.get(category, key, (int)value).getInt();
            ConfigHandler.config.getCategory(category).get(key).set((int)value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
    }
    
    public static void writeConfig(final String category, final String key, final float value) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            ConfigHandler.config.get(category, key, (double)value).getDouble();
            ConfigHandler.config.getCategory(category).get(key).set((double)value);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
    }
    
    public static boolean hasCategory(final String category) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            return ConfigHandler.config.hasCategory(category);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
        return false;
    }
    
    public static boolean hasKey(final String category, final String key) {
        ConfigHandler.config = new Configuration(new File(ConfigHandler.file));
        try {
            ConfigHandler.config.load();
            return ConfigHandler.config.hasCategory(category) && ConfigHandler.config.getCategory(category).containsKey(key);
        }
        catch (Exception e) {
            System.out.println("Cannot load configuration file!");
        }
        finally {
            ConfigHandler.config.save();
        }
        return false;
    }
    
    public static void setFile(final String filename) {
        ConfigHandler.file = "config/" + filename;
    }
    
    public static String getFile() {
        return ConfigHandler.file;
    }
    
    static {
        ConfigHandler.file = "config/nec.cfg";
        ConfigHandler.threads = 1;
    }
}
