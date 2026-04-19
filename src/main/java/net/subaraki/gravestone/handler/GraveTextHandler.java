
package net.subaraki.gravestone.handler;

import net.minecraft.util.StatCollector;

public class GraveTextHandler {

    private static final String[] graveTypes = { "rest", "friend", "hero", "glory", "passed", "silence", "memory",
        "angel", "knight", "barrel" };

    public static String getStringFromMeta(final String nameOfDeathPlayer, final int meta,
        final boolean useMaleEpitaph) {
        if (meta > graveTypes.length) return "error encountered. index out of bounds : " + meta;
        String graveType = graveTypes[meta - 1];
        String maleEpitaph = useMaleEpitaph ? "" : ".female";
        return StatCollector.translateToLocal("grave." + graveType + maleEpitaph + ".1") + nameOfDeathPlayer
            + StatCollector.translateToLocal("grave." + graveType + maleEpitaph + ".2");
    }
}
