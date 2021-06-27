package me.rhys.client.module.render.esp.modes;

import me.rhys.base.event.data.EventTarget;
import me.rhys.base.event.impl.render.RenderEntityEvent;
import me.rhys.base.module.ModuleMode;
import me.rhys.base.module.setting.manifest.Name;
import me.rhys.base.util.render.RenderUtil;
import me.rhys.client.module.render.esp.Esp;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

public class Box extends ModuleMode<Esp> {
    
    public Box(String name, Esp parent) {
        super(name, parent);
    }

    @Name("raytrace")
    public boolean raytrace = true;
    
    @EventTarget
    public void onRender(RenderEntityEvent event) {
        if (!(event.getEntity() instanceof EntityPlayer)
                || !RenderUtil.isInFrustumView(event.getEntity()))
            return;

        if (raytrace && mc.thePlayer.canEntityBeSeen(event.getEntity())) {
            return;
        }

        if (event.getEntity().isInvisible()) {
            return;
        }

        AxisAlignedBB boundingBox = new AxisAlignedBB(event.getEntity().boundingBox.minX -
                event.getEntity().posX + event.getX(), event.getEntity().boundingBox.minY -
                event.getEntity().posY + event.getY(), event.getEntity().boundingBox.minZ -
                event.getEntity().posZ + event.getZ(), event.getEntity().boundingBox.maxX -
                event.getEntity().posX + event.getX(), event.getEntity().boundingBox.maxY -
                event.getEntity().posY + event.getY(), event.getEntity().boundingBox.maxZ -
                event.getEntity().posZ + event.getZ());

        RenderUtil.drawBoundingBox(boundingBox, parent.getColor());
    }
}
