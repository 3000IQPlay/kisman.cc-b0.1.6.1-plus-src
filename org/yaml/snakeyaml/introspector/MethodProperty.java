//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.yaml.snakeyaml.introspector;

import java.beans.*;
import java.lang.reflect.*;
import org.yaml.snakeyaml.error.*;
import java.util.*;
import java.lang.annotation.*;
import org.yaml.snakeyaml.util.*;

public class MethodProperty extends GenericProperty
{
    private final PropertyDescriptor property;
    private final boolean readable;
    private final boolean writable;
    
    private static Type discoverGenericType(final PropertyDescriptor property) {
        final Method readMethod = property.getReadMethod();
        if (readMethod != null) {
            return readMethod.getGenericReturnType();
        }
        final Method writeMethod = property.getWriteMethod();
        if (writeMethod != null) {
            final Type[] paramTypes = writeMethod.getGenericParameterTypes();
            if (paramTypes.length > 0) {
                return paramTypes[0];
            }
        }
        return null;
    }
    
    public MethodProperty(final PropertyDescriptor property) {
        super(property.getName(), (Class)property.getPropertyType(), discoverGenericType(property));
        this.property = property;
        this.readable = (property.getReadMethod() != null);
        this.writable = (property.getWriteMethod() != null);
    }
    
    public void set(final Object object, final Object value) throws Exception {
        if (!this.writable) {
            throw new YAMLException("No writable property '" + this.getName() + "' on class: " + object.getClass().getName());
        }
        this.property.getWriteMethod().invoke(object, value);
    }
    
    public Object get(final Object object) {
        try {
            this.property.getReadMethod().setAccessible(true);
            return this.property.getReadMethod().invoke(object, new Object[0]);
        }
        catch (Exception e) {
            throw new YAMLException("Unable to find getter for property '" + this.property.getName() + "' on object " + object + ":" + e);
        }
    }
    
    public List<Annotation> getAnnotations() {
        List<Annotation> annotations;
        if (this.isReadable() && this.isWritable()) {
            annotations = ArrayUtils.toUnmodifiableCompositeList(this.property.getReadMethod().getAnnotations(), this.property.getWriteMethod().getAnnotations());
        }
        else if (this.isReadable()) {
            annotations = ArrayUtils.toUnmodifiableList(this.property.getReadMethod().getAnnotations());
        }
        else {
            annotations = ArrayUtils.toUnmodifiableList(this.property.getWriteMethod().getAnnotations());
        }
        return annotations;
    }
    
    public <A extends Annotation> A getAnnotation(final Class<A> annotationType) {
        A annotation = null;
        if (this.isReadable()) {
            annotation = this.property.getReadMethod().getAnnotation(annotationType);
        }
        if (annotation == null && this.isWritable()) {
            annotation = this.property.getWriteMethod().getAnnotation(annotationType);
        }
        return annotation;
    }
    
    public boolean isWritable() {
        return this.writable;
    }
    
    public boolean isReadable() {
        return this.readable;
    }
}
