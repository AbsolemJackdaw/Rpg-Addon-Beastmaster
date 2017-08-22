package subaraki.beastmaster.event;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.beastmaster.entity.ai.EntityAIMateHookin;

public class EventAddAI {

	public EventAddAI() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onEntitySpawn(EntityJoinWorldEvent event){

		if(event.getEntity() instanceof EntityAnimal){
			EntityAnimal animal = (EntityAnimal) event.getEntity();

			for(Object task : animal.tasks.taskEntries.toArray()){
				EntityAIBase ai = ((EntityAITaskEntry)task).action;
				if(ai instanceof EntityAIMate){
					animal.tasks.addTask(((EntityAITaskEntry)task).priority, new EntityAIMateHookin(animal, (EntityAIMate) ai));
				}
			}

		}
	}
}
