package sn2.slabhelper.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.block.BlockModelRenderer;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BasicBakedModel;
import net.minecraft.client.texture.Sprite;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockRenderView;
import sn2.slabhelper.ClientSlabHelper;

@Environment(EnvType.CLIENT)
@Mixin(BlockRenderManager.class)
public class BlockRenderManagerMixin {

	@Shadow
	private BlockModels models;
	@Shadow
	private BlockModelRenderer blockModelRenderer;
	@Shadow
	private final Random random = new Random();

	@Inject(method = "tesselateDamage", at = @At("HEAD"), cancellable = true)
	public void onDamage(BlockState state, BlockPos pos, Sprite sprite, BlockRenderView blockRenderView,
			CallbackInfo info) {
		if (ClientSlabHelper.isHalfMine() && state.getBlock() instanceof SlabBlock
				&& state.get(SlabBlock.TYPE) == SlabType.DOUBLE) {
			SlabType type = ClientSlabHelper.getDamageRender(pos);
			if (type != null)
				state = state.with(SlabBlock.TYPE, type);
			if (state.getRenderType() == BlockRenderType.MODEL) {
				BakedModel bakedModel = this.models.getModel(state);
				long l = state.getRenderingSeed(pos);
				BakedModel bakedModel2 = (new BasicBakedModel.Builder(state, bakedModel, sprite, this.random, l))
						.build();
				this.blockModelRenderer.tesselate(blockRenderView, bakedModel2, state, pos,
						Tessellator.getInstance().getBuffer(), true, this.random, l);
			}
			info.cancel();
		}
	}

}
