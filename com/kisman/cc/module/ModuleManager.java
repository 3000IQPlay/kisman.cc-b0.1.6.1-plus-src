//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module;

import net.minecraftforge.common.*;
import com.kisman.cc.module.combat.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.module.chat.*;
import com.kisman.cc.module.render.*;
import com.kisman.cc.module.movement.*;
import com.kisman.cc.module.player.*;
import com.kisman.cc.module.exploit.*;
import com.kisman.cc.module.misc.*;
import com.kisman.cc.util.customfont.*;
import java.util.stream.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.client.event.*;

public class ModuleManager
{
    public ArrayList<Module> modules;
    
    public ModuleManager() {
        this.modules = new ArrayList<Module>();
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.init();
    }
    
    public void init() {
        this.modules.add((Module)new AimBot());
        this.modules.add((Module)new AntiBow());
        this.modules.add((Module)new AntiTrap());
        this.modules.add((Module)new AutoArmor());
        this.modules.add((Module)new AutoBowExploit());
        this.modules.add((Module)new AutoClicker());
        this.modules.add((Module)new AutoCrystal());
        this.modules.add((Module)new AutoCrystalRewrite());
        this.modules.add((Module)new AutoFirework());
        this.modules.add((Module)new AutoPot());
        this.modules.add((Module)new AutoTotem());
        this.modules.add((Module)new AutoTrap());
        this.modules.add((Module)new BowAimBot());
        this.modules.add((Module)new BowSpam());
        this.modules.add((Module)new Burrow());
        this.modules.add((Module)new BurrowBypass());
        this.modules.add((Module)new CevBreaker());
        this.modules.add((Module)new Criticals());
        this.modules.add((Module)new CrystalFiller());
        this.modules.add((Module)new HoleFiller());
        this.modules.add((Module)new KillAura());
        this.modules.add((Module)new OffHand());
        this.modules.add((Module)new SilentXp());
        this.modules.add((Module)new Surround());
        this.modules.add((Module)new Cape());
        this.modules.add((Module)new ClickGUI());
        this.modules.add((Module)new ClientFont());
        this.modules.add((Module)new ColorModule());
        this.modules.add((Module)new Config());
        this.modules.add((Module)new Console());
        this.modules.add((Module)new CSGOGui());
        this.modules.add((Module)new CustomFont());
        this.modules.add((Module)new DiscordRPC());
        this.modules.add((Module)new Dumper());
        this.modules.add((Module)new HUD());
        this.modules.add((Module)new NewGui());
        this.modules.add((Module)new NotEnoughCoinsModule());
        this.modules.add((Module)new ExampleModule());
        this.modules.add((Module)new ParticleGui());
        this.modules.add((Module)new SandBox());
        this.modules.add((Module)new Test());
        this.modules.add((Module)new AntiSpamBypass());
        this.modules.add((Module)new AutoEZ());
        this.modules.add((Module)new AutoGlobal());
        this.modules.add((Module)new ChatAnimation());
        this.modules.add((Module)new ChatSuffix());
        this.modules.add((Module)new Notification());
        this.modules.add((Module)new Spammer());
        this.modules.add((Module)new TimeStamps());
        this.modules.add((Module)new TotemPopCounter());
        this.modules.add((Module)new TraceTeleport());
        this.modules.add(new Ambience());
        this.modules.add(new Animation());
        this.modules.add(new BlockOutline());
        this.modules.add(new Breadcrumbs());
        this.modules.add(new Charms());
        this.modules.add(new ChinaHat());
        this.modules.add(new CityESP());
        this.modules.add(new CrystalModifier());
        this.modules.add(new CustomFog());
        this.modules.add(new CustomFov());
        this.modules.add(new EntityESP());
        this.modules.add(new FullBright());
        this.modules.add(new HoleESP());
        this.modules.add(new ItemCharms());
        this.modules.add(new KismanESP());
        this.modules.add(new NameTags());
        this.modules.add(new NoPitchLimit());
        this.modules.add(new NoRender());
        this.modules.add(new Particle());
        this.modules.add(new PenisESP());
        this.modules.add(new PlayerModifier());
        this.modules.add(new PopCharms());
        this.modules.add(new PortalESP());
        this.modules.add(new RangeVisualisator());
        this.modules.add(new Reverse());
        this.modules.add(new SkyColor());
        this.modules.add(new SpawnsESP());
        this.modules.add(new Spin());
        this.modules.add(new StorageESP());
        this.modules.add(new SwingAnimation());
        this.modules.add(new Tracers());
        this.modules.add(new Trails());
        this.modules.add(new Trajectories());
        this.modules.add(new TrajectoriesRewrite());
        this.modules.add(new ViewModel());
        this.modules.add(new XRay());
        this.modules.add(new AirJump());
        this.modules.add(new Anchor());
        this.modules.add(new AutoJump());
        this.modules.add(new AutoWalk());
        this.modules.add(new ElytraFly());
        this.modules.add(new FastSwim());
        this.modules.add(new Fly());
        this.modules.add(new HoleTP());
        this.modules.add(new IceSpeed());
        this.modules.add(new Jesus());
        this.modules.add(new NoJumpDelay());
        this.modules.add(new NoRotate());
        this.modules.add(new NoSlow());
        this.modules.add(new NoSlowBypass());
        this.modules.add(new NoSlowSneak());
        this.modules.add(new NoWeb());
        this.modules.add(new Parkour());
        this.modules.add(new ReverseStep());
        this.modules.add(new SafeWalk());
        this.modules.add(new Scaffold());
        this.modules.add(new Speed());
        this.modules.add(new Spider());
        this.modules.add(new Sprint());
        this.modules.add(new Step());
        this.modules.add(new Zoom());
        this.modules.add(new AntiKnokBack());
        this.modules.add(new FastBreak());
        this.modules.add(new FastPlace());
        this.modules.add(new NoInteract());
        this.modules.add(new PacketCancel());
        this.modules.add(new Swing());
        this.modules.add(new TeleportBack());
        this.modules.add(new Velocity());
        this.modules.add((Module)new AntiLogger());
        this.modules.add((Module)new AutoKick());
        this.modules.add((Module)new BowExploit());
        this.modules.add((Module)new BowExploitRewrite());
        this.modules.add((Module)new CactusLeave());
        this.modules.add((Module)new Disabler());
        this.modules.add((Module)new Ghost());
        this.modules.add((Module)new KismansDupe());
        this.modules.add((Module)new MiddleClick());
        this.modules.add((Module)new MultiTask());
        this.modules.add((Module)new NoMiningTrace());
        this.modules.add((Module)new NoSwing());
        this.modules.add((Module)new PacketFly());
        this.modules.add((Module)new PacketMine());
        this.modules.add((Module)new Rubberband());
        this.modules.add((Module)new SilentClose());
        this.modules.add((Module)new SoundCoordLogger());
        this.modules.add((Module)new TickShift());
        this.modules.add((Module)new Timer());
        this.modules.add((Module)new WaterLeave());
        this.modules.add((Module)new WebLeave());
        this.modules.add((Module)new AutoLog());
        this.modules.add((Module)new BurrowCounter());
        this.modules.add((Module)new FakePlayer());
        this.modules.add((Module)new ItemScroller());
        this.modules.add((Module)new MurderFinder());
        this.modules.add((Module)new NameProtect());
        this.modules.add((Module)new PigPOV());
        this.modules.add((Module)new TeamRusherLag());
        this.modules.add((Module)new Tracker());
        this.modules.add((Module)new VisualRange());
        this.modules.add((Module)new WeaknessLog());
        this.modules.add((Module)new XCarry());
    }
    
