package subaraki.beastmaster.event;

import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityMob;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import subaraki.beastmaster.entity.EntityBeastmasterPet;

public class EventPetExperience {

	public EventPetExperience() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onDeath(LivingDeathEvent event){
		if(event.getSource().getEntity() instanceof EntityBeastmasterPet){
			
			//imitate vanilla's general experience orb rules
			//10 for mobs (+ 1 per equipment worn)
			//1 + random 3 for animals
			int exp = event.getEntityLiving() instanceof EntityMob ? 8 : event.getEntityLiving() instanceof EntityDragon ? 498 :  event.getEntityLiving() instanceof EntityWither ? 48 : 2;
			exp += event.getEntityLiving().worldObj.rand.nextInt(4);
			
			((EntityBeastmasterPet)event.getSource().getEntity()).addExperience(exp);
		}
	}
}
