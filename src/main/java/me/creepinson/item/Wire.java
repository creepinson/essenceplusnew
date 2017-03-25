package me.creepinson.item;

import me.creepinson.handlers.EnumHandler;
import me.creepinson.handlers.EnumHandler.Wires;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public class Wire extends ModItems{

	public Wire(String name, CreativeTabs tab) {
		super(name, tab);
		this.setHasSubtypes(true);
	}


	@Override
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> items) {
for(int i = 0; i < Wires.values().length; i++)
	items.add(new ItemStack(item, 1, i));

	
	
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		for(int i = 0; i <  Wires.values().length; i++)
		{
		if(stack.getItemDamage() == i)	{
		return "wire" + EnumHandler.Wires.values()[i].getName();
		}
		
		else{
			continue;
		}
		
	
		}
		return "wire" + EnumHandler.Wires.copper.getName();
	
	}
	
	
}