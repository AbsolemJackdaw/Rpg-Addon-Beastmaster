package subaraki.beastmaster.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import subaraki.beastmaster.capability.BmCapability;
import subaraki.beastmaster.capability.BmData;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;
import subaraki.rpginventory.enums.SlotIndex;

public class SummonStowPetLogic {

	public static void summonPet(EntityPlayer player){

		RpgInventoryData inv = RpgInventoryData.get(player);
		BmData beastmaster = player.getCapability(BmCapability.CAPABILITY, null);
		World world = player.world;

		if(inv.getCrystal() != ItemStack.EMPTY){

			if(beastmaster.getPetUuid() != null) // in case when riding or having the pet loaded when logging out
				stowPet(player);

			if(beastmaster.getPetUuid() == null){

				EntityBeastmasterPet pet = BeastMasterPet.instance.getNewPet(world, inv.getCrystal(), player);

				if(pet != null)
					if(!world.isRemote){
						world.spawnEntity(pet);
						beastmaster.setPetid(pet.getUniqueID());
					}
			}
		}
	}

	/**saves pet to crystal, and sets the player's saved uuid from the pet to null, so it can call it again*/
	public static void stowPet(EntityPlayer player){

		BmData beastmaster = player.getCapability(BmCapability.CAPABILITY, null);
		World world = player.world;

		if(beastmaster.getPetUuid() != null){
			EntityBeastmasterPet pet = null;

			for(Entity e : world.loadedEntityList){
				if(e instanceof EntityBeastmasterPet && e.getUniqueID().equals(beastmaster.getPetUuid())){
					pet = (EntityBeastmasterPet)e;
					break;
				}
			}

			if(pet != null){
				savePet(pet, player);
				pet.isDead = true; //manually do 'setDead' as said method stows pet
			}
			//set null anyway, for when stowing is called from loging in riding the pet
			beastmaster.setPetid(null);
		}
	}

	/**saves the pet to the payer's crystal*/
	public static void savePet(EntityBeastmasterPet pet, EntityPlayer player){
		RpgInventoryData inv = RpgInventoryData.get(player);
		ItemStack crystal = inv.getCrystal();
		if(crystal != ItemStack.EMPTY)
			if(pet != null){

				NBTTagCompound livingTag = new NBTTagCompound();
				pet.writeEntityToNBT(livingTag);

				NBTTagCompound entityTag = new NBTTagCompound();
				pet.writeToNBT(entityTag);

				NBTTagCompound stacktag = crystal.getTagCompound();
				if(stacktag == null) stacktag = new NBTTagCompound();
				stacktag.setTag(EntityBeastmasterPet.NBT_STACKDATA_LIVING, livingTag);
				stacktag.setTag(EntityBeastmasterPet.NBT_STACKDATA_ENTITY, entityTag);

				crystal.setTagCompound(stacktag);
				inv.setJewel(SlotIndex.SLOT_CRYSTAL, crystal);
			}
	}
}
