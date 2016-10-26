package subaraki.beastmaster.network;

import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import subaraki.beastmaster.network.PacketStowPet.PacketStowPetHandler;
import subaraki.beastmaster.network.PacketSyncPetting.PacketSyncPettingHandler;

public class NetworkHandler {

	private static final String CHANNEL = "beastmaster_network";
	public static final SimpleNetworkWrapper NETWORK = new SimpleNetworkWrapper(CHANNEL);
	
	public NetworkHandler() {
		NETWORK.registerMessage(PacketStowPetHandler.class, PacketStowPet.class, 0, Side.SERVER);
		NETWORK.registerMessage(PacketSyncPettingHandler.class, PacketSyncPetting.class, 1, Side.CLIENT);
	}
}
