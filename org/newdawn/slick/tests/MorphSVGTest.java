//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.svg.*;
import org.newdawn.slick.*;

public class MorphSVGTest extends BasicGame
{
    private SVGMorph morph;
    private Diagram base;
    private float time;
    private float x;
    
    public MorphSVGTest() {
        super("MorphShapeTest");
        this.x = -300.0f;
    }
    
    public void init(final GameContainer container) throws SlickException {
        this.base = InkscapeLoader.load("testdata/svg/walk1.svg");
        (this.morph = new SVGMorph(this.base)).addStep(InkscapeLoader.load("testdata/svg/walk2.svg"));
        this.morph.addStep(InkscapeLoader.load("testdata/svg/walk3.svg"));
        this.morph.addStep(InkscapeLoader.load("testdata/svg/walk4.svg"));
        container.setVSync(true);
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
        this.morph.updateMorphTime(delta * 0.003f);
        this.x += delta * 0.2f;
        if (this.x > 550.0f) {
            this.x = -450.0f;
        }
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
        g.translate(this.x, 0.0f);
        SimpleDiagramRenderer.render(g, (Diagram)this.morph);
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new MorphSVGTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
