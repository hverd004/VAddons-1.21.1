package net.lostreaper03.mc_v_addons.event;

import net.lostreaper03.mc_v_addons.MCVAddonsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = MCVAddonsMod.MOD_ID, value = Dist.DEDICATED_SERVER, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModServerEvents {
    public static void triggerServerDismountEvent(UUID playerUUID, int entityID, double x, double y, double z) {
        MinecraftServer server = Minecraft.getInstance().getSingleplayerServer();
        if (server != null) {
            server.execute(() -> {
                ServerPlayer player = server.getPlayerList().getPlayer(playerUUID);
                if (player != null) {
                    Entity ridingEntity = player.level().getEntity(entityID);
                    if (ridingEntity != null) {
                        ridingEntity.stopRiding();
                        ridingEntity.teleportTo(x, y, z);
                        System.out.println("Server-side dismount and teleport executed for entity: " + ridingEntity);
                    }
                }
            });
        }
    }
}
