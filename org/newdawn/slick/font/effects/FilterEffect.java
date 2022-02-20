//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.font.effects;

import org.newdawn.slick.*;
import org.newdawn.slick.font.*;
import java.awt.*;
import java.awt.image.*;

public class FilterEffect implements Effect
{
    private BufferedImageOp filter;
    
    public FilterEffect() {
    }
    
    public FilterEffect(final BufferedImageOp filter) {
        this.filter = filter;
    }
    
    public void draw(final BufferedImage image, final Graphics2D g, final UnicodeFont unicodeFont, final Glyph glyph) {
        final BufferedImage scratchImage = EffectUtil.getScratchImage();
        this.filter.filter(image, scratchImage);
        image.getGraphics().drawImage(scratchImage, 0, 0, null);
    }
    
    public BufferedImageOp getFilter() {
        return this.filter;
    }
    
    public void setFilter(final BufferedImageOp filter) {
        this.filter = filter;
    }
}
