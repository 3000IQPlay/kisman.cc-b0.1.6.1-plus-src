//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.state.*;
import org.newdawn.slick.tests.states.*;
import org.newdawn.slick.*;

public class StateBasedTest extends StateBasedGame
{
    public StateBasedTest() {
        super("State Based Test");
    }
    
    public void initStatesList(final GameContainer container) {
        this.addState((GameState)new TestState1());
        this.addState((GameState)new TestState2());
        this.addState((GameState)new TestState3());
    }
    
    public static void main(final String[] argv) {
        try {
            final AppGameContainer container = new AppGameContainer((Game)new StateBasedTest());
            container.setDisplayMode(800, 600, false);
            container.start();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
