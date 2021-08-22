package io.github.sshcrack.additions.core.init;

import io.github.sshcrack.additions.AdditionsMod;
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

    // Block Items
    public static final RegistryObject<BlockItem> MUD = ITEMS.register("mud", () -> new BlockItem(
            BlockInit.MUD.get(),
            new Item.Properties()
                    .group(ItemGroup.BUILDING_BLOCKS)
    ));

}
