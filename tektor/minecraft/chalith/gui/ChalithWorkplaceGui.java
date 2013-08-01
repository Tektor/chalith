package tektor.minecraft.chalith.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import tektor.minecraft.chalith.container.ChalithWorkplaceContainer;
import tektor.minecraft.chalith.entity.ChalithWorkplaceTileEntity;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class ChalithWorkplaceGui extends GuiContainer implements ICrafting{

	public GuiTextField itemNameField;
	private ChalithWorkplaceContainer repairContainer;
	private InventoryPlayer inventory;
	public ChalithWorkplaceTileEntity te;
	public EntityPlayer player;
	
	 public ChalithWorkplaceGui (EntityPlayer playerEnt, InventoryPlayer inventoryPlayer,
             ChalithWorkplaceTileEntity tileEntity) {
     super(new ChalithWorkplaceContainer(inventoryPlayer, tileEntity));
     repairContainer = (ChalithWorkplaceContainer) this.inventorySlots;
     te = tileEntity;
     player = playerEnt;
}

@Override
protected void drawGuiContainerForegroundLayer(int param1, int param2) {
     fontRenderer.drawString("Rune Workbench", 8, 6, 4210752);
     fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
}

@Override
protected void drawGuiContainerBackgroundLayer(float par1, int par2,
             int par3) {
     GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
     this.mc.renderEngine.func_110577_a(new ResourceLocation("chalith","textures/gui/runeWorkbenchGUI.png"));
     int x = (width - xSize) / 2;
     int y = (height - ySize) / 2;
     this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
     this.drawTexturedModalRect(x + 71, y + 20, 0, this.ySize + (this.repairContainer.getSlot(0).getHasStack() ? 0 : 12), 100, 12);
}

public void initGui()
{
    super.initGui();
    Keyboard.enableRepeatEvents(true);
    int i = (this.width - this.xSize) / 2;
    int j = (this.height - this.ySize) / 2;
    this.itemNameField = new GuiTextField(this.fontRenderer, i + 72, j + 22, 90, 12);
    this.itemNameField.setTextColor(-1);
    this.itemNameField.setDisabledTextColour(-1);
    this.itemNameField.setEnableBackgroundDrawing(false);
    this.itemNameField.setMaxStringLength(40);
    this.inventorySlots.removeCraftingFromCrafters(this);
    this.inventorySlots.addCraftingToCrafters(this);
}

public void onGuiClosed()
{
    super.onGuiClosed();
    Keyboard.enableRepeatEvents(false);
    this.inventorySlots.removeCraftingFromCrafters(this);
}

protected void keyTyped(char par1, int par2)
{
    if (this.itemNameField.textboxKeyTyped(par1, par2))
    {
        this.rename();
    }
    else
    {
        super.keyTyped(par1, par2);
    }
}
protected void mouseClicked(int par1, int par2, int par3)
{
    super.mouseClicked(par1, par2, par3);
    this.itemNameField.mouseClicked(par1, par2, par3);
}

private void rename()
{
    String s = this.itemNameField.getText();
    Slot slot = this.repairContainer.getSlot(0);

    if (slot != null && slot.getHasStack() && !slot.getStack().hasDisplayName() && s.equals(slot.getStack().getDisplayName()))
    {
        s = "";
    }
    ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
    DataOutputStream outputStream = new DataOutputStream(bos);
    try {
            outputStream.writeUTF(s);
            outputStream.writeInt(te.xCoord);
            outputStream.writeInt(te.yCoord);
            outputStream.writeInt(te.zCoord);
    } catch (Exception ex) {
            ex.printStackTrace();
    }

    Packet250CustomPayload packet = new Packet250CustomPayload();
    packet.channel = "Chalith";
    packet.data = bos.toByteArray();
    packet.length = bos.size();
    if (!te.worldObj.isRemote) {
            EntityPlayerMP player2 = (EntityPlayerMP) this.player;
    } else if (te.worldObj.isRemote) {
            EntityClientPlayerMP player2 = (EntityClientPlayerMP) this.player;
            PacketDispatcher.sendPacketToServer(packet);
            te.setName(s);
    } else {
    }
}

public void sendContainerAndContentsToPlayer(Container par1Container, List par2List)
{
    this.sendSlotContents(par1Container, 0, par1Container.getSlot(0).getStack());
}

public void sendSlotContents(Container par1Container, int par2, ItemStack par3ItemStack)
{
    if (par2 == 0)
    {
        this.itemNameField.setText(par3ItemStack == null ? "" : par3ItemStack.getDisplayName());
        this.itemNameField.setEnabled(par3ItemStack != null);

        if (par3ItemStack != null)
        {
            this.rename();
        }
    }
}

public void sendProgressBarUpdate(Container par1Container, int par2, int par3) {}

public void drawScreen(int par1, int par2, float par3)
{
	super.drawScreen(par1, par2, par3);
    GL11.glDisable(GL11.GL_LIGHTING);
    this.itemNameField.drawTextBox();
}




}
