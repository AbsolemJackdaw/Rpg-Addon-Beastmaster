package subaraki.beastmaster.capability;

import java.util.UUID;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;

public class BmData {

	private EntityPlayer player;

	private UUID petid;
	
	private boolean isPetting;
	
	public BmData(){
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
		
		//save mix of itemstacks and personal tags
		return tag;
	}
	
	public void readData(NBTBase nbt){

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
}
