package com.buncord.bunmod.init;

import com.buncord.bunmod.BunMod;
import com.buncord.bunmod.entities.FfionEntity;
import com.buncord.bunmod.entities.GerryEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityInit {

  private static final Logger LOGGER = LogManager.getLogger();

  public static final DeferredRegister<EntityType<?>> ENTITIES =
      DeferredRegister.create(ForgeRegistries.ENTITIES, BunMod.MODID);

  public static final RegistryObject<EntityType<GerryEntity>> GERRY = ENTITIES.register(
      "gerry",
      () -> EntityType.Builder.of(GerryEntity::new, MobCategory.CREATURE)
                              .sized(0.4F, 0.5F)
                              .clientTrackingRange(8)
                              .build(BunMod.MODID + ":gerry")
  );

  public static final RegistryObject<EntityType<FfionEntity>> FFION = ENTITIES.register(
      "ffion",
      () -> EntityType.Builder.of(FfionEntity::new, MobCategory.CREATURE)
                              .sized(0.4F, 0.5F)
                              .clientTrackingRange(8)
                              .build(BunMod.MODID + ":ffion")
  );

}
