//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class DoubleClickTest extends BasicGame
{
    private String message;
    
    public DoubleClickTest() {
        super("Double Click Test");
        this.message = "Click or Double Click";
    }
    
    public void init(final GameContainer container) throws SlickException {
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
        g.drawString(this.message, 100.0f, 100.0f);
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new DoubleClickTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
    
    public void mouseClicked(final int button, final int x, final int y, final int clickCount) {
        if (clickCount == 1) {
            this.message = "Single Click: " + button + " " + x + "," + y;
        }
        if (clickCount == 2) {
            this.message = "Double Click: " + button + " " + x + "," + y;
        }
    }
}
