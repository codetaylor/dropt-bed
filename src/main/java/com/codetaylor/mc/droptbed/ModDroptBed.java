package com.codetaylor.mc.droptbed;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(
    modid = "dropt-bed",
    version = "@@VERSION@@",
    name = "Dropt Bed"
)
@Mod.EventBusSubscriber
public class ModDroptBed {

  @SubscribeEvent
  public static void onRegisterItems(RegistryEvent.Register<Block> event) {

    BlockBedReplacement block = new BlockBedReplacement();
    ResourceLocation resourceLocation = new ResourceLocation("minecraft", "bed");
    block.setRegistryName(resourceLocation);
    block.setUnlocalizedName("bed");
    block.setCreativeTab(CreativeTabs.DECORATIONS);
    event.getRegistry().register(block);
  }
}
