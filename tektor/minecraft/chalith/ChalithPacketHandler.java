package tektor.minecraft.chalith;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import tektor.minecraft.chalith.entity.tileentity.ChalithWorkplaceTileEntity;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ChalithPacketHandler implements IPacketHandler{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
        if (packet.channel.equals("Chalith")) {
            handle(packet,player);
    }
	}
	
	private void handle(Packet250CustomPayload packet, Player player) {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
       
        String name;
        int x,y,z;
       
        	try {
                name = inputStream.readUTF(); 
                x = inputStream.readInt();
                y = inputStream.readInt();
                z = inputStream.readInt();
        	} catch (IOException e) {
                e.printStackTrace();
                return;
        	}
        	EntityPlayerMP play = (EntityPlayerMP)player;
        	if (play.worldObj.getBlockTileEntity(x, y, z) instanceof ChalithWorkplaceTileEntity) {
        		ChalithWorkplaceTileEntity ent = (ChalithWorkplaceTileEntity)play.worldObj.getBlockTileEntity(x, y, z);
			    ent.setName(name);
        	}
	}

}
