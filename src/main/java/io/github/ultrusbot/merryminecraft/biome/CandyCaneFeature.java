package io.github.ultrusbot.merryminecraft.biome;

import com.mojang.serialization.Codec;
import io.github.ultrusbot.merryminecraft.MerryMinecraft;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;

import java.util.Random;

public class CandyCaneFeature extends Feature<DefaultFeatureConfig> {

    public CandyCaneFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(StructureWorldAccess world, ChunkGenerator chunkGenerator, Random random, BlockPos pos, DefaultFeatureConfig config) {
        BlockPos genPos = pos.add(random.nextInt((3) - 1) * 8 , 0, random.nextInt((3) - 1) * 8);
        BlockPos topPos = world.getTopPosition(Heightmap.Type.WORLD_SURFACE, genPos);
        Direction facing;
        switch (random.nextInt(4)) {
            case 0:
                facing = Direction.NORTH;
                break;
            case 1:
                facing = Direction.EAST;
                break;
            case 2:
                facing = Direction.SOUTH;
                break;
            case 3:
                facing = Direction.WEST;
                break;
            default:
                facing = Direction.NORTH;
        }
        BlockState block = random.nextInt(2) == 0 ? MerryMinecraft.RED_CANDY_CANE_BLOCK.getDefaultState() : MerryMinecraft.GREEN_CANDY_CANE_BLOCK.getDefaultState();
        if (world.getBlockState(pos) == MerryMinecraft.RED_CANDY_CANE_BLOCK.getDefaultState() || world.getBlockState(pos) == MerryMinecraft.GREEN_CANDY_CANE_BLOCK.getDefaultState()) {
            return false;
        }
        int size = random.nextInt(3) + 4;
        for (int y = 0; y <= size; y++) {
            world.setBlockState(topPos.up(y), block, 3);
            if (y == size) {
                world.setBlockState(topPos.up(y + 1).offset(facing, 1), block, 3);
                world.setBlockState(topPos.up(y + 1).offset(facing, 2), block, 3);
                world.setBlockState(topPos.up(y).offset(facing, 3), block, 3);
            }
        }
        return true;
    }
}
