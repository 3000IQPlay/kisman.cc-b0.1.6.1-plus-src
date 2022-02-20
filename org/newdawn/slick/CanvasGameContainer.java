//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick;

import java.awt.*;
import org.lwjgl.opengl.*;
import org.lwjgl.*;
import javax.swing.*;
import org.newdawn.slick.util.*;

public class CanvasGameContainer extends Canvas
{
    protected Container container;
    protected Game game;
    
    public CanvasGameContainer(final Game game) throws SlickException {
        this(game, false);
    }
    
    public CanvasGameContainer(final Game game, final boolean shared) throws SlickException {
        this.game = game;
        this.setIgnoreRepaint(true);
        this.requestFocus();
        this.setSize(500, 500);
        (this.container = new Container(game, shared)).setForceExit(false);
    }
    
    public void start() throws SlickException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Input.disableControllers();
                    try {
                        Display.setParent((Canvas)CanvasGameContainer.this);
                    }
                    catch (LWJGLException e) {
                        throw new SlickException("Failed to setParent of canvas", (Throwable)e);
                    }
                    CanvasGameContainer.this.container.setup();
                    CanvasGameContainer.this.scheduleUpdate();
                }
                catch (SlickException e2) {
                    e2.printStackTrace();
                    System.exit(0);
                }
            }
        });
    }
    
    private void scheduleUpdate() {
        if (!this.isVisible()) {
            return;
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    CanvasGameContainer.this.container.gameLoop();
                }
                catch (SlickException e) {
                    e.printStackTrace();
                }
                CanvasGameContainer.this.container.checkDimensions();
                CanvasGameContainer.this.scheduleUpdate();
            }
        });
    }
    
    public void dispose() {
    }
    
    public GameContainer getContainer() {
        return (GameContainer)this.container;
    }
    
    private class Container extends AppGameContainer
    {
        public Container(final Game game, final boolean shared) throws SlickException {
            super(game, CanvasGameContainer.this.getWidth(), CanvasGameContainer.this.getHeight(), false);
            this.width = CanvasGameContainer.this.getWidth();
            this.height = CanvasGameContainer.this.getHeight();
            if (shared) {
                enableSharedContext();
            }
        }
        
        protected void updateFPS() {
            super.updateFPS();
        }
        
        protected boolean running() {
            return super.running() && CanvasGameContainer.this.isDisplayable();
        }
        
        public int getHeight() {
            return CanvasGameContainer.this.getHeight();
        }
        
        public int getWidth() {
            return CanvasGameContainer.this.getWidth();
        }
        
        public void checkDimensions() {
            if (this.width == CanvasGameContainer.this.getWidth()) {
                if (this.height == CanvasGameContainer.this.getHeight()) {
                    return;
                }
            }
            try {
                this.setDisplayMode(CanvasGameContainer.this.getWidth(), CanvasGameContainer.this.getHeight(), false);
            }
            catch (SlickException e) {
                Log.error(e);
            }
        }
    }
}