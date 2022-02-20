//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command.commands;

import com.kisman.cc.command.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.text.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.hypixel.util.*;
import java.util.*;
import net.minecraft.client.*;
import java.util.concurrent.*;

public class Flip extends Command
{
    public static LinkedHashMap<String, Double> initialDataset;
    public static LinkedHashMap<String, Double> secondDataset;
    public static LinkedHashMap<String, Double> namedDataset;
    public static LinkedHashMap<String, Double> avgDataset;
    public static LinkedHashMap<String, Integer> demandDataset;
    public static LinkedHashMap<Integer, Long> updatedDataset;
    public static ArrayList<String> ignoredUUIDs;
    public static double purse;
    public static ArrayList<String> commands;
    public static ArrayList<String> rawNames;
    public static ArrayList<Double> percentageProfit;
    public static ScheduledExecutorService scheduledExecutorService;
    private static int auctionPages;
    private static int cycle;
    private static Timer timer;
    
    public Flip() {
        super("Flip");
    }
    
    public void runCommand(final String s, final String[] args) {
        if (ConfigHandler.hasKey("general", "Flip")) {
            if (ConfigHandler.getString("general", "Flip").equals("true")) {
                ConfigHandler.writeConfig("general", "Flip", "false");
            }
            else if (ConfigHandler.getString("general", "Flip").equals("false")) {
                ConfigHandler.writeConfig("general", "Flip", "true");
            }
        }
        else {
            ConfigHandler.writeConfig("general", "Flip", "true");
        }
        flip((EntityPlayer)Flip.mc.player);
    }
    
    public String getDescription() {
        return "";
    }
    
    public String getSyntax() {
        return "flip";
    }
    
    public static void flip(final EntityPlayer sender) {
        Flip.timer.cancel();
        Flip.timer.purge();
        Flip.timer = new Timer();
        if (ConfigHandler.getString("general", "Flip").equals("true")) {
            ChatUtils.message((Object)(TextFormatting.GRAY + "[" + TextFormatting.GOLD + "NEC for 1.12.2 by _kisman_" + TextFormatting.GRAY + "]" + TextFormatting.GREEN + " Flipper alerts enabled"));
            ApiHandler.getBins(Flip.initialDataset);
            ApiHandler.itemIdsToNames(Flip.initialDataset);
            Flip.timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    Flip.auctionPages = ApiHandler.getNumberOfPages() - 1;
                    if (Flip.cycle == Flip.auctionPages) {
                        Flip.cycle = 0;
                    }
                    try {
                        ApiHandler.updatePurseCoins(ConfigHandler.getString("general", "ApiKey"), Flip.mc.player.getName());
                    }
                    catch (Exception e) {
                        ChatUtils.error((Object)(TextFormatting.GRAY + "[" + TextFormatting.GOLD + "NEC for 1.12.2 by _kisman_" + TextFormatting.GRAY + "]Could not load purse."));
                    }
                    final String name = sender.getName();
                    final String id = ConfigHandler.getString("general", "APIKey");
                    try {
                        ApiHandler.updatePurseCoins(id, name);
                    }
                    catch (Exception e2) {
                        ChatUtils.error((Object)(TextFormatting.GRAY + "[" + TextFormatting.GOLD + "NEC for 1.12.2 by _kisman_" + TextFormatting.GRAY + "]" + TextFormatting.RED + "Could not load purse."));
                    }
                    ApiHandler.getFlips(Flip.secondDataset, Flip.cycle, Flip.commands);
                    if (Flip.namedDataset.size() > 0) {
                        Flip.purse = (double)Math.round(Flip.purse);
                        int count = 0;
                        final int demand = Flip.demandDataset.getOrDefault(Flip.rawNames.get(count), 0);
                        for (final Map.Entry<String, Double> entry : Flip.namedDataset.entrySet()) {
                            final long profit = Math.abs(entry.getValue().longValue());
                            ChatUtils.complete((Object)(TextFormatting.GRAY + "[" + TextFormatting.GOLD + "NEC for 1.12.2 by _kisman_" + TextFormatting.GRAY + "] " + TextFormatting.YELLOW + entry.getKey() + " " + ((profit > 200000L || Flip.purse / 5.0 < 100000.0) ? TextFormatting.GREEN : ((profit > 100000L || Flip.purse / 5.0 < 200000.0) ? TextFormatting.GOLD : TextFormatting.YELLOW)) + "+$" + Utils.formatValue(profit) + " " + TextFormatting.GOLD + "PP: " + TextFormatting.GREEN + Flip.percentageProfit.get(count).intValue() + "% " + TextFormatting.GOLD + ((demand == 0) ? ("Sales: " + TextFormatting.GREEN + demand + "/day") : "")));
                            ++count;
                        }
                    }
                    Flip.namedDataset.clear();
                    Flip.commands.clear();
                    Flip.rawNames.clear();
                    Flip.percentageProfit.clear();
                    Flip.cycle++;
                }
            }, 40L, 40L);
            Flip.timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Flip.auctionPages = ApiHandler.getNumberOfPages() - 1;
                    try {
                        ApiHandler.getBins(Flip.initialDataset);
                        ApiHandler.itemIdsToNames(Flip.initialDataset);
                    }
                    catch (Exception e) {
                        ChatUtils.error((Object)(TextFormatting.GRAY + "[" + TextFormatting.GOLD + "NEC for 1.12.2 by _kisman_" + TextFormatting.GRAY + "]" + TextFormatting.RED + " Could not load BINs."));
                    }
                }
            }, 60000L, 60000L);
        }
        else {
            ChatUtils.message((Object)(TextFormatting.GRAY + "[" + TextFormatting.GOLD + "NEC for 1.12.2 by _kisman_" + TextFormatting.GRAY + "]" + TextFormatting.RED + " Flipper alerts disabled"));
            Flip.timer.cancel();
            Flip.timer.purge();
            Flip.timer = new Timer();
        }
    }
    
    static {
        Flip.initialDataset = new LinkedHashMap<String, Double>();
        Flip.secondDataset = new LinkedHashMap<String, Double>();
        Flip.namedDataset = new LinkedHashMap<String, Double>();
        Flip.avgDataset = new LinkedHashMap<String, Double>();
        Flip.demandDataset = new LinkedHashMap<String, Integer>();
        Flip.updatedDataset = new LinkedHashMap<Integer, Long>();
        Flip.ignoredUUIDs = new ArrayList<String>();
        Flip.commands = new ArrayList<String>();
        Flip.rawNames = new ArrayList<String>();
        Flip.percentageProfit = new ArrayList<Double>();
        Flip.scheduledExecutorService = Executors.newScheduledThreadPool(ConfigHandler.threads);
        Flip.auctionPages = 0;
        Flip.cycle = 0;
        Flip.timer = new Timer();
    }
}
