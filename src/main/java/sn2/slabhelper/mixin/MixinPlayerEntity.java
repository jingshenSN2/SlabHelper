package sn2.slabhelper.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import sn2.slabhelper.HalfMinePlayerEntity;

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity implements HalfMinePlayerEntity {
	@Shadow
	public abstract void addChatMessage(Text message, boolean actionBar);

	private boolean isHalfMine = false;

	@Override
	public boolean isHalfMine() {
		return isHalfMine;
	}

	@Override
	public void setHalfMine() {
		isHalfMine = !isHalfMine;
		Text ON = new TranslatableText("message.player.halfmine.on").setStyle(new Style().setColor(Formatting.GREEN));
		Text OFF = new TranslatableText("message.player.halfmine.off").setStyle(new Style().setColor(Formatting.RED));
		this.addChatMessage(new TranslatableText("message.player.halfmine", isHalfMine ? ON : OFF), true);
	}

}
