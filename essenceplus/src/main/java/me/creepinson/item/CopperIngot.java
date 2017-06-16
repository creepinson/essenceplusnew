package me.creepinson.item;

import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;

import me.creepinson.capability.IMagicalDust;
import me.creepinson.capability.MagicDustProvider;
import me.creepinson.handlers.EnumHandler;
import me.creepinson.handlers.EnumHandler.Cores;
import me.creepinson.handlers.EnumHandler.SyringeTypes;
import me.creepinson.lib.RefStrings;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class CopperIngot extends ModItems {

	public CopperIngot(String name, CreativeTabs tab) {
		super(name, tab);
	}


	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer user, EnumHand hand)

	{
		if (!world.isRemote) {
			RayTraceResult pos = user.rayTrace(100, 20);
			double x = pos.getBlockPos().getX();
			double y = pos.getBlockPos().getY();
			double z = pos.getBlockPos().getZ();

			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, new ItemStack(this));
		}

		else {
			return new ActionResult(EnumActionResult.FAIL, new ItemStack(this));
		}

	}
}