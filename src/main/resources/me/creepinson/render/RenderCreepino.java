package me.creepinson.render;

import java.util.Map;

import com.google.common.collect.Maps;

import me.creepinson.entities.Creepino.EntityCreepino;
import me.creepinson.entities.Creepino.ModelCreepino;
import me.creepinson.lib.RefStrings;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderCreepino extends RenderLiving<EntityCreepino>{
   
	 private static final Map<String, ResourceLocation> LAYERED_LOCATION_CACHE = Maps.<String, ResourceLocation>newHashMap();

	
	    public RenderCreepino(RenderManager p_i47205_1_)
	    {
	        super(p_i47205_1_, new ModelCreepino(), 0.75F);
	    }
	protected ResourceLocation getEntityTexture(EntityCreepino entity)
    {
        String s = entity.getTexture();
		
        
        return new ResourceLocation(RefStrings.MODID + ":" + "textures/entity/creepino/creepino_red.png");
        
    }

}
