//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.util;

import org.newdawn.slick.*;

public class Bootstrap
{
    public static void runAsApplication(final Game game, final int width, final int height, final boolean fullscreen) {
        try {
            final AppGameContainer container = new AppGameContainer(game, width, height, fullscreen);
            container.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
