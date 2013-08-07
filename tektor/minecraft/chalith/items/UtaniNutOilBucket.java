package tektor.minecraft.chalith.items;

import tektor.minecraft.chalith.ChalithBase;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.ItemFluidContainer;

public class UtaniNutOilBucket extends ItemBucket{

	Icon icon;
	public UtaniNutOilBucket(int itemID) {
		super(itemID, ChalithBase.utaniNutOilBlock.blockID);
		this.setCreativeTab(CreativeTabs.tabMisc);
		// TODO Auto-generated constructor stub
	}
	public UtaniNutOilBucket(int itemID, int capacity)
    {
        super(itemID,ChalithBase.utaniNutOilBlock.blockID);
    }
	
	@Override
	public Icon getIcon(ItemStack stack, int pass) {
		int id = stack.getItemDamage();
		return icon;
	}

	@Override
	public Icon getIconFromDamageForRenderPass(int par1, int par2) {
		return icon;
	}

	@Override
	public Icon getIconFromDamage(int par1) {
		return icon;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "utaniNutOilBucket";
		}

	

	@Override
	public String getItemDisplayName(ItemStack stack) {
		return "Full Bucket of Utani Nut Oil";
	}

	@Override
	public void registerIcons(IconRegister par1IconRegister) {
		icon = par1IconRegister.registerIcon("chalith:utaniNutOilBucket");
	}
	
	@ForgeSubscribe
	public void onBucketFill( FillBucketEvent event )
	{
		ItemStack result = attemptFill( event.world, event.target );
		
		if ( result != null )
		{
			event.result = result;
			event.setResult( Result.ALLOW );
		}
	}
	
	private ItemStack attemptFill( World world, MovingObjectPosition p )
	{
		int id = world.getBlockId( p.blockX, p.blockY, p.blockZ );
		
		if ( id == ChalithBase.utaniNutOilBlock.blockID )
		{
			if ( world.getBlockMetadata( p.blockX, p.blockY, p.blockZ ) == 0 ) // Check that it is a source block
			{
				world.setBlock( p.blockX, p.blockY, p.blockZ, 0 ); // Remove the fluid block
				
				return new ItemStack( ChalithBase.utaniNutOilBucket );
			}
		}
		
		return null;
	}
	
}
