package com.nekokittygames.thaumictinkerer.common.blocks;

import com.nekokittygames.thaumictinkerer.common.blocks.transvector.BlockTransvectorInterface;
import com.nekokittygames.thaumictinkerer.common.libs.LibBlockNames;
import com.nekokittygames.thaumictinkerer.common.tileentity.TileEntityCamoflage;
import com.nekokittygames.thaumictinkerer.common.tileentity.TileEntityDissimulation;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockDissimulation extends TTCamoBlock<TileEntityDissimulation> {
    public BlockDissimulation() {
        super(LibBlockNames.DISSIMULATION, Material.WOOD, true);
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityDissimulation();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (TTCamoBlock.camoflageFromHand(playerIn, hand, te)) return true;
        return false;

    }
}
