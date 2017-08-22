package subaraki.beastmaster.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import subaraki.beastmaster.mod.AddonBeastMaster;

public abstract class EntityBeastmasterPet extends EntityTameable{

	public static final String NBT_SADDLED = "saddled";
	public static final String NBT_LEVEL = "level";
	public static final String NBT_TOTALEXP = "totalexp";
	public static final String NBT_EXPERIENCE = "experience";
	public static final String NBT_STACKDATA_LIVING = "petFromStack";
	public static final String NBT_STACKDATA_ENTITY = "entityFromStack";

	private static final DataParameter<Boolean> SADDLED = EntityDataManager.<Boolean>createKey(EntityBeastmasterPet.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> LEVEL = EntityDataManager.<Integer>createKey(EntityBeastmasterPet.class, DataSerializers.VARINT);
	private static final DataParameter<Integer> TOTALEXP = EntityDataManager.<Integer>createKey(EntityBeastmasterPet.class, DataSerializers.VARINT);
	private static final DataParameter<Float> EXPERIENCE = EntityDataManager.<Float>createKey(EntityBeastmasterPet.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> WIDTH = EntityDataManager.<Float>createKey(EntityBeastmasterPet.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> HEIGHT = EntityDataManager.<Float>createKey(EntityBeastmasterPet.class, DataSerializers.FLOAT);

	private int regenerateHealthTimer = getRegenDelay();

	public EntityBeastmasterPet(World worldIn) {
		super(worldIn);

		//trigger size saving and setting
		this.addExperienceLevel(0);
	}

	public EntityBeastmasterPet(World world, ItemStack crystal) {
		this(world);

		if(crystal != ItemStack.EMPTY){
			//important data related to livingbase
			if(crystal.hasTagCompound() && crystal.getTagCompound().hasKey(NBT_STACKDATA_LIVING)){
				readEntityFromNBT((NBTTagCompound) crystal.getTagCompound().getTag(NBT_STACKDATA_LIVING));
			}
			//extra data for name tag etc
			if(crystal.hasTagCompound() && crystal.getTagCompound().hasKey(NBT_STACKDATA_ENTITY)){
				readFromNBT((NBTTagCompound) crystal.getTagCompound().getTag(NBT_STACKDATA_ENTITY));
			}
			setSize(getScaledWidth(), getScaledHeight());
			this.width = getScaledWidth();
			this.height = getScaledHeight();
		}
		//trigger size saving and setting
		this.addExperienceLevel(0);
	}

	@Override
	protected void initEntityAI() {

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
		this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
		this.tasks.addTask(6, new EntityAIMate(this, 1.0D));
		this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
		this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(9, new EntityAILookIdle(this));

		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
		this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
		this.targetTasks.addTask(5, new EntityAINearestAttackableTarget(this, EntityMob.class, false));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getSpeedIncreaseForLeveling());
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getHealthIncreaseForLeveling());
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(SADDLED, Boolean.valueOf(false));
		this.dataManager.register(LEVEL, Integer.valueOf(0));
		this.dataManager.register(TOTALEXP, Integer.valueOf(0));
		this.dataManager.register(EXPERIENCE, Float.valueOf(0));
		this.dataManager.register(HEIGHT, Float.valueOf(getBaseHeight()));
		this.dataManager.register(WIDTH, Float.valueOf(getBaseWidth()));

	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	public void onDeath(DamageSource cause) {
		if(getOwner() instanceof EntityPlayer){
			SummonStowPetLogic.stowPet((EntityPlayer) getOwner());
		}
		super.onDeath(cause);
	}
	
	@Override
	public void setDead() {
		super.setDead();
		
		if(getOwner() instanceof EntityPlayer){
			SummonStowPetLogic.stowPet((EntityPlayer) getOwner());
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		regenerateHealthTimer--;

		//when level has changed or entity has re-spawned
		if(this.width != getScaledWidth() && this.height != getScaledHeight()){
			setSize(getScaledWidth(), getScaledHeight()); //entity bb ?
			this.width = getScaledWidth(); //visible bounding box ?
			this.height = getScaledHeight();

			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getHealthIncreaseForLeveling());
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(getSpeedIncreaseForLeveling());
		}

		if(regenerateHealthTimer <= 0){
			heal(1);
			regenerateHealthTimer = getRegenDelay();
		}

		if(this.getOwner() == null){
			this.setDead();
		}
	}

	@Override
	public boolean canAttackClass(Class<? extends EntityLivingBase> cls) {
		return true;
	}

	@Override
	public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase player) {
		return target instanceof EntityPlayer && player instanceof EntityPlayer && !((EntityPlayer)player).canAttackPlayer((EntityPlayer)target) ? false : target instanceof EntityTameable && player.getUniqueID().equals(((EntityTameable)target).getOwnerId()) ? false : !(target instanceof EntityHorse) || !((EntityHorse)target).isTame();
	}

	@Override
	public boolean attackEntityAsMob(Entity victim) {
		return victim.attackEntityFrom(DamageSource.causeMobDamage(this), getAttackDamage());
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return super.attackEntityFrom(source, amount);
	}

	////////////////////////////////////////////////////////////////////////////////
	///////////////////ABSTRACTS FOR CHILD CLASSES//////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	public abstract float getBaseHeight();
	public abstract float getBaseWidth();
	public abstract float getAttackDamage();
	public abstract float getPetSize();
	public abstract ResourceLocation getPetTextures();
	public abstract float getHealthIncreaseForLeveling();
	public abstract float getSpeedIncreaseForLeveling();
	public abstract int getRegenDelay();

	@SideOnly(Side.CLIENT)
	public abstract ModelBase getModel();

	protected ModelBase getPetModel(String identifier){
		return AddonBeastMaster.proxy.getPetModel(identifier);
	};


	////////////////////////////////////////////////////////////////////////////////
	///////////////////INTERACTION////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	@Override
	public boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		
		if(getOwner() instanceof EntityPlayer && ((EntityPlayer)getOwner()).equals(player)){
			if(stack != ItemStack.EMPTY ){
				if(stack.getItem().equals(Items.GOLDEN_CARROT)){
					this.addExperience(9 + rand.nextInt(4));
					stack.shrink(1);
					return true;
				}
				if(stack.getItem() instanceof ItemFood){
					if(getHealth() < getMaxHealth()){
						heal(1);
						stack.shrink(1);
						world.spawnParticle(EnumParticleTypes.HEART, posX,posY+0.5,posZ,0,0,0,new int[0]);
					}
					return true;
				}
				if(!isSaddled() && stack.getItem().equals(Items.SADDLE) && getLevel() >= 50){
					setSaddled();
					stack.shrink(1);
					return true;
				}

				if(stack.getItem().equals(Items.NAME_TAG)){
					SummonStowPetLogic.savePet(this, (EntityPlayer) getOwner());
					return false;
				}
			}
			if(this.isSaddled() && !isBeingRidden() && player.getUniqueID().equals(getOwnerId())){
				mountTo(player);
				return true;
			}
		}
		return false;
	}


