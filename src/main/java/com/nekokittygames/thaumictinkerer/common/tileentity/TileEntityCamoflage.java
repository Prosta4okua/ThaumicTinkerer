package com.nekokittygames.thaumictinkerer.common.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistry;

import javax.annotation.Nullable;

public abstract class TileEntityCamoflage extends TileEntityThaumicTinkerer {

    private IBlockState blockCopy;

    public IBlockState getBlockCopy() {
        return blockCopy;
    }

    public void setBlockCopy(IBlockState blockCopy) {
        this.blockCopy = blockCopy;
    }


  @Override
    public void readExtraNBT(NBTTagCompound compound) {
        if(compound.hasKey("blockName")) {
            Block blk = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(compound.getString("blockName")));
            blockCopy=blk.getStateFromMeta(compound.getInteger("blockMeta"));
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        sendUpdates();
    }

    @Override
    public void writeExtraNBT(NBTTagCompound compound) {
        if(blockCopy!=null) {
            compound.setString("blockName", blockCopy.getBlock().getRegistryName().toString());
            compound.setInteger("blockMeta", blockCopy.getBlock().getMetaFromState(blockCopy));
        }

    }
}
