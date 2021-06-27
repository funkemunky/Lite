package me.rhys.client.module.render.esp;

import me.rhys.base.Lite;
import me.rhys.base.event.data.EventTarget;
import me.rhys.base.event.impl.render.RenderChestEvent;
import me.rhys.base.module.Module;
import me.rhys.base.module.data.Category;
import me.rhys.base.module.setting.manifest.Name;
import me.rhys.base.util.render.RenderUtil;
import me.rhys.client.module.render.HUD;
import me.rhys.client.module.render.esp.modes.Box;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public class Esp extends Module {

    public Esp(String name, String description, Category category, int keyCode) {
        super(name, description, category, keyCode);

        add(new Box("Box", this));
    }

    @Name("chests")
    public boolean renderChests = true;

    @EventTarget
    public void onRenderChest(RenderChestEvent event) {
        if(renderChests) {
            Block block = mc.theWorld.getBlockState(event.getTileEntity().getPos()).getBlock();

            if (!(((block instanceof BlockChest) || (block instanceof BlockEnderChest))))
                return;

            HUD hud = (HUD) Lite.MODULE_FACTORY.findByClass(HUD.class);

            GlStateManager.pushMatrix();
            GlStateManager.disableLighting();
            GlStateManager.depthMask(false);
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            float[] colors = RenderUtil.getColors((hud.rainbow
                    ? Color.getHSBColor(((Minecraft.getSystemTime()
                            + (10 * Minecraft.getMinecraft().thePlayer.ticksExisted)) % 5000F) / 5000F,
                    (float) 1, (float) 0.20).getRGB() : new Color(52, 189, 235).getRGB()));
            RenderUtil.setColor(colors[0], colors[1], colors[2], 0.15f);
            RenderUtil.drawFilledBox(
                    new AxisAlignedBB(event.getX() + 0.0595F, event.getY(), event.getZ() + 0.0595F,
                            event.getX() + 0.9475F, event.getY() + 0.895F, event.getZ() + 0.9475F)
                            .expand(0.02, 0.02, 0.02));
            RenderUtil.setColor(1, 1, 1, 1f);
            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
            GlStateManager.depthMask(true);
            GlStateManager.popMatrix();
        }
    }

    public int getColor() {
        if (Minecraft.getMinecraft().thePlayer == null) return 0;
        HUD hud = (HUD) Lite.MODULE_FACTORY.findByClass(HUD.class);
        return (hud.rainbow
                ? Color.getHSBColor(((Minecraft.getSystemTime()
                        + (10 * Minecraft.getMinecraft().thePlayer.ticksExisted)) % 5000F) / 5000F,
                (float) 1, (float) 0.20).getRGB() : new Color(52, 189, 235).getRed());
    }
}
