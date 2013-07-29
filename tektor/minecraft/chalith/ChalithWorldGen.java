package tektor.minecraft.chalith;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class ChalithWorldGen implements IWorldGenerator {

	@Override
	
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		// avaea
		for (int k = 0; k < 5; k++) {
			int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
			int firstBlockYCoord = 10 + random.nextInt(64);
			int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);
			(new WorldGenMinable(ChalithBase.chalithBaseOre.blockID,2,6, Block.stone.blockID)).generate(
					world, random, firstBlockXCoord, firstBlockYCoord,
					firstBlockZCoord);
		}
		//loryn
		for (int k = 0; k < 4; k++) {
			int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
			int firstBlockYCoord = random.nextInt(32);
			int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);
			(new WorldGenMinable(ChalithBase.chalithBaseOre.blockID,0, 3, Block.stone.blockID)).generate(
					world, random, firstBlockXCoord, firstBlockYCoord,
					firstBlockZCoord);

		}
		//sorfyn
		for (int k = 0; k < 3; k++) {
			int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
			int firstBlockYCoord = random.nextInt(32)+16;
			int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);
			(new WorldGenMinable(ChalithBase.chalithBaseOre.blockID,1, 3, Block.stone.blockID)).generate(
					world, random, firstBlockXCoord, firstBlockYCoord,
					firstBlockZCoord);

		}
		//bloodstone
		for (int k = 0; k < 3; k++) {
			int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
			int firstBlockYCoord = random.nextInt(64);
			int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);
			(new WorldGenMinable(ChalithBase.bloodstone.blockID,0, 30, Block.stone.blockID)).generate(
					world, random, firstBlockXCoord, firstBlockYCoord,
					firstBlockZCoord);

		}
	

	}

}
