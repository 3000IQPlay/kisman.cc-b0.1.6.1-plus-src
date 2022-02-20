//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.settings;

import com.kisman.cc.oldclickgui.*;
import com.kisman.cc.util.*;
import net.minecraft.entity.*;
import com.kisman.cc.module.*;
import com.kisman.cc.hud.hudmodule.*;
import net.minecraft.item.*;
import com.kisman.cc.oldclickgui.csgo.components.*;
import java.util.*;
import java.util.function.*;
import java.awt.*;

public class Setting
{
    private Supplier<Boolean> visibleSuppliner;
    private ColorPicker colorPicker;
    private Colour colour;
    private Entity entity;
    private int index;
    private int color;
    private int key;
    private int selected;
    private String name;
    private Module parent;
    private Setting setparent;
    private HudModule hudParent;
    private String mode;
    private String string;
    private String title;
    private String sval;
    private String dString;
    private ArrayList<String> options;
    private Enum optionEnum;
    private Enum svalEnum;
    private boolean bval;
    private boolean rainbow;
    private boolean syns;
    private boolean hud;
    private boolean opening;
    private boolean onlyOneWord;
    private boolean onlyNumbers;
    private boolean minus;
    private boolean enumCombo;
    private boolean visible;
    private double dval;
    private double min;
    private double max;
    private float[] colorHSB;
    private ItemStack[] items;
    private int r;
    private int g;
    private int b;
    private int a;
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private float red;
    private float green;
    private float blue;
    private float alpha;
    private boolean onlyint;
    private Slider.NumberType numberType;
    
