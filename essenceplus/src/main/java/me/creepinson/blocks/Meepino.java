package me.creepinson.blocks;

import java.util.Random;

import me.creepinson.blocks.item.ItemMeepino;
import me.creepinson.entities.tileentity.TileEntityMeepino;
import me.creepinson.handlers.CreativeTabHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class Meepino {

	public Meepino() {
	}

	public static BlockMeepino block;
	public static ItemMeepino meepinoItem;
	public static Object instance;

	public int addFuel(ItemStack fuel) {
		return 0;
	}

	public void serverLoad(FMLServerStartingEvent event) {
	}

	public void preInit(FMLPreInitializationEvent event) {
		block.setRegistryName("meepino");
		registerBlock(block, meepinoItem, CreativeTabHandler.ESSENCEPLUS_BASE);

	}

	public static void registerBlock(Block block, ItemBlock itemblock, CreativeTabs tab) {
		block.setCreativeTab(tab);
		GameRegistry.register(block);
		GameRegistry.register(meepinoItem.setRegistryName(block.getRegistryName()));

	}

	static {

		block = (BlockMeepino) (new BlockMeepino().setHardness(1.95F).setResistance(5.0F).setLightLevel(0.15F)
				.setUnlocalizedName("meepino").setLightOpacity(7));
		block.setHarvestLevel("pickaxe", 3);
		meepinoItem = new ItemMeepino(block);
	}

	public void registerRenderers() {
	}

	public void load(FMLInitializationEvent event) {
		GameRegistry.registerTileEntity(TileEntityMeepino.class, "TileEntityMeepino");
		if (event.getSide() == Side.CLIENT) {
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0,
					new ModelResourceLocation("meepersplus:meepino", "inventory"));
		}
	}

	public void generateSurface(World world, Random random, int chunkX, int chunkZ) {
	}

	public void generateNether(World world, Random random, int chunkX, int chunkZ) {
	}

}
