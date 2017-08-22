package subaraki.beastmaster.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import subaraki.beastmaster.handler.proxy.ClientProxy;
import subaraki.beastmaster.mod.AddonBeastMaster;

public class EntityPetSpider extends EntityBeastmasterPet {

	private static final ResourceLocation spiderTexture = new ResourceLocation("textures/entity/spider/cave_spider.png");
	private static final ResourceLocation normal = new ResourceLocation("beastmaster:textures/pet/spider.png");
	private static final ResourceLocation saddled = new ResourceLocation("beastmaster:textures/pet/spider_saddled.png");

	public EntityPetSpider(World world) {
		super(world);
	}

	public EntityPetSpider(World world, ItemStack crystal) {
		super(world, crystal);

	}

	@Override
	public float getBaseHeight() {
		return 0.45f;
	}

	@Override
	public float getBaseWidth() {
		return 0.8f;
	}

	@Override
	public float getAttackDamage() {
		// 6 Base Damage
		// 25 Damage at level 200
		return (6 + MathHelper.floor(((getLevel()) * 1.0D) / (10.5263D)));

	}

	@Override
	public float getPetSize() {
		return getLevel() <= 200 ? 0.5f + ((getLevel() / 200.0F)*1.5F) : 2.0f;
	}

	@Override
	public ResourceLocation getPetTextures() {
		return getLevel() < 39 ? spiderTexture : !isSaddled() ? normal: saddled;
	}

	@Override
	public float getHealthIncreaseForLeveling() {
		return 25F + MathHelper.floor(((getLevel()) * 1.0F) / 1.6F);
	}

	@Override
	public float getSpeedIncreaseForLeveling() {
		return 0.2F + (getLevel() / 400F);
	}

	@Override
	public ModelBase getModel() {
		return getLevel() < 10 ? AddonBeastMaster.proxy.getPetModel(ClientProxy.PET_SPIDER_CHILD) : getLevel() < 39 ? AddonBeastMaster.proxy.getPetModel(ClientProxy.PET_SPIDER) : AddonBeastMaster.proxy.getPetModel(ClientProxy.PET_SPIDER_BIG);
	}

	@Override
	public int getRegenDelay() {
		return 80;
	}

	///////////////////

	@Override
	public double getMountedYOffset() {
		return this.height - 0.5d /*player height /2*/;
	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if(entityIn instanceof EntityLivingBase)
			((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, 200, getLevel() < 50 ? 1 : 2));
		return entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), getAttackDamage());
	}
}
