package tektor.minecraft.chalith.container;

import net.minecraft.inventory.InventoryBasic;

public class ChalithWorkbenchContainerInner extends InventoryBasic{

	private ChalithWorkplaceContainer container;
	public ChalithWorkbenchContainerInner(ChalithWorkplaceContainer cont, String par1Str, boolean par2, int par3) {
		super(par1Str, par2, par3);
		container = cont;
	}

	public void onInventoryChanged()
    {
        super.onInventoryChanged();
        this.container.onCraftMatrixChanged(this);
    }
}
