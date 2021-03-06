package com.nekokittygames.thaumictinkerer.common.tileentity;

import com.google.common.base.Predicate;
import com.nekokittygames.thaumictinkerer.ThaumicTinkerer;
import com.nekokittygames.thaumictinkerer.common.blocks.BlockMagnet;
import com.nekokittygames.thaumictinkerer.common.misc.MiscHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.codechicken.lib.vec.Vector3;

import java.util.List;


public class TileEntityMagnet extends TileEntityThaumicTinkerer implements ITickable {

    protected void test()
    {

    }
    protected<T extends Entity> java.util.function.Predicate selectedEntities()
    {
        return  o -> o instanceof EntityItem;
    }

    @Override
    public void update() {
        int redstone=0;
        for(EnumFacing dir:EnumFacing.VALUES)
        {
            redstone=Math.max(redstone,world.getRedstonePower(pos,dir));
        }
        if(redstone>0)
        {
            double x1=pos.getX()+0.5;
            double y1=pos.getY()+0.5;
            double z1=pos.getZ()+0.5;
            BlockMagnet.MagnetPull mode =world.getBlockState(pos).getValue(BlockMagnet.POLE);
            int speedMod = mode== BlockMagnet.MagnetPull.PULL ? 1 : -1;
            double range = redstone >> 1;
            List<Entity> entities=world.<Entity>getEntitiesWithinAABB(Entity.class,new AxisAlignedBB(x1-range,pos.getY(),z1-range,x1+range,y1+range,z1+range), selectedEntities()::test);
            for (Entity entity : entities) {
                if(!filterEntity(entity))
                    return;
                double x2=entity.posX;
                double y2=entity.posY;
                double z2=entity.posZ;
                float distanceSqrd = mode== BlockMagnet.MagnetPull.PULL ? (float) ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) + (z1 - z2) * (z1 - z2)) : 1.1F;
                if (distanceSqrd > 1) {

                    MiscHelper.setEntityMotionFromVector(entity, new Vector3(x1, y1, z1), speedMod * 0.25F);
                    FXDispatcher.INSTANCE.sparkle((float)x2,(float)y2,(float)z2,mode== BlockMagnet.MagnetPull.PULL?0:1,0,mode== BlockMagnet.MagnetPull.PULL?1:0);

                }

            }
        }
    }

    private boolean filterEntity(Entity entity) {
        if(entity.getEntityData().hasKey("PreventRemoteMovement"))
            return !entity.getEntityData().getBoolean("PreventRemoteMovement");
        return true;
    }
}
