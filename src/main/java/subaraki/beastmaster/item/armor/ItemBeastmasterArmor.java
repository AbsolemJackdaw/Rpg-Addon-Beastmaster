package subaraki.beastmaster.item.armor;

import lib.item.armor.ModeledArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import subaraki.beastmaster.config.AMCC;
import subaraki.beastmaster.config.ConfigurationHandler;
import subaraki.beastmaster.handler.proxy.ClientProxy;
import subaraki.beastmaster.item.BeastMasterItems;
import subaraki.beastmaster.mod.AddonBeastMaster;

public class ItemBeastmasterArmor extends ModeledArmor{

	private static final AMCC bm = new AMCC(ConfigurationHandler.instance.beastmaster_armor);
	public static final ArmorMaterial beastmaster_armor = 
			EnumHelper.addArmorMaterial("beastmaster", AddonBeastMaster.MODID+":beastmaster", bm.getDurability(), bm.getReduction(), bm.getEnchantibility(), SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, bm.getToughness());
	
	public ItemBeastmasterArmor(EntityEquipmentSlot slot){
		super(slot, beastmaster_armor, "beastmaster_"+slot.getName());
	}

	@Override
	public String armorClassName() {
		return BeastMasterItems.BEASTMASTER_CLASS;
	}

	@Override
	public Item getLinkedShieldItem() {
		return BeastMasterItems.claws;
	}

	@Override
	protected void get3DArmorModel(EntityLivingBase entityLiving, ItemStack stack, EntityEquipmentSlot armorSlot) {
		switch (armorSlot) {
		case CHEST:
		case FEET:
		case HEAD:
			setArmorModel(AddonBeastMaster.proxy.getArmorModel(ClientProxy.BM_CHEST));
			break;
		case LEGS:
			setArmorModel(AddonBeastMaster.proxy.getArmorModel(ClientProxy.BM_REST));
			break;

		default:
			break;
		}
	}

}
