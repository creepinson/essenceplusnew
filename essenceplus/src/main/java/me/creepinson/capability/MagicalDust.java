package me.creepinson.capability;

import me.creepinson.main.CommonProxy;
import me.creepinson.packet.PacketGetMana;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;

/**

 * Default implementation of IMana

 */

public class MagicalDust implements IMagicalDust

{
	Minecraft mc = Minecraft.getMinecraft();
public NBTTagCompound nbt = new NBTTagCompound();
 private float magicDust = 0F;

 @Override
 public void setNBT()

 {
	 nbt.setFloat("magicdust", this.getMana());


 }


 public void consume(float points)

 {

 this.magicDust -= points;



 if (this.magicDust < 0.0F) this.magicDust = 0.0F;

 }



 public void fill(float points)

 {

 this.magicDust += points;
 CommonProxy.INSTANCE.sendToServer(new PacketGetMana(mc.player, nbt));
 }



 public void set(float points)

 {

 this.magicDust = points;
 CommonProxy.INSTANCE.sendToServer(new PacketGetMana(mc.player, nbt));
 }



 public float getMana()

 {

 return this.magicDust;

 }
public void readFromNBT(NBTTagCompound nbt) {
	
	nbt.getFloat("magicdust");

}
}