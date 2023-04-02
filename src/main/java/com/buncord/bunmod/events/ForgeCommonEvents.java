package com.buncord.bunmod.events;

import com.buncord.bunmod.BunMod;
import com.buncord.bunmod.entities.FfionEntity;
import com.buncord.bunmod.entities.GenevieveEntity;
import com.buncord.bunmod.entities.GerryEntity;
import com.buncord.bunmod.init.EntityInit;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.NameTagItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.EntityInteract;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = BunMod.MODID, bus = Bus.FORGE)
public class ForgeCommonEvents {

  private static final Logger LOGGER = LogManager.getLogger();

  @SubscribeEvent
  public static void onEntityInteract(EntityInteract event) {
    Player entityPlayer = event.getPlayer();
    Entity target = event.getTarget();
    Level level = target.getLevel();

    if (!level.isClientSide() && target instanceof Rabbit) {
      ItemStack itemStack = entityPlayer.getMainHandItem();
      Item item = itemStack.getItem();

      if (item instanceof NameTagItem && itemStack.hasCustomHoverName()) {
        Component component = itemStack.getHoverName();
        String name = component.getContents();

        if (GerryEntity.NICKNAMES.contains(name.toLowerCase())) {
          GerryEntity gerry = new GerryEntity(EntityInit.GERRY.get(), level);
          gerry.copyPosition(target);
          gerry.setCustomName(component);
          gerry.setCustomNameVisible(true);
          gerry.setInvulnerable(true);

          level.addFreshEntity(gerry);
          target.discard();
        } else if (FfionEntity.NICKNAMES.contains(name.toLowerCase())) {
          FfionEntity ffion = new FfionEntity(EntityInit.FFION.get(), level);
          ffion.copyPosition(target);
          ffion.setCustomName(component);
          ffion.setCustomNameVisible(true);
          ffion.setInvulnerable(true);

          level.addFreshEntity(ffion);
          target.discard();
        } else if (GenevieveEntity.NICKNAMES.contains(name.toLowerCase())) {
          GenevieveEntity genevieve = new GenevieveEntity(EntityInit.GENEVIEVE.get(), level);
          genevieve.copyPosition(target);
          genevieve.setCustomName(component);
          genevieve.setCustomNameVisible(true);
          genevieve.setInvulnerable(true);

          level.addFreshEntity(genevieve);
          target.discard();
        }
      }
    }
  }

}
