package subaraki.beastmaster.mod;

import java.lang.reflect.Field;

import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import subaraki.beastmaster.handler.proxy.ServerProxy;
import subaraki.beastmaster.item.BeastMasterItems;
import subaraki.beastmaster.mod.event.DoubleAttackEvent;
import subaraki.beastmaster.mod.event.EventBeastmasterAttraction;

@Mod(modid = AddonBeastMaster.MODID, name = AddonBeastMaster.NAME, version = AddonBeastMaster.VERSION, dependencies = AddonBeastMaster.DEPENDENCY)
public class AddonBeastMaster {

	public static final String MODID = "beastmaster";
	public static final String NAME = "Beastmaster Class Armor";
	public static final String VERSION = "1.10.2 1.0.0.0";
	public static final String DEPENDENCY = "required-after:subcommonlib";

	@SidedProxy(clientSide = "subaraki.beastmaster.handler.proxy.ClientProxy", serverSide = "subaraki.beastmaster.handler.proxy.ServerProxy")
	public static ServerProxy proxy;
	
	public static Field parent = ReflectionHelper.findField(EntityAIFollowParent.class, "parentAnimal", "field_75346_b");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){
		BeastMasterItems.load();
		proxy.registerRenders();
		
		new EventBeastmasterAttraction();
		new DoubleAttackEvent();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		proxy.addRenderLayers();
	}
}
