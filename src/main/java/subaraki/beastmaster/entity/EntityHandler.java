package subaraki.beastmaster.entity;

import java.util.ArrayList;

import lib.entity.EntityRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import subaraki.beastmaster.mod.AddonBeastMaster;

public class EntityHandler {

	public EntityHandler() {
		MinecraftForge.EVENT_BUS.register(this);
		init();
	}

	ArrayList<EntityEntryBuilder> entities = new ArrayList<EntityEntryBuilder>();

	private void init()
	{
		EntityRegistry er = new EntityRegistry();

		EntityEntryBuilder spider = er.registerEntity(EntityPetSpider.class, EntityPetSpider::new,  AddonBeastMaster.MODID, "spider_pet", 0, 256, 4, true);
		EntityEntryBuilder boar = er.registerEntity(EntityPetBoar.class, EntityPetBoar::new,  AddonBeastMaster.MODID, "boar_pet", 1, 256, 4, true);
		EntityEntryBuilder bull = er.registerEntity(EntityPetBull.class, EntityPetBull::new,  AddonBeastMaster.MODID, "bull_pet", 2, 256, 4, true);
		EntityEntryBuilder rooster = er.registerEntity(EntityPetRooster.class, EntityPetRooster::new,  AddonBeastMaster.MODID, "rooster_pet", 3, 256, 4, true);

		entities.add(boar);
		entities.add(rooster);
		entities.add(spider);
		entities.add(bull);
	}

	@SubscribeEvent
	public void registerEntities(RegistryEvent.Register<EntityEntry> e){

		for(EntityEntryBuilder eeb : entities)
			e.getRegistry().register(eeb.build());
	}
}
