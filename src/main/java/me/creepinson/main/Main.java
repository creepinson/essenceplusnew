package me.creepinson.main;


import java.util.Random;

import me.creepinson.entities.Alexion;
import me.creepinson.entities.Craeol;
import me.creepinson.entities.Creepino;
import me.creepinson.entities.Creepino.EntityArrowCustom;
import me.creepinson.entities.Creepino.EntityCreepino;
import me.creepinson.entities.Neote;
import me.creepinson.events.EventHandlerMOD;
import me.creepinson.handlers.GuiHandler;
import me.creepinson.handlers.MobDropsHandler;
import me.creepinson.item.Bullet;
import me.creepinson.item.ButtKicker;
import me.creepinson.lib.RefStrings;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;



@net.minecraftforge.fml.common.Mod(modid = me.creepinson.lib.RefStrings.MODID, version =  me.creepinson.lib.RefStrings.VERSION)
public class Main  implements IFuelHandler, IWorldGenerator{
	
	@Mod.Instance(RefStrings.MODID)
	public static Main instance;
	
	@SidedProxy(clientSide = "me.creepinson.main.ClientProxy", serverSide = "me.creepinson.main.CommonProxy")  
	public static CommonProxy proxy;
	
	//VARIABLES

	
 Creepino creepino = new Creepino();
Bullet bullet = new Bullet();
 Alexion alexion = new Alexion();

me.creepinson.blocks.Meepino mobChamber = new me.creepinson.blocks.Meepino();
ButtKicker buttKicker = new ButtKicker();
Craeol craeol = new Craeol();
Neote neote = new Neote();


@EventHandler
public void severStarting(FMLServerStartingEvent event)
{
	creepino.serverLoad(event);
	bullet.serverLoad(event);
	buttKicker.serverLoad(event);
	mobChamber.serverLoad(event);

	alexion.serverLoad(event);
	craeol.serverLoad(event);
	neote.serverLoad(event);
}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

		chunkX = chunkX * 16;
		chunkZ = chunkZ * 16;
		if (world.provider.getDimension() == -1)
            creepino.generateNether(world, random, chunkX, chunkZ);
		if (world.provider.getDimension() == 0)
			creepino.generateSurface(world, random, chunkX, chunkZ);
		if (world.provider.getDimension() == -1)
			alexion.generateNether(world, random, chunkX, chunkZ);
		if (world.provider.getDimension() == 0)
			alexion.generateSurface(world, random, chunkX, chunkZ);

	}
@Override
public int getBurnTime(ItemStack fuel) {
	if (creepino.addFuel(fuel) != 0)
		return creepino.addFuel(fuel);
	if (alexion.addFuel(fuel) != 0)
		return alexion.addFuel(fuel);
	return 0;
}


	

    //INITS
    @net.minecraftforge.fml.common.Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
    	creepino.instance = this.instance;
    	alexion.instance = this.instance;
    	creepino.preInit(event);
    	alexion.preInit(event);
    
    	bullet.instance = this.instance;
    	buttKicker.instance = this.instance;
    	mobChamber.instance = this.instance;
    	mobChamber.preInit(event);
    	bullet.preInit(event);
    	buttKicker.preInit(event);

    	craeol.instance = this.instance;
    	craeol.preInit(event);
    	
    	neote.instance = this.instance;
    	neote.preInit(event);
    	
    	//SOUNDS
    	
    	ResourceLocation sound9 = new ResourceLocation("meepersplus", "craeol.death");
    	GameRegistry.register(new net.minecraft.util.SoundEvent(sound9).setRegistryName(sound9));

    	ResourceLocation sound10 = new ResourceLocation("meepersplus", "craeol.ambient");
    	GameRegistry.register(new net.minecraft.util.SoundEvent(sound10).setRegistryName(sound10));

    	ResourceLocation sound11= new ResourceLocation("meepersplus", "craeol.hurt");
    	GameRegistry.register(new net.minecraft.util.SoundEvent(sound11).setRegistryName(sound11));
    		
    	
