package subaraki.beastmaster.item;

import com.mojang.realmsclient.gui.ChatFormatting;

import lib.playerclass.PlayerClass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import subaraki.beastmaster.capability.BmCapability;
import subaraki.beastmaster.capability.BmData;
import subaraki.beastmaster.entity.SummonStowPetLogic;
import subaraki.beastmaster.sounds.BeastMasterSounds;
import subaraki.rpginventory.capability.playerinventory.RpgInventoryCapability;
import subaraki.rpginventory.capability.playerinventory.RpgPlayerInventory;

public class ItemWhistle extends Item{

	public ItemWhistle() {

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn,EnumHand hand) {

		if(PlayerClass.isInstanceOf(BeastMasterItems.BEASTMASTER_CLASS)){
			RpgPlayerInventory inv = playerIn.getCapability(RpgInventoryCapability.CAPABILITY, null);
			BmData beastmaster = playerIn.getCapability(BmCapability.CAPABILITY, null);

			if(inv.getCrystal() != null && inv.getCrystal().getMetadata() > 0){
				if(!worldIn.isRemote)
					if(beastmaster.getPetUuid() == null)
						SummonStowPetLogic.summonPet(playerIn);
					else
						SummonStowPetLogic.stowPet(playerIn);
				playerIn.playSound(BeastMasterSounds.whistle, 1f, 1f);
			}else{
				if(!worldIn.isRemote)
					playerIn.addChatComponentMessage(new TextComponentString(ChatFormatting.ITALIC+"You blow the whistle but forgot to get a pet"));
			}
		}else{
			if(!worldIn.isRemote)
				playerIn.addChatComponentMessage(new TextComponentString(ChatFormatting.ITALIC+"You lack the mastery of Beasts to use this whistle"));
		}
		return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
	}
}
