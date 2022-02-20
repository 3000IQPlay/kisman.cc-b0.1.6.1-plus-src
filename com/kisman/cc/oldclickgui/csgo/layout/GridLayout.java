//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.layout;

import com.kisman.cc.oldclickgui.csgo.*;
import java.util.*;

public class GridLayout implements ILayoutManager
{
    private static final int DEFAULT_VERTICAL_PADDING = 7;
    private static final int DEFAULT_HORIZONTAL_PADDING = 7;
    private int columns;
    private int verticalPadding;
    private int horizontalPadding;
    
    public GridLayout(final int columns, final int verticalPadding, final int horizontalPadding) {
        this.columns = columns;
        this.verticalPadding = verticalPadding;
        this.horizontalPadding = horizontalPadding;
    }
    
    public GridLayout(final int columns) {
        this(columns, 7, 7);
    }
    
    @Override
    public int[] getOptimalDiemension(final List<AbstractComponent> components, final int maxWidth) {
        final int rows = (int)Math.ceil(components.size() / (double)this.columns);
        final AbstractComponent[][] comps = new AbstractComponent[rows][this.columns];
        for (int i = 0; i < components.size(); ++i) {
            final AbstractComponent component = components.get(i);
            comps[i / this.columns][i % this.columns] = component;
        }
        final int[] rowHeight = new int[rows];
        for (int j = 0; j < rows; ++j) {
            final AbstractComponent[] comp = comps[j];
            int currHeight = -1;
            for (final AbstractComponent abstractComponent : comp) {
                if (abstractComponent != null && currHeight < abstractComponent.getHeight() + this.horizontalPadding * 2) {
                    currHeight = abstractComponent.getHeight() + this.horizontalPadding * 2;
                }
            }
            rowHeight[j] = currHeight;
        }
        final int[] columnWidth = new int[this.columns];
        for (int k = 0; k < this.columns; ++k) {
            int currWidth = -1;
            for (int l = 0; l < rows; ++l) {
                final AbstractComponent abstractComponent2 = comps[l][k];
                if (abstractComponent2 != null && abstractComponent2.getWidth() + this.verticalPadding * 2 > currWidth) {
                    currWidth = abstractComponent2.getWidth() + this.verticalPadding * 2;
                }
            }
            columnWidth[k] = currWidth;
        }
        int width = this.verticalPadding;
        int height = this.horizontalPadding;
        for (final int m : rowHeight) {
            height += m;
        }
        for (final int m : columnWidth) {
            width += m;
        }
        return new int[] { width, height };
    }
    
    @Override
    public Layout buildLayout(final List<AbstractComponent> components, final int width, final int height) {
        final int rows = (int)Math.ceil(components.size() / (double)this.columns);
        final AbstractComponent[][] comps = new AbstractComponent[this.columns][rows];
        for (int i = 0; i < components.size(); ++i) {
            final AbstractComponent component = components.get(i);
            comps[i % this.columns][i / this.columns] = component;
        }
        final int[] columnWidth = new int[this.columns];
        for (int j = 0; j < comps.length; ++j) {
            final AbstractComponent[] comp = comps[j];
            int currWidth = -1;
            for (final AbstractComponent abstractComponent : comp) {
                if (abstractComponent != null && currWidth < abstractComponent.getWidth() + this.verticalPadding * 2) {
                    currWidth = abstractComponent.getWidth() + this.verticalPadding * 2;
                }
            }
            columnWidth[j] = currWidth;
        }
        int maxWidth = this.verticalPadding * 3;
        for (final int k : columnWidth) {
            maxWidth += k;
        }
        int needed = width - maxWidth;
        int avg = needed / this.columns;
        int count = 0;
        for (final int l : columnWidth) {
            if (l < avg) {
                ++count;
            }
        }
        if (count != 0) {
            avg = needed / count;
            for (int i2 = 0; i2 < columnWidth.length; ++i2) {
                final int m = columnWidth[i2];
                if (m < avg) {
                    columnWidth[i2] = avg;
                }
            }
        }
        maxWidth = this.verticalPadding * 3;
        for (final int k : columnWidth) {
            maxWidth += k;
        }
        needed = width - maxWidth;
        avg = (int)Math.floor(needed / (float)this.columns);
        for (int i3 = 0; i3 < columnWidth.length; ++i3) {
            final int[] array5 = columnWidth;
            final int n5 = i3;
            array5[n5] += avg;
        }
        maxWidth = this.verticalPadding * 3;
        for (final int k : columnWidth) {
            maxWidth += k;
        }
        needed = width - maxWidth;
        final int[] array7 = columnWidth;
        final int n7 = 0;
        array7[n7] += needed;
        final int[] rowHeight = new int[rows];
        for (int i4 = 0; i4 < rows; ++i4) {
            int maxHeight = -1;
            for (int j2 = 0; j2 < this.columns; ++j2) {
                final AbstractComponent abstractComponent2 = comps[j2][i4];
                if (abstractComponent2 != null && abstractComponent2.getHeight() > maxHeight) {
                    maxHeight = abstractComponent2.getHeight();
                }
            }
            rowHeight[i4] = maxHeight;
        }
        final HashMap<AbstractComponent, int[]> layout = new HashMap<AbstractComponent, int[]>();
        int currX = this.verticalPadding;
        for (int i3 = 0; i3 < this.columns; ++i3) {
            int currY = this.horizontalPadding;
            for (int j3 = 0; j3 < rows; ++j3) {
                layout.put(comps[i3][j3], new int[] { currX, currY });
                currY += rowHeight[j3] + this.horizontalPadding;
            }
            currX += columnWidth[i3] + this.verticalPadding;
        }
        int maxHeight2 = -1;
        for (final Map.Entry<AbstractComponent, int[]> abstractComponentEntry : layout.entrySet()) {
            if (abstractComponentEntry.getKey() != null && maxHeight2 < abstractComponentEntry.getValue()[1] + abstractComponentEntry.getKey().getHeight()) {
                maxHeight2 = abstractComponentEntry.getValue()[1] + abstractComponentEntry.getKey().getHeight();
            }
        }
        maxHeight2 += this.horizontalPadding;
        int maxWidth2 = -1;
        for (final Map.Entry<AbstractComponent, int[]> abstractComponentEntry2 : layout.entrySet()) {
            if (abstractComponentEntry2.getKey() != null && maxWidth2 < abstractComponentEntry2.getValue()[0] + abstractComponentEntry2.getKey().getWidth()) {
                maxWidth2 = abstractComponentEntry2.getValue()[0] + abstractComponentEntry2.getKey().getWidth();
            }
        }
        return new Layout(layout, maxHeight2, maxWidth2);
    }
    
    public int getVerticalPadding() {
        return this.verticalPadding;
    }
    
    public void setVerticalPadding(final int verticalPadding) {
        this.verticalPadding = verticalPadding;
    }
    
    public int getHorizontalPadding() {
        return this.horizontalPadding;
    }
    
    public void setHorizontalPadding(final int horizontalPadding) {
        this.horizontalPadding = horizontalPadding;
    }
}
