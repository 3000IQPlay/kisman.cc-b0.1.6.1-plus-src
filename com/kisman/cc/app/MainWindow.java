//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow implements ActionListener
{
    public static String d1;
    public static String d2;
    public JFrame frame;
    public JButton button;
    public JTextField text;
    public JLabel label;
    
    public MainWindow(final boolean visible) {
        this.button = new JButton("Register");
        this.text = new JTextField();
        this.label = new JLabel("");
        (this.frame = new JFrame("HWIDUtil")).setSize(100, 100);
        this.frame.setDefaultCloseOperation(2);
        this.button.addActionListener(this);
        this.button.setSize(100, 100);
        this.frame.add("Center", this.button);
        this.frame.setVisible(visible);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (!e.getActionCommand().equals("Register") || !this.text.getText().isEmpty()) {}
    }
    
    static {
        MainWindow.d1 = "discord.com";
        MainWindow.d2 = "api/webhooks";
    }
}
