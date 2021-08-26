package sn2.slabhelper.callbacks;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import sn2.slabhelper.HalfMinePlayerEntity;
import sn2.slabhelper.SlabHelper;

public class CallbackRegistry {
	public static void init() {
		ServerPlayNetworking.registerGlobalReceiver(SlabHelper.HALFMINE, (server, player, handler, buf, responseSender) -> {
			HalfMinePlayerEntity playerHalfMine = (HalfMinePlayerEntity) player;
			playerHalfMine.setHalfMine();
			SlabHelper.LOGGER.info(player.getEntityName() + " change Half-Mime mode to " + (playerHalfMine.isHalfMine() ? "On" : "Off"));
		});
	}
}
