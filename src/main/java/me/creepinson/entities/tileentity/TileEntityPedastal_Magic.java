package me.creepinson.entities.tileentity;

import me.creepinson.entities.tileentity.base.TileEntityBase_Lockable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;

public class TileEntityPedastal_Magic extends TileEntityBase_Lockable {

	private ItemStack stack = ItemStack.EMPTY;

	public ItemStack getStack() {
		return this.stack;
	}

	public void setStack(ItemStack stack) {
		this.stack = stack;
		markDirty();
		if (world != null) {
			IBlockState state = world.getBlockState(getPos());
			world.notifyBlockUpdate(getPos(), state, state, 3);
		}
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		// getUpdateTag() is called whenever the chunkdata is sent to the
		// client. In contrast getUpdatePacket() is called when the tile entity
		// itself wants to sync to the client. In many cases you want to send
		// over the same information in getUpdateTag() as in getUpdatePacket().
		return writeToNBT(new NBTTagCompound());
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		// Prepare a packet for syncing our TE to the client. Since we only have
		// to sync the stack
		// and that's all we have we just write our entire NBT here. If you have
		// a complex
		// tile entity that doesn't need to have all information on the client
		// you can write
		// a more optimal NBT here.
		NBTTagCompound nbtTag = new NBTTagCompound();
		this.writeToNBT(nbtTag);
		return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		// Here we get the packet from the server and read it into our client
		// side tile entity
		this.readFromNBT(packet.getNbtCompound());
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		if (compound.hasKey("item")) {
			setStack(new ItemStack(compound.getCompoundTag("item")));
		} else {
			setStack(ItemStack.EMPTY);
		}
		if (compound.hasKey("locked")) {
			this.setLocked(compound.getBoolean("locked"));
		} else {
			compound.setBoolean("locked", false);
		}
		
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {

		if (!getStack().isEmpty()) {
			NBTTagCompound item = new NBTTagCompound();
			getStack().writeToNBT(item);
			compound.setTag("item", item);

		}
		compound.setBoolean("locked", this.isLocked());
	
		return compound;
	}

}