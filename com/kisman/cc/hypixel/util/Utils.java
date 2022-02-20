//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.util;

import com.google.gson.*;
import java.io.*;
import com.kisman.cc.*;
import java.net.*;
import java.text.*;

public class Utils
{
    static JsonElement getJson(final String jsonUrl) {
        try {
            final URL url = new URL(jsonUrl);
            final URLConnection conn = url.openConnection();
            return new JsonParser().parse((Reader)new InputStreamReader(conn.getInputStream()));
        }
        catch (Exception e) {
            Kisman.LOGGER.error(e.getMessage(), (Throwable)e);
            return null;
        }
    }
    
    private static String formatValue(final long amount, final long div, final char suffix) {
        return new DecimalFormat(".##").format(amount / (double)div) + suffix;
    }
    
    public static String formatValue(final long amount) {
        if (amount >= 1000000000000000L) {
            return formatValue(amount, 1000000000000000L, 'q');
        }
        if (amount >= 1000000000000L) {
            return formatValue(amount, 1000000000000L, 't');
        }
        if (amount >= 1000000000L) {
            return formatValue(amount, 1000000000L, 'b');
        }
        if (amount >= 1000000L) {
            return formatValue(amount, 1000000L, 'm');
        }
        if (amount >= 100000L) {
            return formatValue(amount, 1000L, 'k');
        }
        return NumberFormat.getInstance().format(amount);
    }
}