ResourceLocation sound6 = new ResourceLocation("meepersplus", "alexion.death");
GameRegistry.register(new net.minecraft.util.SoundEvent(sound6).setRegistryName(sound6));

ResourceLocation sound7 = new ResourceLocation("meepersplus", "alexion.ambient");
GameRegistry.register(new net.minecraft.util.SoundEvent(sound7).setRegistryName(sound7));

ResourceLocation sound8= new ResourceLocation("meepersplus", "alexion.hurt");
GameRegistry.register(new net.minecraft.util.SoundEvent(sound8).setRegistryName(sound8));
	
    	ResourceLocation sound3 = new ResourceLocation("meepersplus", "neote.death");
		GameRegistry.register(new net.minecraft.util.SoundEvent(sound3).setRegistryName(sound3));

		ResourceLocation sound4 = new ResourceLocation("meepersplus", "neote.ambient");
		GameRegistry.register(new net.minecraft.util.SoundEvent(sound4).setRegistryName(sound4));

		ResourceLocation sound5= new ResourceLocation("meepersplus", "neote.hurt");
		GameRegistry.register(new net.minecraft.util.SoundEvent(sound5).setRegistryName(sound5));

    	
    		ResourceLocation sound0 = new ResourceLocation("meepersplus", "creepinoDeath");
    		GameRegistry.register(new net.minecraft.util.SoundEvent(sound0).setRegistryName(sound0));

    		ResourceLocation sound1 = new ResourceLocation("meepersplus", "creepinoHurt");
    		GameRegistry.register(new net.minecraft.util.SoundEvent(sound1).setRegistryName(sound1));

    		ResourceLocation sound2 = new ResourceLocation("meepersplus", "creepinoScreech");
    		GameRegistry.register(new net.minecraft.util.SoundEvent(sound2).setRegistryName(sound2));
//END SOUNDS
        	registerEntity(EntityCreepino.class, "Creepino", 0, (0 << 16) + (255 << 8) + 51, (204 << 16) + (0 << 8) + 0);
        registerEntityNoEgg(EntityArrowCustom.class, "entityBullet", 1);
		proxy.preInit();
     
    }
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	GameRegistry.registerFuelHandler(this);
		GameRegistry.registerWorldGenerator(this, 1);
		if (event.getSide() == Side.CLIENT) {
			OBJLoader.INSTANCE.addDomain("meepersplus");
		}
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		
		creepino.load(event);
		bullet.load(event);
		buttKicker.load(event);
		mobChamber.load(event);
		alexion.load(event);
		craeol.load(event);
		neote.load(event);

		proxy.registerRenderers(this);
		proxy.registerModelBakeryStuff();
		
		proxy.init();
    	MinecraftForge.EVENT_BUS.register(new EventHandlerMOD());
    	MinecraftForge.EVENT_BUS.register(new MobDropsHandler());
    
    	
    }
    @EventHandler
    public void postInit(FMLPostInitializationEvent PostEvent)
    {
		
		proxy.postInit();	
    	
    }
	
    public static void registerEntity(Class entityClass, String name, int ID, int color1, int color2){
        
        
    long seed = name.hashCode();
    Random rand = new Random(seed);
    int primaryColor = rand.nextInt() * 16777215;
    int secondaryColor = rand.nextInt() * 16777215;

    EntityRegistry.registerModEntity(new ResourceLocation(RefStrings.MODID, name), entityClass, name, ID, instance, 64, 10, true, color1, color2);
    }
    public static void registerEntityNoEgg(Class entityClass, String name, int ID){
        
        
        long seed = name.hashCode();
        Random rand = new Random(seed);
        int primaryColor = rand.nextInt() * 16777215;
        int secondaryColor = rand.nextInt() * 16777215;

        EntityRegistry.registerModEntity(new ResourceLocation(RefStrings.MODID, name), entityClass, name, ID, instance, 64, 10, true);

        }
    
    
    
}
