package me.creepinson.gui;

import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import me.creepinson.blocks.mcreator_mobChamber;
import me.creepinson.entities.Creepino;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@SuppressWarnings("unchecked")
public class mcreator_mobChamberGUI {

	public static Object instance;

	public static int GUIID = 1;

	public mcreator_mobChamberGUI() {
	}

	public void load(FMLInitializationEvent event) {
	}

	public void registerRenderers() {
	}

	public void generateNether(World world, Random random, int chunkX, int chunkZ) {
	}

	public void generateSurface(World world, Random random, int chunkX, int chunkZ) {
	}

	public int addFuel(ItemStack fuel) {
		return 0;
	}

	public void serverLoad(FMLServerStartingEvent event) {
	}

	public void preInit(FMLPreInitializationEvent event) {
	}

	public static IInventory inherited;

	public static class GuiContainerMod extends Container {

		World world = null;
		EntityPlayer entity = null;
		int i, j, k;

		public GuiContainerMod(World world, int i, int j, int k, EntityPlayer player) {

			this.world = world;
			this.entity = player;
			this.i = i;
			this.j = j;
			this.k = k;

			TileEntity ent = world.getTileEntity(new BlockPos(i, j, k));
			if (ent != null && (ent instanceof mcreator_mobChamber.TileEntityCustom))
				inherited = (IInventory) ent;
			else
				inherited = new InventoryBasic("", true, 9);

			this.addSlotToContainer(new Slot(inherited, 0, 7, 9) {
				public boolean isItemValid(ItemStack stack) {
					return ((stack != null) && stack.getItem() == Items.EGG);
				}

				public void onSlotChanged() {
					super.onSlotChanged();
					if (getHasStack()) {
						EntityPlayer entity = Minecraft.getMinecraft().player;
						int i = (int) entity.posX;
						int j = (int) entity.posY;
						int k = (int) entity.posZ;
						MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
						World world = server.worlds[0];

					}
				}
			});
			this.addSlotToContainer(new Slot(inherited, 2, 53, 7) {
				public boolean isItemValid(ItemStack stack) {
					return ((stack != null));
				}

				public void onSlotChanged() {
					super.onSlotChanged();
					if (getHasStack()) {
						EntityPlayer entity = Minecraft.getMinecraft().player;
						int i = (int) entity.posX;
						int j = (int) entity.posY;
						int k = (int) entity.posZ;
						MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
						World world = server.worlds[0];

					}
				}
			});
			this.addSlotToContainer(new Slot(inherited, 3, 97, 7) {
				public boolean isItemValid(ItemStack stack) {
					return ((stack != null) && stack.getItem() == Items.GOLD_INGOT);
				}

				public void onSlotChanged() {
					super.onSlotChanged();
					if (getHasStack()) {
						EntityPlayer entity = Minecraft.getMinecraft().player;
						int i = (int) entity.posX;
						int j = (int) entity.posY;
						int k = (int) entity.posZ;
						MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
						World world = server.worlds[0];

					}
				}
			});
			this.addSlotToContainer(new Slot(inherited, 4, 144, 6) {
				public boolean isItemValid(ItemStack stack) {
					return ((stack != null) && stack.getItem() == Item.getItemFromBlock(Blocks.RED_SANDSTONE));
				}

				public void onSlotChanged() {
					super.onSlotChanged();
					if (getHasStack()) {
						EntityPlayer entity = Minecraft.getMinecraft().player;
						int i = (int) entity.posX;
						int j = (int) entity.posY;
						int k = (int) entity.posZ;
						MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
						World world = server.worlds[0];

					}
				}
			});
			bindPlayerInventory(player.inventory);

		}

		@Override
		public boolean canInteractWith(EntityPlayer player) {
			return true;
		}

		protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
			int i;
			int j;

			for (i = 0; i < 3; ++i) {
				for (j = 0; j < 9; ++j) {
					this.addSlotToContainer(new Slot(inventoryPlayer, j + (i + 1) * 9, 8 + j * 18, 84 + i * 18));
				}
			}

			for (i = 0; i < 9; ++i) {
				this.addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
			}
		}

		@Override
		public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
			ItemStack itemstack = null;
			Slot slot = (Slot) this.inventorySlots.get(index);

