package subaraki.beastmaster.event;

import java.util.List;

import lib.playerclass.PlayerClass;
import lib.util.Targetting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import scala.reflect.api.Quasiquotes.Quasiquote.api;
import subaraki.beastmaster.capability.BmCapability;
import subaraki.beastmaster.capability.BmData;
import subaraki.beastmaster.entity.BeastMasterPet;
import subaraki.beastmaster.item.BeastMasterItems;
import subaraki.beastmaster.mod.AddonBeastMaster;
import subaraki.beastmaster.network.NetworkHandler;
import subaraki.beastmaster.network.PacketSyncPetting;

public class EventBeastmasterAttraction {

	public EventBeastmasterAttraction() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event){

		if(event.getEntityLiving() instanceof EntityPlayer){
			if(event.getEntityLiving().ticksExisted % 20 == 0){
				EntityPlayer player = (EntityPlayer) event.getEntityLiving();

				if(!player.isSneaking()){
					return;
				}

				boolean isHoldingFood = false;
				EnumHand heldFood = EnumHand.MAIN_HAND;

				if(!PlayerClass.isInstanceOf(BeastMasterItems.BEASTMASTER_CLASS)){//if regular player

					if(player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem().equals(BeastMasterItems.lure) || 
							player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem().equals(BeastMasterItems.lure)){		
						isHoldingFood = true;
						heldFood =  player.getHeldItemOffhand()!= null && player.getHeldItemOffhand().getItem().equals(BeastMasterItems.lure) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND;

					}else{
						return;//if the player isn't holding lure, return
					}
				}

				BmData beastmaster = player.getCapability(BmCapability.CAPABILITY, null);

				//TODO re-implement when mode angles are changeable
				//				if(beastmaster.isPetting() && !player.worldObj.isRemote){
				//					beastmaster.setPetting(false);
				//					NetworkHandler.NETWORK.sendTo(new PacketSyncPetting(false), (EntityPlayerMP) player);
				//					//set false before checking anything. if it is set to true after, this will have no consequence
				//				}

				RayTraceResult mouseOver = Targetting.rayTraceServerSide(player, 1);
				if(mouseOver != null){
					if(mouseOver.entityHit != null){
						Entity e = mouseOver.entityHit;

						if(e instanceof EntityCreature){
							EntityCreature ec = (EntityCreature)e;

							if(ec instanceof EntityTameable && player.getUniqueID().equals(((EntityTameable)ec).getOwnerId()))
								return;
							if(ec.getAttackingEntity() != null)
								return;

							if(ec instanceof EntityCreature && (ec instanceof EntityAnimal || ec instanceof EntitySpider))
							{

								ec.worldObj.spawnParticle(EnumParticleTypes.HEART, ec.posX, ec.posY+1, ec.posZ, 0, 0, 0, new int[0]);
								ec.getNavigator().tryMoveToEntityLiving(player, 1.0);
								ec.getLookHelper().setLookPositionWithEntity(ec, (float)(ec.getHorizontalFaceSpeed() + 20), (float)ec.getVerticalFaceSpeed());

								double distance = ec.getPosition().getDistance((int)player.posX, (int)player.posY, (int)player.posZ);

								if(distance - (double)ec.width/2d <= 2d){

									if(ec instanceof EntityAnimal){
										//TODO re-implement when mode angles are changeable
										//										if(!beastmaster.isPetting() && !player.worldObj.isRemote){
										//											beastmaster.setPetting(true);
										//											NetworkHandler.NETWORK.sendTo(new PacketSyncPetting(true), (EntityPlayerMP) player);
										//											//set false before checking anything. if it is set to true after, this will have no consequence
										//										}

										if(isHoldingFood)
											if(player.worldObj.rand.nextInt(14)== 0 && !player.worldObj.isRemote) //check world. random doesnt sync ?
												player.getHeldItem(heldFood).stackSize--;

										
										int chance = ec.worldObj.rand.nextInt(27 - (beastmaster.getAnimalAffinity()/10)); //results in 27 - max255/10 = 2
										if(!ec.worldObj.isRemote){
											System.out.println(chance + " " + (27 - (beastmaster.getAnimalAffinity()/10)) + " " + beastmaster.getAnimalAffinity());
										}

										if(!ec.isChild()){
											if(chance == 0 && !ec.worldObj.isRemote){
												EntityItem ei = new EntityItem(ec.worldObj, ec.posX, ec.posY, ec.posZ, new ItemStack(ec.worldObj.rand.nextInt(3)==0 ? BeastMasterItems.claw : BeastMasterItems.fur));
												//this should trigger panic ai
												ec.setRevengeTarget(player);
												//its better to attack them to prevent infinite harvest
												ec.attackEntityFrom(DamageSource.causePlayerDamage(player), 2);
												ec.worldObj.spawnEntityInWorld(ei);
												player.worldObj.playSound(player, player.getPosition(), SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.NEUTRAL, 1, 1);
												
												if(PlayerClass.isInstanceOf(BeastMasterItems.BEASTMASTER_CLASS))
													beastmaster.addAnimalAffinity(3);
												else
													beastmaster.addAnimalAffinity(1);
											}
										}
									}
									else if(PlayerClass.isInstanceOf(BeastMasterItems.BEASTMASTER_CLASS))
										transformChildToCrystal((EntityAnimal)ec, player);

								}else if(ec instanceof EntitySpider){
									//TODO re-implement when mode angles are changeable
									//										if(!beastmaster.isPetting() && !player.worldObj.isRemote){
									//											beastmaster.setPetting(true);
									//											NetworkHandler.NETWORK.sendTo(new PacketSyncPetting(true), (EntityPlayerMP) player);
									//											//set false before checking anything. if it is set to true after, this will have no consequence
									//										}

									if(PlayerClass.isInstanceOf(BeastMasterItems.BEASTMASTER_CLASS))
										transformAdultToCrystal(ec, player);
								}
							}
						}
					}
				}
			}
		}
	}

	private void transformAdultToCrystal(EntityCreature ec, EntityPlayer player){

		int chance = ec.worldObj.rand.nextInt(2);
		if(chance == 0 && !ec.worldObj.isRemote){
			BmData beastmaster = player.getCapability(BmCapability.CAPABILITY, null);
			int chance2 = beastmaster.getAnimalAffinity() > 100 ? 2 : 4;
			if(ec.worldObj.rand.nextInt(chance2)==0 || beastmaster.getAnimalAffinity() > 200){
				ec.setDead();
				int damage = BeastMasterPet.instance.getDamageFromEntity(ec);
				EntityItem ei = new EntityItem(ec.worldObj, ec.posX, ec.posY, ec.posZ, new ItemStack(BeastMasterItems.crystal,1,damage));
				ec.worldObj.spawnEntityInWorld(ei);
			}else{
				ec.knockBack(ec, 2, -player.getLookVec().xCoord, -player.getLookVec().zCoord);
				ec.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, 1, 1);
			}
		}
	}

	private void transformChildToCrystal(EntityAnimal ea, EntityPlayer player){

		int chance = ea.worldObj.rand.nextInt(2);

		if(ea.isChild() && chance == 0 && !ea.worldObj.isRemote){

			EntityAnimal parent = null;
			List<EntityAnimal> list = ea.worldObj.<EntityAnimal>getEntitiesWithinAABB(ea.getClass(), ea.getEntityBoundingBox().expand(8.5D, 4.5D, 8.5D));//look further then the original ai

			for (EntityAnimal aParent : list){
				if(aParent.getClass().equals(ea.getClass())){ //same animal
					if(!aParent.isChild()){ //adult age
						parent = aParent;
						break;
					}
				}
			}
			if(parent == null){
				BmData beastmaster = player.getCapability(BmCapability.CAPABILITY, null);
				int chance2 = beastmaster.getAnimalAffinity() > 100 ? 2 : 4;
				if(ea.worldObj.rand.nextInt(chance2) == 0 || beastmaster.getAnimalAffinity() > 200){
					ea.setDead();
					int damage = BeastMasterPet.instance.getDamageFromEntity(ea);
					EntityItem ei = new EntityItem(ea.worldObj, ea.posX, ea.posY, ea.posZ, new ItemStack(BeastMasterItems.crystal,1,damage));
					ea.worldObj.spawnEntityInWorld(ei);
				}
				else{
					player.attackEntityFrom(DamageSource.causeMobDamage(ea), 2);
					ea.setRevengeTarget(player);	
					ea.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, 1, 1);
				}
			}
		}
	}
}
