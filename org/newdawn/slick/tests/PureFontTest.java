//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class PureFontTest extends BasicGame
{
    private Font font;
    private Image image;
    private static AppGameContainer container;
    
    public PureFontTest() {
        super("Hiero Font Test");
    }
    
    public void init(final GameContainer container) throws SlickException {
        this.image = new Image("testdata/sky.jpg");
        this.font = (Font)new AngelCodeFont("testdata/hiero.fnt", "testdata/hiero.png");
    }
    
    public void render(final GameContainer container, final Graphics g) {
        this.image.draw(0.0f, 0.0f, 800.0f, 600.0f);
        this.font.drawString(100.0f, 32.0f, "On top of old smokey, all");
        this.font.drawString(100.0f, 80.0f, "covered with sand..");
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
    }
    
    public void keyPressed(final int key, final char c) {
        if (key == 1) {
            System.exit(0);
        }
    }
    
    public static void main(final String[] argv) {
        try {
            (PureFontTest.container = new AppGameContainer((Game)new PureFontTest())).setDisplayMode(800, 600, false);
            PureFontTest.container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
