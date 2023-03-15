package com.buncord.bunmod.events;

import com.buncord.bunmod.BunMod;
import com.buncord.bunmod.client.models.FfionModel;
import com.buncord.bunmod.client.models.GerryModel;
import com.buncord.bunmod.client.renderers.FfionRenderer;
import com.buncord.bunmod.client.renderers.GerryRenderer;
import com.buncord.bunmod.init.EntityInit;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;
import net.minecraftforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = BunMod.MODID, bus = Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {

  private static final Logger LOGGER = LogManager.getLogger();

  @SubscribeEvent
  public static void entityRenderers(RegisterRenderers event) {
    event.registerEntityRenderer(EntityInit.GERRY.get(), GerryRenderer::new);
    event.registerEntityRenderer(EntityInit.FFION.get(), FfionRenderer::new);
  }

  @SubscribeEvent
  public static void registerLayerDefinitions(RegisterLayerDefinitions event) {
    event.registerLayerDefinition(GerryModel.LAYER_LOCATION, GerryModel::createBodyLayer);
    event.registerLayerDefinition(FfionModel.LAYER_LOCATION, FfionModel::createBodyLayer);
  }

}
