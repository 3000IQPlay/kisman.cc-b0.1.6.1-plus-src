//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.particle;

import org.lwjgl.input.*;
import java.util.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.util.sb.*;
import org.lwjgl.opengl.*;
import com.kisman.cc.util.*;
import java.awt.*;

public class ParticleSystem
{
    private static final float SPEED = 0.1f;
    private List<Particle> particleList;
    private boolean mouse;
    private boolean rainbow;
    private int dist;
    
    public ParticleSystem(final int initAmount, final boolean mouse, final boolean rainbow, final int dist) {
        this.particleList = new ArrayList<Particle>();
        this.addParticles(initAmount);
        this.mouse = mouse;
        this.dist = dist;
        this.rainbow = rainbow;
    }
    
    public ParticleSystem(final int initAmount, final boolean mouse, final int dist) {
        this.particleList = new ArrayList<Particle>();
        this.addParticles(initAmount);
        this.mouse = mouse;
        this.dist = dist;
    }
    
    public void addParticles(final int amount) {
        for (int i = 0; i < amount; ++i) {
            this.particleList.add(Particle.generateParticle());
        }
    }
    
    public void tick(final int delta) {
        if (Mouse.isButtonDown(0)) {
            this.addParticles(1);
        }
        for (final Particle particle : this.particleList) {
            particle.tick(delta, 0.1f);
        }
    }
    
    public void render() {
        for (final Particle particle : this.particleList) {
            System.out.println("render particle");
            GL11.glColor4f(1.0f, 1.0f, 1.0f, particle.getAlpha() / 255.0f);
            GL11.glPointSize(particle.getSize());
            GL11.glBegin(0);
            GL11.glVertex2f(particle.getX(), particle.getY());
            GL11.glEnd();
            if (this.mouse) {
                Color c = null;
                if (ParticleGui.instance.rainbow.getValBoolean()) {
                    c = ColorSuper.rainbow(50.0f, 0.0f);
                    this.rainbow = true;
                }
                final float distance = (float)MathUtil.distance(particle.getX(), particle.getY(), (float)Mouse.getX(), (float)(Display.getHeight() - Mouse.getY()));
                if (distance >= this.dist) {
                    continue;
                }
                final float alpha = Math.min(1.0f, Math.min(1.0f, 1.0f - distance / this.dist));
                this.drawLine(particle.getX(), particle.getY(), (float)Mouse.getX(), (float)(Display.getHeight() - Mouse.getY()), this.rainbow ? (c.getRed() / 255.0f) : 1.0f, this.rainbow ? (c.getGreen() / 255.0f) : 1.0f, this.rainbow ? (c.getBlue() / 255.0f) : 1.0f, alpha);
            }
            else {
                float nearestDistance = 0.0f;
                Particle nearestParticle = null;
                for (final Particle particle2 : this.particleList) {
                    final float distance2 = particle.getDistanceTo(particle2);
                    if (distance2 <= this.dist && (MathUtil.distance((float)Mouse.getX(), (float)(Display.getHeight() - Mouse.getY()), particle.getX(), particle.getY()) <= this.dist || MathUtil.distance((float)Mouse.getX(), (float)(Display.getHeight() - Mouse.getY()), particle2.getX(), particle2.getY()) <= this.dist) && (nearestDistance <= 0.0f || distance2 <= nearestDistance)) {
                        nearestDistance = distance2;
                        nearestParticle = particle2;
                    }
                }
                if (nearestParticle == null) {
                    continue;
                }
                Color c2 = null;
                if (ParticleGui.instance.rainbow.getValBoolean()) {
                    c2 = ColorSuper.rainbow(50.0f, 0.0f);
                    this.rainbow = true;
                }
                final float alpha2 = Math.min(1.0f, Math.min(1.0f, 1.0f - nearestDistance / this.dist));
                this.drawLine(particle.getX(), particle.getY(), nearestParticle.getX(), nearestParticle.getY(), this.rainbow ? (c2.getRed() / 255.0f) : 1.0f, this.rainbow ? (c2.getGreen() / 255.0f) : 1.0f, this.rainbow ? (c2.getBlue() / 255.0f) : 1.0f, alpha2);
            }
        }
    }
    
    private void drawLine(final float x, final float y, final float x1, final float y1, final float r, final float g, final float b, final float alpha) {
        GL11.glColor4f(r, g, b, alpha);
        GL11.glLineWidth(0.5f);
        GL11.glBegin(1);
        GL11.glVertex2f(x, y);
        GL11.glVertex2f(x1, y1);
        GL11.glEnd();
    }
}
