//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.reflections.scanners;

import java.util.*;

public class MethodAnnotationsScanner extends AbstractScanner
{
    public void scan(final Object cls) {
        for (final Object method : this.getMetadataAdapter().getMethods(cls)) {
            for (final String methodAnnotation : this.getMetadataAdapter().getMethodAnnotationNames(method)) {
                if (this.acceptResult(methodAnnotation)) {
                    this.getStore().put((Object)methodAnnotation, (Object)this.getMetadataAdapter().getMethodFullKey(cls, method));
                }
            }
        }
    }
}
