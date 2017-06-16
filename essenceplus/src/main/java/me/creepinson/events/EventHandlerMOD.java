package me.creepinson.events;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import me.creepinson.capability.IMagicalDust;
import me.creepinson.capability.MagicDustProvider;
import me.creepinson.handlers.ItemHandler;
import me.creepinson.main.CommonProxy;
import me.creepinson.packet.PacketGetMana;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class EventHandlerMOD {

	Minecraft mc = Minecraft.getMinecraft();
	/*
	@SubscribeEvent

	public void onItemPickup(PlayerEvent.ItemPickupEvent event) {
		EntityPlayer player = event.player;
		IMagicalDust magicDust = player.getCapability(MagicDustProvider.MAGIC_DUST_CAP, null);
		ItemStack item = event.pickedUp.getEntityItem();
		if (item.getItem() == ItemHandler.Essence && item.getMetadata() == 4) {
			
			magicDust.fill(2.5F * event.pickedUp.getEntityItem().getCount());
			magicDust.setNBT();
			event.setCanceled(true);

		}

	}

	@SubscribeEvent
	public void onPlayerSleep(PlayerSleepInBedEvent event)

	{

		EntityPlayer player = event.getEntityPlayer();

		if (player.world.isRemote)
			return;

		IMagicalDust magicDust = player.getCapability(MagicDustProvider.MAGIC_DUST_CAP, null);

		magicDust.fill(10);

		String message = String.format(
				"You refreshed yourself in the bed. You received 50 Magical Dust, you have §7%d§r Magical Dust left.",
				(int) magicDust.getMana());

		player.sendMessage(new TextComponentString(message));
		magicDust.setNBT();
	}

	// EVENTS
	@SubscribeEvent
	public void renderOverlay(RenderGameOverlayEvent.Post event) {

		if (event.isCanceled() || event.getType() != ElementType.EXPERIENCE) {
			return;
		}
		int xPos = 0;
		int yPos = 10;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_LIGHTING);

		IMagicalDust magicDust = mc.player.getCapability(MagicDustProvider.MAGIC_DUST_CAP, null);
		
		mc.mcProfiler.startSection("magicalDust");
		int color = Color.MAGENTA.getRGB();
		String text = "" + magicDust.getMana();
		int x = (event.getResolution().getScaledWidth() - mc.fontRendererObj.getStringWidth(text)) / 2;
		int y = event.getResolution().getScaledHeight() - 31 - 4 - 12 + 10;
		mc.fontRendererObj.drawString(text, x, y, color);
		mc.mcProfiler.endSection();

	}

	@SubscribeEvent

	public void onPlayerLogsIn(PlayerLoggedInEvent event)

	{

		EntityPlayer player = event.player;

		IMagicalDust magicDust = player.getCapability(MagicDustProvider.MAGIC_DUST_CAP, null);

		String message = String.format("Hello there, you have §7%d§r Magical Dust left.", (int) magicDust.getMana());

		player.sendMessage(new TextComponentString(message));
		magicDust.setNBT();
	}
	
*/

}