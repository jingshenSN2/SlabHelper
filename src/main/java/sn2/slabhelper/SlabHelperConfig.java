package sn2.slabhelper;

import sn2.slabhelper.config.Config;

public class SlabHelperConfig {
	@Config(category = "item", key = "Slab Hooker", comment = "Disable this if you dont want slab hooker. (wont disable the additional lines on the double slab)")
	public static boolean enableHooker = true;

	@Config(category = "item", key = "Slab Pickaxe", comment = "Enable this if you want slab pickaxe in game.")
	public static boolean enablePickaxe = false;

	@Config(category = "durability", key = "Slab Hooker Max Durability", comment = "Set as 0 to disable comsuming durability.")
	public static int hookerDurability = 64;
	
	@Config(category = "function", key = "Slab Hooker In Creative", comment = "Enable this if you want to use slab hooker only in survival mode.")
	public static boolean useHookerOnlySurvivalMode = false;
	
	@Config(category = "function", key = "Universal half mining", comment = "Enable this if you want that common tools(like stone pickaxe, diamond axe, etc.) can half-mine the full slab.")
	public static boolean universalHalfmining = true;
	
	@Config(category = "render", key = "Additional Line for Double Slab", comment = "Disable this if you dont want additional lines on the double slab.")
	public static boolean renderAdditionalEdges = true;
}
