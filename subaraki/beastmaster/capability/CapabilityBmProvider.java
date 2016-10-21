package subaraki.beastmaster.capability;

import javax.annotation.Nullable;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import subaraki.beastmaster.mod.AddonBeastMaster;

public class CapabilityBmProvider implements ICapabilitySerializable<NBTTagCompound>
{
    /**
     * Unique key to identify the attached provider from others
     */
    public static final ResourceLocation KEY = new ResourceLocation(AddonBeastMaster.MODID, "beastmaster_data");

    /**
     * The instance that we are providing
     */
    final BmData data = new BmData();

    /**gets called before world is initiated. player.worldObj will return null here !*/
    public CapabilityBmProvider(EntityPlayer player){
        data.setPlayer(player);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        if (capability == BmCapability.CAPABILITY)
            return true;
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing){
        if (capability == BmCapability.CAPABILITY)
            return (T)data;
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT(){
        return (NBTTagCompound) BmCapability.CAPABILITY.writeNBT(data, null);
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt){
    	BmCapability.CAPABILITY.readNBT(data, null, nbt);
    }
}
