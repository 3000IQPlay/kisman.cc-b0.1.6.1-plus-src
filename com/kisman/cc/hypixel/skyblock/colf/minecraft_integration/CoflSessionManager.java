//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf.minecraft_integration;

import net.minecraftforge.fml.common.*;
import java.nio.file.*;
import java.time.temporal.*;
import java.time.*;
import java.time.chrono.*;
import java.util.*;
import java.util.stream.*;
import java.io.*;
import com.google.gson.*;
import com.google.gson.stream.*;
import java.lang.reflect.*;

public class CoflSessionManager
{
    public static Gson gson;
    
    public static void UpdateCoflSessions() throws IOException {
        final Map<String, CoflSession> sessions = GetCoflSessions();
        for (final String username : sessions.keySet()) {
            if (!isValidSession(sessions.get(username))) {
                DeleteCoflSession(username);
            }
        }
    }
    
    public static Path GetTempFileFolder() {
        final Path dataPath = Paths.get(Loader.instance().getConfigDir().getPath(), "CoflSky", "sessions");
        dataPath.toFile().mkdirs();
        return dataPath;
    }
    
    public static Map<String, CoflSession> GetCoflSessions() throws IOException {
        final File[] sessions = GetTempFileFolder().toFile().listFiles();
        final Map<String, CoflSession> map = new HashMap<String, CoflSession>();
        for (int i = 0; i < sessions.length; ++i) {
            map.put(sessions[i].getName(), GetCoflSession(sessions[i].getName()));
        }
        return map;
    }
    
    public static boolean isValidSession(final CoflSession session) {
        return session.timestampCreated.plus((TemporalAmount)Duration.ofDays(7L)).isAfter(ZonedDateTime.now());
    }
    
    private static Path GetUserPath(final String username) {
        return Paths.get(GetTempFileFolder().toString() + "/" + username, new String[0]);
    }
    
    public static void DeleteCoflSession(final String username) {
        final Path path = GetUserPath(username);
        path.toFile().delete();
    }
    
    public static void DeleteAllCoflSessions() {
        final Path path = GetTempFileFolder();
        final File[] listFiles;
        final File[] sessions = listFiles = path.toFile().listFiles();
        for (final File f : listFiles) {
            f.delete();
        }
    }
    
    public static CoflSession GetCoflSession(final String username) throws IOException {
        final Path path = GetUserPath(username);
        final File file = path.toFile();
        if (!file.exists()) {
            final CoflSession session = new CoflSession(UUID.randomUUID().toString(), ZonedDateTime.now());
            OverwriteCoflSession(username, session);
            return session;
        }
        final BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        final String raw = reader.lines().collect(Collectors.joining("\n"));
        reader.close();
        final CoflSession session2 = (CoflSession)CoflSessionManager.gson.fromJson(raw, (Class)CoflSession.class);
        return session2;
    }
    
    public static boolean OverwriteCoflSession(final String username, final CoflSession session) throws IOException {
        final Path path = GetUserPath(username);
        final File file = path.toFile();
        file.createNewFile();
        final String data = CoflSessionManager.gson.toJson((Object)session);
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
        bw.append((CharSequence)data);
        bw.flush();
        bw.close();
        return true;
    }
    
    static {
        CoflSessionManager.gson = new GsonBuilder().registerTypeAdapter((Type)ZonedDateTime.class, (Object)new TypeAdapter<ZonedDateTime>() {
            public void write(final JsonWriter out, final ZonedDateTime value) throws IOException {
                out.value(value.toString());
            }
            
            public ZonedDateTime read(final JsonReader in) throws IOException {
                return ZonedDateTime.parse(in.nextString());
            }
        }).enableComplexMapKeySerialization().create();
    }
    
    public static class CoflSession
    {
        public String SessionUUID;
        public ZonedDateTime timestampCreated;
        
        public CoflSession() {
        }
        
        public CoflSession(final String sessionUUID, final ZonedDateTime timestampCreated) {
            this.SessionUUID = sessionUUID;
            this.timestampCreated = timestampCreated;
        }
    }
}
