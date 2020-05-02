package com.codetaylor.mc.droptbed;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod(
    modid = ModDroptBed.MOD_ID,
    version = ModDroptBed.VERSION,
    name = ModDroptBed.NAME
)
public class ModDroptBed {

  public static final String MOD_ID = "dropt-bed";
  public static final String VERSION = "@@VERSION@@";
  public static final String NAME = "Dropt Bed";

  @Mod.Instance
  @SuppressWarnings("unused")
  public static ModDroptBed INSTANCE;

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
