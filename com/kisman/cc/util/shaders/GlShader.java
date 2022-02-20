//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.shaders;

import java.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;
import org.lwjgl.util.vector.*;
import java.awt.*;
import java.nio.*;
import java.io.*;
import java.nio.charset.*;

public class GlShader
{
    private int programId;
    private int vertexShaderId;
    private int fragmentShaderId;
    private String name;
    private Map<String, Integer> uniforms;
    
    public GlShader(final InputStream sourceStream, final String name) {
        this.name = name;
        this.uniforms = new HashMap<String, Integer>();
        if (sourceStream == null) {
            return;
        }
        String source;
        try {
            source = readStreamToString(sourceStream);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }
        final StringBuilder vertexSource = new StringBuilder(source.length() / 2);
        final StringBuilder fragmentSource = new StringBuilder(source.length() / 2);
        int mode = -1;
        for (final String line : source.split("\n")) {
            if (line.contains("#shader vert")) {
                mode = 0;
            }
            else if (line.contains("#shader frag")) {
                mode = 1;
            }
            else if (mode == 0) {
                vertexSource.append(line).append("\n");
            }
            else if (mode == 1) {
                fragmentSource.append(line).append("\n");
            }
        }
        final int vertId = GL20.glCreateShader(35633);
        final int fragId = GL20.glCreateShader(35632);
        GL20.glShaderSource(vertId, (CharSequence)vertexSource);
        GL20.glShaderSource(fragId, (CharSequence)fragmentSource);
        GL20.glCompileShader(vertId);
        GL20.glCompileShader(fragId);
        if (GL20.glGetShaderi(vertId, 35713) == 0) {
            final String error = GL20.glGetShaderInfoLog(vertId, 1024);
            System.err.println("Vertex shader " + name + " could not compile: " + error);
        }
        if (GL20.glGetShaderi(fragId, 35713) == 0) {
            final String error = GL20.glGetShaderInfoLog(fragId, 1024);
            System.err.println("Fragment shader " + name + " could not compile: " + error);
        }
        final int programId = GL20.glCreateProgram();
        this.programId = programId;
        this.vertexShaderId = vertId;
        this.fragmentShaderId = fragId;
        GL20.glAttachShader(programId, vertId);
        GL20.glAttachShader(programId, fragId);
        GL20.glLinkProgram(programId);
        if (GL20.glGetProgrami(programId, 35714) == 0) {
            final String error2 = GL20.glGetShaderInfoLog(programId, 1024);
            System.err.println("Shader " + name + " could not be linked: " + error2);
        }
        GL20.glDetachShader(programId, vertId);
        GL20.glDetachShader(programId, fragId);
        GL20.glValidateProgram(programId);
    }
    
    public GlShader(final String name) {
        this(GlShader.class.getResourceAsStream("/shaders/" + name + ".shader"), name);
    }
    
    public GlShader(final int programId, final int vertexShaderId, final int fragmentShaderId) {
        this.programId = programId;
        this.vertexShaderId = vertexShaderId;
        this.fragmentShaderId = fragmentShaderId;
        this.uniforms = new HashMap<String, Integer>();
    }
    
    public void bind() {
        GL20.glUseProgram(this.programId);
    }
    
    public void unbind() {
        GL20.glUseProgram(0);
    }
    
    public void finalize() {
        this.unbind();
        GL20.glDeleteProgram(this.programId);
    }
    
    public int createUniform(final String uniformName) {
        if (this.uniforms.containsKey(uniformName)) {
            return this.uniforms.get(uniformName);
        }
        final int location = GL20.glGetUniformLocation(this.programId, (CharSequence)uniformName);
        this.uniforms.put(uniformName, location);
        return location;
    }
    
    public void set(final String uniformName, final int value) {
        GL20.glUniform1i(this.createUniform(uniformName), value);
    }
    
    public void set(final String uniformName, final float value) {
        GL20.glUniform1f(this.createUniform(uniformName), value);
    }
    
