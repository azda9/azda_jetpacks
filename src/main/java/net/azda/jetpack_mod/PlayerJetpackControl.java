package net.azda.jetpack_mod;

import net.azda.jetpack_mod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import java.text.Normalizer;

@Mod(Jetpack_Mod.MOD_ID)
public class PlayerJetpackControl {
    public static final String MOD_ID = "jetpack_mod";
    @Mod.EventBusSubscriber(modid = MOD_ID)
    public static class ForgeEvents
    {

        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event)
        {
            var player = event.player;
            var playerLook = player.getLookAngle();
            RegistryObject<Item> jetpackRegistryObject = ModItems.JETPACK;
            RegistryObject<Item> superJetpackRegistryObject = ModItems.JETPACK2;
            Item jetpackItem = jetpackRegistryObject.get();
            Item superJetpackItem = superJetpackRegistryObject.get();
            var playerLookNoY = new Vec3(playerLook.x, 0,playerLook.z).normalize();
            if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() == jetpackItem && (Minecraft.getInstance().player != null)){

                if (Minecraft.getInstance().player.input.jumping) {
                        player.addDeltaMovement(new Vec3(0, 0.06, 0));
                }
                if (Minecraft.getInstance().player.input.up) {
                        player.addDeltaMovement(new Vec3(0.02 * playerLookNoY.x, 0, 0.02 * playerLookNoY.z));
                }
                if (Minecraft.getInstance().player.input.down) {
                        player.addDeltaMovement(new Vec3(-0.02 * playerLookNoY.x, 0, -0.02 * playerLookNoY.z));
                }
                if (Minecraft.getInstance().player.input.right) {
                        player.addDeltaMovement(new Vec3(-0.02 * playerLookNoY.z, 0, 0.02 * playerLookNoY.x));
                }
                if (Minecraft.getInstance().player.input.left) {
                        player.addDeltaMovement(new Vec3(0.02 * playerLookNoY.z, 0, -0.02 * playerLookNoY.x));
                }
            }
            if(player.getItemBySlot(EquipmentSlot.CHEST).getItem() == superJetpackItem && (Minecraft.getInstance().player != null)){

                if (Minecraft.getInstance().player.input.jumping) {
                    player.addDeltaMovement(new Vec3(0, 0.1, 0));
                }
                if (Minecraft.getInstance().player.input.up) {
                    player.addDeltaMovement(new Vec3(0.05 * playerLookNoY.x, 0, 0.05 * playerLookNoY.z));
                }
                if (Minecraft.getInstance().player.input.down) {
                    player.addDeltaMovement(new Vec3(-0.05 * playerLookNoY.x, 0, -0.05 * playerLookNoY.z));
                }
                if (Minecraft.getInstance().player.input.right) {
                    player.addDeltaMovement(new Vec3(-0.05 * playerLookNoY.z, 0, 0.05 * playerLookNoY.x));
                }
                if (Minecraft.getInstance().player.input.left) {
                    player.addDeltaMovement(new Vec3(0.05 * playerLookNoY.z, 0, -0.05 * playerLookNoY.x));
                }
            }
        }
    }
}
