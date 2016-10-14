package subaraki.beastmaster.mod.event;

import java.util.List;

import lib.playerclass.PlayerClass;
import lib.util.Targetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowParent;
import net.minecraft.entity.ai.EntityAITasks.EntityAITaskEntry;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import scala.reflect.api.Quasiquotes.Quasiquote.api;
import subaraki.beastmaster.item.BeastMasterItems;
import subaraki.beastmaster.mod.AddonBeastMaster;

public class EventBeastmasterAttraction {

	public EventBeastmasterAttraction() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public void onLivingUpdate(LivingUpdateEvent event){

		if(event.getEntityLiving() instanceof EntityPlayer){
			if(event.getEntityLiving().ticksExisted % 20 == 0){
				EntityPlayer player = (EntityPlayer) event.getEntityLiving();

				if(!player.isSneaking())
					return;
				if(!PlayerClass.isInstanceOf(BeastMasterItems.BEASTMASTER_CLASS))
					return;

				RayTraceResult mouseOver = Targetting.rayTraceServerSide(player, 1);
				if(mouseOver != null){
					if(mouseOver.entityHit != null){
						Entity e = mouseOver.entityHit;
						if(e instanceof EntityAnimal){
							EntityAnimal ea = (EntityAnimal)e;

							if(ea instanceof EntityTameable && player.getUniqueID().equals(((EntityTameable)ea).getOwnerId()))
								return;
							if(ea.getAttackingEntity() != null)
								return;

							ea.worldObj.spawnParticle(EnumParticleTypes.HEART, ea.posX, ea.posY+1, ea.posZ, 0, 0, 0, new int[0]);
							ea.getNavigator().tryMoveToEntityLiving(player, 1.0);
							ea.getLookHelper().setLookPositionWithEntity(ea, (float)(ea.getHorizontalFaceSpeed() + 20), (float)ea.getVerticalFaceSpeed());

							double distance = ea.getPosition().getDistance((int)player.posX, (int)player.posY, (int)player.posZ);

							if(distance <= 1.5f){
								if(ea.worldObj.rand.nextInt(25) == 0 && !ea.isChild()){
									EntityItem ei = new EntityItem(ea.worldObj, ea.posX, ea.posY, ea.posZ, new ItemStack(ea.worldObj.rand.nextInt(3)==0 ? BeastMasterItems.claw : BeastMasterItems.fur));
									//should trigger panic ai
									ea.setRevengeTarget(player);
									if(!ea.worldObj.isRemote)
										ea.worldObj.spawnEntityInWorld(ei);
									player.worldObj.playSound(player, player.getPosition(), SoundEvents.ENTITY_CHICKEN_EGG, SoundCategory.NEUTRAL, 1, 1);
								}

								int chance = ea.worldObj.rand.nextInt(3);
								if(!ea.worldObj.isRemote)
									System.out.println(chance);
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
										///TODO transform into crystal
										if(ea.worldObj.rand.nextInt(3)==0)
											System.out.println("poof, crystal !!");
										else{
											System.out.println("ouch, a headbut");
											ea.setRevengeTarget(player);	
											ea.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_CRIT, 1, 1);
										}
									}else{
										System.out.println("parent nearby");
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
