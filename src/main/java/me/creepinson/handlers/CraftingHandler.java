package me.creepinson.handlers;

import java.util.UUID;

import me.creepinson.blocks.Meepino;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CraftingHandler {

	public static void init(){
	
		GameRegistry.addRecipe(new ItemStack(ItemHandler.Core, 2, 0), " B ", "BXB", " B ", 'X', new ItemStack(ItemHandler.Essence, 1, 0), 'B', Items.IRON_INGOT);
		GameRegistry.addRecipe(new ItemStack(ItemHandler.StickOfLightning, 1), " F ", " R ", " R ", 'F', new ItemStack(ItemHandler.Core, 1, 0), 'R', Items.STICK);
		
	
		GameRegistry.addShapedRecipe(new ItemStack(Meepino.meepinoItem, 2, 1), " L ", "LCL", " L ", 'L', new ItemStack(Items.DYE, 1, 4), 'C', new ItemStack(ItemHandler.Chip, 1, 0));

		NBTTagCompound c1 = new NBTTagCompound();
		c1.setUniqueId("playerUUID", UUID.randomUUID());
		ItemStack output1 = new ItemStack(ItemHandler.upgrade, 2, 0);
		output1.setTagCompound(c1);
		GameRegistry.addShapedRecipe(output1, " I ", "ICI", " I ", 'I', Items.IRON_INGOT, 'C', new ItemStack(ItemHandler.Chip, 1, 0));
	    
		
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(ItemHandler.Wire, 3, 0), "   ", "III", "   ", 'I', new ItemStack(ItemHandler.CopperIngot, 1)));
        
		GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.Chip, 2, 0), "SWS", "WIW", "SWS", 'W', new ItemStack(ItemHandler.Wire, 1, 0), 'S', new ItemStack(Items.STICK, 1), 'I', Items.IRON_INGOT);
		GameRegistry.addShapedRecipe(new ItemStack(ItemHandler.Chip, 2, 1), " R ", "RCR", " R ", 'R', new ItemStack(Items.REDSTONE, 1), 'C', new ItemStack(ItemHandler.Chip, 1, 0));
	     OreDictionary.registerOre("test", ItemHandler.CopperIngot);
	    
		
		
		
	}
	
	
	
}
