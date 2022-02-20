//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class AntiAliasTest extends BasicGame
{
    public AntiAliasTest() {
        super("AntiAlias Test");
    }
    
    public void init(final GameContainer container) throws SlickException {
        container.getGraphics().setBackground(Color.green);
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
        g.setAntiAlias(true);
        g.setColor(Color.red);
        g.drawOval(100.0f, 100.0f, 100.0f, 100.0f);
        g.fillOval(300.0f, 100.0f, 100.0f, 100.0f);
        g.setAntiAlias(false);
        g.setColor(Color.red);
        g.drawOval(100.0f, 300.0f, 100.0f, 100.0f);
        g.fillOval(300.0f, 300.0f, 100.0f, 100.0f);
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new AntiAliasTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
