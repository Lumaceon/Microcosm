package mods.microcosm.item;

import mods.microcosm.util.Colors;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class ItemExperimentalIngot extends ItemMod
{
    public ItemExperimentalIngot(int maxStack, int maxDamage, String name) {
        super(maxStack, maxDamage, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        IExIngotInfoHandler cap = stack.getCapability(CapabilityExIngotHandler.CAPABILITY, EnumFacing.DOWN);
        if(cap != null)
        {
            if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
            {
                String[] componentNames = cap.getComponentNames();
                if(componentNames != null)
                    Collections.addAll(tooltip, componentNames);
            }
            else
            {
                String[] lines = cap.getLines();
                if(lines != null)
                    Collections.addAll(tooltip, lines);
                tooltip.add("");
                tooltip.add(Colors.LIGHT_PURPLE + "-Shift for component ingots-");
            }
        }
    }

    @Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new ExIngotCapabilityProvider();
    }

    public class ExIngotCapabilityProvider implements ICapabilitySerializable<NBTTagCompound>
    {
        ExIngotInfoHandler implementation = new ExIngotInfoHandler();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return CapabilityExIngotHandler.CAPABILITY != null && CapabilityExIngotHandler.CAPABILITY == capability;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            if(hasCapability(capability, facing))
                return CapabilityExIngotHandler.CAPABILITY.cast(implementation);
            return null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return implementation.serializeNBT();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            implementation.deserializeNBT(nbt);
        }
    }

    public interface IExIngotInfoHandler
    {
        public void setComponentName(int index, String componentName);
        public String getComponentName(int index);
        public String[] getComponentNames();

        public void setLines(String[] lines);
        public String[] getLines();

        public NBTTagCompound serializeNBT();
        public void deserializeNBT(NBTTagCompound nbt);
    }

    public static class ExIngotInfoHandler implements IExIngotInfoHandler
    {
        String[] lines = new String[6];
        String[] componentItemNames = new String[6];

        @Override
        public void setComponentName(int index, String componentName) {
            componentItemNames[index] = componentName;
        }

        @Override
        public String getComponentName(int index) {
            return componentItemNames[index];
        }

        @Override
        public String[] getComponentNames() {
            return componentItemNames;
        }

        @Override
        public void setLines(String[] lines) {
            this.lines = lines;
        }

        @Override
        public String[] getLines() {
            return lines;
        }

        @Override
        public NBTTagCompound serializeNBT()
        {
            NBTTagCompound nbt = new NBTTagCompound();
            NBTTagList list = new NBTTagList();
            if(lines != null)
                for(String s : lines)
                    if(s != null && s.length() > 0)
                        list.appendTag(new NBTTagString(s));
            nbt.setTag("string_list", list);

            list = new NBTTagList();
            if(lines != null)
                for(String s : componentItemNames)
                    if(s != null && s.length() > 0)
                        list.appendTag(new NBTTagString(s));
            nbt.setTag("component_names", list);
            return nbt;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt)
        {
            if(nbt.hasKey("string_list"))
            {
                NBTTagList list = nbt.getTagList("string_list", Constants.NBT.TAG_STRING);
                lines = new String[list.tagCount()];
                for(int i = 0; i < list.tagCount(); i++)
                    lines[i] = list.getStringTagAt(i);
            }
            if(nbt.hasKey("component_names"))
            {
                NBTTagList list = nbt.getTagList("component_names", Constants.NBT.TAG_STRING);
                for(int i = 0; i < list.tagCount(); i++)
                    componentItemNames[i] = list.getStringTagAt(i);
            }
        }
    }

    public static class CapabilityExIngotHandler
    {
        @CapabilityInject(IExIngotInfoHandler.class)
        public static Capability<IExIngotInfoHandler> CAPABILITY = null;

        public static void register()
        {
            CapabilityManager.INSTANCE.register(IExIngotInfoHandler.class, new Capability.IStorage<IExIngotInfoHandler>()
            {
                @Override
                public NBTBase writeNBT(Capability<IExIngotInfoHandler> capability, IExIngotInfoHandler instance, EnumFacing side) {
                    return instance.serializeNBT();
                }

                @Override
                public void readNBT(Capability<IExIngotInfoHandler> capability, IExIngotInfoHandler instance, EnumFacing side, NBTBase base) {
                    instance.deserializeNBT((NBTTagCompound) base);
                }
            }, new Callable<ExIngotInfoHandler>()
            {
                @Override
                public ExIngotInfoHandler call() throws Exception {
                    return new ExIngotInfoHandler();
                }
            });
        }

    }
}
