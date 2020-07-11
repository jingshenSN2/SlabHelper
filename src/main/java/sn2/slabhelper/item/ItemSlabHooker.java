package sn2.slabhelper.item;

import java.util.List;

import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import sn2.slabhelper.SlabHelperConfig;

public class ItemSlabHooker extends Item{

	public ItemSlabHooker(Settings settings) {
		super(settings);
	}
	
	@Override
	public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
	     tooltip.add(new TranslatableText("item.slabhelper.slab_hooker.tooltip"));
	}
	
	@Override
	public ActionResult useOnBlock(ItemUsageContext context) {
		World world = context.getWorld();
		if (world.isClient)
			return ActionResult.PASS;
		PlayerEntity player = context.getPlayer();
		if (SlabHelperConfig.useHookerOnlySurvivalMode && player.isCreative())
			return ActionResult.PASS;
		BlockPos pos = context.getBlockPos();
		BlockState state = world.getBlockState(pos);
		if (!(state.getBlock() instanceof SlabBlock)) 
			return ActionResult.PASS;
		//System.out.println("slab!");
		if (state.get(SlabBlock.TYPE) == SlabType.DOUBLE)
			return ActionResult.PASS;
		//System.out.println("half slab!");
		if (state.get(SlabBlock.TYPE) == SlabType.TOP)
			world.setBlockState(pos, state.with(SlabBlock.TYPE, SlabType.BOTTOM));
		if (state.get(SlabBlock.TYPE) == SlabType.BOTTOM)
			world.setBlockState(pos, state.with(SlabBlock.TYPE, SlabType.TOP));
	    player.addExhaustion(0.005F);
		context.getStack().damage(1, (LivingEntity)player, ((e) -> {
	         e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
	      }));
		//System.out.println(world.getBlockState(pos).get(SlabBlock.TYPE));
		return ActionResult.PASS;
	}
}
