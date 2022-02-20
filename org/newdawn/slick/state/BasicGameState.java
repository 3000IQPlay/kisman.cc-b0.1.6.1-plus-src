//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.state;

import org.newdawn.slick.*;

public abstract class BasicGameState implements GameState
{
    public void inputStarted() {
    }
    
    public boolean isAcceptingInput() {
        return true;
    }
    
    public void setInput(final Input input) {
    }
    
    public void inputEnded() {
    }
    
    @Override
    public abstract int getID();
    
    public void controllerButtonPressed(final int controller, final int button) {
    }
    
    public void controllerButtonReleased(final int controller, final int button) {
    }
    
    public void controllerDownPressed(final int controller) {
    }
    
    public void controllerDownReleased(final int controller) {
    }
    
    public void controllerLeftPressed(final int controller) {
    }
    
    public void controllerLeftReleased(final int controller) {
    }
    
    public void controllerRightPressed(final int controller) {
    }
    
    public void controllerRightReleased(final int controller) {
    }
    
    public void controllerUpPressed(final int controller) {
    }
    
    public void controllerUpReleased(final int controller) {
    }
    
    public void keyPressed(final int key, final char c) {
    }
    
    public void keyReleased(final int key, final char c) {
    }
    
    public void mouseMoved(final int oldx, final int oldy, final int newx, final int newy) {
    }
    
    public void mouseDragged(final int oldx, final int oldy, final int newx, final int newy) {
    }
    
    public void mouseClicked(final int button, final int x, final int y, final int clickCount) {
    }
    
    public void mousePressed(final int button, final int x, final int y) {
    }
    
    public void mouseReleased(final int button, final int x, final int y) {
    }
    
    @Override
    public void enter(final GameContainer container, final StateBasedGame game) throws SlickException {
    }
    
    @Override
    public void leave(final GameContainer container, final StateBasedGame game) throws SlickException {
    }
    
    public void mouseWheelMoved(final int newValue) {
    }
}
