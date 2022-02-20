//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.shaders;

import com.kisman.cc.module.render.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;

public class ItemShader extends FramebufferShader
{
    private static final ItemCharms ITEM_CHARMS;
    private Minecraft mc;
    public boolean blur;
    public float mix;
    public float alpha;
    public float imageMix;
    public boolean useImage;
    public boolean rotate;
    public static final ItemShader ITEM_SHADER;
    
    public ItemShader() {
        super("itemglow.frag");
        this.mc = Minecraft.getMinecraft();
        this.mix = 0.0f;
        this.alpha = 1.0f;
        this.imageMix = 0.0f;
    }
    
    public void setupUniforms() {
        this.setupUniform("texture");
        this.setupUniform("texelSize");
        this.setupUniform("color");
        this.setupUniform("divider");
        this.setupUniform("radius");
        this.setupUniform("maxSample");
        this.setupUniform("dimensions");
        this.setupUniform("blur");
        this.setupUniform("mixFactor");
        this.setupUniform("minAlpha");
        this.setupUniform("image");
        this.setupUniform("imageMix");
        this.setupUniform("useImage");
    }
    
    public void updateUniforms() {
        GL20.glUniform1i(this.getUniform("texture"), 0);
        GL20.glUniform2f(this.getUniform("texelSize"), 1.0f / this.mc.displayWidth * (this.radius * this.quality), 1.0f / this.mc.displayHeight * (this.radius * this.quality));
        GL20.glUniform3f(this.getUniform("color"), this.red, this.green, this.blue);
        GL20.glUniform1f(this.getUniform("divider"), 140.0f);
        GL20.glUniform1f(this.getUniform("radius"), this.radius);
        GL20.glUniform1f(this.getUniform("maxSample"), 10.0f);
        GL20.glUniform2f(this.getUniform("dimensions"), (float)this.mc.displayWidth, (float)this.mc.displayHeight);
        GL20.glUniform1i(this.getUniform("blur"), (int)(this.blur ? 1 : 0));
        GL20.glUniform1f(this.getUniform("mixFactor"), this.mix);
        GL20.glUniform1f(this.getUniform("minAlpha"), this.alpha);
        GL13.glActiveTexture(33992);
        GL11.glBindTexture(3553, 0);
        GL20.glUniform1i(this.getUniform("image"), 8);
        GL13.glActiveTexture(33984);
        GL20.glUniform1f(this.getUniform("imageMix"), this.imageMix);
        GL20.glUniform1i(this.getUniform("useImage"), 0);
    }
    
    static {
        ITEM_CHARMS = ItemCharms.instance;
        ITEM_SHADER = new ItemShader();
    }
}
