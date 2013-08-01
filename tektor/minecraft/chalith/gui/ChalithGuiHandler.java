package tektor.minecraft.chalith.gui;

import tektor.minecraft.chalith.container.ChalithWorkplaceContainer;
import tektor.minecraft.chalith.entity.ChalithWorkplaceTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class ChalithGuiHandler implements IGuiHandler{

	@Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world,
                    int x, int y, int z) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if(tileEntity instanceof ChalithWorkplaceTileEntity){
                    return new ChalithWorkplaceContainer(player.inventory, (ChalithWorkplaceTileEntity) tileEntity);
            }
            return null;
    }

    //returns an instance of the Gui you made earlier
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world,
                    int x, int y, int z) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if(tileEntity instanceof ChalithWorkplaceTileEntity){
                    return new ChalithWorkplaceGui(player, player.inventory, (ChalithWorkplaceTileEntity) tileEntity);
            }
            return null;

    }

}
