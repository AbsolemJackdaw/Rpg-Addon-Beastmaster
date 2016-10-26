package subaraki.beastmaster.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import subaraki.beastmaster.handler.proxy.ClientProxy;
import subaraki.beastmaster.mod.AddonBeastMaster;

public class EntityPetRooster extends EntityBeastmasterPet{

	private static final ResourceLocation normal = new ResourceLocation("beastmaster:textures/pet/rooster.png");
	private static final ResourceLocation saddled = new ResourceLocation("beastmaster:textures/pet/rooster_saddled.png");
	private static final ResourceLocation chickenTexture = new ResourceLocation("textures/entity/chicken.png");


	public EntityPetRooster(World worldIn) {
		super(worldIn);
	}

	public EntityPetRooster(World world, ItemStack crystal) {
		super(world, crystal);
	}

	@Override
	public float getBaseHeight() {
		return -0.2F;
	}

	@Override
	public float getBaseWidth() {
		return -0.2F;
	}

	@Override
	public float getAttackDamage() {
		return (2f + MathHelper.floor_float(((float)getLevel() * 1.0F) / 14.25F));

	}

	@Override
	public float getPetSize() {
		return getLevel() <= 200 ? 0.5f + ((getLevel() / 200.0F)*2F) : 2.5f;
	}

	@Override
	public ResourceLocation getPetTextures() {
		return getLevel() < 39 ? chickenTexture : !isSaddled() ? normal: saddled;
	}

	@Override
	public float getHealthIncreaseForLeveling() {
		return 15F + (MathHelper.floor_float((float)getLevel() * 1.0F) / 2.35F);
	}

	@Override
	public float getSpeedIncreaseForLeveling() {
		return 0.3F + (float)getLevel() / 250F;
	}

	@Override
	public int getRegenDelay() {
		return 30;
	}

	@Override
	public ModelBase getModel() {
		return getLevel() < 10 ? AddonBeastMaster.proxy.getPetModel(ClientProxy.PET_CHICKEN_CHILD) : getLevel() < 39 ? AddonBeastMaster.proxy.getPetModel(ClientProxy.PET_CHICKEN) : AddonBeastMaster.proxy.getPetModel(ClientProxy.PET_CHICKEN_BIG);
	}

	//////////////

	public float wingRotation;
	public float destPos;
	public float oFlapSpeed;
	public float oFlap;
	public float wingRotDelta = 1.0F;

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();
		
		this.oFlap = this.wingRotation;
		this.oFlapSpeed = this.destPos;
		this.destPos = (float)((double)this.destPos + (double)(this.onGround ? -1 : 4) * 0.3D);
		this.destPos = MathHelper.clamp_float(this.destPos, 0.0F, 1.0F);

		if (!this.onGround && this.wingRotDelta < 1.0F)
		{
			this.wingRotDelta = 1.0F;
		}

		this.wingRotDelta = (float)((double)this.wingRotDelta * 0.9D);

		if (!this.onGround && this.motionY < 0.0D)
		{
			this.motionY *= 0.6D;
		}

		this.wingRotation += this.wingRotDelta * 2.0F;
		
	}
	
	@Override
	public void fall(float distance, float damageMultiplier) {
	//keep empty to ignore fall damage
	}
	
	@Override
	public double getMountedYOffset() {
		return this.height - 0.2;
	}

}
