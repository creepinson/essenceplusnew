package me.creepinson.packet;

import me.creepinson.capability.IMagicalDust;
import me.creepinson.capability.MagicDustProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

// The params of the IMessageHandler are <REQ, REPLY>
// This means that the first param is the packet you are receiving, and the second is the packet you are returning.
// The returned packet can be used as a "response" from a sent packet.
public class GetManaHandler implements IMessageHandler<PacketGetMana, IMessage> {

	@Override
	public IMessage onMessage(PacketGetMana message, MessageContext ctx) {

		EntityPlayerMP serverPlayer = ctx.getServerHandler().playerEntity;
		World world = ctx.getServerHandler().playerEntity.world;

		EntityPlayer player = message.player;
		IMagicalDust magicDust = player.getCapability(MagicDustProvider.MAGIC_DUST_CAP, null);

		magicDust.set(message.tag.getFloat("magicdust"));

		return null;
	}
}