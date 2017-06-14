package me.creepinson.render;

import java.util.Map;

import com.google.common.collect.Maps;

import me.creepinson.entities.Creepino.EntityCreepino;
import me.creepinson.entities.Creepino.ModelCreepino;
import me.creepinson.lib.RefStrings;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCreepino extends RenderLiving<EntityCreepino> {

	public RenderCreepino(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
		super(rendermanagerIn, modelbaseIn, shadowsizeIn);

	}

	private final ResourceLocation creepinoWhite = new ResourceLocation(RefStrings.MODID,
			"textures/entity/creepino/creepino_white.png");

	private final ResourceLocation creepinoRed = new ResourceLocation(RefStrings.MODID,
			"textures/entity/creepino/creepino_red.png");

	protected ResourceLocation getEntityTexture(EntityCreepino entity) {
		EntityCreepino thisentity = (EntityCreepino) entity;
		switch (entity.getTextureType()) {
		case 0:
			return creepinoWhite;
		case 1:
			return creepinoRed;
		default:
			return creepinoRed;
		}

	}

}
