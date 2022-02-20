//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf.minecraft_integration;

import java.net.*;
import net.minecraft.client.*;
import com.kisman.cc.hypixel.skyblock.colf.network.*;
import java.util.*;
import java.io.*;

public class PlayerDataProvider
{
    public static String getActivePlayerUUID() {
        try {
            final URL url = new URL("https://api.mojang.com/profiles/minecraft");
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            con.setDoInput(true);
            con.setDoOutput(true);
            final OutputStream os = con.getOutputStream();
            final byte[] bytes = ("[\"" + Minecraft.getMinecraft().session.getUsername() + "\"]").getBytes("UTF-8");
            os.write(bytes);
            os.close();
            final InputStream in = new BufferedInputStream(con.getInputStream());
            final ByteArrayOutputStream result = new ByteArrayOutputStream();
            final byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            final String resString = result.toString("UTF-8");
            System.out.println("Result= " + resString);
            final UUIDHelper[] helpers = (UUIDHelper[])WSClient.gson.fromJson(resString, (Class)UUIDHelper[].class);
            if (helpers.length == 1) {
                return helpers[0].id;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return UUID.randomUUID().toString();
    }
    
    private static class UUIDHelper
    {
        public String id;
        public String name;
    }
}
