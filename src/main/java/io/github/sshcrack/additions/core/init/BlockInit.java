package io.github.sshcrack.additions.core.init;

import io.github.sshcrack.additions.AdditionsMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS,
            AdditionsMod.MOD_ID
    );

    public static final RegistryObject<Block> DIRT_ORE = BLOCKS.register(
            "dirt_ore",
            () -> new Block(AbstractBlock.Properties
                    .create(Material.EARTH, MaterialColor.BROWN)
                    .hardnessAndResistance(1f, 1f)
                    .harvestTool(ToolType.PICKAXE)
                    .harvestLevel(1)
                    .sound(SoundType.GROUND)
                    .setRequiresTool()
            )
    );


}
