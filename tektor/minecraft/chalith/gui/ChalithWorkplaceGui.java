package tektor.minecraft.chalith.gui;

import org.lwjgl.opengl.GL11;

import tektor.minecraft.chalith.container.ChalithWorkplaceContainer;
import tektor.minecraft.chalith.entity.ChalithWorkplaceTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class ChalithWorkplaceGui extends GuiContainer{

	 public ChalithWorkplaceGui (InventoryPlayer inventoryPlayer,
             ChalithWorkplaceTileEntity tileEntity) {
     super(new ChalithWorkplaceContainer(inventoryPlayer, tileEntity));
}

@Override
protected void drawGuiContainerForegroundLayer(int param1, int param2) {
     //draw text and stuff here
     //the parameters for drawString are: string, x, y, color
     fontRenderer.drawString("Rune Workbench", 8, 6, 4210752);
     //draws "Inventory" or your regional equivalent
     fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
}

@Override
protected void drawGuiContainerBackgroundLayer(float par1, int par2,
             int par3) {
     //draw your Gui here, only thing you need to change is the path
     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
     this.mc.renderEngine.func_110577_a(new ResourceLocation("chalith","textures/gui/runeWorkbenchGUI.png"));
     int x = (width - xSize) / 2;
     int y = (height - ySize) / 2;
     this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
}

}
