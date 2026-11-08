package carb;

import carb.gui.CarbScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class CarbClient implements ClientModInitializer {
    private static KeyBinding openGui;

    @Override
    public void onInitializeClient() {
        ModuleManager.init();

        openGui = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.carb.gui", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, "category.carb"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openGui.wasPressed()) client.setScreen(new CarbScreen());
            ModuleManager.tick();
        });
    }
}
