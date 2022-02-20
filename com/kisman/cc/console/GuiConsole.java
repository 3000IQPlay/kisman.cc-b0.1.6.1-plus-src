//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.console;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.util.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.*;
import com.kisman.cc.command.*;
import java.util.*;
import java.awt.*;
import java.io.*;
import org.lwjgl.input.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.util.*;
import net.minecraft.client.*;
import net.minecraft.util.text.*;
import net.minecraft.util.math.*;
import javax.annotation.*;

@SideOnly(Side.CLIENT)
public class GuiConsole extends GuiScreen implements ITabCompleter
{
    private String historyBuffer;
    private int sentHistoryCursor;
    private TabCompleter tabCompleter;
    protected GuiTextField inputField;
    private String defaultInputFieldText;
    private ArrayList cmds;
    
    public GuiConsole() {
        this.historyBuffer = "";
        this.sentHistoryCursor = -1;
        this.defaultInputFieldText = "";
        this.cmds = new ArrayList();
        this.init();
    }
    
    public GuiConsole(final String defaultText) {
        this.historyBuffer = "";
        this.sentHistoryCursor = -1;
        this.defaultInputFieldText = "";
        this.cmds = new ArrayList();
        this.defaultInputFieldText = defaultText;
        this.init();
    }
    
    void init() {
        this.cmds.clear();
        final CommandManager commandManager = Kisman.instance.commandManager;
        for (final Command c : CommandManager.commands) {
            this.cmds.add(c.getCommand() + " - " + c.getDescription());
        }
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.sentHistoryCursor = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
        (this.inputField = new GuiTextField(0, this.fontRenderer, 4, this.height - 12, this.width - 4, 12)).setMaxStringLength(500);
        this.inputField.setEnableBackgroundDrawing(false);
        this.inputField.setFocused(true);
        this.inputField.setText(this.defaultInputFieldText);
        this.inputField.setCanLoseFocus(false);
        this.tabCompleter = new ChatTabCompleter(this.inputField);
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        this.mc.ingameGUI.getChatGUI().resetScroll();
    }
    
    public void updateScreen() {
        this.inputField.updateCursorCounter();
        this.inputField.setTextColor(new Color(255, 0, 0, 255).getRGB());
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        this.tabCompleter.resetRequested();
        if (keyCode == 15) {
            this.tabCompleter.complete();
        }
        else {
            this.tabCompleter.resetDidComplete();
        }
        if (keyCode == 1) {
            this.mc.displayGuiScreen((GuiScreen)null);
        }
        else if (keyCode != 28 && keyCode != 156) {
            if (keyCode == 200) {
                this.getSentHistory(-1);
            }
            else if (keyCode == 208) {
                this.getSentHistory(1);
            }
            else if (keyCode == 201) {
                this.mc.ingameGUI.getChatGUI().scroll(this.mc.ingameGUI.getChatGUI().getLineCount() - 1);
            }
            else if (keyCode == 209) {
                this.mc.ingameGUI.getChatGUI().scroll(-this.mc.ingameGUI.getChatGUI().getLineCount() + 1);
            }
            else {
                this.inputField.textboxKeyTyped(typedChar, keyCode);
            }
        }
        else {
            String s = this.inputField.getText().trim();
            if (!s.isEmpty()) {
                Kisman.instance.commandManager.runCommands("." + s);
                if (s.startsWith("login")) {
                    final String[] pSplit = s.split(" ");
                    if (pSplit.length >= 3) {
                        s = s.replace(pSplit[2], "*");
                    }
                }
                this.mc.ingameGUI.getChatGUI().addToSentMessages(s);
            }
            this.mc.displayGuiScreen((GuiScreen)null);
        }
    }
    
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int i = Mouse.getEventDWheel();
        if (i != 0) {
            if (i > 1) {
                i = 1;
            }
            if (i < -1) {
                i = -1;
            }
            if (!isShiftKeyDown()) {
                i *= 7;
            }
            this.mc.ingameGUI.getChatGUI().scroll(i);
        }
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        if (mouseButton == 0) {
            final ITextComponent itextcomponent = this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());
            if (itextcomponent != null && this.handleComponentClick(itextcomponent)) {
                return;
            }
        }
        this.inputField.mouseClicked(mouseX, mouseY, mouseButton);
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    protected void setText(final String newChatText, final boolean shouldOverwrite) {
        if (shouldOverwrite) {
            this.inputField.setText(newChatText);
        }
        else {
            this.inputField.writeText(newChatText);
        }
    }
    
    public void getSentHistory(final int msgPos) {
        int i = this.sentHistoryCursor + msgPos;
        final int j = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
        i = MathHelper.clamp(i, 0, j);
        if (i != this.sentHistoryCursor) {
            if (i == j) {
                this.sentHistoryCursor = j;
                this.inputField.setText(this.historyBuffer);
            }
            else {
                if (this.sentHistoryCursor == j) {
                    this.historyBuffer = this.inputField.getText();
                }
                this.inputField.setText((String)this.mc.ingameGUI.getChatGUI().getSentMessages().get(i));
                this.sentHistoryCursor = i;
            }
        }
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final int color = ColorUtil.injectAlpha(ColorUtils.astolfoColors(100, 100), 140).getRGB();
        drawRect(2, this.height - 14, this.width - 2, this.height - 2, color);
        this.inputField.drawTextBox();
        final ITextComponent itextcomponent = this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());
        if (itextcomponent != null && itextcomponent.getStyle().getHoverEvent() != null) {
            this.handleComponentHover(itextcomponent, mouseX, mouseY);
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public boolean doesGuiPauseGame() {
        return false;
    }
    
    public void setCompletions(final String... newCompletions) {
        this.tabCompleter.setCompletions(newCompletions);
    }
    
    @SideOnly(Side.CLIENT)
    public static class ChatTabCompleter extends TabCompleter
    {
        private final Minecraft client;
        
        public ChatTabCompleter(final GuiTextField p_i46749_1_) {
            super(p_i46749_1_, false);
            this.client = Minecraft.getMinecraft();
        }
        
        public void complete() {
            super.complete();
            if (this.completions.size() > 1) {
                final StringBuilder stringbuilder = new StringBuilder();
                for (final String s : this.completions) {
                    if (stringbuilder.length() > 0) {
                        stringbuilder.append(", ");
                    }
                    stringbuilder.append(s);
                }
                this.client.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(stringbuilder.toString()), 1);
            }
        }
        
        @Nullable
        public BlockPos getTargetBlockPos() {
            BlockPos blockpos = null;
            if (this.client.objectMouseOver != null && this.client.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
                blockpos = this.client.objectMouseOver.getBlockPos();
            }
            return blockpos;
        }
    }
}
