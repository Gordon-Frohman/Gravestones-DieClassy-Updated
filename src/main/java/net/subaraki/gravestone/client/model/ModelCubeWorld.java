
package net.subaraki.gravestone.client.model;

import java.io.*;
import java.util.*;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import net.minecraftforge.common.util.*;

import org.lwjgl.opengl.*;

public class ModelCubeWorld extends ModelBase {

    byte[][][][] modelData;
    int[] pointers;
    final ModelRenderer cube;
    public static final int MAX_COLOR = 256;
    public static final float LUMINANCE_RED = 0.2126f;
    public static final float LUMINANCE_GREEN = 0.7152f;
    public static final float LUMINANCE_BLUE = 0.0722f;
    double hue;
    double saturation;
    double lightness;
    int[] lum_red_lookup;
    int[] lum_green_lookup;
    int[] lum_blue_lookup;
    int[] final_red_lookup;
    int[] final_green_lookup;
    int[] final_blue_lookup;

    public ModelCubeWorld(final InputStream stream) {
        this.hue = 180.0;
        this.saturation = 50.0;
        this.lightness = 0.0;
        this.cube = new ModelRenderer((ModelBase) this, 0, 0).addBox(0.0f, 0.0f, 0.0f, 1, 1, 1);
        int it = 0;
        int area = 0;
        byte[] sizesBuffer = new byte[12];
        try {
            int i;
            while ((i = stream.read()) != -1) {
                final byte val = (byte) (i & 0xFF);
                if (it < 12) {
                    sizesBuffer[it] = val;
                } else {
                    if (it == 12) {
                        final int x = sizesBuffer[0] | sizesBuffer[1] << 8
                            | sizesBuffer[2] << 16
                            | sizesBuffer[3] << 24;
                        final int y = sizesBuffer[4] | sizesBuffer[5] << 8
                            | sizesBuffer[6] << 16
                            | sizesBuffer[7] << 24;
                        final int z = sizesBuffer[8] | sizesBuffer[9] << 8
                            | sizesBuffer[10] << 16
                            | sizesBuffer[11] << 24;
                        this.modelData = new byte[x][y][z][3];
                        area = x * y;
                        sizesBuffer = null;
                    }
                    final int it2 = it - 12;
                    final int it1d = it2 / 3;
                    final int z = it1d / area;
                    final int it2d = it1d - area * z;
                    final int x2 = it2d % this.modelData.length;
                    final int y2 = it2d / this.modelData.length;
                    if (x2 >= this.modelData.length || y2 >= this.modelData[0].length
                        || z >= this.modelData[0][0].length) {
                        ++it;
                        continue;
                    }
                    this.modelData[x2][y2][z][it2 % 3] = val;
                }
                ++it;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.calculatePointers(false);
    }

    public void calculatePointers(final boolean renderHidden) {
        final List<Integer> newPointers = new ArrayList<Integer>();
        for (int x = 0; x < this.modelData.length; ++x) {
            for (int y = 0; y < this.modelData[0].length; ++y) {
                for (int z = 0; z < this.modelData[0][0].length; ++z) {
                    if (this.cubeExists(x, y, z)) {
                        if (!renderHidden) {
                            int hiddenSides = 0;
                            for (final ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                                if (this.cubeExists(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ)) {
                                    ++hiddenSides;
                                }
                            }
                            if (hiddenSides == 6) {
                                continue;
                            }
                        }
                        newPointers.add((x & 0x3FF) | (y & 0x3FF) << 10 | (z & 0x3FF) << 20);
                    }
                }
            }
        }
        this.pointers = new int[newPointers.size()];
        int it = 0;
        for (final int i : newPointers) {
            this.pointers[it] = i;
            ++it;
        }
    }

    public boolean cubeExists(final int x, final int y, final int z) {
        final int xs = this.modelData.length;
        final int ys = this.modelData[0].length;
        final int zs = this.modelData[0][0].length;
        if (x >= xs || x < 0 || y >= ys || y < 0 || z >= zs || z < 0) {
            return false;
        }
        final byte[] values = this.modelData[x][y][z];
        return values[0] != 0 || values[1] != 0 || values[2] != 0;
    }

    public byte[][][][] getModelData() {
        return this.modelData;
    }

    public void render() {
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glEnable(32826);
        final float scale = 0.0625f;
        GL11.glScalef(0.0625f, 0.0625f, 0.0625f);
        GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
        for (final int i : this.pointers) {
            final int x = i & 0x3FF;
            final int y = i >> 10 & 0x3FF;
            final int z = i >> 20 & 0x3FF;
            GL11.glTranslatef((float) x, (float) y, (float) z);
            final byte[] color = this.modelData[x][y][z];
            GL11.glColor3ub(color[0], color[1], color[2]);
            this.cube.render(1.0f);
            GL11.glTranslatef((float) (-x), (float) (-y), (float) (-z));
        }
        GL11.glEnable(3553);
        GL11.glPopMatrix();
    }

    public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5,
        final float par6, final float par7) {
        this.render();
    }
}
