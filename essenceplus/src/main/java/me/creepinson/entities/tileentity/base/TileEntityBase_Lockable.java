package me.creepinson.entities.tileentity.base;

import java.util.UUID;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBase_Lockable extends TileEntity {



	private boolean lockable;

	public boolean isLockable() {
		return lockable;
	}

	public void setLockable(boolean lockable) {

		this.lockable = lockable;

	}

	private boolean locked;

	public boolean isLocked() {

		return this.locked;
	}

	public void setLocked(boolean locked) {

		this.locked = locked;

	}

}
