package subaraki.beastmaster.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import subaraki.beastmaster.handler.proxy.ClientProxy;
import subaraki.beastmaster.mod.AddonBeastMaster;

public class EntityPetBull extends EntityBeastmasterPet{

	private static final ResourceLocation normal = new ResourceLocation("beastmaster:textures/pet/bull.png");
	private static final ResourceLocation saddled = new ResourceLocation("beastmaster:textures/pet/bull_saddled.png");
	private static final ResourceLocation cowTexture = new ResourceLocation("textures/entity/cow/cow.png");

	public EntityPetBull(World worldIn) {
		super(worldIn);
	}

	public EntityPetBull(World world, ItemStack crystal) {
		super(world, crystal);
	}

	@Override
	public float getBaseHeight() {
		return 0.1F + (((float)getLevel()/ 200.0F) * 0.5F);
	}

	@Override
	public float getBaseWidth() {
		return 0.2F + + (((float)getLevel()/ 200.0F) * 0.3F);
	}

	@Override
	public float getAttackDamage() {
		// 4 Base Damage
		// 15 Damage at level 200
		return (4 + MathHelper.floor_double(((getLevel()) * 1.0D) / 14.18D));
	}

	@Override
	public float getPetSize() {
		return getLevel() <= 200 ? 0.5F + (((float)getLevel()/ 200.0F) * 1.5F) : 2.0f;
	}

	@Override
	public ResourceLocation getPetTextures() {
		return getLevel() < 39 ? cowTexture : !isSaddled() ? normal : saddled;

	}

	@Override
	public float getHealthIncreaseForLeveling() {
		return 30F + MathHelper.floor_float((float)getLevel()/1.538F);
	}

	@Override
	public float getSpeedIncreaseForLeveling() {
		return 0.2F + (float)getLevel()/500F;
	}

	@Override
	public int getRegenDelay() {
		return 40;
	}

	@Override
	public ModelBase getModel() {
		return getLevel() < 10 ? AddonBeastMaster.proxy.getPetModel(ClientProxy.PET_COW_CHILD) : getLevel() < 39 ? AddonBeastMaster.proxy.getPetModel(ClientProxy.PET_COW) : AddonBeastMaster.proxy.getPetModel(ClientProxy.PET_COW_BIG);
	}

	@Override
	public double getMountedYOffset() {
		return this.height;
	}
}
