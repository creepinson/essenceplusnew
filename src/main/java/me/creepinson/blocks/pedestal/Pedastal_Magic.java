
package me.creepinson.blocks.pedestal;

import me.creepinson.blocks.base.ModBlocks;
import me.creepinson.entities.tileentity.TESRPedastal_Magic;
import me.creepinson.entities.tileentity.TileEntityPedastal_Magic;
import me.creepinson.handlers.ItemHandler;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Pedastal_Magic extends ModBlocks implements ITileEntityProvider{
	public Pedastal_Magic(Material mat, String name, CreativeTabs tab, float hardness, float resistance, int harvest, String tool) {
		  super(mat, name, tab, hardness, resistance, harvest, tool);
		 }

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
	
		return new TileEntityPedastal_Magic();
	}
	@Override
	public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te,
			ItemStack stack) {
		
		if(world.isRemote){

			TileEntityPedastal_Magic teCv = (TileEntityPedastal_Magic)te;
			if(te instanceof TileEntityPedastal_Magic){
			EntityItem itemDropped = new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ());
			itemDropped.setEntityItemStack(((TileEntityPedastal_Magic) te).getStack());
			world.spawnEntity(itemDropped);
				((TileEntityPedastal_Magic) te).getStack().setCount(0);
		super.harvestBlock(world, player, pos, state, te, stack);
			}
		}
		}
	
	
	
    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(getRegistryName(), "inventory"));
        // Bind our TESR to our tile entity
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPedastal_Magic.class, new TESRPedastal_Magic());
    }

  

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }

    @Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }

    private TileEntityPedastal_Magic getTE(World world, BlockPos pos) {
        return (TileEntityPedastal_Magic) world.getTileEntity(pos);
    }
@Override
public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
	  if (world.isRemote) {
        
		
		  
		  TileEntityPedastal_Magic te = getTE(world, pos);
          
		  if(player.getHeldItem(hand).getItem() == ItemHandler.upgrade && player.getHeldItem(hand).getMetadata() == 0){
				if(!te.isLocked()){
					te.setLocked(true);
					player.sendMessage(new TextComponentTranslation(TextFormatting.AQUA + "[EssencePlus] " + TextFormatting.DARK_RED + "This pedestal is locked!"));
				}
				else {
					player.sendMessage(new TextComponentTranslation(TextFormatting.AQUA + "[EssencePlus] " + TextFormatting.DARK_GREEN + "This pedestal is has been unlocked!"));
			  te.setLocked(false);
				}
		  }
		  else{
	
		  
          if (te.getStack().isEmpty()) {
        	  if(te.isLocked()){
            	  
         		 player.sendMessage(new TextComponentTranslation(TextFormatting.AQUA + "[EssencePlus] " + TextFormatting.RED + "This pedestal is locked!"));
         	  
         	  
           }
           else{
        	  if (!player.getHeldItem(hand).isEmpty()) {
                  // There is no item in the pedestal and the player is holding an item. We move that item
                  // to the pedestal
                  te.setStack(player.getHeldItem(hand));
                  player.inventory.setInventorySlotContents(player.inventory.currentItem, ItemStack.EMPTY);
                  // Make sure the client knows about the changes in the player inventory
    
              }
           }
          } else if (!te.getStack().isEmpty()){
        	  if(te.isLocked()){
            	  
         		 player.sendMessage(new TextComponentTranslation(TextFormatting.AQUA + "[EssencePlus] " + TextFormatting.RED + "This pedestal is locked!"));
         	  
         	  
           }
           else{
        	  // There is a stack in the pedestal. In this case we remove it and try to put it in the
              // players inventory if there is room
              ItemStack stack = te.getStack();
           
              if (!player.inventory.addItemStackToInventory(stack)) {
                  // Not possible. Throw item in the world
                  EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), stack);
                  world.spawnEntity(entityItem);
              } 
              te.setStack(ItemStack.EMPTY);
          }
      }
	  }
	  }
	  
      // Return true also on the client to make sure that MC knows we handled this and will not try to place
      // a block on the client
      return true;
  }

}

	
