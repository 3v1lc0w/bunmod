package com.buncord.bunmod.client.renderers;

import com.buncord.bunmod.BunMod;
import com.buncord.bunmod.client.models.FfionModel;
import com.buncord.bunmod.entities.FfionEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class FfionRenderer extends MobRenderer<FfionEntity, FfionModel<FfionEntity>> {
  private static final ResourceLocation RABBIT_FFION_LOCATION =
      new ResourceLocation(BunMod.MODID, "textures/entities/ffion.png");

  public FfionRenderer(EntityRendererProvider.Context context) {
    super(context, new FfionModel<>(context.bakeLayer(FfionModel.LAYER_LOCATION)), 0.3F);
  }

  public @NotNull ResourceLocation getTextureLocation(@NotNull FfionEntity entity) {
    return RABBIT_FFION_LOCATION;
  }

}
