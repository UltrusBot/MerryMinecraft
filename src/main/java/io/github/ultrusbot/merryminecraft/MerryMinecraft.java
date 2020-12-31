package io.github.ultrusbot.merryminecraft;

import io.github.ultrusbot.merryminecraft.biome.WonderlandBiome;
import io.github.ultrusbot.merryminecraft.block.CustomStairsBlock;
import io.github.ultrusbot.merryminecraft.block.GiftBlock;
import io.github.ultrusbot.merryminecraft.block.StringLightBlock;
import io.github.ultrusbot.merryminecraft.enchant.IceSkateEnchantment;
import io.github.ultrusbot.merryminecraft.item.CustomMusicDiscItem;
import io.github.ultrusbot.merryminecraft.item.MerryToolMaterials;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.ConfiguredFeature;

public class MerryMinecraft implements ModInitializer {
    public static final String MOD_ID = "merryminecraft";
    public static Item RED_CANDY_CANE;

    public static final ItemGroup MERRY_ITEMS = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "items"),
            () -> new ItemStack(RED_CANDY_CANE));

    public static Item GREEN_CANDY_CANE = new Item(new FabricItemSettings().group(MERRY_ITEMS).food(FoodComponents.COOKIE));

    public static SoundEvent SKATING_MUSIC_EVENT = new SoundEvent(new Identifier(MOD_ID, "skating_music"));
    public static Item SKATING_MUSIC_DISC = new CustomMusicDiscItem(15, SKATING_MUSIC_EVENT, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE).group(MERRY_ITEMS));

    public static SoundEvent LET_IT_SNOW_MUSIC_EVENT = new SoundEvent(new Identifier(MOD_ID, "let_it_snow_music"));
    public static Item LET_IT_SNOW_MUSIC_DISC = new CustomMusicDiscItem(15, LET_IT_SNOW_MUSIC_EVENT, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE).group(MERRY_ITEMS));

    public static SoundEvent JINGLE_BELLS_MUSIC_EVENT = new SoundEvent(new Identifier(MOD_ID, "jingle_bells_music"));
    public static Item JINGLE_BELLS_MUSIC_DISC = new CustomMusicDiscItem(15, JINGLE_BELLS_MUSIC_EVENT, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE).group(MERRY_ITEMS));

    public static SoundEvent WWYAMC_MUSIC_EVENT = new SoundEvent(new Identifier(MOD_ID, "we_wish_you_a_merry_christmas_music"));
    public static Item WWYAMC_MUSIC_DISC = new CustomMusicDiscItem(15, WWYAMC_MUSIC_EVENT, new FabricItemSettings().maxCount(1).rarity(Rarity.RARE).group(MERRY_ITEMS));

    public static Block RED_CANDY_CANE_BLOCK = new Block(FabricBlockSettings.of(Material.AGGREGATE).breakByTool(FabricToolTags.PICKAXES).hardness(0.5f));
    public static Block RED_CANDY_CANE_STAIRS = new CustomStairsBlock(RED_CANDY_CANE_BLOCK.getDefaultState(), FabricBlockSettings.copyOf(RED_CANDY_CANE_BLOCK));
    public static Block GREEN_CANDY_CANE_BLOCK = new Block(FabricBlockSettings.of(Material.AGGREGATE).breakByTool(FabricToolTags.PICKAXES).hardness(0.5f));
    public static Block GREEN_CANDY_CANE_STAIRS = new CustomStairsBlock(GREEN_CANDY_CANE_BLOCK.getDefaultState(), FabricBlockSettings.copyOf(GREEN_CANDY_CANE_BLOCK));

    public static Block STRING_LIGHT = new StringLightBlock(FabricBlockSettings.of(Material.GLASS).breakInstantly().noCollision().luminance(15).sounds(BlockSoundGroup.GLASS));

    public static Item RED_CANDY_CANE_SWORD = new SwordItem(MerryToolMaterials.RED_CANDY_CANE, 3, -2.4F, new FabricItemSettings().group(MERRY_ITEMS));
    public static Item GREEN_CANDY_CANE_SWORD = new SwordItem(MerryToolMaterials.RED_CANDY_CANE, 3, -2.4F, new FabricItemSettings().group(MERRY_ITEMS));

    public static Enchantment ICE_SKATING_ENCHANTMENT = new IceSkateEnchantment();

    public static Block RED_GIFT_BLOCK = new GiftBlock(FabricBlockSettings.of(Material.WOOD).breakInstantly().nonOpaque().sounds(BlockSoundGroup.WOOD));

    public static Block GINGERBREAD_BLOCK = new Block(FabricBlockSettings.of(Material.AGGREGATE).strength(0.5f).sounds(BlockSoundGroup.WOOD));
    public static Item GINGERBREAD_MAN = new Item(new FabricItemSettings().group(MERRY_ITEMS).food(FoodComponents.COOKIE));

    public static final RegistryKey<Biome> WONDERLAND_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier(MOD_ID, "wonderland"));
    public static final Biome WONDERLAND_BIOME = WonderlandBiome.createWonderlandBiome();

    @Override
    public void onInitialize() {
        RED_CANDY_CANE = new Item(new FabricItemSettings().group(MERRY_ITEMS).food(FoodComponents.COOKIE));
        Registry.register(Registry.SOUND_EVENT, SKATING_MUSIC_EVENT.getId(), SKATING_MUSIC_EVENT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "skating_music_disc"), SKATING_MUSIC_DISC);

        Registry.register(Registry.SOUND_EVENT, LET_IT_SNOW_MUSIC_EVENT.getId(), LET_IT_SNOW_MUSIC_EVENT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "let_it_snow_music_disc"), LET_IT_SNOW_MUSIC_DISC);

        Registry.register(Registry.SOUND_EVENT, JINGLE_BELLS_MUSIC_EVENT.getId(), JINGLE_BELLS_MUSIC_EVENT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "jingle_bells_music_disc"), JINGLE_BELLS_MUSIC_DISC);

        Registry.register(Registry.SOUND_EVENT, WWYAMC_MUSIC_EVENT.getId(), WWYAMC_MUSIC_EVENT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "merry_christmas_music_disc"), WWYAMC_MUSIC_DISC);

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "red_candy_cane_block"), RED_CANDY_CANE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_candy_cane_block"), new BlockItem(RED_CANDY_CANE_BLOCK, new FabricItemSettings().group(MERRY_ITEMS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "red_candy_cane_stairs"), RED_CANDY_CANE_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_candy_cane_stairs"), new BlockItem(RED_CANDY_CANE_STAIRS, new FabricItemSettings().group(MERRY_ITEMS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "green_candy_cane_block"), GREEN_CANDY_CANE_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "green_candy_cane_block"), new BlockItem(GREEN_CANDY_CANE_BLOCK, new FabricItemSettings().group(MERRY_ITEMS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "green_candy_cane_stairs"), GREEN_CANDY_CANE_STAIRS);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "green_candy_cane_stairs"), new BlockItem(GREEN_CANDY_CANE_STAIRS, new FabricItemSettings().group(MERRY_ITEMS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "string_lights"), STRING_LIGHT);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "string_lights"), new BlockItem(STRING_LIGHT, new FabricItemSettings().group(MERRY_ITEMS)));

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "red_gift"), RED_GIFT_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_gift"), new BlockItem(RED_GIFT_BLOCK, new FabricItemSettings().group(MERRY_ITEMS)));

        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_candy_cane"), RED_CANDY_CANE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "green_candy_cane"), GREEN_CANDY_CANE);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "red_candy_cane_sword"), RED_CANDY_CANE_SWORD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "green_candy_cane_sword"), GREEN_CANDY_CANE_SWORD);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "gingerbread_man"), GINGERBREAD_MAN);

        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "gingerbread_block"), GINGERBREAD_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "gingerbread_block"), new BlockItem(GINGERBREAD_BLOCK, new FabricItemSettings().group(MERRY_ITEMS)));

        Registry.register(Registry.FEATURE, new Identifier(MOD_ID, "big_candy_cane"), WonderlandBiome.BIG_CANDY_CANE_FEATURE);
        RegistryKey<ConfiguredFeature<?, ?>> bigCandyCane = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "big_candy_cane"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, bigCandyCane.getValue(), WonderlandBiome.BIG_CANDY_CANE_CONFIGURED);

        Registry.register(BuiltinRegistries.BIOME, WONDERLAND_KEY.getValue(), WONDERLAND_BIOME);
        OverworldBiomes.addContinentalBiome(WONDERLAND_KEY, OverworldClimate.SNOWY, 2D);


        Registry.register(Registry.ENCHANTMENT, new Identifier(MOD_ID, "ice_skating"), ICE_SKATING_ENCHANTMENT);
    }
}
