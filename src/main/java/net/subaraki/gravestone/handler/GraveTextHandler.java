
package net.subaraki.gravestone.handler;

import net.minecraft.util.StatCollector;

public class GraveTextHandler {

    private static String[] graveTypes = { "rest", "friend", "hero", "glory", "passed", "silence", "memory", "angel",
        "knight", "barrel" };

    public static String getStringFromMeta(String nameOfDeadPlayer, int meta, boolean useMaleEpitaph) {
        if (meta > graveTypes.length || meta <= 0) return "error encountered. index out of bounds : " + meta;
        String graveType = graveTypes[meta - 1];
        String maleEpitaph = useMaleEpitaph ? "" : ".female";
        return StatCollector.translateToLocal("grave." + graveType + maleEpitaph + ".1") + nameOfDeadPlayer
            + StatCollector.translateToLocal("grave." + graveType + maleEpitaph + ".2");
    }
}
