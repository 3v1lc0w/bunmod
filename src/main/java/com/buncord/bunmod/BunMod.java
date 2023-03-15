package com.buncord.bunmod;

import com.buncord.bunmod.init.EntityInit;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(BunMod.MODID)
public class BunMod
{
    public static final String MODID = "bunmod";

    private static final Logger LOGGER = LogManager.getLogger();

    public BunMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        EntityInit.ENTITIES.register(bus);
    }

}
