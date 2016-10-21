package subaraki.beastmaster.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerChangedDimensionEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedOutEvent;
import subaraki.beastmaster.capability.CapabilityBmProvider;
import subaraki.beastmaster.entity.SummonStowPetLogic;

public class PlayerTracker {

	public PlayerTracker() {
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public void onCapabilityAttach(AttachCapabilitiesEvent.Entity event){
		final Entity entity = event.getEntity();

		if (entity instanceof EntityPlayer)
			event.addCapability(CapabilityBmProvider.KEY, new CapabilityBmProvider((EntityPlayer)entity)); 
	}
	
	////////////////////////////////////////
	///////////////LOGOUT///////////////////
	////////////////////////////////////////

	private void onLogout(EntityPlayer player){
		SummonStowPetLogic.stowPet(player);
	}
}
