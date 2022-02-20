//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.util;

import java.io.*;
import java.net.*;

public interface ResourceLocation
{
    InputStream getResourceAsStream(final String p0);
    
    URL getResource(final String p0);
}
