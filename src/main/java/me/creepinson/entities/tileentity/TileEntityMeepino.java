package me.creepinson.entities.tileentity;

import me.creepinson.blocks.BlockMeepino;
import net.minecraft.block.BlockClay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class TileEntityMeepino extends TileEntity implements ITickable, ICapabilityProvider{

Minecraft mc = Minecraft.getMinecraft();
WorldClient world = mc.world;
	
	public TileEntityMeepino() {
		

	}
	@Override
	public void update() {
		
		if(BlockMeepino.isActivated){
			
			BlockPos frontC = new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ() + 1);
			BlockPos backC = new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ() - 1);
			BlockPos veryCenterBlock = new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ());
		
	this.world.setBlockToAir(veryCenterBlock);
	this.world.setBlockState(backC, Blocks.LAPIS_BLOCK.getDefaultState());
	this.world.setBlockState(frontC, Blocks.LAPIS_BLOCK.getDefaultState());
	    BlockMeepino.isActivated = false;
		}
		
		
		
	}

}

	
