package subaraki.beastmaster.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import subaraki.beastmaster.capability.BmCapability;
import subaraki.beastmaster.capability.BmData;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;
import subaraki.rpginventory.enums.SlotIndex;

public class SummonStowPetLogic {

	public static void summonPet(EntityPlayer player){

		RpgPlayerInventory inv = player.getCapability(RpgInventoryCapability.CAPABILITY, null);
		BmData beastmaster = player.getCapability(BmCapability.CAPABILITY, null);
		World world = player.worldObj;

		if(inv.getCrystal() != null){

			if(beastmaster.getPetUuid() == null){

				EntityBeastmasterPet pet = BeastMasterPet.instance.getNewPet(world, inv.getCrystal(), player);

				if(pet != null)
					if(!world.isRemote){
						world.spawnEntityInWorld(pet);
						beastmaster.setPetid(pet.getUniqueID());
					}
			}
		}
	}

	/**saves pet to crystal, and sets the player's saved uuid from the pet to null, so it can call it again*/
	public static void stowPet(EntityPlayer player){

		BmData beastmaster = player.getCapability(BmCapability.CAPABILITY, null);
		World world = player.worldObj;

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
				pet.setDead();
				beastmaster.setPetid(null);
			}
		}
	}

	/**saves the pet to the payer's crystal*/
	public static void savePet(EntityBeastmasterPet pet, EntityPlayer player){
		RpgPlayerInventory inv = player.getCapability(RpgInventoryCapability.CAPABILITY, null);
		ItemStack crystal = inv.getCrystal();
		if(crystal != null)
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
