package io.github.sshcrack.additions.core.init;

import io.github.sshcrack.additions.AdditionsMod;
import io.github.sshcrack.additions.common.items.DirtWand;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            AdditionsMod.MOD_ID
    );

    public static final RegistryObject<Item> FIRST_ITEM = ITEMS.register(
            "first_item",
            () -> new Item(new Item.Properties().group(ItemGroup.TOOLS))
    );

    public static final RegistryObject<Item> DIRT_INGOT = ITEMS.register(
            "dirt_ingot",
            () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS))
    );


    public static final RegistryObject<Item> RAW_DIRT = ITEMS.register(
            "raw_dirt",
            () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS))
    );

    public static final RegistryObject<DirtWand> DIRT_WAND = ITEMS.register("dirt_wand", () -> new DirtWand(
            new Item.Properties().group(ItemGroup.COMBAT)
                .defaultMaxDamage(25)
                .setNoRepair()
    ));

    // Block Items
    public static final RegistryObject<BlockItem> DIRT_ORE = ITEMS.register("dirt_ore", () -> new BlockItem(
            BlockInit.DIRT_ORE.get(),
            new Item.Properties()
                    .group(ItemGroup.MATERIALS)
    ));

}
