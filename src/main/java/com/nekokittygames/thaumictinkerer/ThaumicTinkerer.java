package com.nekokittygames.thaumictinkerer;

import com.nekokittygames.thaumictinkerer.common.foci.FocusEffectDislocate;
import com.nekokittygames.thaumictinkerer.common.libs.LibMisc;
import com.nekokittygames.thaumictinkerer.common.proxy.ITTProxy;
import com.nekokittygames.thaumictinkerer.common.recipes.ModRecipes;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;
import org.lwjgl.util.Color;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.casters.FocusEngine;
import thaumcraft.api.casters.FocusNode;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;
import thaumcraft.proxies.IProxy;

@Mod(modid = LibMisc.MOD_ID, name = LibMisc.MOD_NAME, version = LibMisc.MOD_VERSION,dependencies = LibMisc.MOD_DEPENDENCIES)
public class ThaumicTinkerer
{
    public static Logger logger;

    @SidedProxy(serverSide = "com.nekokittygames.thaumictinkerer.common.proxy.CommonProxy",clientSide = "com.nekokittygames.thaumictinkerer.client.proxy.ClientProxy")
    public static ITTProxy proxy;



    @Mod.Instance(LibMisc.MOD_ID)
    public static ThaumicTinkerer instance;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {

        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        ModRecipes.InitializeRecipes();
        ResearchCategories.registerCategory("THAUMIC_TINKERER",(String)null,new AspectList(),new ResourceLocation("thaumictinkerer","textures/items/share_book.png"),new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_1.jpg"),new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
        ThaumcraftApi.registerResearchLocation(new ResourceLocation("thaumictinkerer", "research/misc" ));
        ThaumcraftApi.registerResearchLocation(new ResourceLocation("thaumictinkerer", "research/baubles" ));
        ThaumcraftApi.registerResearchLocation(new ResourceLocation("thaumictinkerer", "research/machines" ));
        proxy.registerRenderers();
        FocusEngine.registerElement(FocusEffectDislocate.class,new ResourceLocation("thaumictinkerer","blocks/dark_quartz_block"), 8760709);

    }
}

