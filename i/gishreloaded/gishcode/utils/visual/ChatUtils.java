//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package i.gishreloaded.gishcode.utils.visual;

import i.gishreloaded.gishcode.wrappers.*;
import net.minecraft.util.text.*;
import com.kisman.cc.*;

public class ChatUtils
{
    public static void component(final ITextComponent component) {
        if (Wrapper.INSTANCE.player() == null || Wrapper.INSTANCE.mc().ingameGUI.getChatGUI() == null) {
            return;
        }
        Wrapper.INSTANCE.mc().ingameGUI.getChatGUI().printChatMessage(new TextComponentTranslation(TextFormatting.WHITE + "", new Object[0]).appendSibling(component));
    }
    
    public static void simpleMessage(final Object message) {
        component((ITextComponent)new TextComponentTranslation((String)message, new Object[0]));
    }
    
    public static void message(final Object message) {
        component((ITextComponent)new TextComponentTranslation(TextFormatting.GRAY + "[" + TextFormatting.WHITE + Kisman.getName() + TextFormatting.GRAY + "] " + message, new Object[0]));
    }
    
    public static void complete(final Object message) {
        component((ITextComponent)new TextComponentTranslation(TextFormatting.GRAY + "[" + TextFormatting.GREEN + Kisman.getName() + TextFormatting.GRAY + "] " + message, new Object[0]));
    }
    
    public static void warning(final Object message) {
        component((ITextComponent)new TextComponentTranslation(TextFormatting.GRAY + "[" + TextFormatting.GOLD + Kisman.getName() + TextFormatting.GRAY + "] " + message, new Object[0]));
    }
    
    public static void error(final Object message) {
        component((ITextComponent)new TextComponentTranslation(TextFormatting.GRAY + "[" + TextFormatting.RED + Kisman.getName() + TextFormatting.GRAY + "] " + message, new Object[0]));
    }
}
