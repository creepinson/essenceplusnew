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


	public TileEntityMeepino() {
		

	}
	@Override
	public void update() {
		
		if(BlockMeepino.isActivated){
			
			BlockPos frontC = new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ() + 1);
			BlockPos backC = new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ() - 1);
			BlockPos leftC = new BlockPos(this.getPos().getX() + 1, this.getPos().getY(), this.getPos().getZ() + 1);
			BlockPos rightC = new BlockPos(this.getPos().getX() - 1, this.getPos().getY(), this.getPos().getZ() - 1);
			
			BlockPos frontL = new BlockPos(this.getPos().getX() - 1, this.getPos().getY(), this.getPos().getZ() + 1);
			BlockPos backL = new BlockPos(this.getPos().getX() - 1, this.getPos().getY(), this.getPos().getZ() - 1);
			BlockPos frontR = new BlockPos(this.getPos().getX() - 1, this.getPos().getY(), this.getPos().getZ() + 1);
			BlockPos backR = new BlockPos(this.getPos().getX() + 1, this.getPos().getY(), this.getPos().getZ() - 1);
				
			
			BlockPos veryCenterBlock = new BlockPos(this.getPos().getX(), this.getPos().getY(), this.getPos().getZ());
		
	this.world.setBlockState(veryCenterBlock, Blocks.MOSSY_COBBLESTONE.getDefaultState());
	this.world.setBlockState(backC, Blocks.LAPIS_BLOCK.getDefaultState());
	this.world.setBlockState(frontC, Blocks.LAPIS_BLOCK.getDefaultState());
	this.world.setBlockState(frontL, Blocks.LAPIS_BLOCK.getDefaultState());
	this.world.setBlockState(frontR, Blocks.LAPIS_BLOCK.getDefaultState());
	this.world.setBlockState(backL, Blocks.LAPIS_BLOCK.getDefaultState());
	this.world.setBlockState(backR, Blocks.LAPIS_BLOCK.getDefaultState());
	this.world.setBlockState(leftC, Blocks.LAPIS_BLOCK.getDefaultState());
	this.world.setBlockState(rightC, Blocks.LAPIS_BLOCK.getDefaultState());
	BlockMeepino.isActivated = false;
		}
		
		
		
	}

}

	
