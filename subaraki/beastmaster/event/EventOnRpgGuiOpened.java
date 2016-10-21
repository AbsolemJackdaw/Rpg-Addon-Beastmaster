package subaraki.beastmaster.event;

import java.util.UUID;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import subaraki.beastmaster.network.NetworkHandler;
import subaraki.beastmaster.network.PacketStowPet;
import subaraki.rpginventory.gui.GuiRpg;
import subaraki.rpginventory.handler.proxy.ClientProxy;

public class EventOnRpgGuiOpened {

	public EventOnRpgGuiOpened() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onguiopen(GuiOpenEvent event){
		if(event.getGui() instanceof GuiRpg){
			UUID uuid = Minecraft.getMinecraft().thePlayer.getUniqueID();
			NetworkHandler.NETWORK.sendToServer(new PacketStowPet(uuid));
		}
	}
}
