package me.creepinson.entities;


import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumHand;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.init.Items;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.Minecraft;

import java.util.Random;

import me.creepinson.lib.RefStrings;

import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class Alexion {

	public int mobid = 0;
	public static Object instance;

    public void load(FMLInitializationEvent event){
	}

    public void generateNether(World world, Random random, int chunkX, int chunkZ){}
	public void generateSurface(World world, Random random, int chunkX, int chunkZ){}
	public int addFuel(ItemStack fuel){
		return 0;
	}
	
	@SideOnly(Side.CLIENT)
	public void registerRenderers(){
		RenderLiving customRender = new RenderLiving(Minecraft.getMinecraft().getRenderManager(), new Alexion.ModelAlexion(), 0){protected ResourceLocation getEntityTexture(Entity par1Entity){return new ResourceLocation(RefStrings.MODID + ":" + "textures/entity/alexion-texturemap.png");}};
		RenderingRegistry.registerEntityRenderingHandler(Alexion.Entityalexion.class, customRender);
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowCustom.class, new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), new ItemStack(Items.STICK).getItem() ,Minecraft.getMinecraft().getRenderItem()));

	}
	
	public void serverLoad(FMLServerStartingEvent event){}
	public void preInit(FMLPreInitializationEvent event){
		int entityID = MathHelper.getRandomUUID().hashCode();
		mobid = entityID;
		EntityRegistry.registerModEntity(new ResourceLocation("meepersplus:alexion"), Alexion.Entityalexion.class, "alexion", entityID, instance, 64, 1, true, (204 << 16) + (0 << 8) + 204, (255 << 16) + (51 << 8) + 102);
		EntityRegistry.addSpawn(Alexion.Entityalexion.class, 17, 3, 25, EnumCreatureType.MONSTER , Biome.REGISTRY.getObject(new ResourceLocation("forest")));

        
		
		int entityID2 = MathHelper.getRandomUUID().hashCode();
EntityRegistry.registerModEntity(new ResourceLocation("meepersplus:entitybulletalexion"), EntityArrowCustom.class, "entitybulletalexion", entityID2, instance, 64, 1, true);

	}
	
	public static Biome[] clean(net.minecraft.util.registry.RegistryNamespaced<ResourceLocation, Biome> in) {
		Iterator<Biome> itr = in.iterator();
		ArrayList<Biome> ls = new ArrayList<Biome>();
		while(itr.hasNext()){
			ls.add(itr.next());
		}
		return ls.toArray(new Biome[ls.size()]);
	}

    public static class EntityArrowCustom extends EntityTippedArrow {public EntityArrowCustom(World a) {super(a);}
public EntityArrowCustom(World worldIn, double x, double y, double z) {super(worldIn, x, y, z);}
public EntityArrowCustom(World worldIn, EntityLivingBase shooter) {super(worldIn, shooter);}}

   public static class Entityalexion extends EntityMob  implements IRangedAttackMob 
	{
		World world = null;
	    public Entityalexion(World var1)
	    {
	        super(var1);
	        world = var1;
	        experienceValue = 10;
	        this.isImmuneToFire = false;
	        addRandomArmor();
			setNoAI(!true);
        	this.tasks.addTask(0, new EntityAISwimming(this));
this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
this.tasks.addTask(8, new EntityAILookIdle(this));
this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
this.targetTasks.addTask(7, new EntityAIHurtByTarget(this, false));
this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityPlayerMP.class, true));
this.targetTasks.addTask(7, new EntityAIHurtByTarget(this, false));
this.tasks.addTask(3, new EntityAIWander(this, 0.8D));
this.tasks.addTask(1, new EntityAISwimming(this));
this.tasks.addTask(6, new EntityAIAttackMelee(this, 1.0D, false));
this.tasks.addTask(1, new EntityAIAttackRanged(this, 1.25D, 20, 10.0F));

			
	    }

	    

protected void applyEntityAttributes(){
super.applyEntityAttributes();
this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.44999999999999996D);
this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10D);
if(this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE)!=null)this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D);
}

	    
protected void addRandomArmor(){

}

