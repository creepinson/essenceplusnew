package me.creepinson.handlers;


import me.creepinson.entities.tileentity.TESRPedastal_Magic;
import me.creepinson.entities.tileentity.TileEntityPedastal_Magic;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class BlockHandler {
	public static Block BlockMobChamberCore;
	public static ItemBlock MobChamberCore;
	public static Block BlockMobChamber;
	public static ItemBlock MobChamber;
	public static Block BlockPedastal_Magic;
	public static ItemBlock Pedastal_Magic;
	
	public static void init(){
	 
		 
	   
	    BlockMobChamberCore = new me.creepinson.blocks.BlockMagicalCore(Material.ROCK, "MobChamberCore", CreativeTabHandler.ESSENCEPLUS_BASE, 5F, 15F, 3, "pickaxe");
		  
	    MobChamberCore = (ItemBlock) new ItemBlock(BlockMobChamberCore);

	    BlockPedastal_Magic = new me.creepinson.blocks.Pedastal_Magic(Material.ROCK, "pedastal_magic", CreativeTabHandler.ESSENCEPLUS_BASE, 5F, 15F, 3, "pedastal_magic");
		  
	    Pedastal_Magic = (ItemBlock) new ItemBlock(BlockPedastal_Magic);
	
	    
	}
	 
	 public static void register(){
	
		  GameRegistry.register(BlockMobChamberCore);
		  GameRegistry.register(MobChamberCore, BlockMobChamberCore.getRegistryName());
		  GameRegistry.register(BlockPedastal_Magic);
		  GameRegistry.register(Pedastal_Magic, BlockPedastal_Magic.getRegistryName());
	 
		  
	 }
	 
	 public static void registerRenders(){
	 
		 registerRender(BlockMobChamber);
		 registerRender(BlockMobChamberCore);
		 registerRender(BlockPedastal_Magic);
	
	 }
	 
	 public static void registerRender(Block block){
	 
Item item = Item.getItemFromBlock(block);
ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	 
	 }
	 
	 
}
