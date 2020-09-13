package sn2.slabhelper.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class MathUtils {
	public static BlockState getNewState(BlockPos pos, PlayerEntity player, BlockState state) {
		SlabType hitType = getHitType(pos, player, state);
		if (hitType == SlabType.TOP)
			state = state.with(SlabBlock.TYPE, SlabType.BOTTOM);
		else
			state = state.with(SlabBlock.TYPE, SlabType.TOP);
		return state;
	}

	public static SlabType getHitType(BlockPos pos, PlayerEntity player, BlockState state) {
		HitResult result = player.raycast(6, 0, false);
		Vec3d hit = result.getPos();
		double hitY = hit.getY();
		double relativeY = hitY - (int) hitY;
		// hit at top or bottom
		if (relativeY == 0) {
			// hit at bottom
			if (hitY >= player.getPos().y + (double) player.getEyeHeight(player.getPose()))
				return SlabType.BOTTOM;
			// hit at top
			else
				return SlabType.TOP;
		}
		// hit at y>0.5
		else if (relativeY >= 0.5)
			return SlabType.TOP;
		// hit at y<0.5
		else
			return SlabType.BOTTOM;
	}
}
