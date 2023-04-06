package com.buncord.bunmod.entities;

import com.google.common.collect.ImmutableList;
import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CarpetBlock;
import net.minecraft.world.level.block.GrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public class GerryEntity extends Rabbit {

  private static final Logger LOGGER = LogManager.getLogger();

  public static final List<String> NICKNAMES = ImmutableList.of(
      "gerry",
      "boy",
      "boi",
      "baby",
      "the boy",
      "the boi",
      "the baby"
  );

  public GerryEntity(
      EntityType<? extends GerryEntity> entity,
      Level level
  )
  {
    super(entity, level);
    this.setRabbitType(6);
  }

  protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
    return new GerryEntity.GerryPathNavigation(this, level);
  }

  static class GerryPathNavigation extends GroundPathNavigation {
    GerryPathNavigation(GerryEntity entity, Level level) {
      super(entity, level);
    }

    @Override
    protected @NotNull PathFinder createPathFinder(int maxVisitedNodes) {
      this.nodeEvaluator = new GerryEntity.GerryNodeEvaluator();
      return new PathFinder(this.nodeEvaluator, maxVisitedNodes);
    }
  }

  static class GerryNodeEvaluator extends WalkNodeEvaluator {

    @Override
    public @NotNull BlockPathTypes getBlockPathType(
        @NotNull BlockGetter blockGetter,
        int x,
        int y,
        int z
    ) {
      BlockPathTypes originalBlockPathType = super.getBlockPathType(blockGetter, x, y, z);

      if (originalBlockPathType == BlockPathTypes.WALKABLE) {
        BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
        blockPos.set(x, y, z);

        BlockState blockState = blockGetter.getBlockState(blockPos);
        Block block = blockState.getBlock();

        if (!(block instanceof CarpetBlock || block instanceof GrassBlock)) {
          return BlockPathTypes.BLOCKED;
        }
      }

      return originalBlockPathType;
    }
  }

}
