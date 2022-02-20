//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf.network;

import com.kisman.cc.hypixel.skyblock.colf.*;
import java.util.*;
import java.net.*;
import java.io.*;
import com.google.gson.*;

public class QueryServerCommands
{
    private static Gson gson;
    
    public static String QueryCommands() {
        final String queryResult = GetRequest(MainColf.commandUri);
        if (queryResult != null) {
            final CommandInfo[] commands = (CommandInfo[])QueryServerCommands.gson.fromJson(queryResult, (Class)CommandInfo[].class);
            System.out.println(">>> " + Arrays.toString(commands));
            final StringBuilder sb = new StringBuilder();
            if (commands.length > 0) {
                for (final CommandInfo cm : commands) {
                    sb.append(cm + "\n");
                }
            }
            return sb.toString().trim();
        }
        return "§4ERROR: Could not connect to command server!";
    }
    
    private static String GetRequest(final String uri) {
        try {
            System.out.println("Get request");
            final URL url = new URL(uri);
            final HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("User-Agent", "CoflMod");
            con.setDoInput(true);
            System.out.println("InputStream");
            final InputStream in = new BufferedInputStream(con.getInputStream());
            final ByteArrayOutputStream result = new ByteArrayOutputStream();
            final byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            final String resString = result.toString("UTF-8");
            System.out.println("Result= " + resString);
            return resString;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    static {
        QueryServerCommands.gson = new GsonBuilder().create();
    }
    
    private static class CommandInfo
    {
        public String subCommand;
        public String description;
        
        public CommandInfo() {
        }
        
        public CommandInfo(final String subCommand, final String description) {
            this.subCommand = subCommand;
            this.description = description;
        }
        
        @Override
        public String toString() {
            return this.subCommand + ": " + this.description;
        }
    }
}
