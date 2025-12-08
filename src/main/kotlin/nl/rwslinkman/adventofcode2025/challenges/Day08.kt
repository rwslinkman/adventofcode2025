package nl.rwslinkman.adventofcode2025.challenges

import nl.rwslinkman.adventofcode2025.AdventChallenge
import nl.rwslinkman.adventofcode2025.Puzzle
import kotlin.math.abs

@Puzzle("day08/input.txt")
object Day08: AdventChallenge {

    private data class JunctionBox(val x: Long, val y: Long, val z: Long)

    override fun part1(inputString: String): Any {
        val junctionBoxes = inputString.lines().map {
            val split = it.split(",")
            JunctionBox(
                x = split[0].toLong(),
                y = split[1].toLong(),
                z = split[2].toLong()
            )
        }

        val distances: MutableMap<Long, Pair<JunctionBox, JunctionBox>> = mutableMapOf()
        junctionBoxes.forEach { fromBox ->
            junctionBoxes.forEach { toBox ->
                if(fromBox != toBox) {
                    val distance = distance(fromBox, toBox)
                    distances[distance] = fromBox to toBox
                }
            }
        }
        val sortedDistances = distances.toSortedMap().values.toList()
        val circuits: MutableList<MutableSet<JunctionBox>> = junctionBoxes.map { mutableSetOf(it) }.toMutableList()
        val iterations = if(junctionBoxes.size == 20) 10 else 1000

        repeat(iterations) { i ->
            val shortestConnection = sortedDistances[i]
            val modifiedCircuits = mutableListOf<Set<JunctionBox>>()
            circuits.forEach { circuit ->
                if(circuit.contains(shortestConnection.first) || circuit.contains(shortestConnection.second)) {
                    circuit.add(shortestConnection.first)
                    circuit.add(shortestConnection.second)
                    modifiedCircuits.add(circuit)
                }
            }
            if(modifiedCircuits.size > 1) {
                val combinedCircuit = mutableSetOf<JunctionBox>()
                for (boxes in modifiedCircuits) {
                    circuits.remove(boxes)
                    combinedCircuit.addAll(boxes)
                }
                circuits.add(combinedCircuit)
            }
        }
        val sortedCircuits = circuits.sortedByDescending { it.size }
        return sortedCircuits.take(3).fold(1L) { acc, it ->
            acc * it.size
        }
    }

    private fun distance(a: JunctionBox, b: JunctionBox): Long {
        val dx = abs(a.x - b.x)
        val dy = abs(a.y - b.y)
        val dz = abs(a.z - b.z)
        return dx*dx + dy*dy + dz*dz
    }

    override fun part2(inputString: String): Any {
        val junctionBoxes = inputString.lines().map {
            val split = it.split(",")
            JunctionBox(
                x = split[0].toLong(),
                y = split[1].toLong(),
                z = split[2].toLong()
            )
        }

        val distances: MutableMap<Long, Pair<JunctionBox, JunctionBox>> = mutableMapOf()
        junctionBoxes.forEach { fromBox ->
            junctionBoxes.forEach { toBox ->
                if(fromBox != toBox) {
                    val distance = distance(fromBox, toBox)
                    distances[distance] = fromBox to toBox
                }
            }
        }
        val sortedDistances = distances.toSortedMap().values.toList()
        val circuits: MutableList<MutableSet<JunctionBox>> = junctionBoxes.map { mutableSetOf(it) }.toMutableList()

        var iteration = 0
        while(true) {
            val shortestConnection = sortedDistances[iteration]
            val modifiedCircuits = mutableListOf<Set<JunctionBox>>()
            circuits.forEach { circuit ->
                if(circuit.contains(shortestConnection.first) || circuit.contains(shortestConnection.second)) {
                    circuit.add(shortestConnection.first)
                    circuit.add(shortestConnection.second)
                    modifiedCircuits.add(circuit)
                }
            }
            if(modifiedCircuits.size > 1) {
                val combinedCircuit = mutableSetOf<JunctionBox>()
                for (boxes in modifiedCircuits) {
                    circuits.remove(boxes)
                    combinedCircuit.addAll(boxes)
                }
                circuits.add(combinedCircuit)
            }

            if(circuits.size == 1 && circuits[0].size == junctionBoxes.size) {
                return shortestConnection.first.x * shortestConnection.second.x
            }
            iteration++
        }
    }
}