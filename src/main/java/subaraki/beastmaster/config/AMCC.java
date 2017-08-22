package subaraki.beastmaster.config;

import net.minecraft.item.ItemArmor.ArmorMaterial;

//armor material configuration converter
public class AMCC {

	private String[] entries; 

	public AMCC(String[] entries) {
		this.entries = entries;
	}

	public int getDurability(){
		return Integer.valueOf(entries[0]);
	}

	public int[] getReduction(){

		return new int[]{
				Integer.valueOf(entries[1]),
				Integer.valueOf(entries[2]),
				Integer.valueOf(entries[3]),
				Integer.valueOf(entries[4]),
		};
	}

	public float getToughness(){
		return Float.valueOf(entries[6]);
	}
	
	public int getEnchantibility(){
		return Integer.valueOf(entries[5]);
	}
}
