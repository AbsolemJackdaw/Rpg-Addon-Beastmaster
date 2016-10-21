package subaraki.beastmaster.network;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import subaraki.beastmaster.capability.BmCapability;
import subaraki.beastmaster.capability.BmData;

public class PacketSyncPetting implements IMessage {

	public boolean isPetting;
	
	public PacketSyncPetting() {
	
	}
	
	public PacketSyncPetting(boolean isPetting){
		this.isPetting = isPetting;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		isPetting = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(isPetting);
	}
	
	public static class PacketSyncPettingHandler implements IMessageHandler<PacketSyncPetting, IMessage>{

		@Override
		public IMessage onMessage(PacketSyncPetting message, MessageContext ctx) {
			Minecraft.getMinecraft().addScheduledTask(() -> {
				Minecraft.getMinecraft().thePlayer.getCapability(BmCapability.CAPABILITY, null).setPetting(message.isPetting);
			});
			return null;
		}
		
	}
}
