package subaraki.beastmaster.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLLog;

public class ConfigurationHandler
{
	public static ConfigurationHandler instance = new ConfigurationHandler();

	public int claw_uses;
	public int claw_damage;

	public String beastmaster_armor[];

	public void loadConfig(File file)
	{
		Configuration config = new Configuration(file);
		config.load();
		loadSettings(config);
		config.save();
	}

	private void loadSettings(Configuration config)
	{
		config.addCustomCategoryComment("Weapon Uses", "define the number of maximum uses for the weapons - min 100, max : " + Integer.MAX_VALUE);
		config.addCustomCategoryComment("Damage", "define the ammount of damage dealt by mentioned object");
		config.addCustomCategoryComment("Armor", "define the armor's uses, resistance, enchantabilty and toughness");

		claw_uses = config.getInt("claw uses", "Weapon Uses", 180, 100, Integer.MAX_VALUE, "");
		claw_damage = config.getInt("claw damage", "Damage", 8, 0, Integer.MAX_VALUE, "");

		beastmaster_armor = config.getStringList("beastmaster armor stats", "Armor", new String[]{"23", "2", "3", "2", "1", "0", "2.0"}, "uses, armor reduction(head, chest, legs, feet), toughness");
	}
}