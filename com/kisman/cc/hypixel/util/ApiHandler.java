//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hypixel.util;

import com.kisman.cc.command.commands.*;
import com.kisman.cc.*;
import com.google.gson.*;
import java.time.*;
import java.nio.charset.*;
import java.util.*;

public class ApiHandler
{
    private static final ArrayList<String> filter;
    private static final ArrayList<String> nameFilter;
    
    public static void getBins(final HashMap<String, Double> dataset) {
        boolean skip = false;
        Flip.initialDataset.clear();
        try {
            final JsonObject binJson = Utils.getJson("https://moulberry.codes/lowestbin.json").getAsJsonObject();
            for (final Map.Entry<String, JsonElement> auction : binJson.entrySet()) {
                skip = false;
                for (final String name : ApiHandler.nameFilter) {
                    if (auction.getKey().contains(name)) {
                        skip = true;
                    }
                }
                if (!skip) {
                    dataset.put(auction.getKey(), auction.getValue().getAsDouble());
                }
            }
        }
        catch (Exception e) {
            Kisman.LOGGER.error(e.getMessage(), (Throwable)e);
        }
        Flip.initialDataset.putAll(dataset);
    }
    
    public static void getAuctionAverages(final LinkedHashMap<String, Double> dataset) {
        Flip.secondDataset.clear();
        try {
            final JsonObject items = Objects.requireNonNull(Utils.getJson("https://moulberry.codes/auction_averages/3day.json")).getAsJsonObject();
            for (final Map.Entry<String, JsonElement> jsonElement : items.entrySet()) {
                if (jsonElement.getValue().getAsJsonObject().has("clean_price")) {
                    dataset.put(jsonElement.getKey(), jsonElement.getValue().getAsJsonObject().get("clean_price").getAsDouble());
                }
                if (jsonElement.getValue().getAsJsonObject().has("price") && !jsonElement.getValue().getAsJsonObject().has("clean_price")) {
                    dataset.put(jsonElement.getKey(), jsonElement.getValue().getAsJsonObject().get("price").getAsDouble());
                }
            }
        }
        catch (Exception e) {
            Kisman.LOGGER.error(e.getMessage(), (Throwable)e);
        }
        Flip.secondDataset.putAll(dataset);
        for (final Map.Entry<String, Double> entry : Flip.secondDataset.entrySet()) {
            if (Flip.initialDataset.containsKey(entry.getKey()) && Flip.initialDataset.get(entry.getKey()) > entry.getValue()) {
                Flip.initialDataset.remove(entry.getKey());
            }
        }
    }
    
    public static void itemIdsToNames(final LinkedHashMap<String, Double> initialDataset) {
        Flip.namedDataset.clear();
        final LinkedHashMap<String, Double> datasettemp = new LinkedHashMap<String, Double>();
        datasettemp.putAll((Map<?, ?>)initialDataset);
        initialDataset.clear();
        try {
            final JsonArray itemArray = Objects.requireNonNull(Utils.getJson("https://api.hypixel.net/resources/skyblock/items")).getAsJsonObject().get("items").getAsJsonArray();
            for (final Map.Entry<String, Double> auction : datasettemp.entrySet()) {
                final String key = auction.getKey();
                final Double value = auction.getValue();
                for (final JsonElement item : itemArray) {
                    if (item.getAsJsonObject().get("id").getAsString().equals(key) && item.getAsJsonObject().has("category") && !ApiHandler.filter.contains(item.getAsJsonObject().get("category").getAsString())) {
                        final String name = item.getAsJsonObject().get("name").getAsString();
                        initialDataset.put(name, value);
                    }
                }
            }
            Flip.secondDataset.putAll(initialDataset);
            final LinkedHashMap<String, Double> unsortedMap = (LinkedHashMap<String, Double>)Flip.secondDataset;
            final LinkedHashMap<String, Double> sortedMap = new LinkedHashMap<String, Double>();
            final Double n;
            unsortedMap.entrySet().stream().sorted((Comparator<? super Object>)Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> n = sortedMap.put(x.getKey(), Double.valueOf(Math.round((double)x.getValue()))));
            Flip.secondDataset = sortedMap;
        }
        catch (Exception e) {
            Kisman.LOGGER.error(e.getMessage(), (Throwable)e);
        }
    }
    
