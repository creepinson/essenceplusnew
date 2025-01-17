package me.creepinson.entities.tileentity;

import org.lwjgl.opengl.GL11;

import me.creepinson.lib.RefStrings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class TESRPedastal_Magic extends TileEntitySpecialRenderer<TileEntityPedastal_Magic> {

	private IModel model;
	private IBakedModel bakedModel;

	private IBakedModel getBakedModel() {
		// Since we cannot bake in preInit() we do lazy baking of the model as
		// soon as we need it
		// for rendering
		if (bakedModel == null) {
			try {
				model = ModelLoaderRegistry.getModel(new ResourceLocation(RefStrings.MODID, "block/pedastal_magic"));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			bakedModel = model.bake(TRSRTransformation.identity(), DefaultVertexFormats.ITEM,
					location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString()));
		}
		return bakedModel;
	}

	@Override
	public void renderTileEntityAt(TileEntityPedastal_Magic te, double x, double y, double z, float partialTicks,
			int destroyStage) {
		GlStateManager.pushAttrib();
		GlStateManager.pushMatrix();

		// Translate to the location of our tile entity
		GlStateManager.translate(x, y, z);
		GlStateManager.disableRescaleNormal();

		// Render the rotating handles
		renderHandles(te);

		// Render our item
		renderItem(te);

		GlStateManager.popMatrix();
		GlStateManager.popAttrib();

	}

	private void renderHandles(TileEntityPedastal_Magic te) {
		GlStateManager.pushMatrix();

		GlStateManager.translate(0, 0, 0);

		RenderHelper.disableStandardItemLighting();
		this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
		if (Minecraft.isAmbientOcclusionEnabled()) {
			GlStateManager.shadeModel(GL11.GL_SMOOTH);
		} else {
			GlStateManager.shadeModel(GL11.GL_FLAT);
		}

		World world = te.getWorld();
		// Translate back to local view coordinates so that we can do the acual
		// rendering here
		GlStateManager.translate(-te.getPos().getX(), -te.getPos().getY(), -te.getPos().getZ());

		Tessellator tessellator = Tessellator.getInstance();
		tessellator.getBuffer().begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
		Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelRenderer().renderModel(world,
				getBakedModel(), world.getBlockState(te.getPos()), te.getPos(), Tessellator.getInstance().getBuffer(),
				false);
		tessellator.draw();

		RenderHelper.enableStandardItemLighting();
		GlStateManager.popMatrix();
	}

	private void renderItem(TileEntityPedastal_Magic te) {
		ItemStack stack = te.getStack();
		long angle = (System.currentTimeMillis() / 10) % 360;

		if (!stack.isEmpty()) {
			RenderHelper.enableStandardItemLighting();
			GlStateManager.enableLighting();
			GlStateManager.pushMatrix();
			// Translate to the center of the block and .9 points higher
			GlStateManager.translate(.5, 1.5, .5);
			GlStateManager.scale(.4f, .4f, .4f);

			GlStateManager.rotate(angle, te.getPos().getX(), te.getPos().getY(), te.getPos().getZ());

			Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.NONE);

			GlStateManager.popMatrix();
		}
	}

}