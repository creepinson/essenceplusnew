package me.creepinson.item;

import me.creepinson.handlers.EnumHandler;
import me.creepinson.handlers.EnumHandler.SyringeTypes;
import me.creepinson.handlers.ItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class Syringe extends ModItems{

	public Syringe(String name, CreativeTabs tab) {
		super(name, tab);
		this.setHasSubtypes(true);
		
	}
	

	@Override
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> items) {
for(int i = 0; i < SyringeTypes.values().length; i++)
	items.add(new ItemStack(item, 1, i));

	
	
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		for(int i = 0; i < SyringeTypes.values().length; i++)
		{
		if(stack.getItemDamage() == i)	{
		return this.getUnlocalizedName() + "." + EnumHandler.SyringeTypes.values()[i].getName();
		}
		
		else{
			continue;
		}
		
	
		}
		return this.getUnlocalizedName() + "." + EnumHandler.SyringeTypes.Empty.getName();
	
	}
	public boolean itemInteractionForEntity(ItemStack item, EntityPlayer user, EntityLivingBase target, EnumHand hand)
    {
  if(item.getItemDamage() == 0)
  {
	  if (target instanceof EntityZombie)
  
        {
        	 user.inventory.deleteStack(user.getHeldItem(hand));
				user.inventory.addItemStackToInventory(new ItemStack(ItemHandler.Syringe, 1, 1));
		    
            return true;
        }
  }
  else
  {
            return super.itemInteractionForEntity(item, user, target, hand);
        
	  }
  return super.itemInteractionForEntity(item, user, target, hand);
    }

	
@Override
public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer user, EnumHand hand) {

	{
		 if(user.getHeldItemMainhand().getItemDamage() == 0)
		  {
				RayTraceResult raytrace = ForgeHooks.rayTraceEyes(user, 5);
			    
			   
			    	
			    	user.inventory.deleteStack(user.getHeldItemMainhand());
					user.inventory.addItemStackToInventory(new ItemStack(ItemHandler.Syringe, 1, 2));
		  }
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, user.getHeldItemMainhand());
			    }
			 
	
}
	
			}
		

		


