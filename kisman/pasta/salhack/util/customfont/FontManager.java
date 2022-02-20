//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package kisman.pasta.salhack.util.customfont;

import kisman.pasta.salhack.util.render.*;
import com.kisman.cc.*;

public class FontManager
{
    public SalFontRenderer[] FontRenderers;
    public SalFontRenderer TWCenMt18;
    public SalFontRenderer TwCenMtStd28;
    public SalFontRenderer VerdanaBold;
    
    public FontManager() {
        this.FontRenderers = new SalFontRenderer[25];
        this.TWCenMt18 = null;
        this.TwCenMtStd28 = null;
        this.VerdanaBold = null;
    }
    
    public void load() {
        this.TWCenMt18 = new SalFontRenderer("Tw Cen MT", 18.0f);
        this.TwCenMtStd28 = new SalFontRenderer("Tw Cen MT Std", 28.14f);
        this.VerdanaBold = new SalFontRenderer("VerdanaBold", 20.0f);
        for (int l_I = 0; l_I < this.FontRenderers.length; ++l_I) {
            this.FontRenderers[l_I] = new SalFontRenderer("Tw Cen MT", (float)l_I);
        }
    }
    
    public void loadCustomFont(final String customFont) {
        for (int l_I = 0; l_I < this.FontRenderers.length; ++l_I) {
            this.FontRenderers[l_I] = new SalFontRenderer(customFont, (float)l_I);
        }
    }
    
    public SalFontRenderer getFontBySize(int p_Size) {
        if (p_Size > this.FontRenderers.length) {
            p_Size = this.FontRenderers.length - 1;
        }
        return this.FontRenderers[p_Size];
    }
    
    public float drawStringWithShadow(final String p_Name, final float p_X, final float p_Y, final int p_Color) {
        return (float)this.FontRenderers[22].drawStringWithShadow(p_Name, p_X, p_Y, p_Color);
    }
    
    public float getStringHeight(final String p_Name) {
        return this.FontRenderers[22].getStringHeight(p_Name);
    }
    
    public float getStringWidth(final String p_Name) {
        return this.FontRenderers[22].getStringWidth(p_Name);
    }
    
    public static FontManager get() {
        return Kisman.instance.fontManager;
    }
}
