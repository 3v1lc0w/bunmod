package com.buncord.bunmod.events;

import com.buncord.bunmod.BunMod;
import com.buncord.bunmod.entities.FfionEntity;
import com.buncord.bunmod.entities.GenevieveEntity;
import com.buncord.bunmod.entities.GerryEntity;
import com.buncord.bunmod.init.EntityInit;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = BunMod.MODID, bus = Bus.MOD)
public class ModCommonEvents {

  private static final Logger LOGGER = LogManager.getLogger();

  @SubscribeEvent
  public static void entityAttributes(EntityAttributeCreationEvent event) {
    event.put(EntityInit.GERRY.get(), GerryEntity.createAttributes().build());
    event.put(EntityInit.FFION.get(), FfionEntity.createAttributes().build());
    event.put(EntityInit.GENEVIEVE.get(), GenevieveEntity.createAttributes().build());
  }

}
