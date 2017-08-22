package subaraki.beastmaster.sounds;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import subaraki.beastmaster.mod.AddonBeastMaster;

public class BeastMasterSounds {

	public static SoundEvent whistle;
	
	public static void registerSounds(){
		ResourceLocation whistle_sound = new ResourceLocation(AddonBeastMaster.MODID,"whistle_sound");
		ForgeRegistries.SOUND_EVENTS.register(new SoundEvent(whistle_sound).setRegistryName(whistle_sound));
		whistle = SoundEvent.REGISTRY.getObject(whistle_sound);
	}
}
