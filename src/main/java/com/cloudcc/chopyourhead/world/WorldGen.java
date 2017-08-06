package com.cloudcc.chopyourhead.world;

import com.cloudcc.chopyourhead.init.ModBlocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by hi112 on 02.12.2016.
 */
public class WorldGen implements IWorldGenerator {

    private WorldGenerator MITHRIL_ORE;
    private WorldGenerator ADAMANTIT_ORE;

    public WorldGen(){

        this.MITHRIL_ORE = new WorldGenMinable(ModBlocks.mithril_ore.getDefaultState(),3);
        this.ADAMANTIT_ORE = new WorldGenMinable(ModBlocks.adamantit_ore.getDefaultState(),3);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        switch (world.provider.getDimension())
        {
            case 0: //Overworld

                this.runGenerator(this.MITHRIL_ORE, world, random, chunkX, chunkZ, 100, 5, 35);


                break;
            case -1: //Nether

                this.runGenerator(this.ADAMANTIT_ORE, world, random, chunkX, chunkZ, 10, 45, 112);
        }

    }

    private void runGenerator(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chanceToSpawn, int minHeight, int maxHeight)
    {
        if(minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
            throw new IllegalArgumentException("Min or Max Height out of bounds");

        int heightDiff = maxHeight - minHeight + 1;
        for(int i = 0; i < chanceToSpawn; i++)
        {
            int x = chunkX * 16 + random.nextInt(16);
            int y = minHeight + random.nextInt(heightDiff);
            int z = chunkZ * 16 + random.nextInt(16);
            generator.generate(world, random, new BlockPos(x,y,z));
        }
    }
}
