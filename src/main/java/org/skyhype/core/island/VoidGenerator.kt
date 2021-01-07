package org.skyhype.core.island

import org.bukkit.World
import org.bukkit.block.Biome
import org.bukkit.generator.ChunkGenerator
import java.util.*

class VoidGenerator : ChunkGenerator() {
    override fun generateChunkData(world: World, random: Random, x: Int, z: Int, biomeGrid: BiomeGrid): ChunkData {

        for (x in 0..15 step 4) {
            for (z in 0..15 step 4) {
                for (y in 0..world.maxHeight step 4) {
                    biomeGrid.setBiome(x, y, z, Biome.PLAINS)
                }
            }
        }

        return this.createChunkData(world)
    }

    override fun canSpawn(world: World, x: Int, z: Int): Boolean {
        return false
    }

    override fun isParallelCapable(): Boolean {
        return true
    }
}