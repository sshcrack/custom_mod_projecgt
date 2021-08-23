package io.github.sshcrack.additions.common.items;

import io.github.sshcrack.additions.AdditionsMod;
import io.github.sshcrack.additions.core.util.RayTrace;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.List;

public class DirtWand extends Item  {
    public DirtWand(Properties properties) {
        super(properties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);

        tooltip.add(new TranslationTextComponent("tooltip.dirt_wand.short_description"));
        tooltip.add(new TranslationTextComponent("tooltip.dirt_wand.hold_shift")); //

        long handle = Minecraft.getInstance().getMainWindow().getHandle();
        if(InputMappings.isKeyDown(handle, GLFW.GLFW_KEY_LEFT_SHIFT)) {
            tooltip.add(new TranslationTextComponent("tooltip.dirt_wand.long_description"));
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        double eyePosY = player.getPosYEye();
        Vector3d location = player.getPositionVec();

        Vector3d eyePos = new Vector3d(location.x, eyePosY, location.z);
        Vector3d lookVec = player.getLookVec();

        BlockPos pos = RayTrace.getBlockLookedAt(world, eyePos, lookVec);
        if(pos == null)
            return ActionResult.resultSuccess(player.getHeldItem(hand));

        ItemStack item = player.getHeldItem(hand);

        item.attemptDamageItem(1, world.getRandom(), (ServerPlayerEntity) player);

        ZombieEntity zombie = new ZombieEntity(world);
        zombie.setNoAI(true);

        double zombieX = pos.getX();
        double zombieY = pos.getY();
        double zombieZ = pos.getZ();
        zombie.setPosition(zombieX, zombieY, zombieZ);

        world.addEntity(zombie);

        return ActionResult.resultSuccess(item);
    }
}
