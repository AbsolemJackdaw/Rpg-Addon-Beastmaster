package subaraki.beastmaster.handler.proxy;

import net.minecraft.client.model.ModelBiped;
import subaraki.beastmaster.item.BeastMasterItems;
import subaraki.beastmaster.item.armor.model.ModelBeastArmor;

public class ClientProxy extends ServerProxy {

	private static final ModelBeastArmor bm_chest = new ModelBeastArmor(1.0f);
	private static final ModelBeastArmor bm_rest = new ModelBeastArmor(0.5f);
	public static final String BM_CHEST = "BMCHEST";
	public static final String BM_REST = "BMREST";

	@Override
	public ModelBiped getArmorModel(String type) {

		switch (type) {
		case BM_CHEST:
			return bm_chest;
		case BM_REST:
			return bm_rest;
		}

		return bm_chest;
	}

	@Override
	public void registerRenders() {
		BeastMasterItems.registerRenders();
	}

	@Override
	public void addRenderLayers() {
//		RenderPlayer p = ((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default"));
//		RenderPlayer p2 = ((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim"));
		
	}
}
