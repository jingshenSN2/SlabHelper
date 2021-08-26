package sn2.slabhelper;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

public class SlabHelperKey {

	public static KeyBinding halfmine = new KeyBinding("key.slabhelper.halfmine", // The translation key of the
																					// keybinding's name
			InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
			GLFW.GLFW_KEY_GRAVE_ACCENT, // The keycode of the key
			"category.slabhelper.key" // The translation key of the keybinding's category.
	);

	public static void init() {
		KeyBindingHelper.registerKeyBinding(halfmine);
	}
}
