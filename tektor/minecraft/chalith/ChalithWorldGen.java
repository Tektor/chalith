package tektor.minecraft.chalith;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class ChalithWorldGen implements IWorldGenerator {

	@Override
	// avaea
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		for (int k = 0; k < 10; k++) {
			int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
			int firstBlockYCoord = 10 + random.nextInt(64);
			int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);
			(new WorldGenMinable(ChalithBase.avaeaOre.blockID, 6)).generate(
					world, random, firstBlockXCoord, firstBlockYCoord,
					firstBlockZCoord);
		}
		for (int k = 0; k < 7; k++) {
			int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
			int firstBlockYCoord = random.nextInt(32);
			int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);
			(new WorldGenMinable(ChalithBase.chalithBaseOre.blockID, 0, 6)).generate(
					world, random, firstBlockXCoord, firstBlockYCoord,
					firstBlockZCoord);

		}
		for (int k = 0; k < 5; k++) {
			int firstBlockXCoord = chunkX * 16 + random.nextInt(16);
			int firstBlockYCoord = random.nextInt(32);
			int firstBlockZCoord = chunkZ * 16 + random.nextInt(16);
			(new WorldGenMinable(ChalithBase.chalithBaseOre.blockID, 1, 6)).generate(
					world, random, firstBlockXCoord, firstBlockYCoord,
					firstBlockZCoord);

		}

	}

}
