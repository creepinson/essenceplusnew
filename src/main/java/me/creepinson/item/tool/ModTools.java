package me.creepinson.item.tool;


import me.creepinson.lib.RefStrings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTools extends Item {
public static void Main(){

}

public final Item.ToolMaterial material;



public ModTools(Item.ToolMaterial material){

	  this.material = material;
      this.maxStackSize = 1;
      this.setMaxDamage(material.getMaxUses());

      
	 }
public ModTools(Item.ToolMaterial material, CreativeTabs tab){

	  this.material = material;
    this.maxStackSize = 1;
    this.setMaxDamage(material.getMaxUses());
    this.setCreativeTab(tab);
    
	 }

}
