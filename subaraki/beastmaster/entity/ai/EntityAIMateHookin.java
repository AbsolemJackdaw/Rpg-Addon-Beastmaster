package subaraki.beastmaster.entity.ai;

import java.util.Random;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumParticleTypes;
import subaraki.beastmaster.capability.BmCapability;
import subaraki.beastmaster.capability.BmData;

public class EntityAIMateHookin extends EntityAIBase{

	private EntityAIMate aiMate;
	private EntityAnimal animal;

	private int timer = 0;

	public EntityAIMateHookin(EntityAnimal animal, EntityAIMate aiMate) {
		this.aiMate = aiMate;
		this.animal = animal;
	}

	@Override
	public boolean shouldExecute() {
		//starts executing when aiMate does
		return aiMate.shouldExecute();
	}

	@Override
	public boolean continueExecuting() {
		//Stops/continues as long as the aiMate does and our timer hits 60. (to prevent it stopping before it reaches 0)
		boolean canContinue = false;
		
		try {
			canContinue = aiMate.continueExecuting();
		} catch (NullPointerException e) {
			//do nothing
		}
		
		return canContinue && timer < 60;
	}

	@Override
	public void updateTask() {

		//update timer to match the spawnBabyDelay
		timer++;

		if(timer >= 60){
			if(animal.getPlayerInLove() != null)
				animal.getPlayerInLove().getCapability(BmCapability.CAPABILITY, null).addAnimalAffinity(2);
		}
	}

	@Override
	public void resetTask() {
		//reset when aiMate stops executing
		timer = 0;
	}

}
