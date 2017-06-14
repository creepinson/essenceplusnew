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
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.ai.EntityAIWatchClosest;
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

public class Craeol {

	public int mobid = 0;
	public static Object instance;

	public void load(FMLInitializationEvent event) {
	}

	public void generateNether(World world, Random random, int chunkX, int chunkZ) {
	}

	public void generateSurface(World world, Random random, int chunkX, int chunkZ) {
	}

	public int addFuel(ItemStack fuel) {
		return 0;
	}

	@SideOnly(Side.CLIENT)
	public void registerRenderers() {
		RenderLiving customRender = new RenderLiving(Minecraft.getMinecraft().getRenderManager(),
				new Craeol.ModelCraeol(), 0) {
			protected ResourceLocation getEntityTexture(Entity par1Entity) {
				return new ResourceLocation(RefStrings.MODID + ":" + "textures/entity/craeol-texturemap.png");
			}
		};
		RenderingRegistry.registerEntityRenderingHandler(Craeol.Entitycraeol.class, customRender);
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowCustom.class,
				new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), new ItemStack(Items.CHICKEN).getItem(),
						Minecraft.getMinecraft().getRenderItem()));

	}

	public void serverLoad(FMLServerStartingEvent event) {
	}

	public void preInit(FMLPreInitializationEvent event) {
		int entityID = MathHelper.getRandomUUID().hashCode();
		mobid = entityID;
		EntityRegistry.registerModEntity(new ResourceLocation("meepersplus:craeol"), Craeol.Entitycraeol.class,
				"craeol", entityID, instance, 64, 1, true, (255 << 16) + (0 << 8) + 0, (204 << 16) + (204 << 8) + 204);
		EntityRegistry.addSpawn(Craeol.Entitycraeol.class, 19, 3, 30, EnumCreatureType.MONSTER,
				Biome.REGISTRY.getObject(new ResourceLocation("desert")));

		int entityID2 = MathHelper.getRandomUUID().hashCode();
		EntityRegistry.registerModEntity(new ResourceLocation("meepersplus:entitybulletcraeol"),
				EntityArrowCustom.class, "entitybulletcraeol", entityID2, instance, 64, 1, true);

	}

	public static Biome[] clean(net.minecraft.util.registry.RegistryNamespaced<ResourceLocation, Biome> in) {
		Iterator<Biome> itr = in.iterator();
		ArrayList<Biome> ls = new ArrayList<Biome>();
		while (itr.hasNext()) {
			ls.add(itr.next());
		}
		return ls.toArray(new Biome[ls.size()]);
	}

	public static class EntityArrowCustom extends EntityTippedArrow {
		public EntityArrowCustom(World a) {
			super(a);
		}

		public EntityArrowCustom(World worldIn, double x, double y, double z) {
			super(worldIn, x, y, z);
		}

		public EntityArrowCustom(World worldIn, EntityLivingBase shooter) {
			super(worldIn, shooter);
		}
	}

	public static class Entitycraeol extends EntityMob implements IRangedAttackMob {
		World world = null;

		public Entitycraeol(World var1) {
			super(var1);
			world = var1;
			experienceValue = 6;
			this.isImmuneToFire = false;
			addRandomArmor();
			setNoAI(!true);
			this.tasks.addTask(0, new EntityAISwimming(this));
			this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
			this.tasks.addTask(8, new EntityAILookIdle(this));
			this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityDragon.class, true));
			this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, false));
			this.tasks.addTask(1, new EntityAILookIdle(this));
			this.tasks.addTask(1, new EntityAISwimming(this));
			this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityDragon.class, 6.0F));
			this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, false));
			this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
			this.targetTasks.addTask(10, new EntityAIHurtByTarget(this, false));
			this.targetTasks.addTask(9, new EntityAINearestAttackableTarget(this, EntityPlayerMP.class, true));
			this.targetTasks.addTask(10, new EntityAIHurtByTarget(this, false));
			this.tasks.addTask(1, new EntityAIAttackRanged(this, 1.25D, 20, 10.0F));

		}

		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12D);
			if (this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2D);
		}

		protected void addRandomArmor() {

		}

		public void onLivingUpdate() {

			super.onLivingUpdate();
			World par1World = this.world;
			int par2 = (int) this.posX;
			int par3 = (int) this.posY;
			int par4 = (int) this.posZ;
			Random par5Random = this.rand;
			if (true)
				for (int l = 0; l < 4; ++l) {
					double d0 = (double) ((float) par2 + par5Random.nextFloat());
					double d1 = (double) ((float) par3 + par5Random.nextFloat());
					double d2 = (double) ((float) par4 + par5Random.nextFloat());
					double d3 = 0.0D;
					double d4 = 0.0D;
					double d5 = 0.0D;
					int i1 = par5Random.nextInt(2) * 2 - 1;
					d3 = ((double) par5Random.nextFloat() - 0.5D) * 0.3999999985098839D;
					d4 = ((double) par5Random.nextFloat() - 0.5D) * 0.3999999985098839D;
					d5 = ((double) par5Random.nextFloat() - 0.5D) * 0.3999999985098839D;
					par1World.spawnParticle(EnumParticleTypes.SPIT, d0, d1, d2, d3, d4, d5);
				}
		}

		public void attackEntityWithRangedAttack(EntityLivingBase target, float flval) {
			EntityArrowCustom entityarrow = new EntityArrowCustom(this.world, this);
			double d0 = target.posY + (double) target.getEyeHeight() - 1.1;
			double d1 = target.posX - this.posX;
			double d2 = d0 - entityarrow.posY;
			double d3 = target.posZ - this.posZ;
			float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
			entityarrow.setThrowableHeading(d1, d2 + (double) f, d3, 1.6F, 12.0F);
			this.world.spawnEntity(entityarrow);
		}

		@Override
		protected Item getDropItem() {
			return new ItemStack(Items.IRON_INGOT).getItem();
		}

		@Override
		protected net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("meepersplus:craeol.ambient"));
		}

		@Override
		protected net.minecraft.util.SoundEvent getHurtSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("meepersplus:craeol.hurt"));
		}

		@Override
		protected net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("meepersplus:craeol.death"));
		}

		@Override
		public void onStruckByLightning(EntityLightningBolt entityLightningBolt) {
			super.onStruckByLightning(entityLightningBolt);
			int i = (int) this.posX;
			int j = (int) this.posY;
			int k = (int) this.posZ;
			Entity entity = this;

		}

		@Override
		public void fall(float l, float d) {
			super.fall(l, d);
			int i = (int) this.posX;
			int j = (int) this.posY;
			int k = (int) this.posZ;
			super.fall(l, d);
			Entity entity = this;

		}

		@Override
		public void onDeath(DamageSource source) {
			super.onDeath(source);
			int i = (int) this.posX;
			int j = (int) this.posY;
			int k = (int) this.posZ;
			Entity entity = this;

		}

		@Override
		public boolean processInteract(EntityPlayer entity, EnumHand hand) {
			super.processInteract(entity, hand);
			int i = (int) this.posX;
			int j = (int) this.posY;
			int k = (int) this.posZ;

			return true;
		}

		@Override
		protected float getSoundVolume() {
			return 1.0F;
		}

	}

	/**
	 * Craeol - creepopolis Created using Tabula 5.1.0
	 */
	public static class ModelCraeol extends ModelBase {
		public ModelRenderer Head;
		public ModelRenderer Body;
		public ModelRenderer ArmBR;
		public ModelRenderer ArmBL;
		public ModelRenderer ArmTR;
		public ModelRenderer ArmTL;
		public ModelRenderer LegR;
		public ModelRenderer LegL;

		public ModelCraeol() {
			this.textureWidth = 128;
			this.textureHeight = 128;
			this.ArmBR = new ModelRenderer(this, 0, 0);
			this.ArmBR.setRotationPoint(-5.0F, 6.0F, -0.5F);
			this.ArmBR.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
			this.setRotateAngle(ArmBR, -1.5707963267948966F, 0.0F, 0.0F);
			this.ArmBL = new ModelRenderer(this, 1, 29);
			this.ArmBL.setRotationPoint(3.0F, 6.0F, -0.5F);
			this.ArmBL.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
			this.setRotateAngle(ArmBL, -1.5707963267948966F, 0.0F, 0.0F);
			this.Body = new ModelRenderer(this, 105, 50);
			this.Body.setRotationPoint(-3.0F, 0.0F, -3.0F);
			this.Body.addBox(0.0F, 0.0F, 0.0F, 6, 9, 5, 0.0F);
			this.Head = new ModelRenderer(this, 105, 0);
			this.Head.setRotationPoint(-2.0F, -4.0F, -2.0F);
			this.Head.addBox(0.0F, 0.0F, 0.0F, 4, 4, 4, 0.0F);
			this.LegL = new ModelRenderer(this, 20, 48);
			this.LegL.setRotationPoint(0.5F, 9.0F, -0.5F);
			this.LegL.addBox(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
			this.LegR = new ModelRenderer(this, 20, 10);
			this.LegR.setRotationPoint(-3.0F, 9.0F, -0.5F);
			this.LegR.addBox(0.0F, 0.0F, 0.0F, 2, 15, 2, 0.0F);
			this.ArmTR = new ModelRenderer(this, 57, 0);
			this.ArmTR.setRotationPoint(-5.0F, 1.5F, -0.5F);
			this.ArmTR.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
			this.setRotateAngle(ArmTR, -1.5707963267948966F, 0.0F, 0.0F);
			this.ArmTL = new ModelRenderer(this, 57, 102);
			this.ArmTL.setRotationPoint(3.0F, 1.5F, -0.5F);
			this.ArmTL.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
			this.setRotateAngle(ArmTL, -1.5707963267948966F, 0.0F, 0.0F);
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			this.ArmBR.render(f5);
			this.ArmBL.render(f5);
			this.Body.render(f5);
			this.Head.render(f5);
			this.LegL.render(f5);
			this.LegR.render(f5);
			this.ArmTR.render(f5);
			this.ArmTL.render(f5);
		}

		/**
		 * This is a helper function from Tabula to set the rotation of model
		 * parts
		 */
		public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
			modelRenderer.rotateAngleX = x;
			modelRenderer.rotateAngleY = y;
			modelRenderer.rotateAngleZ = z;
		}

		@Override
		public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
				float headPitch, float scaleFactor, Entity entityIn) {
			this.Head.rotateAngleX = headPitch / (180F / (float) Math.PI);
			this.LegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
			this.LegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.2F * limbSwingAmount;

			super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor,
					entityIn);
		}
	}

}
