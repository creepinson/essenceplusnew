
package me.creepinson.blocks.pedestal;

import java.util.List;

import javax.annotation.Nullable;

import me.creepinson.blocks.base.ModBlocks;
import me.creepinson.entities.tileentity.TESRPedastal_Magic;
import me.creepinson.entities.tileentity.TileEntityPedastal_Magic;
import me.creepinson.handlers.ItemHandler;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
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

    public static final AxisAlignedBB BOUNDING = new AxisAlignedBB(0.0D, 0.0D, 0.D, 1.0D, 2.0D, 1.0D);

	
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
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			java.util.List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {

		  addCollisionBoxToList(pos, entityBox, collidingBoxes, BOUNDING);
		
	}

	 protected static void addCollisionBoxToList(BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable AxisAlignedBB blockBox)
	    {
	        if (blockBox != NULL_AABB)
	        {
	            AxisAlignedBB axisalignedbb = blockBox.offset(pos);

	            if (entityBox.intersectsWith(axisalignedbb))
	            {
	                collidingBoxes.add(axisalignedbb);
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
          
		 
			  
		  if((player.getHeldItem(hand).getItem() == ItemHandler.key  && player.getHeldItem(hand).getMetadata() == 0 && player.getHeldItem(hand).getTagCompound().getUniqueId("playerUUID") == te.getTileData().getUniqueId("playerUUID"))){
			  if(!te.isLockable()){ 
					player.sendMessage(new TextComponentTranslation(TextFormatting.AQUA + "[EssencePlus] Block Update>" + TextFormatting.DARK_RED + "This pedestal does not have a locking upgrade applied, therefore you cannot lock it!"));	  
			  }
			  else{
			  if(!te.isLocked()){
					te.setLocked(true);
					player.sendMessage(new TextComponentTranslation(TextFormatting.AQUA + "[EssencePlus] Security> " + TextFormatting.DARK_RED + "This pedestal has been locked!"));
				}
				else {
					player.sendMessage(new TextComponentTranslation(TextFormatting.AQUA + "[EssencePlus] Security> " + TextFormatting.DARK_GREEN + "This pedestal has been unlocked!"));
			  te.setLocked(false);
				}
		  
			  }
		  }
		  else if (player.getHeldItem(hand).getItem() == ItemHandler.key && player.getHeldItem(hand).getMetadata() == 0){
	

				if(!te.isLockable()){
					NBTTagCompound comp = new NBTTagCompound();
					comp.setUniqueId("playerUUID", player.getHeldItem(hand).getTagCompound().getUniqueId("playerUUID"));
					ItemStack key = new ItemStack(ItemHandler.key, 1, 0);
					key.setTagCompound(comp);
					te.getTileData().setUniqueId("playerUUID", player.getHeldItem(hand).getTagCompound().getUniqueId("playerUUID"));
					player.inventory.removeStackFromSlot(player.inventory.currentItem);
					te.setLockable(true);
					player.sendMessage(new TextComponentTranslation(TextFormatting.AQUA + "[EssencePlus] Block Update>" + TextFormatting.DARK_GREEN + "A locking upgrade has just been applied to this pedestal!"));
					  if (!player.inventory.addItemStackToInventory(key)) {
		                  // Not possible. Throw item in the world
		                  EntityItem entityItem = new EntityItem(world, pos.getX(), pos.getY()+1, pos.getZ(), key);
		                  world.spawnEntity(entityItem);
		              } 
				}
				else{
					 player.sendMessage(new TextComponentTranslation(TextFormatting.AQUA + "[EssencePlus]  Block Error>" + TextFormatting.RED + "You have already applied this upgrade!"));
		         	  	
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