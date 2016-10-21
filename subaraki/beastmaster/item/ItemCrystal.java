package subaraki.beastmaster.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import subaraki.beastmaster.entity.EntityBeastmasterPet;
import subaraki.rpginventory.enums.JewelTypes;
import subaraki.rpginventory.item.RpgInventoryItem;

public class ItemCrystal extends RpgInventoryItem{

	public ItemCrystal(JewelTypes armortype, String registerylocalizeName) {
		super(armortype, registerylocalizeName);
		setHasSubtypes(true);
	}

	@Override
	public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
		for (int i = 0; i < 5; ++i){
			subItems.add(new ItemStack(itemIn, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getMetadata();
		return super.getUnlocalizedName() + "_" + meta ;
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return stack.getMetadata() > 0;
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {

		if(stack.hasTagCompound()){
			if(stack.getTagCompound().hasKey(EntityBeastmasterPet.NBT_STACKDATA_ENTITY)){
				NBTTagCompound entityTag = (NBTTagCompound) stack.getTagCompound().getTag(EntityBeastmasterPet.NBT_STACKDATA_ENTITY);
				if(entityTag.hasKey("CustomName"))
					return entityTag.getString("CustomName");
			}
		}

		return super.getItemStackDisplayName(stack);
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);

		if(stack.hasTagCompound()){
			NBTTagCompound nbt = stack.getTagCompound();
			if(nbt.hasKey(EntityBeastmasterPet.NBT_STACKDATA_LIVING)){
				NBTTagCompound entityTag = (NBTTagCompound) nbt.getTag(EntityBeastmasterPet.NBT_STACKDATA_LIVING);
				if(entityTag.hasKey(EntityBeastmasterPet.NBT_LEVEL))
					tooltip.add("Lvl : " + entityTag.getInteger(EntityBeastmasterPet.NBT_LEVEL));
			}
		}
	}
}
