package subaraki.beastmaster.capability;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class BmData {

	private EntityPlayer player;

	private UUID petid;

	private boolean isPetting;

	private int animalAffinity = 0;

	public BmData(){
	}

	public static BmData get(EntityPlayer player){
		return player.getCapability(BmCapability.CAPABILITY, null);
	}
	
	public EntityPlayer getPlayer() { 
		return player; 
	}

	public void setPlayer(EntityPlayer newPlayer){
		this.player = newPlayer;
	}

	public NBTBase writeData(){
		NBTTagCompound tag = new NBTTagCompound();
		//add our own tags
		if(petid != null)
			tag.setString("petuuid", petid.toString());
		
		tag.setInteger("animalAffinity", animalAffinity);
		//save mix of itemstacks and personal tags
		return tag;
	}

	public void readData(NBTBase nbt){
		if(((NBTTagCompound)nbt).hasKey("petuuid")){
			String uuid = ((NBTTagCompound)nbt).getString("petuuid");
			if(uuid != null && !uuid.isEmpty() && uuid.length() > 1) //uuid's are always pretty long.
				petid = UUID.fromString(uuid);
		}
		
		animalAffinity = ((NBTTagCompound)nbt).getInteger("animalAffinity");
	}

	public UUID getPetUuid() {
		return petid;
	}

	public void setPetid(UUID petid) {
		this.petid = petid;
	}

	public boolean isPetting() {
		return isPetting;
	}

	public void setPetting(boolean isPetting) {
		this.isPetting = isPetting;
	}

	public void addAnimalAffinity(int affinity){
		if(animalAffinity + affinity < 255) 
			animalAffinity += affinity;
	}

	public int getAnimalAffinity() {
		return animalAffinity;
	}
}
