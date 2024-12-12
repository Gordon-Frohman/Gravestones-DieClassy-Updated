
package net.subaraki.gravestone.handler;

import net.minecraft.util.*;

public class GraveTextHandler {

    public static String getStringFromMeta(final String nameOfDeathPlayer, final int meta) {
        final String rest1 = StatCollector.translateToLocal("grave.rest.1");
        final String rest2 = StatCollector.translateToLocal("grave.rest.2");
        final String friend1 = StatCollector.translateToLocal("grave.friend.1");
        final String friend2 = StatCollector.translateToLocal("grave.friend.2");
        final String hero1 = StatCollector.translateToLocal("grave.hero.1");
        final String hero2 = StatCollector.translateToLocal("grave.hero.2");
        final String glory1 = StatCollector.translateToLocal("grave.glory.1");
        final String glory2 = StatCollector.translateToLocal("grave.glory.2");
        final String passed1 = StatCollector.translateToLocal("grave.passed.1");
        final String passed2 = StatCollector.translateToLocal("grave.passed.2");
        final String silence1 = StatCollector.translateToLocal("grave.silence.1");
        final String silence2 = StatCollector.translateToLocal("grave.silence.2");
        final String memory1 = StatCollector.translateToLocal("grave.memory.1");
        final String memory2 = StatCollector.translateToLocal("grave.memory.2");
        final String angle1 = StatCollector.translateToLocal("grave.angle.1");
        final String angle2 = StatCollector.translateToLocal("grave.angle.2");
        final String knight1 = StatCollector.translateToLocal("grave.knight.1");
        final String knight2 = StatCollector.translateToLocal("grave.knight.2");
        final String barrel1 = StatCollector.translateToLocal("grave.barrel.1");
        final String barrel2 = StatCollector.translateToLocal("grave.barrel.2");
        if (meta == 1) {
            return rest1 + nameOfDeathPlayer + rest2;
        }
        if (meta == 2) {
            return friend1 + nameOfDeathPlayer + friend2;
        }
        if (meta == 3) {
            return hero1 + nameOfDeathPlayer + hero2;
        }
        if (meta == 4) {
            return glory1 + nameOfDeathPlayer + glory2;
        }
        if (meta == 5) {
            return passed1 + nameOfDeathPlayer + passed2;
        }
        if (meta == 7) {
            return silence1 + nameOfDeathPlayer + silence2;
        }
        if (meta == 6) {
            return memory1 + nameOfDeathPlayer + memory2;
        }
        if (meta == 8) {
            return angle1 + nameOfDeathPlayer + angle2;
        }
        if (meta == 9) {
            return knight1 + nameOfDeathPlayer + knight2;
        }
        if (meta == 10) {
            return barrel1 + nameOfDeathPlayer + barrel2;
        }
        return "error encountered. index out of bounds : " + meta;
    }
}
