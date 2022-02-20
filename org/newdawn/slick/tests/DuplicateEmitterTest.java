//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.particles.*;
import java.io.*;
import org.newdawn.slick.*;

public class DuplicateEmitterTest extends BasicGame
{
    private GameContainer container;
    private ParticleSystem explosionSystem;
    private ConfigurableEmitter explosionEmitter;
    
    public DuplicateEmitterTest() {
        super("DuplicateEmitterTest");
    }
    
    public void init(final GameContainer container) throws SlickException {
        this.container = container;
        try {
            this.explosionSystem = ParticleIO.loadConfiguredSystem("testdata/endlessexplosion.xml");
            (this.explosionEmitter = (ConfigurableEmitter)this.explosionSystem.getEmitter(0)).setPosition(400.0f, 100.0f);
            for (int i = 0; i < 5; ++i) {
                final ConfigurableEmitter newOne = this.explosionEmitter.duplicate();
                if (newOne == null) {
                    throw new SlickException("Failed to duplicate explosionEmitter");
                }
                newOne.name = newOne.name + "_" + i;
                newOne.setPosition((float)((i + 1) * 133), 400.0f);
                this.explosionSystem.addEmitter((ParticleEmitter)newOne);
            }
        }
        catch (IOException e) {
            throw new SlickException("Failed to load particle systems", (Throwable)e);
        }
    }
    
    public void update(final GameContainer container, final int delta) throws SlickException {
        this.explosionSystem.update(delta);
    }
    
    public void render(final GameContainer container, final Graphics g) throws SlickException {
        this.explosionSystem.render();
    }
    
    public void keyPressed(final int key, final char c) {
        if (key == 1) {
            this.container.exit();
        }
        if (key == 37) {
            this.explosionEmitter.wrapUp();
        }
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new DuplicateEmitterTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
