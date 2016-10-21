package subaraki.beastmaster.item;

import static lib.item.ItemRegistry.registerItem;
import static lib.item.ItemRegistry.registerRender;

import lib.item.shield.ItemCustomShield;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import subaraki.beastmaster.item.armor.ItemBeastmasterArmor;
import subaraki.beastmaster.item.weapon.ItemClaws;
import subaraki.beastmaster.mod.AddonBeastMaster;
import subaraki.rpginventory.enums.JewelTypes;
import subaraki.rpginventory.item.RpgInventoryItem;
import subaraki.rpginventory.item.RpgItems.LocalizeEnum;

public class BeastMasterItems {

	public static final String BEASTMASTER_CLASS="beastmaster";
	public static ItemBeastmasterArmor bm_helm, bm_chest, bm_legs, bm_feet;
	public static ItemClaws claws;
	public static Item beastmasterplate;
	public static ItemCustomShield beastmastershield;
	
	public static ItemCrystal crystal;
	
	public static Item fur, claw, whistle;
	
	final static String modid = AddonBeastMaster.MODID;
	
	public static final CreativeTabs tab =  new CreativeTabs("addon_beastmaster") {
		@Override
		public Item getTabIconItem() {
			return bm_helm;
		}
	};
	
	public static void load(){
		
		bm_helm = (ItemBeastmasterArmor) new ItemBeastmasterArmor(EntityEquipmentSlot.HEAD).setCreativeTab(tab);
		bm_chest = (ItemBeastmasterArmor) new ItemBeastmasterArmor(EntityEquipmentSlot.CHEST).setCreativeTab(tab);
		bm_legs = (ItemBeastmasterArmor) new ItemBeastmasterArmor(EntityEquipmentSlot.LEGS).setCreativeTab(tab);
		bm_feet = (ItemBeastmasterArmor) new ItemBeastmasterArmor(EntityEquipmentSlot.FEET).setCreativeTab(tab);

		claws = (ItemClaws) new ItemClaws().setUnlocalizedName(modid+".claws").setRegistryName(modid+".claws").setCreativeTab(tab);
		
		beastmasterplate = new Item().setCreativeTab(tab).setUnlocalizedName(modid+".beastmasterplate").setRegistryName("beastmasterplate");
		beastmastershield = (ItemCustomShield) new ItemCustomShield(){
			@Override
			public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
				return super.getIsRepairable(toRepair, repair);
			}
		}.setCreativeTab(tab).setMaxDamage(250).setUnlocalizedName(modid+".beastmastershield").setRegistryName("beastmastershield");
		
		claw = new Item().setCreativeTab(tab).setUnlocalizedName(modid+".animalclaw").setRegistryName(modid+".animalclaw");
		fur = new Item().setCreativeTab(tab).setUnlocalizedName(modid+".roughfur").setRegistryName(modid+".roughfur");
		
		crystal = (ItemCrystal) new ItemCrystal(JewelTypes.CRYSTAL, modid+".crystal").setCreativeTab(tab);
	
		whistle = (ItemWhistle) new ItemWhistle().setCreativeTab(tab).setUnlocalizedName(modid+".whistle").setRegistryName(modid+".whistle");
		
		register();
	}
	
	private static void register(){
		registerItem(bm_helm);
		registerItem(bm_chest);
		registerItem(bm_legs);
		registerItem(bm_feet);
		registerItem(claws);
		registerItem(fur);
		registerItem(claw);
		registerItem(crystal);
		registerItem(whistle);
	}
	
	public static void registerRenders(){
		registerRender(bm_helm, bm_helm.getModeltextureLocation(), modid);
		registerRender(bm_chest, bm_chest.getModeltextureLocation(), modid);
		registerRender(bm_legs, bm_legs.getModeltextureLocation(), modid);
		registerRender(bm_feet, bm_feet.getModeltextureLocation(), modid);
		registerRender(claws, "weapon/claw", modid);
		registerRender(fur, "fur", modid);
		registerRender(claw, "claw", modid);
		for(int i=0;i<5;i++)
		registerRender(crystal, "crystal", modid, i);
		registerRender(whistle, "whistle", modid);
	}
}
