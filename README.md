# Gravestones – Die Classy (Updated)

An updated fork of the original **Gravestones – Die Classy** mod for **Minecraft 1.7.10**. When a player dies, their entire inventory is captured and stored inside a decorative gravestone placed at the death location, preventing item despawn. This fork fixes several long-standing bugs and adds broad compatibility with other mods that modify the player inventory.

> **Original mod** by Subaraki (AbsolemJackdaw). This fork is maintained with the original author's [permission](https://github.com/Gordon-Frohman/Gravestones-DieClassy-Updated/blob/master/Permission.JPG), provided it remains open source.

---

## Features

- **Death inventory capture** – All items are saved into a gravestone `TileEntity` and persist through world reloads.
- **10 distinct 3D grave models** – Including Angel, Skeleton, GraveStone, Knight, Pillar, Stone Cross, Tomb, Wooden Grave, and two Cube World variants. The active model cycles with player level or can be changed manually.
- **Player skin rendering** – A skin system based on vanilla skull mechanic.
- **Fixed armor rendering** – Armor on the player bust gravestone now renders correctly (the original mod rendered it perpendicular to the model).
- **Auto-Equip** – Players can quickly re-equip their gear directly from the grave GUI.
- **Conflict resolution** – Properly handles other mods that grant keep-inventory on death (e.g. Twilight Forest, Thaumic Horizons), preventing double-saving or item loss.
- **Russian translation** included.
  - Want to translate the mod into your language? Contributions are welcome!

---

## Mod Compatibility

The mod integrates with the following mods to ensure their custom inventory slots are also saved to the grave:

| Mod | Integration |
|---|---|
| Baubles | Bauble slots |
| Tinkers' Construct | Tool slots |
| RPG Inventory | Extended inventory slots |
| Galacticraft | Oxygen/gear slots |
| Mariculture | Custom slots |
| Cosmetic Armor Reworked | Cosmetic slots |
| Satchels | Satchel contents |
| Aether Legacy | Aether accessory slots |
| Mine & Blade: Battlegear 2 | Weapon/shield slots |
| Traveller's Gear | Gear slots |
| Sextiary Sector | Custom slots |
| Adventure Backpack | Backpack contents |
| Backhand | Off-hand slot |

---

## Configuration

The config file is generated at `config/gravestonemod.cfg` on first launch.

| Option | Default | Description |
|---|---|---|
| `enable Graves Trough Key` | `true` | Allows changing the grave model via a keybind. |
| `change graves every x level` | `5` | How many player levels between automatic grave model changes. |
| `grave orders` | `6,1,2,3,7,4,5,10,9,8` | The order in which grave models are cycled (by model index). |
| `Toggle debug lines to print in the console` | `false` | Enables verbose debug logging. |

---

## Building from Source

This project uses [RetroFuturaGradle](https://github.com/GTNewHorizons/RetroFuturaGradle) and [Jabel](https://github.com/bsideup/jabel), which allow writing modern Java syntax (up to Java 17) while targeting the Java 8 JVM required by Minecraft 1.7.10.

**Requirements:** JDK 17+

```bash
# Clone the repository
git clone https://github.com/Gordon-Frohman/Gravestones-DieClassy-Updated.git
cd Gravestones-DieClassy-Updated

# Build
./gradlew build
```

The output jar will be in `build/libs/`.

To launch a development client:

```bash
./gradlew runClient
```

---

## Version Info

| Property | Value |
|---|---|
| Mod version | 1.8.1 |
| Minecraft version | 1.7.10 |
| Forge version | 10.13.4.1614 |
| CurseForge | [838635](https://www.curseforge.com/minecraft/mc-mods/gravestones-die-classy-updated) |

---

## Credits

- **Subaraki (AbsolemJackdaw)** – Original mod author
- **Sergius Onesimus** – Fork maintainer

---

## License

This project is open source. See the original mod's licensing terms. Redistribution of this fork must remain open source per the [agreement](https://github.com/Gordon-Frohman/Gravestones-DieClassy-Updated/blob/master/Permission.JPG) with the original author.