    public Setting(final String name, final Module parent, final int key) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.key = key;
        this.mode = "Bind";
    }
    
    public Setting(final String name, final Module parent, final Setting setparent, final String title) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.setparent = setparent;
        this.title = title;
        this.mode = "CategoryLine";
    }
    
    public Setting(final String name, final Module parent, final String title, final boolean open) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.title = title;
        this.opening = open;
        this.mode = "Category";
    }
    
    public Setting(final String name, final Module parent, final String sval, final String dString, final boolean opening) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.sval = sval;
        this.dString = dString;
        this.opening = opening;
        this.onlyOneWord = false;
        this.minus = true;
        this.onlyNumbers = false;
        this.mode = "String";
    }
    
    public Setting(final String name, final Module parent, final String sval, final String dString, final boolean opening, final boolean onlyOneWord) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.sval = sval;
        this.dString = dString;
        this.opening = opening;
        this.onlyOneWord = onlyOneWord;
        this.minus = true;
        this.onlyNumbers = false;
        this.mode = "String";
    }
    
    public Setting(final String name, final Module parent, final String gays, final String lgbtq) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.title = gays;
        this.sval = lgbtq;
        this.mode = "yep";
    }
    
    public Setting(final String name, final Module parent, final String title) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.title = title;
        this.parent = parent;
        this.mode = "Line";
    }
    
    public Setting(final String name, final Module parent, final String sval, final ArrayList<String> options) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.sval = sval;
        this.svalEnum = null;
        this.options = options;
        this.optionEnum = null;
        this.mode = "Combo";
    }
    
    public Setting(final String name, final Module parent, final String sval, final List<String> options) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.sval = sval;
        this.svalEnum = null;
        this.options = new ArrayList<String>(options);
        this.optionEnum = null;
        this.mode = "Combo";
    }
    
    public Setting(final String name, final Module parent, final Enum options) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.sval = options.name();
        this.svalEnum = options;
        this.options = null;
        this.optionEnum = options;
        this.enumCombo = true;
        this.mode = "Combo";
    }
    
    public Setting(final String name, final Module parent, final boolean bval) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.bval = bval;
        this.mode = "Check";
    }
    
    public Setting(final String name, final HudModule parent, final boolean bval) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.hudParent = parent;
        this.bval = bval;
        this.mode = "CheckHud";
        this.hud = true;
    }
    
    public Setting(final String name, final Module parent, final float red, final float green, final float blue, final float alpha) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        this.mode = "ExampleColor";
    }
    
    public Setting(final String name, final Module parent, final double dval, final double min, final double max, final Slider.NumberType numberType) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.dval = dval;
        this.min = min;
        this.max = max;
        this.onlyint = numberType.equals((Object)Slider.NumberType.INTEGER);
        this.mode = "Slider";
        this.numberType = numberType;
    }
    
    public Setting(final String name, final Module parent, final double dval, final double min, final double max, final boolean onlyint) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.dval = dval;
        this.min = min;
        this.max = max;
        this.onlyint = onlyint;
        this.mode = "Slider";
        this.numberType = (onlyint ? Slider.NumberType.INTEGER : Slider.NumberType.DECIMAL);
    }
    
    public Setting(final String name, final Module parent, final String title, final float[] colorHSB, final boolean simpleMode) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.title = title;
        this.colorHSB = colorHSB;
        this.mode = (simpleMode ? "ColorPickerSimple" : "ColorPicker");
    }
    
    public Setting(final String name, final Module parent, final String title, final float[] colorHSB) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.title = title;
        this.colorHSB = colorHSB;
        this.mode = "ColorPicker";
    }
    
    public Setting(final String name, final HudModule parent, final String title, final float[] colorHSB) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.hudParent = parent;
        this.title = title;
        this.colorHSB = colorHSB;
        this.mode = "ColorPickerHud";
        this.hud = true;
    }
    
    public Setting(final String name, final HudModule parent, final int x1, final int y1, final int x2, final int y2) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.hudParent = parent;
        this.mode = "DrawHud";
        this.hud = true;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public Setting(final String name, final Module parent, final String title, final Entity entity) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.title = title;
        this.entity = entity;
        this.mode = "Preview";
    }
    
    public Setting(final String name, final Module parent, final String title, final ItemStack[] items) {
        this.visibleSuppliner = (() -> true);
        this.index = 0;
        this.key = 0;
        this.selected = -1;
        this.hud = false;
        this.enumCombo = false;
        this.visible = true;
        this.onlyint = false;
        this.numberType = Slider.NumberType.DECIMAL;
        this.name = name;
        this.parent = parent;
        this.title = title;
        this.items = items;
        this.mode = "Items";
    }
    
    @Override
    public boolean equals(final Object obj) {
        return this.isCombo() && this.sval.equals(obj);
    }
    
    public boolean isVisible() {
        return this.visibleSuppliner.get();
    }
    
    public Setting setVisible(final Supplier<Boolean> suppliner) {
        this.visibleSuppliner = suppliner;
        return this;
    }
    
    public void setVisible(final boolean visible) {
        this.visibleSuppliner = (() -> visible);
    }
    
    public String[] getStringValues() {
        if (!this.enumCombo) {
            return this.options.toArray(new String[this.options.size()]);
        }
        return Arrays.stream(this.optionEnum.getClass().getEnumConstants()).map((Function<? super Enum, ?>)Enum::name).toArray(String[]::new);
    }
    
    public String getStringFromIndex(final int index) {
        if (index != -1) {
            return this.getStringValues()[index];
        }
        return "";
    }
    
    public int getSelectedIndex() {
        final String[] modes = this.getStringValues();
        int object = -1;
        for (int i = 0; i < modes.length; ++i) {
            final String mode = modes[i];
            if (mode.equalsIgnoreCase(this.sval)) {
                object = i;
            }
        }
        return object;
    }
    
    public boolean isOnlyint() {
        return this.onlyint;
    }
    
    public void setOnlyint(final boolean onlyint) {
        this.onlyint = onlyint;
    }
    
    public Slider.NumberType getNumberType() {
        return this.numberType;
    }
    
    public void setNumberType(final Slider.NumberType numberType) {
        this.numberType = numberType;
    }
    
    public Enum getNextModeEnum() {
        if (this.optionEnum != null) {
            final Enum enumVal = this.optionEnum;
            final String[] values = Arrays.stream(enumVal.getClass().getEnumConstants()).map((Function<? super Enum, ?>)Enum::name).toArray(String[]::new);
            this.index = ((this.index + 1 > values.length - 1) ? 0 : (this.index + 1));
            return (Enum)Enum.valueOf(enumVal.getClass(), values[this.index]);
        }
        return null;
    }
    
    public void updateColor(final float red, final float green, final float blue, final float alpha) {
        this.red = red;
        this.green = green;
        this.alpha = alpha;
    }
    
    public boolean isEnumCombo() {
        return this.enumCombo;
    }
    
    public float getRed() {
        return this.red;
    }
    
    public void setRed(final float red) {
        this.red = red;
    }
    
    public float getGreen() {
        return this.green;
    }
    
    public void setGreen(final float green) {
        this.green = green;
    }
    
    public float getBlue() {
        return this.blue;
    }
    
    public void setBlue(final float blue) {
        this.blue = blue;
    }
    
    public float getAlpha() {
        return this.alpha;
    }
    
    public void setAlpha(final float alpha) {
        this.alpha = alpha;
    }
    
    public Enum getDoModeEnum() {
        if (this.optionEnum != null) {
            final Enum enumVal = this.optionEnum;
            final String[] values = Arrays.stream(enumVal.getClass().getEnumConstants()).map((Function<? super Enum, ?>)Enum::name).toArray(String[]::new);
            this.index = ((this.index-- < 0) ? values.length : this.index);
            return (Enum)Enum.valueOf(enumVal.getClass(), values[this.index]);
        }
        return null;
    }
    
    public boolean isOnlyNumbers() {
        return this.onlyNumbers;
    }
    
    public Setting setOnlyNumbers(final boolean onlyNumbers) {
        this.onlyNumbers = onlyNumbers;
        return this;
    }
    
    public boolean isMinus() {
        return this.minus;
    }
    
    public void setMinus(final boolean minus) {
        this.minus = minus;
    }
    
    public ItemStack[] getItems() {
        return this.items;
    }
    
    public void setItems(final ItemStack[] items) {
        this.items = items;
    }
    
    public Entity getEntity() {
        return this.entity;
    }
    
    public int getValInt() {
        return (int)this.dval;
    }
    
    public boolean isOnlyOneWord() {
        return this.onlyOneWord;
    }
    
    public void setOnlyOneWord(final boolean onlyOneWord) {
        this.onlyOneWord = onlyOneWord;
    }
    
    public boolean isSyns() {
        return this.syns;
    }
    
    public void setSyns(final boolean syns) {
        this.syns = syns;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    public Colour getColour() {
        return this.colour;
    }
    
    public void setColour(final Colour colour) {
        this.colour = colour;
    }
    
    public Enum getValEnum() {
        return this.svalEnum;
    }
    
    public void setValEnum(final Enum svalEnum) {
        this.svalEnum = svalEnum;
    }
    
    public Enum getOptionEnum() {
        return this.optionEnum;
    }
    
    public void setOptionEnum(final Enum optionEnum) {
        this.optionEnum = optionEnum;
    }
    
    public Setting getSetparent() {
        return this.setparent;
    }
    
    public void setSetparent(final Setting setparent) {
        this.setparent = setparent;
    }
    
    public String getdString() {
        return this.dString;
    }
    
    public void setdString(final String dString) {
        this.dString = dString;
    }
    
    public boolean isOpening() {
        return this.opening;
    }
    
    public void setOpening(final boolean opening) {
        this.opening = opening;
    }
    
    public int getX1() {
        return this.x1;
    }
    
    public int getY1() {
        return this.y1;
    }
    
    public int getX2() {
        return this.x2;
    }
    
    public int getY2() {
        return this.y2;
    }
    
    public void setX1(final int num) {
        this.x1 = num;
    }
    
    public void setY1(final int num) {
        this.y1 = num;
    }
    
    public void setX2(final int num) {
        this.x2 = num;
    }
    
    public void setY2(final int num) {
        this.y2 = num;
    }
    
    public HudModule getParentHudModule() {
        return this.hudParent;
    }
    
    public boolean isHud() {
        return this.hud;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public void setIndex(final int index) {
        this.index = index;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Module getParentMod() {
        return this.parent;
    }
    
    public String getValString() {
        return this.sval;
    }
    
    public void setValString(final String in) {
        this.sval = in;
    }
    
    public ArrayList<String> getOptions() {
        return this.options;
    }
    
    public boolean getValBoolean() {
        return this.bval;
    }
    
    public void setValBoolean(final boolean in) {
        this.bval = in;
    }
    
    public double getValDouble() {
        if (this.onlyint) {
            this.dval = (int)this.dval;
        }
        return this.dval;
    }
    
    public float getValFloat() {
        if (this.onlyint) {
            this.dval = (int)this.dval;
        }
        return (float)this.dval;
    }
    
    public long getValLong() {
        if (this.onlyint) {
            this.dval = (int)this.dval;
        }
        return (long)this.dval;
    }
    
    public void setValDouble(final double in) {
        this.dval = in;
    }
    
    public double getMin() {
        return this.min;
    }
    
    public double getMax() {
        return this.max;
    }
    
    public int getR() {
        return this.r;
    }
    
    public int getG() {
        return this.g;
    }
    
    public int getB() {
        return this.b;
    }
    
    public int getA() {
        return this.a;
    }
    
    public void setR(final int r) {
        this.r = r;
    }
    
    public void setG(final int g) {
        this.g = g;
    }
    
    public void setB(final int b) {
        this.b = b;
    }
    
    public void setA(final int a) {
        this.a = a;
    }
    
    public boolean isRainbow() {
        return this.rainbow;
    }
    
    public void setRainbow(final boolean rainbow) {
        this.rainbow = rainbow;
    }
    
    public int getColor() {
        return this.color;
    }
    
    public Color getColor(final boolean colorPicker) {
        return new Color(this.getR(), this.getG(), this.getB(), this.getA());
    }
    
    public void setColor(final int color) {
        this.color = color;
    }
    
    public ColorPicker getColorPicker() {
        return this.colorPicker;
    }
    
    public float getColor(final int index) {
        return this.colorHSB[index];
    }
    
    public float[] getColorHSB() {
        return this.colorHSB;
    }
    
    public void setColor(final float color, final int index) {
        this.colorHSB[index] = color;
    }
    
    public void setColor(final float[] color) {
        this.colorHSB = color;
    }
    
    public void setColorPicker(final ColorPicker colorPicker) {
        this.colorPicker = colorPicker;
    }
    
    public boolean isItems() {
        return this.mode.equalsIgnoreCase("Items");
    }
    
    public boolean isPreview() {
        return this.mode.equalsIgnoreCase("Preview");
    }
    
    public boolean isBind() {
        return this.mode.equalsIgnoreCase("Bind");
    }
    
    public boolean isCategory() {
        return this.mode.equalsIgnoreCase("Category");
    }
    
    public boolean isString() {
        return this.mode.equalsIgnoreCase("String");
    }
    
    public boolean isVoid() {
        return this.mode.equalsIgnoreCase("Void");
    }
    
    public boolean isCombo() {
        return this.mode.equalsIgnoreCase("Combo");
    }
    
    public boolean isCheck() {
        return this.mode.equalsIgnoreCase("Check");
    }
    
    public boolean isCheckHud() {
        return this.mode.equalsIgnoreCase("CheckHud");
    }
    
    public boolean isSlider() {
        return this.mode.equalsIgnoreCase("Slider");
    }
    
    public boolean isLine() {
        return this.mode.equalsIgnoreCase("Line");
    }
    
    public boolean isCategoryLine() {
        return this.mode.equalsIgnoreCase("CategoryLine");
    }
    
    public boolean isColorPicker() {
        return this.mode.equalsIgnoreCase("ColorPicker");
    }
    
    public boolean isColorPickerSimple() {
        return this.mode.equalsIgnoreCase("ColorPickerSimple");
    }
    
    public boolean isColorPickerHud() {
        return this.mode.equalsIgnoreCase("ColorPickerHud");
    }
    
    public boolean isDrawHud() {
        return this.mode.equalsIgnoreCase("DrawHud");
    }
    
    public boolean isExampleColor() {
        return this.mode.equalsIgnoreCase("ExampleColor");
    }
    
    public boolean onlyInt() {
        return this.onlyint;
    }
}
