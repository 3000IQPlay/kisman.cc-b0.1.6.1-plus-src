//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.*;
import java.awt.*;
import java.awt.event.*;

public class CanvasContainerTest extends BasicGame
{
    private Image tga;
    private Image scaleMe;
    private Image scaled;
    private Image gif;
    private Image image;
    private Image subImage;
    private float rot;
    
    public CanvasContainerTest() {
        super("Canvas Container Test");
    }
    
    public void init(final GameContainer container) throws SlickException {
        final Image image = new Image("testdata/logo.tga");
        this.tga = image;
        this.image = image;
        this.scaleMe = new Image("testdata/logo.tga", true, 2);
        this.gif = new Image("testdata/logo.gif");
        this.scaled = this.gif.getScaledCopy(120, 120);
        this.subImage = this.image.getSubImage(200, 0, 70, 260);
        this.rot = 0.0f;
    }
    
    public void render(final GameContainer container, final Graphics g) {
        this.image.draw(0.0f, 0.0f);
        this.image.draw(500.0f, 0.0f, 200.0f, 100.0f);
        this.scaleMe.draw(500.0f, 100.0f, 200.0f, 100.0f);
        this.scaled.draw(400.0f, 500.0f);
        final Image flipped = this.scaled.getFlippedCopy(true, false);
        flipped.draw(520.0f, 500.0f);
        final Image flipped2 = flipped.getFlippedCopy(false, true);
        flipped2.draw(520.0f, 380.0f);
        final Image flipped3 = flipped2.getFlippedCopy(true, false);
        flipped3.draw(400.0f, 380.0f);
        for (int i = 0; i < 3; ++i) {
            this.subImage.draw((float)(200 + i * 30), 300.0f);
        }
        g.translate(500.0f, 200.0f);
        g.rotate(50.0f, 50.0f, this.rot);
        g.scale(0.3f, 0.3f);
        this.image.draw();
        g.resetTransform();
    }
    
    public void update(final GameContainer container, final int delta) {
        this.rot += delta * 0.1f;
        if (this.rot > 360.0f) {
            this.rot -= 360.0f;
        }
    }
    
    public static void main(final String[] argv) {
        try {
            final CanvasGameContainer container = new CanvasGameContainer((Game)new CanvasContainerTest());
            final Frame frame = new Frame("Test");
            frame.setLayout(new GridLayout(1, 2));
            frame.setSize(500, 500);
            frame.add((Component)container);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(final WindowEvent e) {
                    System.exit(0);
                }
            });
            frame.setVisible(true);
            container.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void keyPressed(final int key, final char c) {
        if (key == 57) {
            if (this.image == this.gif) {
                this.image = this.tga;
            }
            else {
                this.image = this.gif;
            }
        }
    }
}
