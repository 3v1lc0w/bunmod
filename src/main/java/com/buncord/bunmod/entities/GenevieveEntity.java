package com.buncord.bunmod.entities;

import com.google.common.collect.ImmutableList;
import java.util.List;
import java.util.Set;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.WrappedGoal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GenevieveEntity extends Rabbit {

  private static final Logger LOGGER = LogManager.getLogger();

  public static final List<String> NICKNAMES = ImmutableList.of(
      "genevieve",
      "genebun",
      "brionybun",
      "bunlove",
      "bunnybun"
  );

  public GenevieveEntity(
      EntityType<? extends GenevieveEntity> entity,
      Level level
  )
  {
    super(entity, level);
    this.setRabbitType(8);

    Set<WrappedGoal> availableGoals = this.goalSelector.getAvailableGoals();
    availableGoals.removeIf(wrappedGoal -> wrappedGoal.getGoal().getClass().getName().equals(
        "net.minecraft.world.entity.ai.goal.LookAtPlayerGoal"
    ));

    this.goalSelector.addGoal(
        11,
        new GenevieveLookAtPlayerGoal(this, Player.class, 10.0F)
    );
  }

  private static class GenevieveLookAtPlayerGoal extends LookAtPlayerGoal {

    public GenevieveLookAtPlayerGoal(
        GenevieveEntity mob,
        Class<? extends LivingEntity> target,
        float lookDistance
    ) {
      super(mob, target, lookDistance);
    }

    @Override
    public boolean canUse() {
      if (this.mob.getRandom().nextFloat() >= this.probability) {
        return false;
      }
      else {
        if (this.mob.getTarget() != null) {
          this.lookAt = this.mob.getTarget();
        }

        if (this.lookAtType == Player.class) {
          this.lookAt = this.mob.level.getNearestPlayer(
              this.lookAtContext,
              this.mob,
              this.mob.getX(),
              this.mob.getEyeY(),
              this.mob.getZ()
          );

          if (
              this.lookAt != null &&
              "brionykay".equalsIgnoreCase(this.lookAt.getName().getContents())
          ) {
            ServerLevel serverLevel = (ServerLevel) this.mob.getLevel();

            ParticleOptions particleoptions = ParticleTypes.HEART;

            for(int i = 0; i < 7; ++i) {
              double d0 = this.mob.getRandom().nextGaussian() * 0.02D;
              double d1 = this.mob.getRandom().nextGaussian() * 0.02D;
              double d2 = this.mob.getRandom().nextGaussian() * 0.02D;
              serverLevel.sendParticles(
                  particleoptions,
                  this.mob.getRandomX(1.0D),
                  this.mob.getRandomY() + 0.5D,
                  this.mob.getRandomZ(1.0D),
                  1,
                  d0,
                  d1,
                  d2,
                  0.0F
              );
            }

          }
        }
        else {
          this.lookAt = this.mob.level.getNearestEntity(this.mob.level.getEntitiesOfClass(
              this.lookAtType,
              this.mob.getBoundingBox()
                      .inflate(this.lookDistance, 3.0D, this.lookDistance),
              (p_148124_) -> true
          ), this.lookAtContext, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        }

        return this.lookAt != null;
      }
    }
  }

}
