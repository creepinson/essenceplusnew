package me.creepinson.entities.tileentity.base;

import java.util.UUID;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBase_Lockable extends TileEntity{

	
public UUID lockedPlayerUUID;
	
public void setUUID(UUID uuid){
	
	lockedPlayerUUID = uuid;
	
}

public boolean lockable;

public boolean isLockable() {
	return lockable;
}

public void setLockable(boolean lockable){
	
	
	
}


public boolean locked;


public boolean isLocked() {

	return this.locked;
}

public void setLocked(boolean locked) {

	this.locked = locked;
	
	
}


}
