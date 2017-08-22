package subaraki.beastmaster.handler.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.model.ModelSpider;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import subaraki.beastmaster.entity.EntityPetBoar;
import subaraki.beastmaster.entity.EntityPetBull;
import subaraki.beastmaster.entity.EntityPetRooster;
import subaraki.beastmaster.entity.EntityPetSpider;
import subaraki.beastmaster.entity.model.ModelBoar;
import subaraki.beastmaster.entity.model.ModelBull;
import subaraki.beastmaster.entity.model.ModelPetSpider;
import subaraki.beastmaster.entity.model.ModelRooster;
import subaraki.beastmaster.entity.render.RenderBeastMasterPet;
import subaraki.beastmaster.item.BeastMasterItems;
import subaraki.beastmaster.item.armor.model.ModelBeastArmor;

public class ClientProxy extends ServerProxy {

	private static final ModelBeastArmor bm_chest = new ModelBeastArmor(1.0f);
	private static final ModelBeastArmor bm_rest = new ModelBeastArmor(0.5f);
	public static final String BM_CHEST = "BMCHEST";
	public static final String BM_REST = "BMREST";


	public static final String PET_SPIDER_CHILD = "child_spider";
	public static final String PET_SPIDER = "spider";
	public static final String PET_SPIDER_BIG = "big_spider";
	public static final ModelSpider spider = new ModelSpider();
	public static final ModelPetSpider buff_spider = new ModelPetSpider();

	public static final String PET_PIG_CHILD = "child_pig";
	public static final String PET_PIG = "pig";
	public static final String PET_PIG_BIG = "big_pig";
	public static final ModelPig pig = new ModelPig();
	public static final ModelBoar buff_pig = new ModelBoar();

	public static final String PET_COW_CHILD = "child_cow";
	public static final String PET_COW = "cow";
	public static final String PET_COW_BIG = "big_cow";
	public static final ModelCow cow = new ModelCow();
	public static final ModelBull buff_cow = new ModelBull();

	public static final String PET_CHICKEN_CHILD = "child_chicken";
	public static final String PET_CHICKEN = "chicken";
	public static final String PET_CHICKEN_BIG = "big_chicken";
	public static final ModelChicken chicken = new ModelChicken();
	public static final ModelRooster buff_chicken = new ModelRooster();

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

		RenderingRegistry.registerEntityRenderingHandler(EntityPetSpider.class, RenderBeastMasterPet<EntityPetSpider>::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPetBoar.class, RenderBeastMasterPet<EntityPetBoar>::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPetBull.class, RenderBeastMasterPet<EntityPetBull>::new);
		RenderingRegistry.registerEntityRenderingHandler(EntityPetRooster.class, RenderBeastMasterPet<EntityPetRooster>::new);
		
	}

	@Override
	public ModelBase getPetModel(String identifier) {
		switch (identifier) {

		case PET_SPIDER_CHILD:
		case PET_SPIDER: return spider;
		case PET_SPIDER_BIG: return buff_spider;
			
		case PET_PIG_CHILD: 
		case PET_PIG :  return pig;
		case PET_PIG_BIG : return buff_pig;

		case PET_COW_CHILD:
		case PET_COW : return cow;
		case PET_COW_BIG : return buff_cow;
		
		case PET_CHICKEN_CHILD :
		case PET_CHICKEN : return chicken;
		case PET_CHICKEN_BIG : return buff_chicken;

		}
		return null;
	}

	@Override
	public void addRenderLayers() {

	}

	@Override
	public void registerColors() {
		ItemColors ic = Minecraft.getMinecraft().getItemColors();

		ic.registerItemColorHandler(

				new IItemColor() {
					@Override
					public int getColorFromItemstack(ItemStack stack, int tintIndex) {
						if (stack.getItemDamage() == 1) 
							return 0xc36113;
						if (stack.getItemDamage() == 2) 
							return 0x0a8274;
						if (stack.getItemDamage() == 3) 
							return 0xe71809;
						if (stack.getItemDamage() == 4) 
							return 0x41ed84;

						return 0xffffff;
					}
				},
				BeastMasterItems.crystal
				);
		
		ic.registerItemColorHandler(
				new IItemColor() {
					@Override
					public int getColorFromItemstack(ItemStack stack, int tintIndex) {
						return 0x8b5e4d;
					}
				},
				BeastMasterItems.craftLeather
				);
	}
}
