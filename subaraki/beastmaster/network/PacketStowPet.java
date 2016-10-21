package subaraki.beastmaster.network;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import subaraki.beastmaster.entity.SummonStowPetLogic;

public class PacketStowPet implements IMessage{
	
	public PacketStowPet() {
	}
	
	public UUID uuid;
	public PacketStowPet(UUID uuid){
		this.uuid = uuid;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		uuid = UUID.fromString(ByteBufUtils.readUTF8String(buf));
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeUTF8String(buf, uuid.toString());
	}
	
	public static class PacketStowPetHandler implements IMessageHandler<PacketStowPet, IMessage>{

		@Override
		public IMessage onMessage(PacketStowPet message, MessageContext ctx) {
			((WorldServer)ctx.getServerHandler().playerEntity.worldObj).addScheduledTask(() -> {
				EntityPlayer player = ctx.getServerHandler().playerEntity.worldObj.getPlayerEntityByUUID(message.uuid);
				
				SummonStowPetLogic.stowPet(player);
			});
			return null;
		}
	}
}
