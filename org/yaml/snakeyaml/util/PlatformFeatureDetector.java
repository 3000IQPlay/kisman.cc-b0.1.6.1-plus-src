//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.yaml.snakeyaml.util;

public class PlatformFeatureDetector
{
    private Boolean isRunningOnAndroid;
    
    public PlatformFeatureDetector() {
        this.isRunningOnAndroid = null;
    }
    
    public boolean isRunningOnAndroid() {
        if (this.isRunningOnAndroid == null) {
            this.isRunningOnAndroid = System.getProperty("java.runtime.name").startsWith("Android Runtime");
        }
        return this.isRunningOnAndroid;
    }
}
