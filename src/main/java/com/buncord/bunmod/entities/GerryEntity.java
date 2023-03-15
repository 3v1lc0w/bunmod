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

    protected @NotNull PathFinder createPathFinder(int maxVisitedNodes) {
      this.nodeEvaluator = new GerryEntity.GerryNodeEvaluator();
      return new PathFinder(this.nodeEvaluator, maxVisitedNodes);
    }
  }

  static class GerryNodeEvaluator extends WalkNodeEvaluator {
    private final BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

    public @NotNull BlockPathTypes getBlockPathType(BlockGetter blockGetter, int x, int y, int z) {
      this.pos.set(x, y , z);

      BlockState blockstate = blockGetter.getBlockState(this.pos);
      Block block = blockstate.getBlock();

      return !(block instanceof CarpetBlock)
             ? BlockPathTypes.BLOCKED
             : getBlockPathTypeStatic(blockGetter, this.pos);
    }
  }

}
