package me.rhys.base.event.impl.render;

import me.rhys.base.event.Event;
import net.minecraft.tileentity.TileEntity;

public class RenderChestEvent extends Event {

    private TileEntity tileEntity;
    private double x;
    private double y;
    private double z;
    private float partialTicks;
    private int destroyStage;

    public RenderChestEvent(TileEntity tileEntity, double x, double y, double z, float partialTicks, int destroyStage) {
        this.tileEntity = tileEntity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.partialTicks = partialTicks;
        this.destroyStage = destroyStage;
    }

    public TileEntity getTileEntity() {
        return tileEntity;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getPartialTicks() {
        return partialTicks;
    }

    public int getDestroyStage() {
        return destroyStage;
    }

}
