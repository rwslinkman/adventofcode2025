package nl.rwslinkman.adventofcode2025.challenges

import nl.rwslinkman.adventofcode2025.AdventChallenge
import nl.rwslinkman.adventofcode2025.Puzzle
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

@Puzzle("day09/input.txt")
object Day09: AdventChallenge {

    private data class Position(val x: Long, val y: Long)

    override fun part1(inputString: String): Any {
        val cornerCoordinates = inputString.lines().map{
            val split = it.split(",")
            Position(split[0].toLong(), split[1].toLong())
        }

        val surfaceMap: MutableMap<Pair<Position, Position>, Long> = mutableMapOf()
        for(one in cornerCoordinates) {
            for (two in cornerCoordinates) {
                if(one == two) continue

                val dx = abs(one.x - two.x + 1)
                val dy = abs(one.y - two.y + 1)
                val surface = dx * dy * 1L
                surfaceMap[one to two] = surface
            }
        }
        return surfaceMap.values.max()
    }

    private data class Area(val surface: Long, val one: Position, val two: Position)

    override fun part2(inputString: String): Any {
        val cornerCoordinates = inputString.lines().map{
            val split = it.split(",")
            Position(split[0].toLong(), split[1].toLong())
        }.toMutableList()

        val shapeEdges = mutableSetOf<Position>()
        cornerCoordinates.add(cornerCoordinates.first())
        cornerCoordinates.windowed(2).forEach { window ->
            val one = window[0]
            val two = window[1]

            if (one.x == two.x) {
                // vertical line
                val startY = min(one.y, two.y)
                val endY = max(one.y, two.y)
                for (y in startY..endY) {
                    shapeEdges.add(Position(one.x, y))
                }
            } else if (one.y == two.y) {
                // horizontal line
                val startX = min(one.x, two.x)
                val endX = max(one.x, two.x)
                for (x in startX..endX) {
                    shapeEdges.add(Position(x, one.y))
                }
            }
        }
        cornerCoordinates.removeLast()

        val areas = mutableListOf<Area>()
        for (one in cornerCoordinates) {
            for (two in cornerCoordinates) {
                if (one == two) continue

                val dx = abs(one.x - two.x) + 1
                val dy = abs(one.y - two.y) + 1
                val surface = dx * dy * 1L
                areas.add(Area(surface, one, two))
            }
        }

        val sortedAreas = areas.sortedByDescending { it.surface }
        var best: Area? = null
        for(area in sortedAreas) {
            val minX = min(area.one.x, area.two.x)
            val maxX = max(area.one.x, area.two.x)
            val minY = min(area.one.y, area.two.y)
            val maxY = max(area.one.y, area.two.y)

            var invalid = false
            for (pos in shapeEdges) {
                if(pos.x > minX && pos.x < maxX && pos.y > minY && pos.y < maxY) {
                    invalid = true
                    break
                }
            }
            if(invalid) {
                continue
            }
            best = area
            break
        }

        val result = best!!
        val dx = abs(result.one.x - result.two.x) + 1
        val dy = abs(result.one.y - result.two.y) + 1
        return dx * dy * 1L
    }
}