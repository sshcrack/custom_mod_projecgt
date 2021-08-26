package io.github.sshcrack.additions.common.items;

import io.github.sshcrack.additions.AdditionsMod;
import io.github.sshcrack.additions.core.util.Circle;
import io.github.sshcrack.additions.core.util.RayTrace;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.item.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector2f;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;
import org.lwjgl.glfw.GLFW;

import javax.annotation.Nullable;
import java.util.ArrayList;
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
        CooldownTracker tracker = player.getCooldownTracker();
        ItemStack item = player.getHeldItem(hand);
        if(tracker.hasCooldown(this))
            return ActionResult.resultFail(item);

        double eyePosY = player.getPosYEye();
        Vector3d location = player.getPositionVec();

        Vector3d eyePos = new Vector3d(location.x, eyePosY, location.z);
        Vector3d lookVec = player.getLookVec();

        BlockPos pos = RayTrace.getBlockLookedAt(world, eyePos, lookVec);
        if(pos == null)
            return ActionResult.resultSuccess(player.getHeldItem(hand));

        item.attemptDamageItem(1, world.getRandom(), null);
        if(item.getDamage() <= item.getMaxDamage())
            player.setHeldItem(hand, ItemStack.EMPTY);

        int radius = 3;
        Vector2f middle2D = Circle.blockToVector2D(pos);
        ArrayList<Vector2f> circle = Circle.getFullCircle(radius, middle2D);

        circle.forEach(circleItem -> {
            float x = circleItem.x;
            float z = circleItem.y;
            float y = pos.getY() +1;
            BlockState state = Blocks.DIRT.getDefaultState();

            FallingBlockEntity entity = new FallingBlockEntity(world, x, y, z, state);
            entity.addVelocity(0, 1, 0);
            entity.setHurtEntities(true);
            entity.fallTime = -1000;
            entity.shouldDropItem = false;

            world.addEntity(entity);
        });

        tracker.setCooldown(this, 20 * 5);
        return ActionResult.resultSuccess(item);
    }
}
