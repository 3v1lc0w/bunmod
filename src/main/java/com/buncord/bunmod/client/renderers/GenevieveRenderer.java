package com.buncord.bunmod.client.renderers;

import com.buncord.bunmod.BunMod;
import com.buncord.bunmod.client.models.FfionModel;
import com.buncord.bunmod.client.models.GenevieveModel;
import com.buncord.bunmod.entities.GenevieveEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class GenevieveRenderer
    extends MobRenderer<GenevieveEntity, GenevieveModel<GenevieveEntity>>
{
  private static final ResourceLocation RABBIT_GENEVIEVE_LOCATION =
      new ResourceLocation(BunMod.MODID, "textures/entities/genevieve.png");

  public GenevieveRenderer(EntityRendererProvider.Context context) {
    super(context, new GenevieveModel<>(context.bakeLayer(FfionModel.LAYER_LOCATION)), 0.3F);
  }

  public @NotNull ResourceLocation getTextureLocation(@NotNull GenevieveEntity entity) {
    return RABBIT_GENEVIEVE_LOCATION;
  }

}
