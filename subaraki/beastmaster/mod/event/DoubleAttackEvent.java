package subaraki.beastmaster.mod.event;

import net.minecraft.util.EnumHand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import subaraki.beastmaster.item.BeastMasterItems;

public class DoubleAttackEvent {

	public DoubleAttackEvent() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	EnumHand prevSwing = null;

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event){

	}
}
