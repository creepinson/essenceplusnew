package me.creepinson.handlers;

import me.creepinson.gui.mcreator_mobChamberGUI;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;


public class GuiHandler implements IGuiHandler {
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (id == mcreator_mobChamberGUI.GUIID)
			return new mcreator_mobChamberGUI.GuiContainerMod(world, x, y, z, player);
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		if (id == mcreator_mobChamberGUI.GUIID)
			return new mcreator_mobChamberGUI.GuiWindow(world, x, y, z, player);
		return null;
	}
}

	

