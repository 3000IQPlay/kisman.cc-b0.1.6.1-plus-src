//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.yaml.snakeyaml.introspector;

import java.lang.annotation.*;
import java.util.*;

public class MissingProperty extends Property
{
    public MissingProperty(final String name) {
        super(name, Object.class);
    }
    
    @Override
    public Class<?>[] getActualTypeArguments() {
        return (Class<?>[])new Class[0];
    }
    
    @Override
    public void set(final Object object, final Object value) throws Exception {
    }
    
    @Override
    public Object get(final Object object) {
        return object;
    }
    
    @Override
    public List<Annotation> getAnnotations() {
        return Collections.emptyList();
    }
    
    @Override
    public <A extends Annotation> A getAnnotation(final Class<A> annotationType) {
        return null;
    }
}
