package subaraki.beastmaster.entity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BeastMasterPet {

	public static BeastMasterPet instance;
	
	public BeastMasterPet() {
		instance = this;
		
		thepets = new ArrayList<Class>();
		thepets.add(EntityPetBoar.class);//boar
		thepets.add(EntityPetSpider.class);
		thepets.add(EntityPetBull.class);//bull
		thepets.add(EntityPetRooster.class);//chicken
	}
	
	private ArrayList< Class > thepets;

	public EntityBeastmasterPet getNewPet(World world, ItemStack crystal, EntityPlayer player){
		EntityBeastmasterPet pet = null;

		if(crystal != null){
			if(crystal.getMetadata() == 0)
				return null;
			
			try {
				
				pet = (EntityBeastmasterPet) thepets.get(crystal.getMetadata()-1).getDeclaredConstructor(World.class, ItemStack.class).newInstance(world, crystal);
				pet.setOwnerId(player.getUniqueID());
				pet.setTamed(true);
				pet.setPosition(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
				
				if(pet.getHealth() <= 0)
					pet.setHealth(1);
				
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}

		}

		return pet;
	}
	
	public int getDamageFromPet(EntityBeastmasterPet pet){
		if(pet instanceof EntityPetSpider)
			return 2;
		if(pet instanceof EntityPetBoar)
			return 1;
		if(pet instanceof EntityPetBull)
			return 3;
		if(pet instanceof EntityPetRooster)
			return 4;
		
		return 0;
	}
	
	public int getDamageFromEntity(EntityLivingBase pet){
		if(pet instanceof EntityPig)
			return 1;
		if(pet instanceof EntitySpider)
			return 2;
		if(pet instanceof EntityCow)
			return 3;
		if(pet instanceof EntityChicken)
			return 4;
		
		return 0;
	}
}
