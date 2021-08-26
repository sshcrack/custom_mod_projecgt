package io.github.sshcrack.additions.core.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RayTrace {

    @Nullable
    public static BlockPos getBlockLookedAt(World world, Vector3d eyePosition, Vector3d look) {
        int maxDistance = 30;

        BlockPos finalPos = null;
        for(int i = 0; i < maxDistance; i++) {
            Vector3d currLook = look.mul(i, i, i);
            Vector3d addition = eyePosition.add(currLook);

            int additionX = (int) Math.round(addition.x);
            int additionY = (int) Math.round(addition.y);
            int additionZ = (int) Math.round(addition.z);

            BlockPos blockCoords = new BlockPos(additionX, additionY, additionZ);
            BlockState state = world.getBlockState(blockCoords);
            boolean isSolid = state.isSolid();

            if(!isSolid)
                continue;

            finalPos = blockCoords;
            break;
        }

        return finalPos;
    }

}
