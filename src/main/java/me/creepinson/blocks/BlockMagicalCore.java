package me.creepinson.blocks;

import java.awt.Robot;
import java.util.Timer;
import java.util.TimerTask;

import javax.annotation.Nullable;

import me.creepinson.blocks.base.ModBlocks;
import me.creepinson.entities.tileentity.TileEntityPedastal_Magic;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class BlockMagicalCore extends ModBlocks {

	public BlockMagicalCore(Material mat, String name, CreativeTabs tab, float hardness, float resistance, int harvest, String tool) {
		  super(mat, name, tab, hardness, resistance, harvest, tool);
		 }
	

		
	    
@Override
public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,	EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	
	  
	   
	  
	  if(!worldIn.isRemote){
		  BlockPos topRight = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1);
		  BlockPos topLeft = new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1);
		  BlockPos bottomRight = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1);
		  BlockPos bottomLeft = new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1);
			
		  TileEntity te1 = worldIn.getTileEntity(topRight);
		  TileEntity te2 = worldIn.getTileEntity(topLeft);
		  TileEntity te3 = worldIn.getTileEntity(bottomRight);
		  TileEntity te4 = worldIn.getTileEntity(bottomLeft);
		  
		  if(te1 instanceof TileEntityPedastal_Magic		 && te2 instanceof TileEntityPedastal_Magic && te3 instanceof TileEntityPedastal_Magic && te4 instanceof TileEntityPedastal_Magic){
           if(((TileEntityPedastal_Magic) te1).getStack().getItem() == Items.BONE && ((TileEntityPedastal_Magic) te2).getStack().getItem() == Items.BONE && ((TileEntityPedastal_Magic) te3).getStack().getItem() == Items.BONE && ((TileEntityPedastal_Magic) te4).getStack().getItem() == Items.BONE){
        	    
				 ((TileEntityPedastal_Magic) te1).setStack(null);
				((TileEntityPedastal_Magic) te2).setStack(null);
				((TileEntityPedastal_Magic) te3).setStack(null);
				((TileEntityPedastal_Magic) te4).setStack(null);
			
               
               EntityItem itemResult1 = new EntityItem(worldIn, pos.getX(), pos.getY() + 1, pos.getZ(), new ItemStack(me.creepinson.handlers.ItemHandler.boneLarge, 1));
               worldIn.spawnParticle(EnumParticleTypes.CLOUD, pos.getX(), pos.getY() + 1, pos.getZ(), 0.0D, 0.0D, 0.0D);
         	  
     		  worldIn.spawnEntity(itemResult1);
     		 
           }
           else{
  			 playerIn.sendMessage(new TextComponentTranslation(TextFormatting.AQUA + "[EssencePlus] " + TextFormatting.RED + "Invalid Recipe!"));
           }
		  }
           else{
        	   playerIn.sendMessage(new TextComponentTranslation(TextFormatting.AQUA + "[EssencePlus] " + TextFormatting.RED + "No Pedastals Found!"));
           }
		  }
		
		  
		  return true;
	}
	
	
    
}
