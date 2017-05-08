package subaraki.beastmaster.item.weapon;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import subaraki.beastmaster.item.BeastMasterItems;

public class ItemClaws extends ItemSword{

	private static final ToolMaterial claws = EnumHelper.addToolMaterial("claws", 0, 180, 3, 8, 52);

	public ItemClaws() {
		super(claws);

		this.addPropertyOverride(new ResourceLocation("blocking"), new IItemPropertyGetter()
		{
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn)
			{
				return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
			}
		});
	}

	@Override
	public EnumAction getItemUseAction(ItemStack stack){
		return EnumAction.BLOCK;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack){
		return 72000;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand){

		ItemStack itemStackIn = playerIn.getHeldItem(hand);

		if(hand.equals(EnumHand.OFF_HAND)){
			playerIn.setActiveHand(hand);
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		}else{
			return new ActionResult(EnumActionResult.PASS, itemStackIn);
		}
	}

	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return repair.getItem().equals(BeastMasterItems.claw) ? true : super.getIsRepairable(toRepair, repair);
	}
}
