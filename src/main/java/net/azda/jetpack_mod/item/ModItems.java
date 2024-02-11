package net.azda.jetpack_mod.item;

import net.azda.jetpack_mod.item.ModArmorMaterials;
import net.azda.jetpack_mod.Jetpack_Mod;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Jetpack_Mod.MOD_ID);

    public static final RegistryObject<Item> JETPACK = ITEMS.register("jetpack", () -> new ArmorItem(ModArmorMaterials.JETPACK,
            ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> JETPACK2 = ITEMS.register("super_jetpack", () -> new ArmorItem(ModArmorMaterials.JETPACK,
            ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
