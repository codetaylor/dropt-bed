package com.codetaylor.mc.droptbed;

import net.minecraftforge.common.config.Config;

@Config(modid = ModDroptBed.MOD_ID, name = ModDroptBed.MOD_ID)
public class ModDroptBedConfig {

  @Config.Comment({
      "By default, the vanilla bed is designed to bypass the harvest event that",
      "Dropt hooks and spawn its drops directly in the world. Enabling this bed",
      "replacement will overwrite the vanilla bed with a version designed to",
      "use the harvest event.",
      "Default: " + true
  })
  @Config.RequiresMcRestart
  public static boolean REPLACE_VANILLA_BED = true;

}
