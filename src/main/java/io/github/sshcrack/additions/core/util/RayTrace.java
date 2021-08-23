package io.github.sshcrack.additions.core.util;

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

            int additionX = (int) addition.x;
            int additionY = (int) addition.y;
            int additionZ = (int) addition.z;

            BlockPos blockCoords = new BlockPos(additionX, additionY, additionZ);
            boolean isAir = world.isAirBlock(blockCoords);

            if(isAir)
                continue;

            finalPos = blockCoords;
            break;
        }

        return finalPos;
    }
}
