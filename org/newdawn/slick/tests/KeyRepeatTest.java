//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.*;

public class KeyRepeatTest extends BasicGame
{
    private int count;
    private Input input;
    
    public KeyRepeatTest() {
        super("KeyRepeatTest");
    }
    
    public void init(final GameContainer container) throws SlickException {
        (this.input = container.getInput()).enableKeyRepeat(300, 100);
    }
    
    public void render(final GameContainer container, final Graphics g) {
        g.drawString("Key Press Count: " + this.count, 100.0f, 100.0f);
        g.drawString("Press Space to Toggle Key Repeat", 100.0f, 150.0f);
        g.drawString("Key Repeat Enabled: " + this.input.isKeyRepeatEnabled(), 100.0f, 200.0f);
    }
    
    public void update(final GameContainer container, final int delta) {
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new KeyRepeatTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
    
    public void keyPressed(final int key, final char c) {
        ++this.count;
        if (key == 57) {
            if (this.input.isKeyRepeatEnabled()) {
                this.input.disableKeyRepeat();
            }
            else {
                this.input.enableKeyRepeat(300, 100);
            }
        }
    }
}
