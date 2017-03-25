package me.creepinson.handlers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabHandler {
	
	
	public static final CreativeTabs ESSENCEPLUS_CREEPOLA = new CreativeTabs("Creepola"){
	    @Override public ItemStack getTabIconItem() {
	        return new ItemStack(ItemHandler.Syringe);
	    }
	};

	public static final CreativeTabs ESSENCEPLUS_BASE = new CreativeTabs("EssencePlusBASE"){
	    @Override public ItemStack getTabIconItem() {
	        return new ItemStack(ItemHandler.Essence);
	    }
	};
	
	public static final CreativeTabs ESSENCEPLUS_TECH = new CreativeTabs("EssencePlusTECH"){
	    @Override public ItemStack getTabIconItem() {
	        return new ItemStack(ItemHandler.Chip);
	    }
	};
}
