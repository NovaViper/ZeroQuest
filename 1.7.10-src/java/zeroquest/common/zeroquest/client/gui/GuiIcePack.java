package common.zeroquest.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import common.zeroquest.ZeroQuest;
import common.zeroquest.entity.EntityIceZertum;
import common.zeroquest.inventory.ContainerIcePack;

public class GuiIcePack extends GuiContainer
{
    private float xSize_Wolf;
    private float ySize_Wolf;
    private EntityIceZertum wolf;
    private boolean mouseWasDown;
    
    private static final ResourceLocation gui = new ResourceLocation(ZeroQuest.modid + ":" + "textures/gui/petInventoryIce.png");


    public GuiIcePack(InventoryPlayer inventoryplayer, EntityIceZertum entityIceZertum) {
        super(new ContainerIcePack(inventoryplayer, entityIceZertum));
        this.mouseWasDown = false;
        this.wolf = entityIceZertum;
        this.allowUserInput = false;
        this.ySize = ySize + 60;
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
    	String s = this.wolf.inventory.hasCustomInventoryName() ? this.wolf.inventory.getInventoryName() : StatCollector.translateToLocal(this.wolf.inventory.getInventoryName());
        this.fontRendererObj.drawString(s, this.xSize / 2 - 10, 14, 0x008080);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("Inventory"), 8, this.ySize - 155 + 2, 0x008080);
    }


    @Override
    public void drawScreen(int i, int j, float f) {
        super.drawScreen(i, j, f);
        xSize_Wolf = i;
        ySize_Wolf = j;
    }


    @Override
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(gui);
        int l = (width - xSize) / 2;
        int i1 = (height - ySize) / 2;
        drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);

        
    for (int j1 = 0; j1 < 3; j1++) //Y Axis
    {
        for (int k1 = 0; k1 < 5; k1++) //X Axis
        {
            drawTexturedModalRect(l + 78 + 18 * k1, i1 + 9 + 18 * j1 + 15, 197, 2, 18, 18);
        }
    }


    GuiInventory.func_147046_a(l + 42, i1 + 51, 30, (float)(l + 51) - xSize_Wolf, (float)((i1 + 75) - 50) - ySize_Wolf, this.wolf);
    }
}
