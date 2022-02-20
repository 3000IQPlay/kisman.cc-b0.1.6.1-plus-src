//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.geom.*;
import org.newdawn.slick.*;

public class PolygonTest extends BasicGame
{
    private Polygon poly;
    private boolean in;
    private float y;
    
    public PolygonTest() {
        super("Polygon Test");
    }
    
    public void init(final GameContainer container) throws SlickException {
        (this.poly = new Polygon()).addPoint(300.0f, 100.0f);
        this.poly.addPoint(320.0f, 200.0f);
        this.poly.addPoint(350.0f, 210.0f);
        this.poly.addPoint(280.0f, 250.0f);
        this.poly.addPoint(300.0f, 200.0f);
        this.poly.addPoint(240.0f, 150.0f);
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
        this.in = this.poly.contains((float)container.getInput().getMouseX(), (float)container.getInput().getMouseY());
        this.poly.setCenterY(0.0f);
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
        if (this.in) {
            g.setColor(Color.red);
            g.fill((Shape)this.poly);
        }
        g.setColor(Color.yellow);
        g.fillOval(this.poly.getCenterX() - 3.0f, this.poly.getCenterY() - 3.0f, 6.0f, 6.0f);
        g.setColor(Color.white);
        g.draw((Shape)this.poly);
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new PolygonTest(), 640, 480, false);
            container.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