    public void set(final String uniformName, final boolean value) {
        GL20.glUniform1i(this.createUniform(uniformName), (int)(value ? 1 : 0));
    }
    
    public void set(final String uniformName, final Vec2f value) {
        GL20.glUniform2f(this.createUniform(uniformName), value.x, value.y);
    }
    
    public void set(final String uniformName, final Vector4f value) {
        GL20.glUniform4f(this.createUniform(uniformName), value.x, value.y, value.z, value.w);
    }
    
    public void set(final String uniformName, final Color value) {
        GL20.glUniform4f(this.createUniform(uniformName), value.getRed() / 255.0f, value.getBlue() / 255.0f, value.getGreen() / 255.0f, value.getAlpha() / 255.0f);
    }
    
    public void set(final String uniformName, final FloatBuffer matrix) {
        GL20.glUniformMatrix4(this.createUniform(uniformName), false, matrix);
    }
    
    public void set(final String uniformName, final int[] integers) {
        GL20.glUniform4i(this.createUniform(uniformName), integers[0], integers[1], integers[2], integers[3]);
    }
    
    public int getVertexShaderId() {
        return this.vertexShaderId;
    }
    
    public int getFragmentShaderId() {
        return this.fragmentShaderId;
    }
    
    public int getProgramId() {
        return this.programId;
    }
    
    private static String readStreamToString(final InputStream inputStream) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final byte[] buffer = new byte[512];
        int read;
        while ((read = inputStream.read(buffer, 0, buffer.length)) != -1) {
            out.write(buffer, 0, read);
        }
        return new String(out.toByteArray(), StandardCharsets.UTF_8);
    }
    
    public static GlShader createShader(final String name) {
        final InputStream sourceStream = GlShader.class.getResourceAsStream("/shaders/" + name + ".shader");
        if (sourceStream == null) {
            return null;
        }
        String source;
        try {
            source = readStreamToString(sourceStream);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        final StringBuilder vertexSource = new StringBuilder(source.length() / 2);
        final StringBuilder fragmentSource = new StringBuilder(source.length() / 2);
        int mode = -1;
        for (final String line : source.split("\n")) {
            if (line.contains("#shader vert")) {
                mode = 0;
            }
            else if (line.contains("#shader frag")) {
                mode = 1;
            }
            else if (mode == 0) {
                vertexSource.append(line).append("\n");
            }
            else if (mode == 1) {
                fragmentSource.append(line).append("\n");
            }
        }
        final int vertId = GL20.glCreateShader(35633);
        final int fragId = GL20.glCreateShader(35632);
        GL20.glShaderSource(vertId, (CharSequence)vertexSource);
        GL20.glShaderSource(fragId, (CharSequence)fragmentSource);
        GL20.glCompileShader(vertId);
        GL20.glCompileShader(fragId);
        if (GL20.glGetShaderi(vertId, 35713) == 0) {
            final String error = GL20.glGetShaderInfoLog(vertId, 1024);
            System.err.println("Vertex shader " + name + " could not compile: " + error);
        }
        if (GL20.glGetShaderi(fragId, 35713) == 0) {
            final String error = GL20.glGetShaderInfoLog(fragId, 1024);
            System.err.println("Fragment shader " + name + " could not compile: " + error);
        }
        final int programId = GL20.glCreateProgram();
        final GlShader shader = new GlShader(programId, vertId, fragId);
        GL20.glAttachShader(programId, vertId);
        GL20.glAttachShader(programId, fragId);
        GL20.glLinkProgram(programId);
        if (GL20.glGetProgrami(programId, 35714) == 0) {
            final String error2 = GL20.glGetShaderInfoLog(programId, 1024);
            System.err.println("Shader " + name + " could not be linked: " + error2);
        }
        GL20.glDetachShader(programId, vertId);
        GL20.glDetachShader(programId, fragId);
        GL20.glValidateProgram(programId);
        return shader;
    }
}
