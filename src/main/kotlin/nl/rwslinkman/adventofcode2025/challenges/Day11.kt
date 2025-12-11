package nl.rwslinkman.adventofcode2025.challenges

import nl.rwslinkman.adventofcode2025.AdventChallenge
import nl.rwslinkman.adventofcode2025.Puzzle
import java.util.PriorityQueue
import kotlin.collections.forEach

@Puzzle("day11/input.txt")
object Day11: AdventChallenge {

    private const val YOU = "you"
    private const val OUT = "out"

    override fun part1(inputString: String): Any {
        val splitter = Regex("([a-z]{3}): (.*)")
        val devices = inputString.lines().associate {
            val (src, dsts) = splitter.find(it)!!.destructured
            val dst = dsts.split(" ").toList()
            src to dst
        }

        val start = devices[YOU]!!
        val queue = PriorityQueue<String>()
        start.forEach { queue.add(it) }
        val visited = mutableSetOf(YOU)
        var pathCount = 0L
        while(queue.isNotEmpty()) {
            val current = queue.poll()
            visited.add(current)

            if(current == OUT) {
                pathCount++
                continue
            }

            val options = devices[current] ?: emptySet()
            queue.addAll(options)
        }
        return pathCount
    }

    private const val SERVER = "svr"
    private const val DAC = "dac"
    private const val FFT = "fft"

    private data class Item(val name: String, val connections: List<String>)
    private data class ItemKey(val name: String, val passedDAC: Boolean, val passedFFT: Boolean)

    override fun part2(inputString: String): Any {
        val splitter = Regex("([a-z]{3}): (.*)")
        val devices = inputString.lines().map {
            val (src, dsts) = splitter.find(it)!!.destructured
            val dst = dsts.split(" ").toList()
            Item(src, dst)
        }

        val server = devices.first { it.name == SERVER }
        return countPaths(devices, mutableMapOf(), server, OUT, passedDAC = false, passedFFT = false)
    }

    private fun countPaths(devices: List<Item>, memo: MutableMap<ItemKey, Long>, item: Item, target: String, passedDAC: Boolean, passedFFT: Boolean): Long {
        var hasDAC = passedDAC
        var hasFFT = passedFFT

        val key = ItemKey(item.name, hasDAC, hasFFT)
        if(memo.containsKey(key)) {
            return memo[key]!!
        }

        if(item.name == DAC) {
            hasDAC = true
        }
        if(item.name == FFT) {
            hasFFT = true
        }

        if(item.name == target) {
            // found
            val result = if(hasDAC && hasFFT) 1L else 0L
            memo[key] = result
            return result
        }

        if(item.connections.isEmpty()) {
            memo[key] = 0
            return 0
        }

        var pathCount = 0L
        for(neighbour in item.connections) {
            val next = devices.find { it.name == neighbour } ?: Item(neighbour, emptyList())
            pathCount += countPaths(devices, memo, next, target, hasDAC, hasFFT)
        }
        memo[key] = pathCount
        return pathCount
    }
}