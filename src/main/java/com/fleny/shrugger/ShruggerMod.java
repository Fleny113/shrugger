package com.fleny.shrugger;

import com.mojang.logging.LogUtils;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.server.command.ConfigCommand;
import org.slf4j.Logger;

@Mod(ShruggerMod.MODID)
public class ShruggerMod
{
    public static final String MODID = "shrugger";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public ShruggerMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for mod loading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("Hello world, ready to be shrugging ¯\\_(ツ)_/¯");
    }

    @Mod.EventBusSubscriber(modid = ShruggerMod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class ModEvents {
        private static final Logger LOGGER = LogUtils.getLogger();

        @SubscribeEvent
        public static void onCommandsRegister(RegisterCommandsEvent event) {
            LOGGER.info("Registering commands...");

            new ShrugCommand(event.getDispatcher());

            ConfigCommand.register(event.getDispatcher());
        }
    }
}
