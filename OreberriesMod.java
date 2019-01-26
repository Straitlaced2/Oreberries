package com.straitlaced.oreberries;

import com.straitlaced.oreberries.proxy.CommonProxy;
import com.straitlaced.oreberries.server.command.VillagerCommand;
import com.straitlaced.oreberries.server.init.ModRecipes;
import com.straitlaced.oreberries.server.init.OreberryOreDictRegistry;
import com.straitlaced.oreberries.server.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;


@Plugin(name="OreberriesMod", category = "STDOUT")
@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class OreberriesMod {

    @Mod.Instance(Reference.MODID)
    public static OreberriesMod INSTANCE;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
    public static CommonProxy PROXY;

    public static Logger LOGGER;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        LOGGER = event.getModLog();
        PROXY.preInit();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        PROXY.Init();
    }

    @Mod.EventHandler
    public void init(FMLPostInitializationEvent event) {
        PROXY.postInit();
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand(new VillagerCommand());
    }
}

