package com.buncord.bunmod.client.renderers;

import com.buncord.bunmod.BunMod;
import com.buncord.bunmod.client.models.GerryModel;
import com.buncord.bunmod.entities.GerryEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class GerryRenderer extends MobRenderer<GerryEntity, GerryModel<GerryEntity>> {
  private static final ResourceLocation RABBIT_GERRY_LOCATION =
      new ResourceLocation(BunMod.MODID, "textures/entities/gerry.png");

  public GerryRenderer(EntityRendererProvider.Context context) {
    super(context, new GerryModel<>(context.bakeLayer(GerryModel.LAYER_LOCATION)), 0.3F);
  }

  public @NotNull ResourceLocation getTextureLocation(@NotNull GerryEntity entity) {
    return RABBIT_GERRY_LOCATION;
  }

}
