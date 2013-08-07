package tektor.minecraft.chalith.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.chalith.container.DryStandContainer;
import tektor.minecraft.chalith.container.OilPressContainer;
import tektor.minecraft.chalith.entity.DryStand;
import tektor.minecraft.chalith.entity.OilPress;

public class OilPressGui extends GuiContainer{
	
	OilPress oil;

	public OilPressGui (InventoryPlayer inventoryPlayer,
            OilPress e) {
    super(new OilPressContainer(inventoryPlayer, e));
    oil = e;
}

@Override
protected void drawGuiContainerForegroundLayer(int param1, int param2) {
    //draw text and stuff here
    //the parameters for drawString are: string, x, y, color
    fontRenderer.drawString("Oil Press", 8, 6, 9919952);
    //draws "Inventory" or your regional equivalent
    fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 9919952);
    if(oil.container.ent.isLocked)
    {
    	fontRenderer.drawString("A Voice tells ", 101, 12, 9919952);
    	fontRenderer.drawString("you, that this ", 101, 27, 9919952);
    	fontRenderer.drawString("oil press is  ", 101, 42, 9919952);
    	fontRenderer.drawString("working at the ", 101, 57, 9919952);
    	fontRenderer.drawString("moment. ", 101, 72, 9919952);
    }
}

@Override
protected void drawGuiContainerBackgroundLayer(float par1, int par2,
            int par3) {
    //draw your Gui here, only thing you need to change is the path
	 this.mc.renderEngine.func_110577_a(new ResourceLocation("chalith","textures/gui/oilPressGUI.png"));
     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    int x = (width - xSize) / 2;
    int y = (height - ySize) / 2;
    this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
}

}
