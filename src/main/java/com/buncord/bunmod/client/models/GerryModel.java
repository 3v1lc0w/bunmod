package com.buncord.bunmod.client.models;

import com.buncord.bunmod.BunMod;
import com.buncord.bunmod.entities.GerryEntity;
import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class GerryModel<T extends GerryEntity> extends EntityModel<T> {
  public static final ModelLayerLocation LAYER_LOCATION =
      new ModelLayerLocation(new ResourceLocation(BunMod.MODID, "gerry"), "main");

  private final ModelPart leftRearFoot;
  private final ModelPart rightRearFoot;
  private final ModelPart leftHaunch;
  private final ModelPart rightHaunch;
  private final ModelPart body;
  private final ModelPart leftFrontLeg;
  private final ModelPart rightFrontLeg;
  private final ModelPart head;
  private final ModelPart rightEar;
  private final ModelPart leftEar;
  private final ModelPart tail;
  private final ModelPart nose;
  private float jumpRotation;

  public GerryModel(ModelPart modelPart) {
    this.leftRearFoot = modelPart.getChild("left_hind_foot");
    this.rightRearFoot = modelPart.getChild("right_hind_foot");
    this.leftHaunch = modelPart.getChild("left_haunch");
    this.rightHaunch = modelPart.getChild("right_haunch");
    this.body = modelPart.getChild("body");
    this.leftFrontLeg = modelPart.getChild("left_front_leg");
    this.rightFrontLeg = modelPart.getChild("right_front_leg");
    this.head = modelPart.getChild("head");
    this.rightEar = modelPart.getChild("right_ear");
    this.leftEar = modelPart.getChild("left_ear");
    this.tail = modelPart.getChild("tail");
    this.nose = modelPart.getChild("nose");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();
    partdefinition.addOrReplaceChild("left_hind_foot", CubeListBuilder.create().texOffs(26, 24).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F), PartPose.offset(3.0F, 17.5F, 3.7F));
    partdefinition.addOrReplaceChild("right_hind_foot", CubeListBuilder.create().texOffs(8, 24).addBox(-1.0F, 5.5F, -3.7F, 2.0F, 1.0F, 7.0F), PartPose.offset(-3.0F, 17.5F, 3.7F));
    partdefinition.addOrReplaceChild("left_haunch", CubeListBuilder.create().texOffs(30, 15).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 5.0F), PartPose.offsetAndRotation(3.0F, 17.5F, 3.7F, -0.34906584F, 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("right_haunch", CubeListBuilder.create().texOffs(16, 15).addBox(-1.0F, 0.0F, 0.0F, 2.0F, 4.0F, 5.0F), PartPose.offsetAndRotation(-3.0F, 17.5F, 3.7F, -0.34906584F, 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.0F, -10.0F, 6.0F, 5.0F, 10.0F), PartPose.offsetAndRotation(0.0F, 19.0F, 8.0F, -0.34906584F, 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("left_front_leg", CubeListBuilder.create().texOffs(8, 15).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F), PartPose.offsetAndRotation(3.0F, 17.0F, -1.0F, -0.17453292F, 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("right_front_leg", CubeListBuilder.create().texOffs(0, 15).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 7.0F, 2.0F), PartPose.offsetAndRotation(-3.0F, 17.0F, -1.0F, -0.17453292F, 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(32, 0).addBox(-2.5F, -4.0F, -5.0F, 5.0F, 4.0F, 5.0F), PartPose.offset(0.0F, 16.0F, -1.0F));
    partdefinition.addOrReplaceChild("right_ear", CubeListBuilder.create().texOffs(52, 0).addBox(-2.5F, -9.0F, -1.0F, 2.0F, 5.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 16.0F, -1.0F, 0.0F, -0.2617994F, 0.0F));
    partdefinition.addOrReplaceChild("left_ear", CubeListBuilder.create().texOffs(58, 0).addBox(0.5F, -9.0F, -1.0F, 2.0F, 5.0F, 1.0F), PartPose.offsetAndRotation(0.0F, 16.0F, -1.0F, 0.0F, 0.2617994F, 0.0F));
    partdefinition.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(52, 6).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 2.0F), PartPose.offsetAndRotation(0.0F, 20.0F, 7.0F, -0.3490659F, 0.0F, 0.0F));
    partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(32, 9).addBox(-0.5F, -2.5F, -5.5F, 1.0F, 1.0F, 1.0F), PartPose.offset(0.0F, 16.0F, -1.0F));
    return LayerDefinition.create(meshdefinition, 64, 32);
  }

  public void renderToBuffer(
      @NotNull PoseStack poseStack,
      @NotNull VertexConsumer vertexConsumer,
      int uv2,
      int overlayCoords,
      float r,
      float g,
      float b,
      float a
  ) {
    poseStack.pushPose();
    poseStack.scale(0.84999999F, 0.84999999F, 0.84999999F);
    poseStack.translate(0.0D, 0.5D, 0D);
    ImmutableList.of(this.head, this.leftEar, this.rightEar, this.nose).forEach((modelPart) ->
        modelPart.render(poseStack, vertexConsumer, uv2, overlayCoords, r, g, b, a)
    );
    poseStack.popPose();

    poseStack.pushPose();
    poseStack.scale(0.6F, 0.6F, 0.6F);
    poseStack.translate(0.0D, 1.0D, 0.0D);
    ImmutableList.of(
        this.leftRearFoot, this.rightRearFoot, this.leftHaunch,
        this.rightHaunch, this.body, this.leftFrontLeg, this.rightFrontLeg
    ).forEach((modelPart) ->
        modelPart.render(poseStack, vertexConsumer, uv2, overlayCoords, r, g, b, a));
    poseStack.popPose();

    poseStack.pushPose();
    poseStack.scale(0.4F, 0.4F, 0.4F);
    poseStack.translate(0.0D, 2.20D, 0.25D);
    ImmutableList.of(this.tail).forEach((modelPart) ->
        modelPart.render(poseStack, vertexConsumer, uv2, overlayCoords, r, g, b, a));
    poseStack.popPose();
  }

  public void setupAnim(
      T entity,
      float animationPosition,
      float animationSpeed,
      float partialTick,
      float headYRot,
      float headXRot
  ) {
    float f = partialTick - (float)entity.tickCount;
    this.nose.xRot = headXRot * ((float)Math.PI / 180F);
    this.head.xRot = headXRot * ((float)Math.PI / 180F);
    this.rightEar.xRot = headXRot * ((float)Math.PI / 180F);
    this.leftEar.xRot = headXRot * ((float)Math.PI / 180F);
    this.nose.yRot = headYRot * ((float)Math.PI / 180F);
    this.head.yRot = headYRot * ((float)Math.PI / 180F);
    this.rightEar.yRot = this.nose.yRot - 0.2617994F;
    this.leftEar.yRot = this.nose.yRot + 0.2617994F;
    this.jumpRotation = Mth.sin(entity.getJumpCompletion(f) * (float)Math.PI);
    this.leftHaunch.xRot = (this.jumpRotation * 50.0F - 21.0F) * ((float)Math.PI / 180F);
    this.rightHaunch.xRot = (this.jumpRotation * 50.0F - 21.0F) * ((float)Math.PI / 180F);
    this.leftRearFoot.xRot = this.jumpRotation * 50.0F * ((float)Math.PI / 180F);
    this.rightRearFoot.xRot = this.jumpRotation * 50.0F * ((float)Math.PI / 180F);
    this.leftFrontLeg.xRot = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
    this.rightFrontLeg.xRot = (this.jumpRotation * -40.0F - 11.0F) * ((float)Math.PI / 180F);
  }

  public void prepareMobModel(
      @NotNull T entity,
      float animationPosition,
      float animationSpeed,
      float partialTick
  ) {
    super.prepareMobModel(entity, animationPosition, animationSpeed, partialTick);
    this.jumpRotation = Mth.sin(entity.getJumpCompletion(partialTick) * (float)Math.PI);
  }

}
