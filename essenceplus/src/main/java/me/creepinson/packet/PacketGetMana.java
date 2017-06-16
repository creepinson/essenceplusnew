package me.creepinson.packet;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import me.creepinson.lib.Utils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketGetMana implements IMessage {
	private boolean messageValid;
	public EntityPlayer player;
	public UUID uuid;
	public NBTTagCompound tag;
	// A default constructor is always required
	public PacketGetMana() {
		this.messageValid = false;
	}
	public PacketGetMana(EntityPlayer player, NBTTagCompound tag){
		this.player = player;
		this.tag = tag;
		this.messageValid = true;
		
	}
	@Override
	public void toBytes(ByteBuf buf) {

		if (!this.messageValid)
			return;
		
		buf.writeLong(player.getUniqueID().getLeastSignificantBits());
		buf.writeLong(player.getUniqueID().getMostSignificantBits());
	
		ByteBufUtils.writeTag(buf, this.tag);
		
		
		
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		// Reads the int back from the buf. Note that if you have multiple
		// values, you must read in the same order you wrote.

		try {
		uuid = new UUID(buf.readLong(), buf.readLong());
		
		ByteBufUtils.readTag(buf);
		
		
		} catch (IndexOutOfBoundsException e) {
			Utils.getLogger().catching(e);
		return;
		}
this.messageValid = true;
	}
}
