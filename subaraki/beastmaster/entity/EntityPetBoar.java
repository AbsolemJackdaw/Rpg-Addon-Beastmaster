package subaraki.beastmaster.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import subaraki.beastmaster.handler.proxy.ClientProxy;
import subaraki.beastmaster.mod.AddonBeastMaster;

public class EntityPetBoar extends EntityBeastmasterPet{

	private ResourceLocation pig = new ResourceLocation("textures/entity/pig/pig.png");
	private ResourceLocation normal = new ResourceLocation("beastmaster:textures/pet/boar.png");
	private ResourceLocation saddled = new ResourceLocation("beastmaster:textures/pet/boar_saddled.png");

	public EntityPetBoar(World worldIn) {
		super(worldIn);
	}

	public EntityPetBoar(World world, ItemStack crystal) {
		super(world, crystal);
	}

	@Override
	public float getBaseHeight() {
		return 0.4F;
	}

	@Override
	public float getBaseWidth() {
		return 0.4F;
	}

	@Override
	public float getAttackDamage() {
		// 7 Base Damage
		// 30 Damage at level 200
		return (7 + MathHelper.floor(((getLevel()) *1.0D) / 9.52D));

	}

	@Override
	public float getPetSize() {
		return  0.5F + (((float)getLevel() / 200.0F) * 1.5F);
	}

	@Override
	public ResourceLocation getPetTextures() {
		return  getLevel() < 39 ? pig : !isSaddled() ? normal : saddled;
	}

	@Override
	public float getHealthIncreaseForLeveling() {
		return 20F + MathHelper.floor(((float)getLevel()) / 2.5F);
	}

	@Override
	public float getSpeedIncreaseForLeveling() {
		return 0.2F + (float)getLevel() / 600F;
	}

	@Override
	public int getRegenDelay() {
		return 60;
	}

	@Override
	public ModelBase getModel() {
		return getLevel() < 10 ? AddonBeastMaster.proxy.getPetModel(ClientProxy.PET_PIG_CHILD) : getLevel() < 39 ? AddonBeastMaster.proxy.getPetModel(ClientProxy.PET_PIG) : AddonBeastMaster.proxy.getPetModel(ClientProxy.PET_PIG_BIG);
	}

}
