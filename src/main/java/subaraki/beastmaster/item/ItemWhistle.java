package subaraki.beastmaster.item;

import com.mojang.realmsclient.gui.ChatFormatting;

import lib.playerclass.capability.PlayerClass;
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
import subaraki.rpginventory.capability.playerinventory.RpgInventoryData;

public class ItemWhistle extends Item{

	public ItemWhistle() {

	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn,EnumHand hand) {

		ItemStack itemStackIn = playerIn.getHeldItem(hand);
		
		if(PlayerClass.get(playerIn).isPlayerClass(BeastMasterItems.BEASTMASTER_CLASS)){
			RpgInventoryData inv = RpgInventoryData.get(playerIn);
			BmData beastmaster = playerIn.getCapability(BmCapability.CAPABILITY, null);

			if(inv.getCrystal() != ItemStack.EMPTY && inv.getCrystal().getMetadata() > 0){
				if(!worldIn.isRemote)
					if(beastmaster.getPetUuid() == null)
						SummonStowPetLogic.summonPet(playerIn);
					else
						SummonStowPetLogic.stowPet(playerIn);
				playerIn.playSound(BeastMasterSounds.whistle, 1f, 1f);
			}else{
				if(!worldIn.isRemote)
					playerIn.sendMessage(new TextComponentString(ChatFormatting.ITALIC+"You blow the whistle but forgot to get a pet"));
			}
		}else{
			if(!worldIn.isRemote)
				playerIn.sendMessage(new TextComponentString(ChatFormatting.ITALIC+"You lack the mastery of Beasts to use this whistle"));
		}
		return super.onItemRightClick(worldIn, playerIn, hand);
	}
}
