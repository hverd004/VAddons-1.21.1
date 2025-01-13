package net.lostreaper03.mc_v_addons.event;

import net.lostreaper03.mc_v_addons.MCVAddonsMod;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = MCVAddonsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {
    //key handling
    public static final KeyMapping STOP_RIDING_KEY = new KeyMapping("key.mc_v_addons.stop_riding", GLFW.GLFW_KEY_R, "key.categories.mc_v_addons");
    
    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (STOP_RIDING_KEY.isDown()) {
            System.out.println("DONT PANIC");
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            System.out.println(player != null);
            System.out.println(player.getFirstPassenger());
            mc.execute(() -> {
                if (player.getFirstPassenger() != null) {
                    Entity ridingEntity = player.getFirstPassenger();
                    System.out.println(ridingEntity.getVehicle());
                    ridingEntity.stopRiding();
                    ModServerEvents.triggerServerDismountEvent(player.getUUID(), ridingEntity.getId(), player.getX(), player.getY(), player.getZ());

                    System.out.println(ridingEntity.getVehicle());
                    System.out.println("Stopped riding entity: " + ridingEntity);
                }
            });
        }
    }
}
