package me.creepinson.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelLoukatorin - Creepinson
 * Created using Tabula 5.1.0
 */
public class ModelLoukatorin extends ModelBase {
    public ModelRenderer Head;
    public ModelRenderer Neck;
    public ModelRenderer Body;
    public ModelRenderer LegL;
    public ModelRenderer LegR;

    public ModelLoukatorin() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.Body = new ModelRenderer(this, 26, 0);
        this.Body.setRotationPoint(-2.0F, 4.0F, -0.5F);
        this.Body.addBox(0.0F, 0.0F, 0.0F, 4, 10, 3, 0.0F);
        this.LegL = new ModelRenderer(this, 0, 42);
        this.LegL.setRotationPoint(0.5F, 14.0F, -0.5F);
        this.LegL.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.setRotateAngle(LegL, -0.00951133087581189F, 0.0F, 0.0F);
        this.Head = new ModelRenderer(this, 105, 0);
        this.Head.setRotationPoint(-2.5F, 0.0F, -6.0F);
        this.Head.addBox(0.0F, 0.0F, 0.0F, 5, 4, 4, 0.0F);
        this.LegR = new ModelRenderer(this, 31, 42);
        this.LegR.setRotationPoint(-2.5F, 14.0F, -0.5F);
        this.LegR.addBox(0.0F, 0.0F, 0.0F, 2, 10, 2, 0.0F);
        this.Neck = new ModelRenderer(this, 65, 0);
        this.Neck.setRotationPoint(-2.0F, 0.0F, -2.0F);
        this.Neck.addBox(0.0F, 0.0F, 0.0F, 4, 4, 8, 0.0F);
        this.setRotateAngle(Neck, -0.31869712141416456F, 0.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.Body.render(f5);
        this.LegL.render(f5);
        this.Head.render(f5);
        this.LegR.render(f5);
        this.Neck.render(f5);
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
		this.Head.rotateAngleX = headPitch / (180F / (float) Math.PI);
		this.LegL.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.2F * limbSwingAmount;
		this.LegR.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.2F * limbSwingAmount;

		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor,
				entityIn);
	}
}