			if (slot != null && slot.getHasStack()) {
				ItemStack itemstack1 = slot.getStack();
				itemstack = itemstack1.copy();

				if (index < 9) {
					if (!this.mergeItemStack(itemstack1, 9, (45 - 9), true)) {// fixes
																				// shiftclick
																				// error
						return null;
					}
				} else if (!this.mergeItemStack(itemstack1, 0, 9, false)) {
					return null;
				}

				if (itemstack1.getCount() == 0) {
					slot.putStack((ItemStack) null);
				} else {
					slot.onSlotChanged();
				}

				if (itemstack1.getCount() == itemstack.getCount()) {
					return null;
				}

				slot.onTake(playerIn, itemstack1);
			}

			return itemstack;
		}

		public void onContainerClosed(EntityPlayer playerIn) {
			super.onContainerClosed(playerIn);

		}
	}

	public static class GuiWindow extends GuiContainer {

		int i = 0;
		int j = 0;
		int k = 0;
		EntityPlayer entity = null;

		public GuiWindow(World world, int i, int j, int k, EntityPlayer entity) {
			super(new GuiContainerMod(world, i, j, k, entity));
			this.i = i;
			this.j = j;
			this.k = k;
			this.entity = entity;
			this.xSize = 176;
			this.ySize = 166;
		}

		private static final ResourceLocation texture = new ResourceLocation("mobchambergui.png");

		protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
			int posX = (this.width) / 2;
			int posY = (this.height) / 2;
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

			this.mc.renderEngine.bindTexture(texture);
			int k = (this.width - this.xSize) / 2;
			int l = (this.height - this.ySize) / 2;
			this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

			zLevel = 100.0F;

		}

		protected void mouseClicked(int par1, int par2, int par3) throws java.io.IOException {
			super.mouseClicked(par1, par2, par3);

		}

		public void updateScreen() {
			super.updateScreen();
			int posX = (this.width) / 2;
			int posY = (this.height) / 2;

		}

		@Override
		protected void keyTyped(char par1, int par2) throws java.io.IOException {

			super.keyTyped(par1, par2);

		}

		protected void drawGuiContainerForegroundLayer(int par1, int par2) {
			int posX = (this.width) / 2;
			int posY = (this.height) / 2;
			this.fontRendererObj.drawString("Insert items matching a specific recipe, and click generate mob! (WARNING: INSERT ONLY 1 ITEM FOR EACH STACK IN EACH SLOT!!)", (0), (48), 0x00cc00);

		}

		public void onGuiClosed() {
			super.onGuiClosed();
			Keyboard.enableRepeatEvents(false);
		}

		public void initGui() {
			super.initGui();
			this.guiLeft = (this.width - 176) / 2;
			this.guiTop = (this.height - 166) / 2;
			Keyboard.enableRepeatEvents(true);
			this.buttonList.clear();
			int posX = (this.width) / 2;
			int posY = (this.height) / 2;
			this.buttonList.add(new GuiButton(0, this.guiLeft + 0, this.guiTop + 29, 132, 20, "Generate Mob"));

		}

		protected void actionPerformed(GuiButton button) {
			MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		
		
			
			World world = server.worlds[0];

			if (button.id == 0) {

				if (inherited.getStackInSlot(0) == new ItemStack(Items.EGG) && inherited.getStackInSlot(1) == new ItemStack(Items.SLIME_BALL) && inherited.getStackInSlot(2) == ItemStack.EMPTY && inherited.getStackInSlot(3) == ItemStack.EMPTY) {
		  			Entity creepino = new Creepino.EntitysheepGunner(world);
					if (creepino != null && !world.isRemote) {
						creepino.setLocationAndAngles(i, j + 1, k, world.rand.nextFloat() * 360F, 0.0F);
						world.spawnEntity(creepino);
						inherited.decrStackSize(0, 1);
						
						inherited.decrStackSize(1, 1);
					
						inherited.decrStackSize(2, 1);
				
						inherited.decrStackSize(3, 1);
					}
					else{
						
					    server.sendMessage(new TextComponentTranslation(TextFormatting.AQUA + "[EssencePlus] " + TextFormatting.RED + "Invalid Recipe/No Items!"));
						
					}

						
					}

		

				}

			}

		}


		public boolean doesGuiPauseGame() {
			return false;
		}

	}