    private static String getUuid(final String name) {
        try {
            return Objects.requireNonNull(Utils.getJson("https://api.mojang.com/users/profiles/minecraft/" + name)).getAsJsonObject().get("id").getAsString();
        }
        catch (Exception e) {
            Kisman.LOGGER.error(e.getMessage(), (Throwable)e);
            return null;
        }
    }
    
    public static void updatePurseCoins(final String key, final String name) {
        final String uuid = getUuid(name);
        try {
            final JsonArray profilesArray = Objects.requireNonNull(Utils.getJson("https://api.hypixel.net/skyblock/profiles?key=" + key + "&uuid=" + uuid)).getAsJsonObject().get("profiles").getAsJsonArray();
            int profileIndex = 0;
            Instant lastProfileSave = Instant.EPOCH;
            for (int i = 0; i < profilesArray.size(); ++i) {
                Instant lastSaveLoop;
                try {
                    lastSaveLoop = Instant.ofEpochMilli(profilesArray.get(i).getAsJsonObject().get("members").getAsJsonObject().get(uuid).getAsJsonObject().get("last_save").getAsLong());
                }
                catch (Exception e2) {
                    continue;
                }
                if (lastSaveLoop.isAfter(lastProfileSave)) {
                    profileIndex = i;
                    lastProfileSave = lastSaveLoop;
                }
            }
            Flip.purse = profilesArray.get(profileIndex).getAsJsonObject().get("members").getAsJsonObject().get(uuid).getAsJsonObject().get("coin_purse").getAsDouble();
        }
        catch (Exception e) {
            Kisman.LOGGER.error(e.getMessage(), (Throwable)e);
        }
    }
    
    public static void getFlips(final LinkedHashMap<String, Double> dataset, final int i, final ArrayList<String> commands) {
        Flip.commands.clear();
        try {
            final JsonArray auctionsArray = Objects.requireNonNull(Utils.getJson("https://api.hypixel.net/skyblock/auctions?page=" + i)).getAsJsonObject().get("auctions").getAsJsonArray();
            for (final JsonElement item : auctionsArray) {
                for (final Map.Entry<String, Double> entry : dataset.entrySet()) {
                    if (item.getAsJsonObject().get("item_name").getAsString().contains(entry.getKey()) && item.getAsJsonObject().has("bin") && item.getAsJsonObject().get("bin").getAsString().contains("true") && item.getAsJsonObject().has("starting_bid") && item.getAsJsonObject().get("starting_bid").getAsDouble() < entry.getValue() && item.getAsJsonObject().get("starting_bid").getAsDouble() <= Flip.purse) {
                        final String rawName = item.getAsJsonObject().get("item_name").getAsString();
                        final String name = new String(rawName.getBytes(), StandardCharsets.UTF_8);
                        if (entry.getValue() - item.getAsJsonObject().get("starting_bid").getAsLong() <= 50000.0) {
                            continue;
                        }
                        Flip.namedDataset.put(name, entry.getValue() - item.getAsJsonObject().get("starting_bid").getAsLong());
                        if (!item.getAsJsonObject().has("uuid")) {
                            continue;
                        }
                        commands.add("/viewauction " + item.getAsJsonObject().get("uuid").getAsString());
                    }
                }
            }
        }
        catch (Exception e) {
            Kisman.LOGGER.error(e.getMessage(), (Throwable)e);
        }
        Flip.commands.addAll(commands);
    }
    
    public static int getNumberOfPages() {
        int pages = 0;
        try {
            pages = Objects.requireNonNull(Utils.getJson("https://api.hypixel.net/skyblock/auctions?page=0")).getAsJsonObject().get("totalPages").getAsInt();
        }
        catch (Exception e) {
            Kisman.LOGGER.error(e.getMessage(), (Throwable)e);
        }
        return pages;
    }
    
    static {
        filter = new ArrayList<String>(Arrays.asList("TRAVEL_SCROLL", "COSMETIC", "DUNGEON_PASS", "ARROW_POISON", "PET_ITEM"));
        nameFilter = new ArrayList<String>(Arrays.asList("STARRED", "SALMON", "PERFECT", "BEASTMASTER", "MASTER_SKULL"));
    }
}
