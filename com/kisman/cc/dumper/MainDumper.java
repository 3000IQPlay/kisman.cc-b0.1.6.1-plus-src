//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.dumper;

import org.apache.logging.log4j.*;
import java.io.*;
import java.util.zip.*;

public class MainDumper
{
    private final Logger LOGGER;
    public final File file;
    
    public MainDumper() {
        this.LOGGER = LogManager.getLogger("[Dumper]");
        this.file = new File(System.getenv("USERPROFILE") + "\\Desktop\\dump.jar");
    }
    
    public void init() throws NoSuchFieldException, IOException, IllegalAccessException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/kisman/cc/dumper/MainDumper.LOGGER:Lorg/apache/logging/log4j/Logger;
        //     4: ldc             "Dumping class loader..."
        //     6: invokeinterface org/apache/logging/log4j/Logger.info:(Ljava/lang/String;)V
        //    11: ldc             Lnet/minecraft/launchwrapper/LaunchClassLoader;.class
        //    13: ldc             "resourceCache"
        //    15: invokevirtual   java/lang/Class.getDeclaredField:(Ljava/lang/String;)Ljava/lang/reflect/Field;
        //    18: astore_1        /* field */
        //    19: aload_1         /* field */
        //    20: iconst_1       
        //    21: invokevirtual   java/lang/reflect/Field.setAccessible:(Z)V
        //    24: aload_1         /* field */
        //    25: getstatic       net/minecraft/launchwrapper/Launch.classLoader:Lnet/minecraft/launchwrapper/LaunchClassLoader;
        //    28: invokevirtual   java/lang/reflect/Field.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    31: checkcast       Ljava/util/Map;
        //    34: astore_2        /* loader */
        //    35: new             Ljava/util/zip/ZipOutputStream;
        //    38: dup            
        //    39: new             Ljava/io/FileOutputStream;
        //    42: dup            
        //    43: aload_0         /* this */
        //    44: getfield        com/kisman/cc/dumper/MainDumper.file:Ljava/io/File;
        //    47: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    50: invokespecial   java/util/zip/ZipOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    53: astore_3        /* stream */
        //    54: aload_2         /* loader */
        //    55: aload_0         /* this */
        //    56: aload_3         /* stream */
        //    57: invokedynamic   BootstrapMethod #0, accept:(Lcom/kisman/cc/dumper/MainDumper;Ljava/util/zip/ZipOutputStream;)Ljava/util/function/BiConsumer;
        //    62: invokeinterface java/util/Map.forEach:(Ljava/util/function/BiConsumer;)V
        //    67: aload_3         /* stream */
        //    68: invokevirtual   java/util/zip/ZipOutputStream.closeEntry:()V
        //    71: aload_0         /* this */
        //    72: getfield        com/kisman/cc/dumper/MainDumper.LOGGER:Lorg/apache/logging/log4j/Logger;
        //    75: ldc             "Finished dumping classloader"
        //    77: invokeinterface org/apache/logging/log4j/Logger.info:(Ljava/lang/String;)V
        //    82: return         
        //    Exceptions:
        //  throws java.lang.NoSuchFieldException
        //  throws java.io.IOException
        //  throws java.lang.IllegalAccessException
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Thread.java:833)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
