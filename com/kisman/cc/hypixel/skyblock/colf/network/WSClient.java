//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf.network;

import java.net.*;
import java.io.*;
import com.neovisionaries.ws.client.*;
import java.security.*;
import com.kisman.cc.hypixel.skyblock.colf.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.hypixel.skyblock.colf.commands.*;
import com.google.gson.*;

public class WSClient extends WebSocketAdapter
{
    public static Gson gson;
    public URI uri;
    public WebSocket socket;
    public boolean shouldRun;
    public WebSocketState currentState;
    
    public WSClient(final URI uri) {
        this.shouldRun = false;
        this.currentState = WebSocketState.CLOSED;
        this.uri = uri;
    }
    
    public void start() throws IOException, WebSocketException, NoSuchAlgorithmException {
        final WebSocketFactory factory = new WebSocketFactory();
        factory.setVerifyHostname(false);
        factory.setSSLContext(NaiveSSLContext.getInstance("TLSv1.2"));
        factory.setConnectionTimeout(10000);
        (this.socket = factory.createSocket(this.uri)).addListener((WebSocketListener)this);
        this.socket.connect();
    }
    
    public void stop() {
        System.out.println("Closing Socket");
        this.socket.clearListeners();
        this.socket.disconnect();
        System.out.println("Socket closed");
    }
    
    public void onStateChanged(final WebSocket websocket, final WebSocketState newState) throws Exception {
        System.out.println("WebSocket Changed state to: " + newState);
        this.currentState = newState;
        if (newState == WebSocketState.CLOSED && this.shouldRun) {
            MainColf.wrapper.restartWebsocketConnection();
        }
        super.onStateChanged(websocket, newState);
    }
    
    public void onTextMessage(final WebSocket websocket, final String text) throws Exception {
        System.out.println("Received: " + text);
        final JsonStringCommand cmd = (JsonStringCommand)WSClient.gson.fromJson(text, (Class)JsonStringCommand.class);
        ChatUtils.message((Object)cmd);
    }
    
    public void sendCommand(final Command cmd) {
        final String json = WSClient.gson.toJson((Object)cmd);
        this.socket.sendText(json);
    }
    
    static {
        WSClient.gson = new GsonBuilder().create();
    }
}
