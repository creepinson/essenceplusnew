package me.creepinson.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTPrimitive;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

/**

 * This class is responsible for saving and reading mana data from or to server

 */

public class MagicDustStorage implements IStorage<IMagicalDust>

{

 @Override

 public NBTBase writeNBT(Capability<IMagicalDust> capability, IMagicalDust instance, EnumFacing side)

 {

 return new NBTTagFloat(instance.getMana());

 }



 @Override

 public void readNBT(Capability<IMagicalDust> capability, IMagicalDust instance, EnumFacing side, NBTBase nbt)

 {

 instance.set(((NBTPrimitive) nbt).getFloat());

 }

}