//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.skyblock.colf.network;

import java.security.*;
import javax.net.ssl.*;
import java.security.cert.*;

public class NaiveSSLContext
{
    private NaiveSSLContext() {
    }
    
    public static SSLContext getInstance(final String protocol) throws NoSuchAlgorithmException {
        return init(SSLContext.getInstance(protocol));
    }
    
    public static SSLContext getInstance(final String protocol, final Provider provider) throws NoSuchAlgorithmException {
        return init(SSLContext.getInstance(protocol, provider));
    }
    
    public static SSLContext getInstance(final String protocol, final String provider) throws NoSuchAlgorithmException, NoSuchProviderException {
        return init(SSLContext.getInstance(protocol, provider));
    }
    
    private static SSLContext init(final SSLContext context) {
        try {
            context.init(null, new TrustManager[] { new NaiveTrustManager() }, null);
        }
        catch (KeyManagementException e) {
            throw new RuntimeException("Failed to initialize an SSLContext.", e);
        }
        return context;
    }
    
    private static class NaiveTrustManager implements X509TrustManager
    {
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        
        @Override
        public void checkClientTrusted(final X509Certificate[] certs, final String authType) {
        }
        
        @Override
        public void checkServerTrusted(final X509Certificate[] certs, final String authType) {
        }
    }
}
