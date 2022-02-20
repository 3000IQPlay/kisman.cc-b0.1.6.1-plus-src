//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import java.awt.*;
import org.newdawn.slick.font.effects.*;
import org.newdawn.slick.*;
import java.io.*;

public class UnicodeFontTest extends BasicGame
{
    private UnicodeFont unicodeFont;
    
    public UnicodeFontTest() {
        super("Font Test");
    }
    
    public void init(final GameContainer container) throws SlickException {
        container.setShowFPS(false);
        this.unicodeFont = new UnicodeFont("c:/windows/fonts/arial.ttf", 48, false, false);
        this.unicodeFont.getEffects().add(new ColorEffect(Color.white));
        container.getGraphics().setBackground(org.newdawn.slick.Color.darkGray);
    }
    
    public void render(final GameContainer container, final Graphics g) {
        g.setColor(org.newdawn.slick.Color.white);
        final String text = "This is UnicodeFont!\nIt rockz. Kerning: T,";
        this.unicodeFont.drawString(10.0f, 33.0f, text);
        g.setColor(org.newdawn.slick.Color.red);
        g.drawRect(10.0f, 33.0f, (float)this.unicodeFont.getWidth(text), (float)this.unicodeFont.getLineHeight());
        g.setColor(org.newdawn.slick.Color.blue);
        final int yOffset = this.unicodeFont.getYOffset(text);
        g.drawRect(10.0f, (float)(33 + yOffset), (float)this.unicodeFont.getWidth(text), (float)(this.unicodeFont.getHeight(text) - yOffset));
        this.unicodeFont.addGlyphs("~!@!#!#$%___--");
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
        this.unicodeFont.loadGlyphs(1);
    }
    
    public static void main(final String[] args) throws SlickException, IOException {
        Input.disableControllers();
        final AppGameContainer container = new AppGameContainer((Game)new UnicodeFontTest());
        container.setDisplayMode(512, 600, false);
        container.setTargetFrameRate(20);
        container.start();
    }
}
