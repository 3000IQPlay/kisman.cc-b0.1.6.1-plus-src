//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.hwid;

import java.security.*;
import java.net.*;
import java.io.*;
import com.kisman.cc.*;
import java.util.*;

public class HWID
{
    public static List<String> hwids;
    
    public static String getHWID() {
        try {
            final String toEncrypt = System.getenv("COMPUTERNAME") + System.getProperty("user.name") + System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("PROCESSOR_LEVEL");
            final MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(toEncrypt.getBytes());
            final StringBuffer hexString = new StringBuffer();
            final byte[] digest;
            final byte[] byteData = digest = md.digest();
            for (final byte aByteData : digest) {
                final String hex = Integer.toHexString(0xFF & aByteData);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
    
    public static List<String> getHWIDList() {
        try {
            final URL url = new URL("https://pastebin.com/raw/yM7s0G4u");
            final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                HWID.hwids.add(inputLine);
            }
        }
        catch (Exception e) {
            Kisman.LOGGER.error("Load HWID Failed!");
        }
        return HWID.hwids;
    }
    
    static {
        HWID.hwids = new ArrayList<String>();
    }
}