    public Module getModule(final String name) {
        for (final Module m : this.modules) {
            if (m.getName().equalsIgnoreCase(name)) {
                return m;
            }
        }
        return null;
    }
    
    public ArrayList<Module> getModuleList() {
        return this.modules;
    }
    
    public ArrayList<Module> getModulesInCategory(final Category c) {
        final ArrayList<Module> mods = new ArrayList<Module>();
        for (final Module m : this.modules) {
            if (m.getCategory() == c) {
                mods.add(m);
            }
        }
        return mods;
    }
    
    public ArrayList<Module> getEnabledModules() {
        final ArrayList<Module> enabled = new ArrayList<Module>();
        final ArrayList<Module> list;
        this.modules.stream().forEach(module -> {
            if (module.isToggled()) {
                list.add(module);
            }
            return;
        });
        return enabled;
    }
    
    public ArrayList<Module> getSortModuleList(final boolean reverse) {
        final ArrayList<Module> sorted = new ArrayList<Module>();
        this.getEnabledModules().stream().filter(module -> module.visible).sorted(Comparator.comparing(module -> CustomFontUtil.getStringWidth(module.getName() + " " + module.getDisplayInfo()) * (reverse ? -1 : 1))).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList());
        return sorted;
    }
    
    @SubscribeEvent
    public void onKey(final InputEvent.KeyInputEvent event) {
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        for (final Module m : this.modules) {
            if (m.isToggled()) {
                m.update();
            }
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent event) {
        for (final Module m : this.modules) {
            if (m.isToggled()) {
                m.render();
            }
        }
    }
    
    public void key(final char typedChar, final int key, final Module mod) {
        if (mod.isToggled()) {
            mod.key();
            mod.key(key);
            mod.key(typedChar, key);
        }
    }
}
