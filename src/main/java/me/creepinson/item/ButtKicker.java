package me.creepinson.item;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

import me.creepinson.handlers.CreativeTabHandler;
import me.creepinson.handlers.ItemHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

public class ButtKicker {

	public ButtKicker() {
	}

	public static Item block;
	public static Object instance;

	public void load(FMLInitializationEvent event) {
		ItemStack stack = new ItemStack(block, 1);
		if (event.getSide() == Side.CLIENT)
			Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
					.register(block, 0, new ModelResourceLocation("meepersplus:buttkicker", "inventory"));

		GameRegistry.addRecipe(stack, new Object[]{"X1X", "X4X", "X7X", Character.valueOf('1'), Items.GOLD_INGOT, Character.valueOf('4'),
				new ItemStack(ItemHandler.Essence, 1, 3), Character.valueOf('7'), Items.GOLD_INGOT,});
	}

	public void generateNether(World world, Random random, int chunkX, int chunkZ) {
	}

	public void generateSurface(World world, Random random, int chunkX, int chunkZ) {
	}

	public int addFuel(ItemStack fuel) {
		return 0;
	}

	public void serverLoad(FMLServerStartingEvent event) {
	}

	public void preInit(FMLPreInitializationEvent event) {
	}

	public void registerRenderers() {
	}

	static {
		Item.ToolMaterial enumt = EnumHelper.addToolMaterial("BUTTKICKER", 2, 101, 20F, 100, 2);
		block = (Item) (new ItemSword(enumt) {
			public Set<String> getToolClasses(ItemStack stack) {
				HashMap<String, Integer> ret = new HashMap<String, Integer>();
				ret.put("sword", 2);
				return ret.keySet();
			}
			@Override
			public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
				tooltip.add(TextFormatting.LIGHT_PURPLE + "Magical...");
				
				super.addInformation(stack, playerIn, tooltip, advanced);
			}
		}).setUnlocalizedName("ButtKicker");
		block.setRegistryName("ButtKicker");
		GameRegistry.register(block);
		block.setCreativeTab(CreativeTabHandler.ESSENCEPLUS_CREEPOLA);

	}

}
