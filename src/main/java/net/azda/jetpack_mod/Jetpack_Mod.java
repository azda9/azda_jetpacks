package net.azda.jetpack_mod;

import com.mojang.logging.LogUtils;
import net.azda.jetpack_mod.item.ModItems;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.joml.Vector3d;
import org.slf4j.Logger;

import javax.swing.text.JTextComponent;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Jetpack_Mod.MOD_ID)
public class Jetpack_Mod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "jetpack_mod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public Jetpack_Mod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.JETPACK);
        }
        if(event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(ModItems.JETPACK2);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }
    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID)
    public static class ForgeEvents
    {
        @SubscribeEvent
        public static void onPlayerTick(TickEvent.PlayerTickEvent event)
        {
            var player = event.player;
            var playerLook = player.getLookAngle();
            if (Minecraft.getInstance().player != null) {
                if (Minecraft.getInstance().player.input.jumping) {
                    player.addDeltaMovement(new Vec3(0, 0.1, 0));
                }
                if (Minecraft.getInstance().player.input.up) {
                    player.addDeltaMovement(new Vec3(0.05 * playerLook.x, 0, 0.05 * playerLook.z));
                }
                if (Minecraft.getInstance().player.input.down) {
                    player.addDeltaMovement(new Vec3(-0.05 * playerLook.x, 0, -0.05 * playerLook.z));
                }
                if (Minecraft.getInstance().player.input.right) {
                    player.addDeltaMovement(new Vec3(-0.05 * playerLook.z, 0, 0.05 * playerLook.x));
                }
                if (Minecraft.getInstance().player.input.left) {
                    player.addDeltaMovement(new Vec3(0.05 * playerLook.z, 0, -0.05 * playerLook.x));
                }
            }
        }
    }
}
