package subaraki.beastmaster.handler.proxy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import subaraki.beastmaster.entity.EntityPetBoar;
import subaraki.beastmaster.entity.EntityPetBull;
import subaraki.beastmaster.entity.EntityPetRooster;
import subaraki.beastmaster.entity.EntityPetSpider;
import subaraki.beastmaster.mod.AddonBeastMaster;

public class ServerProxy {

	public void registerRenders(){};
	public ModelBiped getArmorModel(String type){return null;};
	public void addRenderLayers(){}
	public void registerColors() {};
	public ModelBase getPetModel(String identifier){return null;};
	
	public void registerEntities(){
		EntityRegistry.registerModEntity(EntityPetSpider.class, "spider_pet", 0, AddonBeastMaster.MODID, 256, 4, true);
		EntityRegistry.registerModEntity(EntityPetBoar.class, "boar_pet", 1, AddonBeastMaster.MODID, 256, 4, true);
		EntityRegistry.registerModEntity(EntityPetBull.class, "bull_pet", 2, AddonBeastMaster.MODID, 256, 4, true);
		EntityRegistry.registerModEntity(EntityPetRooster.class, "rooster_pet", 3, AddonBeastMaster.MODID, 256, 4, true);

	}
}
