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

		ins.creepino.registerRenderers();
		ins.bullet.registerRenderers();
		ins.buttKicker.registerRenderers();
		ins.mobChamber.registerRenderers();
		ins.alexion.registerRenderers();
		ins.craeol.registerRenderers();
        ins.neote.registerRenderers();
		
		
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