	////////////////////////////////////////////////////////////////////////////////
	///////////////////SAVING ENTITY////////////////////////////////////////////////
	////////////////SAVING ENTITY TO ITEM///////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setBoolean(NBT_SADDLED, isSaddled());
		nbt.setInteger(NBT_LEVEL, this.dataManager.get(LEVEL));
		nbt.setInteger(NBT_TOTALEXP, this.dataManager.get(TOTALEXP));
		nbt.setFloat(NBT_EXPERIENCE, this.dataManager.get(EXPERIENCE));
		nbt.setFloat("width", this.dataManager.get(WIDTH));
		nbt.setFloat("height", this.dataManager.get(HEIGHT));

	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		this.dataManager.set(SADDLED, nbt.getBoolean(NBT_SADDLED));
		this.dataManager.set(LEVEL, nbt.getInteger(NBT_LEVEL));
		this.dataManager.set(TOTALEXP, nbt.getInteger(NBT_TOTALEXP));
		this.dataManager.set(EXPERIENCE, nbt.getFloat(NBT_EXPERIENCE));
		this.dataManager.set(WIDTH, nbt.getFloat("width"));
		this.dataManager.set(HEIGHT, nbt.getFloat("height"));

	}

	@Override
	public void setCustomNameTag(String name) {
		super.setCustomNameTag(name);
	}


	////////////////////////////////////////////////////////////////////////////////
	///////////////////RIDING DATA////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

	@Override
	public Entity getControllingPassenger() {
		return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
	}

	@Override
	public double getMountedYOffset() {
		return this.height - 0.5d /*player height /2*/;
	}

	@Override
	public boolean canBeSteered() {
		if(getLevel() >= 50)
			return true;
		return super.canBeSteered();
	}

	private void mountTo(EntityPlayer player) {
		player.rotationYaw = this.rotationYaw;
		player.rotationPitch = this.rotationPitch;

		if (!this.world.isRemote){
			player.startRiding(this);
		}
	}

	@Override
	public void moveRelative(float strafe, float up, float forward, float friction) {
		if (this.isBeingRidden() && this.canBeSteered() && this.isSaddled())
		{
			EntityLivingBase entitylivingbase = (EntityLivingBase)this.getControllingPassenger();
			this.rotationYaw = entitylivingbase.rotationYaw;
			this.prevRotationYaw = this.rotationYaw;
			this.rotationPitch = entitylivingbase.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.renderYawOffset = this.rotationYaw;
			this.rotationYawHead = this.renderYawOffset;
			strafe = entitylivingbase.moveStrafing * 0.5F;
			forward = entitylivingbase.moveForward;

			this.stepHeight = 1.0F;
			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

			if (forward <= 0.0F)
			{
				forward *= 0.25F;

			}

			if (this.canPassengerSteer())
			{
				this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
				super.moveRelative(strafe, up, forward, friction);
			}
			else if (entitylivingbase instanceof EntityPlayer)
			{
				this.motionX = 0.0D;
				this.motionY = 0.0D;
				this.motionZ = 0.0D;
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.posX - this.prevPosX;
			double d0 = this.posZ - this.prevPosZ;
			float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

			if (f2 > 1.0F)
			{
				f2 = 1.0F;
			}

			this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		}
		else
		{
			this.jumpMovementFactor = 0.02F;
			super.moveRelative(strafe, up, forward, friction);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////DATA/////////////////////////////////////////////////////////
	///////////////////////////MANAGER///////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////

	public int getLevel(){
		int level = this.dataManager.get(LEVEL);
		return level > 200 ? 200 : level;
	}

	public int getTotalExperience(){
		return this.dataManager.get(TOTALEXP);
	}

	public void addExperience(int amount){
		if(getLevel() >= 200)
			return;

		int totalExp = this.dataManager.get(TOTALEXP);
		float currentExp = (float)this.dataManager.get(EXPERIENCE);

		int crashCap = Integer.MAX_VALUE - totalExp;
		if (amount > crashCap)
			amount = crashCap;

		currentExp += (float)amount / (float)this.capForLevel();

		for (totalExp += amount; currentExp >= 1.0F; currentExp /= (float)this.capForLevel())
		{
			currentExp = (currentExp - 1.0F) * (float)capForLevel();
			this.addExperienceLevel(1);
		}

		this.dataManager.set(EXPERIENCE, currentExp);
		this.dataManager.set(TOTALEXP, totalExp);

		if(getOwner() instanceof EntityPlayer)
			SummonStowPetLogic.savePet(this, (EntityPlayer) getOwner());

	}

	public void addExperienceLevel(int levels)
	{
		int level = getLevel()+levels > 200 ? 200 : getLevel()+levels;

		this.dataManager.set(LEVEL, level);

		float scale = getPetSize();
		float width = getBaseWidth() + scale;
		float height = getBaseHeight() + scale;

		this.dataManager.set(WIDTH, width);
		this.dataManager.set(HEIGHT, height);

		if (getLevel() < 0)
		{
			this.dataManager.set(LEVEL,0);
			this.dataManager.set(EXPERIENCE,0f);
			this.dataManager.set(TOTALEXP,0);
		}
	}

	private int capForLevel(){

		return getLevel() >= 30 ? 112 + (getLevel() - 30) * 9 : (this.getLevel() >= 15 ? 37 + (this.getLevel() - 15) * 5 : 7 + this.getLevel() * 2);

	}

	public void setSaddled(){
		this.dataManager.set(SADDLED, true);
		if(getOwner() instanceof EntityPlayer)
			SummonStowPetLogic.savePet(this, (EntityPlayer) getOwner());
	}

	public boolean isSaddled(){
		return this.dataManager.get(SADDLED);
	}

	public float getScaledWidth(){
		return this.dataManager.get(WIDTH);
	}

	public float getScaledHeight(){
		return this.dataManager.get(HEIGHT);
	}

	@Override
	public boolean isChild() {
		return getLevel() < 10;
	}
}
