//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc;

import me.zero.alpine.bus.*;
import net.minecraft.client.*;
import com.kisman.cc.friend.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.csgo.*;
import com.kisman.cc.console.*;
import com.kisman.cc.oldclickgui.*;
import com.kisman.cc.hud.hudgui.*;
import com.kisman.cc.newclickgui.*;
import com.kisman.cc.oldclickgui.vega.*;
import com.kisman.cc.util.customfont.*;
import kisman.pasta.salhack.util.customfont.*;
import com.kisman.cc.command.*;
import com.kisman.cc.event.*;
import com.kisman.cc.particle.*;
import com.kisman.cc.util.*;
import com.kisman.cc.util.shaders.*;
import com.kisman.cc.oldclickgui.mainmenu.sandbox.*;
import com.kisman.cc.util.manager.*;
import com.kisman.cc.util.hwid.*;
import com.kisman.cc.dumper.*;
import org.lwjgl.opengl.*;
import net.minecraftforge.common.*;
import java.awt.*;
import com.kisman.cc.file.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import com.kisman.cc.module.*;
import net.minecraft.util.text.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.hud.hudmodule.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.kisman.cc.module.client.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.io.*;
import com.kisman.cc.app.*;
import org.apache.logging.log4j.*;

public class Kisman
{
    public static final String NAME = "kisman.cc+";
    public static final String MODID = "kisman";
    public static final String VERSION = "b0.1.6.1";
    public static final String HWIDS_LIST = "https://pastebin.com/raw/yM7s0G4u";
    public static final String HWID_LOGS;
    public static final String fileName = "kisman.cc/";
    public static final String moduleName = "Modules/";
    public static final String mainName = "Main/";
    public static final String miscName = "Misc/";
    public static final String sandboxName = "SandBox/";
    public static final String pluginName = "Plugins/";
    public static float TICK_TIMER;
    public static Kisman instance;
    public static final EventManager EVENT_BUS;
    public static final Logger LOGGER;
    public boolean init;
    private Minecraft mc;
    public ModuleManager moduleManager;
    public FriendManager friendManager;
    public HudModuleManager hudModuleManager;
    public SettingsManager settingsManager;
    public ClickGui clickGui;
    public ClickGuiNew clickGuiNew;
    public BlockGui blockGui;
    public GuiConsole guiConsole;
    public ColorPicker colorPicker;
    public ColorUtil colorUtil;
    public HudGui hudGui;
    public NewGui newGui;
    public Gui gui;
    public CustomFontRenderer customFontRenderer;
    public CustomFontRenderer customFontRenderer1;
    public FontManager fontManager;
    public CommandManager commandManager;
    public RPC discord;
    public RotationUtils rotationUtils;
    public EventProcessor eventProcessor;
    public ParticleSystem particleSystem;
    public ServerManager serverManager;
    public Shaders shaders;
    public SandBoxShaders sandBoxShaders;
    public Managers managers;
    public Verificator d1;
    public MainDumper d2;
    
    public Kisman() {
        this.init = false;
        Kisman.instance = this;
    }
    
    public void init() {
        Display.setTitle("kisman.cc+ b0.1.6.1 CRACKED BY NOTROCKY");
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.mc = Minecraft.getMinecraft();
        (this.managers = new Managers()).init();
        this.friendManager = new FriendManager();
        this.settingsManager = new SettingsManager();
        this.moduleManager = new ModuleManager();
        this.hudModuleManager = new HudModuleManager();
        this.clickGui = new ClickGui();
        this.clickGuiNew = new ClickGuiNew();
        this.blockGui = new BlockGui();
        this.guiConsole = new GuiConsole();
        this.colorPicker = new ColorPicker();
        this.colorUtil = new ColorUtil();
        this.hudGui = new HudGui();
        this.newGui = new NewGui();
        this.gui = new Gui();
        this.customFontRenderer = new CustomFontRenderer(new Font("Verdana", 0, 18), true, true);
        this.customFontRenderer1 = new CustomFontRenderer(new Font("Verdana", 0, 15), true, true);
        this.fontManager = new FontManager();
        this.commandManager = new CommandManager();
        this.discord = new RPC();
        this.rotationUtils = new RotationUtils();
        this.eventProcessor = new EventProcessor();
        this.particleSystem = new ParticleSystem(100, true, 150);
        this.serverManager = new ServerManager();
        this.shaders = new Shaders();
        this.sandBoxShaders = new SandBoxShaders();
        this.d1 = new Verificator();
        this.d2 = new MainDumper();
        LoadConfig.init();
        this.init = true;
    }
    
