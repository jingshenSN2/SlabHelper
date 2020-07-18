package sn2.slabhelper;

import java.util.HashMap;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import sn2.slabhelper.callbacks.CallbackRegistry;
import sn2.slabhelper.config.ConfigHandler;
import sn2.slabhelper.config.SlabHelperConfig;
import sn2.slabhelper.item.ItemRegistry;

public class SlabHelper implements ModInitializer {
	
	public static final String MODID = "slabhelper";
	public static final String VERSION = "1.3.0";
	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public static Identifier HALFMINE = new Identifier(SlabHelper.MODID, "half");	
	private static HashMap<UUID, Boolean> enableHalfMine = new HashMap<UUID, Boolean>();
		
	@Override
	public void onInitialize() {
		new ConfigHandler(SlabHelperConfig.class, MODID);
		// check update
		VersionCheck.init();
		// register item
		ItemRegistry.init();
		// register events
		CallbackRegistry.init();
		LOGGER.info("Slab Helper initialized");
	}
	
	public static boolean getHalfMineStatus(PlayerEntity player) {
		if (enableHalfMine.containsKey(player.getUuid()))
			return enableHalfMine.get(player.getUuid());
		return false;
	}
	
	public static void setHalfMineStatus(PlayerEntity player, boolean enable) {
		if (enableHalfMine.containsKey(player.getUuid()))
			enableHalfMine.replace(player.getUuid(), enable);
		else
			enableHalfMine.put(player.getUuid(), enable);
		Text ON = new TranslatableText("message.player.halfmine.on").setStyle(
				new Style().setColor(Formatting.GREEN));
		Text OFF = new TranslatableText("message.player.halfmine.off").setStyle(
				new Style().setColor(Formatting.RED));
		player.addChatMessage(new TranslatableText("message.player.halfmine", enable? ON:OFF), true); // just no color?
	}
}
