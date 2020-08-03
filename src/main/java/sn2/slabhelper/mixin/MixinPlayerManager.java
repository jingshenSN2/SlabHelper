package sn2.slabhelper.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.text.ClickEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import sn2.slabhelper.VersionCheck;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.network.ClientConnection;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(PlayerManager.class)
public class MixinPlayerManager {

	@Inject(method = "onPlayerConnect", at = @At("TAIL"), cancellable = true)
	public void SLABHELPER$LOGIN(ClientConnection connection, ServerPlayerEntity player, CallbackInfo info) {
		// Update check
		String update = VersionCheck.check();
		if (update != null) {
			Text text = new TranslatableText("message.player.update", update,
					new TranslatableText("message.player.update.here").setStyle(new Style().setUnderline(true)
							.setItalic(true).setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
									"https://www.curseforge.com/minecraft/mc-mods/slab-helper"))));
			player.addChatMessage(text.setStyle(new Style().setColor(Formatting.GREEN)), false);
		}
	}
}
