//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.fills.*;
import org.newdawn.slick.geom.*;
import org.newdawn.slick.opengl.renderer.*;
import org.newdawn.slick.*;

public class GradientTest extends BasicGame
{
    private GameContainer container;
    private GradientFill gradient;
    private GradientFill gradient2;
    private GradientFill gradient4;
    private Rectangle rect;
    private Rectangle center;
    private RoundedRectangle round;
    private RoundedRectangle round2;
    private Polygon poly;
    private float ang;
    
    public GradientTest() {
        super("Gradient Test");
    }
    
    public void init(final GameContainer container) throws SlickException {
        this.container = container;
        this.rect = new Rectangle(400.0f, 100.0f, 200.0f, 150.0f);
        this.round = new RoundedRectangle(150.0f, 100.0f, 200.0f, 150.0f, 50.0f);
        this.round2 = new RoundedRectangle(150.0f, 300.0f, 200.0f, 150.0f, 50.0f);
        this.center = new Rectangle(350.0f, 250.0f, 100.0f, 100.0f);
        (this.poly = new Polygon()).addPoint(400.0f, 350.0f);
        this.poly.addPoint(550.0f, 320.0f);
        this.poly.addPoint(600.0f, 380.0f);
        this.poly.addPoint(620.0f, 450.0f);
        this.poly.addPoint(500.0f, 450.0f);
        this.gradient = new GradientFill(0.0f, -75.0f, Color.red, 0.0f, 75.0f, Color.yellow, true);
        this.gradient2 = new GradientFill(0.0f, -75.0f, Color.blue, 0.0f, 75.0f, Color.white, true);
        this.gradient4 = new GradientFill(-50.0f, -40.0f, Color.green, 50.0f, 40.0f, Color.cyan, true);
    }
    
    public void render(final GameContainer container, final Graphics g) {
        g.rotate(400.0f, 300.0f, this.ang);
        g.fill((Shape)this.rect, (ShapeFill)this.gradient);
        g.fill((Shape)this.round, (ShapeFill)this.gradient);
        g.fill((Shape)this.poly, (ShapeFill)this.gradient2);
        g.fill((Shape)this.center, (ShapeFill)this.gradient4);
        g.setAntiAlias(true);
        g.setLineWidth(10.0f);
        g.draw((Shape)this.round2, (ShapeFill)this.gradient2);
        g.setLineWidth(2.0f);
        g.draw((Shape)this.poly, (ShapeFill)this.gradient);
        g.setAntiAlias(false);
        g.fill((Shape)this.center, (ShapeFill)this.gradient4);
        g.setAntiAlias(true);
        g.setColor(Color.black);
        g.draw((Shape)this.center);
        g.setAntiAlias(false);
    }
    
    public void update(final GameContainer container, final int delta) {
        this.ang += delta * 0.01f;
    }
    
    public static void main(final String[] argv) {
        try {
            Renderer.setRenderer(2);
            final AppGameContainer container = new AppGameContainer((Game)new GradientTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
    
    public void keyPressed(final int key, final char c) {
        if (key == 1) {
            this.container.exit();
        }
    }
}
