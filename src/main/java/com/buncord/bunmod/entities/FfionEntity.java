package com.buncord.bunmod.entities;

import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class FfionEntity extends Rabbit {

  private static final Logger LOGGER = LogManager.getLogger();

  public static final List<String> NICKNAMES = ImmutableList.of(
      "ffion",
      "fifi",
      "fion",
      "ffifi",
      "ffionn",
      "fionn"
  );

  int moreCrimeTicks;

  public FfionEntity(
      EntityType<? extends FfionEntity> entity,
      Level level
  )
  {
    super(entity, level);
    this.setRabbitType(7);
    this.goalSelector.addGoal(12, new FfionEntity.DoCrimeGoal(this));
  }

  public void addAdditionalSaveData(@NotNull CompoundTag compoundTag) {
    super.addAdditionalSaveData(compoundTag);
    compoundTag.putInt("MoreCrimeTicks", this.moreCrimeTicks);
  }

  public void readAdditionalSaveData(@NotNull CompoundTag compoundTag) {
    super.readAdditionalSaveData(compoundTag);
    this.moreCrimeTicks = compoundTag.getInt("MoreCrimeTicks");
  }

  public void customServerAiStep() {
    super.customServerAiStep();

    if (this.moreCrimeTicks > 0) {
      this.moreCrimeTicks -= this.random.nextInt(2);
      if (this.moreCrimeTicks < 0) {
        this.moreCrimeTicks = 0;
      }
    }
  }

  static class DoCrimeGoal extends MoveToBlockGoal {
    private final FfionEntity ffionEntity;
    private boolean wantsToCrime;
    private boolean canCrime;

    public DoCrimeGoal(FfionEntity ffionEntity) {
      super(ffionEntity, 1.0F, 16);
      this.ffionEntity = ffionEntity;
    }

    public boolean canUse() {
      if (this.nextStartTick <= 0) {
        if (!net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(
            this.ffionEntity.level,
            this.ffionEntity
        )) {
          return false;
        }

        this.canCrime = false;
        this.wantsToCrime = true;
      }

      return super.canUse();
    }

    public boolean canContinueToUse() {
      return this.canCrime && super.canContinueToUse();
    }

    public void tick() {
      super.tick();

      this.ffionEntity.getLookControl().setLookAt(
          (double)this.blockPos.getX() + 0.5D,
          (this.blockPos.getY() + 1),
          (double)this.blockPos.getZ() + 0.5D,
          10.0F,
          (float)this.ffionEntity.getMaxHeadXRot()
      );

      if (this.isReachedTarget()) {
        Level level = this.ffionEntity.level;
        BlockPos blockpos = this.blockPos.above();
        BlockState blockstate = level.getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (this.canCrime && block instanceof CarpetBlock) {
          level.destroyBlock(blockpos, true, this.ffionEntity);

          this.ffionEntity.moreCrimeTicks = 40;
        }

        this.canCrime = false;
        this.nextStartTick = 10;
      }
    }

    protected boolean isValidTarget(LevelReader levelReader, @NotNull BlockPos blockPos) {
      BlockState blockstateAbove = levelReader.getBlockState(blockPos.above());
      Block blockAbove = blockstateAbove.getBlock();

      if (blockAbove instanceof CarpetBlock && this.wantsToCrime && !this.canCrime) {
        this.canCrime = true;
        return true;
      }

      return false;
    }
  }

}
