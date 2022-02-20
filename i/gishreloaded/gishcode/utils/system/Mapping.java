//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package i.gishreloaded.gishcode.utils.system;

import net.minecraft.client.*;

public class Mapping
{
    public static String session;
    public static String yaw;
    public static String pitch;
    public static String rightClickDelayTimer;
    public static String getPlayerInfo;
    public static String playerTextures;
    public static String currentGameType;
    public static String connection;
    public static String blockHitDelay;
    public static String isInWeb;
    public static String curBlockDamageMP;
    public static String isHittingBlock;
    public static String onUpdateWalkingPlayer;
    public static String fire;
    public static String isImmuneToFire;
    
    public static boolean isNotObfuscated() {
        try {
            return Minecraft.class.getDeclaredField("instance") != null;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    static {
        Mapping.session = (isNotObfuscated() ? "session" : "session");
        Mapping.yaw = (isNotObfuscated() ? "yaw" : "yaw");
        Mapping.pitch = (isNotObfuscated() ? "pitch" : "pitch");
        Mapping.rightClickDelayTimer = (isNotObfuscated() ? "rightClickDelayTimer" : "rightClickDelayTimer");
        Mapping.getPlayerInfo = (isNotObfuscated() ? "getPlayerInfo" : "getPlayerInfo");
        Mapping.playerTextures = (isNotObfuscated() ? "playerTextures" : "playerTextures");
        Mapping.currentGameType = (isNotObfuscated() ? "currentGameType" : "currentGameType");
        Mapping.connection = (isNotObfuscated() ? "connection" : "connection");
        Mapping.blockHitDelay = (isNotObfuscated() ? "blockHitDelay" : "blockHitDelay");
        Mapping.isInWeb = (isNotObfuscated() ? "isInWeb" : "isInWeb");
        Mapping.curBlockDamageMP = (isNotObfuscated() ? "curBlockDamageMP" : "curBlockDamageMP");
        Mapping.isHittingBlock = (isNotObfuscated() ? "isHittingBlock" : "isHittingBlock");
        Mapping.onUpdateWalkingPlayer = (isNotObfuscated() ? "onUpdateWalkingPlayer" : "onUpdateWalkingPlayer");
        Mapping.fire = (isNotObfuscated() ? "fire" : "fire");
        Mapping.isImmuneToFire = (isNotObfuscated() ? "isImmuneToFire" : "isImmuneToFire");
    }
}
