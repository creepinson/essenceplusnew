package me.creepinson.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants.NBT;

public class MagicDustProvider implements ICapabilitySerializable<NBTBase>

{

	
 @CapabilityInject(IMagicalDust.class)

 public static final Capability<IMagicalDust> MAGIC_DUST_CAP = null;



 private IMagicalDust instance = MAGIC_DUST_CAP.getDefaultInstance();



 @Override

 public boolean hasCapability(Capability<?> capability, EnumFacing facing)

 {

 return capability == MAGIC_DUST_CAP;

 }



 @Override

 public <T> T getCapability(Capability<T> capability, EnumFacing facing)

 {

 return capability == MAGIC_DUST_CAP ? MAGIC_DUST_CAP.<T> cast(this.instance) : null;

 }



 @Override

 public NBTBase serializeNBT()

 {

 return MAGIC_DUST_CAP.getStorage().writeNBT(MAGIC_DUST_CAP, this.instance, null);

 }




 @Override

 public void deserializeNBT(NBTBase nbt)

 {

	 MAGIC_DUST_CAP.getStorage().readNBT(MAGIC_DUST_CAP, this.instance, null, nbt);

 }
 

}
