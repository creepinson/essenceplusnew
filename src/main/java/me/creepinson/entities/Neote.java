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
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.init.Biomes;
import net.minecraft.init.Items;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.Minecraft;

import java.util.Random;

import me.creepinson.lib.RefStrings;

import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class Neote {

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
				new Neote.ModelNeote(), 0) {
			protected ResourceLocation getEntityTexture(Entity par1Entity) {
				return new ResourceLocation(RefStrings.MODID + ":" + "textures/entity/neote-texturemap.png");
			}
		};
		RenderingRegistry.registerEntityRenderingHandler(Neote.Entityneote.class, customRender);

	}

	public void serverLoad(FMLServerStartingEvent event) {
	}

	public void preInit(FMLPreInitializationEvent event) {
		int entityID = MathHelper.getRandomUUID().hashCode();
		mobid = entityID;
		EntityRegistry.registerModEntity(new ResourceLocation("meepersplus:neote"), Neote.Entityneote.class, "neote",
				entityID, instance, 64, 1, true, (0 << 16) + (0 << 8) + 153, (204 << 16) + (0 << 8) + 102);
		EntityRegistry.addSpawn(Neote.Entityneote.class, 8, 4, 5, EnumCreatureType.CREATURE, Biomes.MESA);

	}

	public static Biome[] clean(net.minecraft.util.registry.RegistryNamespaced<ResourceLocation, Biome> in) {
		Iterator<Biome> itr = in.iterator();
		ArrayList<Biome> ls = new ArrayList<Biome>();
		while (itr.hasNext()) {
			ls.add(itr.next());
		}
		return ls.toArray(new Biome[ls.size()]);
	}

	public static class Entityneote extends EntityMob {
		World world = null;

		public Entityneote(World var1) {
			super(var1);
			world = var1;
			experienceValue = 3;
			this.isImmuneToFire = false;
			addRandomArmor();
			setNoAI(!true);
			this.tasks.addTask(0, new EntityAISwimming(this));
			this.tasks.addTask(6, new EntityAIWander(this, 1.0D));
			this.tasks.addTask(8, new EntityAILookIdle(this));
			this.tasks.addTask(7, new EntityAIWander(this, 0.8D));
			this.tasks.addTask(3, new EntityAILookIdle(this));
			this.tasks.addTask(2, new EntityAISwimming(this));
			this.tasks.addTask(10, new EntityAIPanic(this, 1.2D));

		}

		protected void applyEntityAttributes() {
			super.applyEntityAttributes();
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.65D);
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15D);
			if (this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) != null)
				this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1D);
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
					par1World.spawnParticle(EnumParticleTypes.DRIP_LAVA, par2, par3, par4, 0.0D, 0.0D, 0.0D);
				}
		}
		protected void addRandomArmor() {

		}

		protected void dropRareDrop(int par1) {
			this.dropItem(new ItemStack(Items.GOLD_INGOT).getItem(), 1);
		}

		@Override
		protected Item getDropItem() {
			return new ItemStack(Items.IRON_INGOT).getItem();
		}

		@Override
		protected net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("meepersplus:neote.ambient"));
		}

		@Override
		protected net.minecraft.util.SoundEvent getHurtSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("meepersplus:neote.hurt"));
		}

		@Override
		protected net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) net.minecraft.util.SoundEvent.REGISTRY
					.getObject(new ResourceLocation("meepersplus:neote.death"));
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
	 * Neote - Creepinson Created using Tabula 5.1.0
	 */
	public static class ModelNeote extends ModelBase {
		public ModelRenderer Neck;
		public ModelRenderer Head;
		public ModelRenderer Body;
		public ModelRenderer LegL;
		public ModelRenderer LegR;

		public ModelNeote() {
			this.textureWidth = 128;
			this.textureHeight = 128;
			this.LegL = new ModelRenderer(this, 110, 0);
			this.LegL.setRotationPoint(1.5F, 6.8F, 1.5F);
			this.LegL.addBox(0.0F, 0.0F, 0.0F, 1, 18, 2, 0.0F);
			this.Head = new ModelRenderer(this, 0, 0);
			this.Head.setRotationPoint(-2.0F, 0.0F, -7.0F);
			this.Head.addBox(0.0F, 0.0F, 0.0F, 5, 4, 6, 0.0F);
			this.Body = new ModelRenderer(this, 0, 86);
			this.Body.setRotationPoint(-1.5F, 2.8F, 0.5F);
			this.Body.addBox(0.0F, 0.0F, 0.0F, 4, 4, 6, 0.0F);
			this.Neck = new ModelRenderer(this, 0, 38);
			this.Neck.setRotationPoint(-2.0F, 0.8F, -1.0F);
			this.Neck.addBox(0.0F, 0.0F, 0.0F, 5, 2, 6, 0.0F);
			this.LegR = new ModelRenderer(this, 110, 34);
			this.LegR.setRotationPoint(-1.5F, 6.8F, 1.5F);
			this.LegR.addBox(0.0F, 0.0F, 0.0F, 1, 18, 2, 0.0F);
		}

		@Override
		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
			this.LegL.render(f5);
			this.Head.render(f5);
			this.Body.render(f5);
			this.Neck.render(f5);
			this.LegR.render(f5);
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
