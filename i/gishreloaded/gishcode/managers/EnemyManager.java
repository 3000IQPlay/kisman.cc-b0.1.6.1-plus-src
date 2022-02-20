//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package i.gishreloaded.gishcode.managers;

import java.util.*;
import i.gishreloaded.gishcode.utils.visual.*;

public class EnemyManager
{
    public static ArrayList<String> enemysList;
    public static ArrayList<String> murders;
    public static ArrayList<String> detects;
    
    public static void addEnemy(final String enemyname) {
        if (!EnemyManager.enemysList.contains(enemyname)) {
            EnemyManager.enemysList.add(enemyname);
            ChatUtils.message(enemyname + " Added to §cenemys §7list.");
        }
    }
    
    public static void removeEnemy(final String enemyname) {
        if (EnemyManager.enemysList.contains(enemyname)) {
            EnemyManager.enemysList.remove(enemyname);
            ChatUtils.message(enemyname + " Removed from §cenemys §7list.");
        }
    }
    
    public static void clear() {
        if (!EnemyManager.enemysList.isEmpty()) {
            EnemyManager.enemysList.clear();
            ChatUtils.message("§cEnemys §7list clear.");
        }
    }
    
    static {
        EnemyManager.enemysList = new ArrayList<String>();
        EnemyManager.murders = new ArrayList<String>();
        EnemyManager.detects = new ArrayList<String>();
    }
}