public void onLivingUpdate() {

super.onLivingUpdate();
		World par1World = this.world;
				int par2 = (int) this.posX;
				int par3 = (int) this.posY;
				int par4 = (int) this.posZ;
		Random par5Random = this.rand;
if(true)
        for (int l = 0; l < 9; ++l)
        {
            double d0 = (double)((float)par2 + par5Random.nextFloat());
            double d1 = (double)((float)par3 + par5Random.nextFloat());
            double d2 = (double)((float)par4 + par5Random.nextFloat());
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int i1 = par5Random.nextInt(2) * 2 - 1;
            d3 = ((double)par5Random.nextFloat() - 0.5D) * 0.2999999985098839D;
            d4 = ((double)par5Random.nextFloat() - 0.5D) * 0.2999999985098839D;
            d5 = ((double)par5Random.nextFloat() - 0.5D) * 0.2999999985098839D;
            par1World.spawnParticle(EnumParticleTypes.CRIT_MAGIC, d0, d1, d2, d3, d4, d5);
        }
}
		
		public void attackEntityWithRangedAttack(EntityLivingBase target, float flval){
EntityArrowCustom entityarrow = new EntityArrowCustom(this.world, this);
double d0 = target.posY + (double)target.getEyeHeight() - 1.1;
double d1 = target.posX - this.posX;
double d2 = d0 - entityarrow.posY;
double d3 = target.posZ - this.posZ;
float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
entityarrow.setThrowableHeading(d1, d2 + (double)f, d3, 1.6F, 12.0F);
this.world.spawnEntity(entityarrow);
}

		
		@Override
		protected Item getDropItem()
		{
			return new ItemStack(Items.GOLD_INGOT).getItem();
		}

	    @Override
	    protected net.minecraft.util.SoundEvent getAmbientSound()
	    {
	        return (net.minecraft.util.SoundEvent)net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("meepersplus:alexion.ambient"));
	    }

	    @Override
	    protected net.minecraft.util.SoundEvent getHurtSound()
	    {
	        return (net.minecraft.util.SoundEvent)net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("meepersplus:alexion.hurt"));
	    }
		
		@Override
	    protected net.minecraft.util.SoundEvent getDeathSound()
	    {
			return (net.minecraft.util.SoundEvent)net.minecraft.util.SoundEvent.REGISTRY.getObject(new ResourceLocation("meepersplus:alexion.death"));
	    }

		@Override
	    public void onStruckByLightning(EntityLightningBolt entityLightningBolt){
			super.onStruckByLightning(entityLightningBolt);
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			Entity entity = this;
			
		}

		@Override
		public void fall(float l, float d){
			super.fall(l,d);
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			super.fall(l,d);
			Entity entity = this;
			
		}

		@Override
		public void onDeath(DamageSource source){
			super.onDeath(source);
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			Entity entity = this;
			
			
		}

		@Override
		public boolean processInteract(EntityPlayer entity, EnumHand hand){
			super.processInteract(entity,hand);
			int i = (int)this.posX;
			int j = (int)this.posY;
			int k = (int)this.posZ;
			

			return true;
		}
		
		@Override
		protected float getSoundVolume()
		{
        return 1.0F;
		}
		
	}

	


/**
* Alexion - Creepinson
* Created using Tabula 5.1.0
*/
public static class ModelAlexion extends ModelBase {
public ModelRenderer BackR;
public ModelRenderer FrontLeg;
public ModelRenderer BackL;
public ModelRenderer Head;
public ModelRenderer Body;

public ModelAlexion() {
this.textureWidth = 128;
this.textureHeight = 128;
this.FrontLeg = new ModelRenderer(this, 0, 51);
this.FrontLeg.setRotationPoint(-2.0F, 16.0F, -5.6F);
this.FrontLeg.addBox(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
this.BackL = new ModelRenderer(this, 102, 95);
this.BackL.setRotationPoint(1.9F, 16.0F, 1.2F);
this.BackL.addBox(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
this.BackR = new ModelRenderer(this, 32, 95);
this.BackR.setRotationPoint(-5.8F, 16.0F, 1.2F);
this.BackR.addBox(0.0F, 0.0F, 0.0F, 3, 8, 3, 0.0F);
this.Body = new ModelRenderer(this, 80, 0);
this.Body.setRotationPoint(-4.3F, 9.0F, -7.0F);
this.Body.addBox(0.0F, 0.0F, 0.0F, 8, 7, 14, 0.0F);
this.Head = new ModelRenderer(this, 0, 0);
this.Head.setRotationPoint(-3.5F, 3.1F, -11.7F);
this.Head.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
}

@Override
public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
this.FrontLeg.render(f5);
this.BackL.render(f5);
this.BackR.render(f5);
this.Body.render(f5);
this.Head.render(f5);
}

/**
* This is a helper function from Tabula to set the rotation of model parts
*/
public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
modelRenderer.rotateAngleX = x;
modelRenderer.rotateAngleY = y;
modelRenderer.rotateAngleZ = z;
}

 @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
              float headPitch, float scaleFactor, Entity entityIn) {
    
 this.FrontLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
 this.BackL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
 this.BackR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
     
     

super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }
}

}
