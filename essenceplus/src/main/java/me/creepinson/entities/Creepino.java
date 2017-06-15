package me.creepinson.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import me.creepinson.handlers.ItemHandler;
import me.creepinson.item.Bullet;
import me.creepinson.lib.RefStrings;
import me.creepinson.render.RenderCreepino;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Biomes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SuppressWarnings("unchecked")
public class Creepino {
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

		RenderingRegistry.registerEntityRenderingHandler(EntityCreepino.class,
				new RenderCreepino(Minecraft.getMinecraft().getRenderManager(), new Creepino.ModelCreepino(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowCustom.class,
				new RenderSnowball(Minecraft.getMinecraft().getRenderManager(), new ItemStack(Bullet.block).getItem(),
						Minecraft.getMinecraft().getRenderItem()));

	}

	public void serverLoad(FMLServerStartingEvent event) {
	}

	public void preInit(FMLPreInitializationEvent event) {
		int entityID = MathHelper.getRandomUUID().hashCode();
		mobid = entityID;

		
		int entityID2 = MathHelper.getRandomUUID().hashCode();

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

	public static class EntityCreepino extends EntityMob implements IRangedAttackMob {

		private static final DataParameter<Integer> SPOT_COLOR = EntityDataManager
				.<Integer>createKey(EntityCreepino.class, DataSerializers.VARINT);
		private static final String[] TEXTURES = new String[] {
				RefStrings.MODID + "textures/entity/creepino/creepino_white.png",
				RefStrings.MODID + "textures/entity/creepino/creepino_red.png" };
		private String texturePrefix;
		private static final String[] TEXTURES_ABBR = new String[] { "cw", "cr" };

		public int getTextureType() {
			return this.dataManager.get(SPOT_COLOR);
		}

		World world = null;

		public EntityCreepino(World var1) {
			super(var1);
			world = var1;
			experienceValue = 5;
			this.isImmuneToFire = false;
			addRandomArmor();
			setNoAI(!true);
			this.tasks.addTask(0, new EntityAISwimming(this));

			this.tasks.addTask(6, new EntityAIWander(this, 1.0D));

			this.tasks.addTask(8, new EntityAILookIdle(this));

			this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));

			this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0D, false));

			this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.8F));

			this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayerMP.class, true));

			this.targetTasks.addTask(4, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));

			this.targetTasks.addTask(6, new EntityAINearestAttackableTarget(this, EntityChicken.class, true));

			this.targetTasks.addTask(5, new EntityAIHurtByTarget(this, false));

			this.tasks.addTask(1, new EntityAILookIdle(this));

			this.tasks.addTask(1, new EntityAIWander(this, 0.8D));

			this.tasks.addTask(6, new EntityAIAttackMelee(this, 1.0D, false));

this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));

this.targetTasks.addTask(2, new EntityAIHurtByTarget(this, false));

		}

		public void setSpot(int variant) {
			this.dataManager.set(SPOT_COLOR, Integer.valueOf(variant));
		}

		public int getSpot() {
			return ((Integer) this.dataManager.get(SPOT_COLOR)).intValue();
		}

		protected void entityInit() {
			Random rand = new Random();

			int n = rand.nextInt(1) + 0;
			super.entityInit();
			this.dataManager.register(SPOT_COLOR, n);

		}

		public void writeEntityToNBT(NBTTagCompound compound) {
			super.writeEntityToNBT(compound);
			compound.setInteger("Variant", this.getSpot());
		}

		public void readEntityFromNBT(NBTTagCompound compound) {
			super.readEntityFromNBT(compound);
			this.setSpot(compound.getInteger("Variant"));
		}

		private void resetTexturePrefix() {
			this.texturePrefix = null;
		}

		@SideOnly(Side.CLIENT)
		private void setTexturePaths() {
			int i = this.getSpot();

			this.texturePrefix = TEXTURES[i];

		}

		@SideOnly(Side.CLIENT)
		public String getTexture() {
			if (this.texturePrefix == null) {
				this.setTexturePaths();
			}

			return this.texturePrefix;
		}

		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12D);
			if (this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5D);
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
				for (int l = 0; l < 6; ++l) {
					double d0 = (double) ((float) par2 + par5Random.nextFloat());
					double d1 = (double) ((float) par3 + par5Random.nextFloat());
					double d2 = (double) ((float) par4 + par5Random.nextFloat());
					double d3 = 0.0D;
					double d4 = 0.0D;
					double d5 = 0.0D;
					par1World.spawnParticle(EnumParticleTypes.SLIME, d0, d1, d2, d3, d4, d5);
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
			return new ItemStack(ItemHandler.Essence, 3, 3).getItem();
		}

		@Override
		protected net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("meepersplus:creepinoScreech"));
		}

		@Override
		protected net.minecraft.util.SoundEvent getHurtSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("meepersplus:creepinoHurt"));
		}

		@Override
		protected net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("meepersplus:creepinoDeath"));
		}

		@Override
		public void onStruckByLightning(EntityLightningBolt entityLightningBolt) {
			super.onStruckByLightning(entityLightningBolt);
			int i = (int) this.posX;
			int j = (int) this.posY;
			int k = (int) this.posZ;
			Entity entity = this;

			j += 1;

			entity.motionY += 0.5;

			entity.setFire(1);

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
		protected boolean processInteract(EntityPlayer entity, EnumHand hand) {
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

	public static class ModelCreepino extends ModelBase {
		public ModelRenderer Body;
		public ModelRenderer Leg;
		public ModelRenderer Head;

		public ModelCreepino() {
			this.textureWidth = 128;
			this.textureHeight = 128;
			this.Leg = new ModelRenderer(this, 39, 44);
			this.Leg.setRotationPoint(-2.0F, 9.0F, -2.0F);
			this.Leg.addBox(0.0F, 0.0F, 0.0F, 4, 15, 4, 0.0F);
			this.Body = new ModelRenderer(this, 92, 0);
			this.Body.setRotationPoint(-4.0F, -5.2F, -4.0F);
			this.Body.addBox(0.0F, 0.0F, 0.0F, 8, 14, 8, 0.0F);
			this.Head = new ModelRenderer(this, 0, 8);
			this.Head.setRotationPoint(-3.0F, -11.2F, -3.0F);
			this.Head.addBox(0.0F, 0.0F, 0.0F, 6, 6, 6, 0.0F);
			this.setRotateAngle(Head, 0.0F, -0.03717551306747922F, 0.0F);
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			this.Leg.render(f5);
			this.Body.render(f5);
			this.Head.render(f5);
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
			this.Leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
			super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor,
					entityIn);
		}
	}

}