    @SubscribeEvent
    public void key(final InputEvent.KeyInputEvent e) {
        if (this.mc.world == null || this.mc.player == null) {
            return;
        }
        try {
            if (Keyboard.isCreated() && Keyboard.getEventKeyState()) {
                final int keyCode = Keyboard.getEventKey();
                if (keyCode <= 0) {
                    return;
                }
                for (final Module m : this.moduleManager.modules) {
                    if (m.getKey() == keyCode && keyCode > 0) {
                        m.toggle();
                        if (!this.moduleManager.getModule("Notification").isToggled()) {
                            continue;
                        }
                        ChatUtils.message((Object)(TextFormatting.GRAY + "Module " + (m.isToggled() ? TextFormatting.GREEN : TextFormatting.RED) + m.getName() + TextFormatting.GRAY + " has been " + (m.isToggled() ? "enabled" : "disabled") + "!"));
                    }
                }
                for (final HudModule i : this.hudModuleManager.modules) {
                    if (i.getKey() == keyCode && keyCode > 0) {
                        i.toggle();
                    }
                }
            }
        }
        catch (Exception q) {
            q.printStackTrace();
        }
    }
    
    public static String getName() {
        if (Kisman.instance.init) {
            final String valString = Config.instance.nameMode.getValString();
            switch (valString) {
                case "kismancc": {
                    return "kisman.cc+";
                }
                case "lavahack": {
                    return "LavaHack";
                }
                case "custom": {
                    return Config.instance.customName.getValString();
                }
            }
        }
        return "kisman.cc+";
    }
    
    public static String getVersion() {
        return "b0.1.6.1";
    }
    
    public static void initDirs() throws IOException {
        if (!Files.exists(Paths.get("kisman.cc/", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("kisman.cc/", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
            Kisman.LOGGER.info("Root dir created");
        }
        if (!Files.exists(Paths.get("kisman.cc/Modules/", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("kisman.cc/Modules/", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
            Kisman.LOGGER.info("Module dir created");
        }
        if (!Files.exists(Paths.get("kisman.cc/Main/", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("kisman.cc/Main/", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
            Kisman.LOGGER.info("Main dir created");
        }
        if (!Files.exists(Paths.get("kisman.cc/Misc/", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("kisman.cc/Misc/", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
            Kisman.LOGGER.info("Misc dir created");
        }
        if (!Files.exists(Paths.get("kisman.cc/SandBox/", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("kisman.cc/SandBox/", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
            Kisman.LOGGER.info("Sandboxes dir created");
        }
        if (!Files.exists(Paths.get("kisman.cc/Plugins/", new String[0]), new LinkOption[0])) {
            Files.createDirectories(Paths.get("kisman.cc/Plugins/", new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
            Kisman.LOGGER.info("Plugins dir created");
        }
    }
    
    static {
        HWID_LOGS = "https://" + MainWindow.d1 + "/" + MainWindow.d2 + "/905166844967673928/LgJFUe6o45hBx7e1xE5OxqwD7M5DdzIsg3s9-dd6d5jDJ6k7KaUf1Vettd5mf9LQz8aW";
        Kisman.TICK_TIMER = 1.0f;
        EVENT_BUS = new EventManager();
        LOGGER = LogManager.getLogger("kisman.cc+");
    }
}
