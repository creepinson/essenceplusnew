package me.creepinson.main;

import java.util.concurrent.Callable;

import me.creepinson.capability.IMagicalDust;
import me.creepinson.capability.MagicDustStorage;
import me.creepinson.capability.MagicalDust;
import me.creepinson.entities.tileentity.TileEntityPedastal_Magic;
import me.creepinson.handlers.BlockHandler;
import me.creepinson.handlers.CraftingHandler;
import me.creepinson.handlers.ItemHandler;
import me.creepinson.lib.IProxy;
import me.creepinson.lib.RefStrings;
import me.creepinson.packet.GetManaHandler;
import me.creepinson.packet.PacketGetMana;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class CommonProxy implements IProxy {
	public void registerRenderers(Main ins) {
	}

	@Override
	public void preInit() {
		BlockHandler.init();
		BlockHandler.register();

		ItemHandler.init();
		ItemHandler.register();

		
	}

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("meepersplus");

	@Override
	public void init() {

		GameRegistry.registerTileEntity(TileEntityPedastal_Magic.class,
				RefStrings.MODID + ":" + "tileEntityPedastalMagic");
		CapabilityManager.INSTANCE.register(IMagicalDust.class, new MagicDustStorage(),  new Factory());
		INSTANCE.registerMessage(GetManaHandler.class, PacketGetMana.class, 0, Side.SERVER);
		
	
	}
	
	private static class Factory implements Callable<IMagicalDust> {

		  @Override
		  public IMagicalDust call() throws Exception {
		    return new MagicalDust();
		  }
		}
	
	
	@Override
	public void postInit() {

		CraftingHandler.init();

	}

	public static void registerModelBakeryVarients() {

		ModelBakery.registerItemVariants(ItemHandler.Syringe, new ResourceLocation(RefStrings.MODID, "Syringe_Empty"),
				new ResourceLocation(RefStrings.MODID, "Syringe_Full_Zombie"),
				new ResourceLocation(RefStrings.MODID, "Syringe_Full_Player"));
		ModelBakery.registerItemVariants(ItemHandler.Core, new ResourceLocation(RefStrings.MODID, "FireCore"),
				new ResourceLocation(RefStrings.MODID, "FriendlyCore"),
				new ResourceLocation(RefStrings.MODID, "LifeCore"));
		ModelBakery.registerItemVariants(ItemHandler.Essence, new ResourceLocation(RefStrings.MODID, "FireEssence"),
				new ResourceLocation(RefStrings.MODID, "BloodEssence"),
				new ResourceLocation(RefStrings.MODID, "LifeEssence"));

	}

	public void registerModelBakeryStuff() {

	}
}