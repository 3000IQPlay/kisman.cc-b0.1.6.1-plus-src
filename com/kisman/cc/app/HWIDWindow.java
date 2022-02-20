//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.app;

import com.kisman.cc.util.hwid.*;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;

public class HWIDWindow
{
    public static Frame frame;
    
    public static void init() {
        HWIDWindow.frame.setVisible(true);
    }
    
    static {
        HWIDWindow.frame = new Frame();
    }
    
    public static class Frame extends JFrame
    {
        public Frame() {
            this.setTitle("Verification failed.");
            this.setDefaultCloseOperation(2);
            this.setLocationRelativeTo(null);
            copyToClipboard();
            final String message = "Sorry, you are not on the HWID list.\nHWID: " + HWID.getHWID() + "\n(Copied to clipboard.)";
            JOptionPane.showMessageDialog(this, message, "Could not verify your HWID successfully.", -1, UIManager.getIcon("OptionPane.errorIcon"));
        }
        
        public static void copyToClipboard() {
            final StringSelection selection = new StringSelection(HWID.getHWID());
            final Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, selection);
        }
    }
}
