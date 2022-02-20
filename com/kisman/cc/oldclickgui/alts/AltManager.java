//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.alts;

import net.minecraft.client.*;
import java.net.*;
import com.mojang.authlib.yggdrasil.*;
import com.mojang.authlib.*;
import net.minecraft.util.*;
import com.kisman.cc.*;
import java.lang.reflect.*;
import java.util.*;

public class AltManager
{
    private static final List<AltEntry> alts;
    private static Minecraft mc;
    
    public static YggdrasilUserAuthentication logIn(final String email, final String password, final boolean setSession) {
        final YggdrasilUserAuthentication auth = (YggdrasilUserAuthentication)new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
        auth.setUsername(email);
        auth.setPassword(password);
        final YggdrasilUserAuthentication yggdrasilUserAuthentication;
        Session session;
        new Thread(() -> {
            try {
                yggdrasilUserAuthentication.logIn();
                if (setSession) {
                    session = new Session(yggdrasilUserAuthentication.getSelectedProfile().getName(), yggdrasilUserAuthentication.getSelectedProfile().getId().toString(), yggdrasilUserAuthentication.getAuthenticatedToken(), "mojang");
                    if (session != null) {
                        setSession(session);
                    }
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            return;
        }).start();
        return auth;
    }
    
    public static List<AltEntry> getAlts() {
        return AltManager.alts;
    }
    
    public static void setSession(final Session newSession) {
        final Class<? extends Minecraft> mc = Minecraft.getMinecraft().getClass();
        try {
            Field session = null;
            for (final Field field : mc.getDeclaredFields()) {
                if (field.getType().isInstance(newSession)) {
                    session = field;
                    Kisman.LOGGER.info("Attempting Injection into Session.");
                }
            }
            if (session == null) {
                throw new IllegalStateException("No field of type " + Session.class.getCanonicalName() + " declared.");
            }
            session.setAccessible(true);
            session.set(Minecraft.getMinecraft(), newSession);
            session.setAccessible(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    static {
        alts = new ArrayList<AltEntry>();
        AltManager.mc = Minecraft.getMinecraft();
    }
}
