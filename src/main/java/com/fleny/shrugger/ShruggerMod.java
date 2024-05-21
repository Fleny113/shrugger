package com.fleny.shrugger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(ShruggerMod.MODID)
public class ShruggerMod
{
    public static final String MODID = "shrugger";

    public ShruggerMod()
    {
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @Mod.EventBusSubscriber(modid = ShruggerMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class ModEvents {
        @SubscribeEvent
        public static void onCommandsRegister(RegisterCommandsEvent event) {
            new ShrugCommand(event.getDispatcher());
        }
    }
}
