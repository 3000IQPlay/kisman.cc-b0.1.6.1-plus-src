//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf.network;

import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.client.*;
import com.kisman.cc.hypixel.skyblock.colf.minecraft_integration.*;
import java.net.*;
import java.io.*;
import com.neovisionaries.ws.client.*;
import java.security.*;
import com.kisman.cc.hypixel.skyblock.colf.commands.*;

public class WSClientWrapper
{
    public WSClient socket;
    public boolean isRunning;
    private String[] uris;
    
    public WSClientWrapper(final String[] uris) {
        this.uris = uris;
    }
    
    public void restartWebsocketConnection() {
        this.socket.socket.clearListeners();
        this.socket.stop();
        System.out.println("Sleeping...");
        ChatUtils.warning((Object)"Lost connection to Coflnet, trying to reestablish the connection in 2 Seconds...");
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.socket = new WSClient(this.socket.uri);
        this.isRunning = false;
        this.start();
    }
    
    public boolean startConnection() {
        if (this.isRunning) {
            return false;
        }
        for (final String s : this.uris) {
            System.out.println("Trying connection with uri=" + s);
            if (this.initializeNewSocket(s)) {
                return true;
            }
        }
        ChatUtils.warning((Object)"Cofl could not establish a connection to any server!");
        return false;
    }
    
    public boolean initializeNewSocket(final String uriPrefix) {
        String uri = uriPrefix;
        uri += "?version=b0.1.6.1";
        uri = uri + "&player=" + Minecraft.getMinecraft().session.getUsername();
        try {
            CoflSessionManager.UpdateCoflSessions();
            final String coflSessionID = CoflSessionManager.GetCoflSession(Minecraft.getMinecraft().session.username).SessionUUID;
            uri = uri + "&SId=" + coflSessionID;
            this.socket = new WSClient(URI.create(uri));
            final boolean successfull = this.start();
            if (successfull) {
                this.socket.shouldRun = true;
            }
            return successfull;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    private synchronized boolean start() {
        if (!this.isRunning) {
            try {
                this.socket.start();
                return this.isRunning = true;
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (WebSocketException e2) {
                e2.printStackTrace();
            }
            catch (NoSuchAlgorithmException e3) {
                e3.printStackTrace();
            }
            return false;
        }
        return false;
    }
    
    public synchronized void stop() {
        if (this.isRunning) {
            this.socket.shouldRun = false;
            this.socket.stop();
            this.isRunning = false;
            this.socket = null;
        }
    }
    
    public synchronized void SendMessage(final Command cmd) {
        if (this.isRunning) {
            this.socket.sendCommand(cmd);
        }
        else {
            ChatUtils.error((Object)"tried sending a callback to coflnet but failed. the connection must be closed.");
        }
    }
    
    public String GetStatus() {
        return "" + this.isRunning + " " + ((this.socket != null) ? this.socket.currentState.toString() : "NOT_INITIALIZED");
    }
}
