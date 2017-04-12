package me.creepinson.main;

import me.creepinson.entities.tileentity.TESRPedastal_Magic;
import me.creepinson.entities.tileentity.TileEntityPedastal_Magic;
import me.creepinson.events.EventHandlerMOD;
import me.creepinson.handlers.BlockHandler;
import me.creepinson.handlers.GuiHandler;
import me.creepinson.handlers.ItemHandler;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRenderers(Main ins) {
		ins.maa0.registerRenderers();
		ins.maa1.registerRenderers();
		ins.maa2.registerRenderers();
		ins.maa3.registerRenderers();
		ins.maa4.registerRenderers();
		ins.maa5.registerRenderers();
		
		
		
	}
	@Override
	public void preInit() {
		  super.preInit();
		ItemHandler.registerRenders();
		BlockHandler.registerRenders();
		
	}

	public void init() {
		  super.init();
		  ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedastal_Magic.class, new TESRPedastal_Magic());
			
NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new GuiHandler());
	}

	
}
