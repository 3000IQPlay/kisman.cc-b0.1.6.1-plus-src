//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;

public class CurveTest extends BasicGame
{
    private Curve curve;
    private Vector2f p1;
    private Vector2f c1;
    private Vector2f c2;
    private Vector2f p2;
    private Polygon poly;
    
    public CurveTest() {
        super("Curve Test");
        this.p1 = new Vector2f(100.0f, 300.0f);
        this.c1 = new Vector2f(100.0f, 100.0f);
        this.c2 = new Vector2f(300.0f, 100.0f);
        this.p2 = new Vector2f(300.0f, 300.0f);
    }
    
    public void init(final GameContainer container) throws SlickException {
        container.getGraphics().setBackground(Color.white);
        this.curve = new Curve(this.p2, this.c2, this.c1, this.p1);
        (this.poly = new Polygon()).addPoint(500.0f, 200.0f);
        this.poly.addPoint(600.0f, 200.0f);
        this.poly.addPoint(700.0f, 300.0f);
        this.poly.addPoint(400.0f, 300.0f);
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
    }
    
    private void drawMarker(final Graphics g, final Vector2f p) {
        g.drawRect(p.x - 5.0f, p.y - 5.0f, 10.0f, 10.0f);
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
        g.setColor(Color.gray);
        this.drawMarker(g, this.p1);
        this.drawMarker(g, this.p2);
        g.setColor(Color.red);
        this.drawMarker(g, this.c1);
        this.drawMarker(g, this.c2);
        g.setColor(Color.black);
        g.draw((Shape)this.curve);
        g.fill((Shape)this.curve);
        g.draw((Shape)this.poly);
        g.fill((Shape)this.poly);
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new CurveTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
