package nl.rwslinkman.adventofcode2025.challenges

import nl.rwslinkman.adventofcode2025.AdventChallenge
import nl.rwslinkman.adventofcode2025.Puzzle

@Puzzle("day12/input.txt")
object Day12: AdventChallenge {

    private const val PART_OF_SHAPE = '#'
    private data class Present(val shape: List<List<Char>>, val area: Int)
    private data class Instruction(val width: Int, val height: Int, val quantities: List<Int>)

    override fun part1(inputString: String): Any {
        val regionsRegex = Regex("(\\d*)x(\\d*):\\s(.*)")
        val inputLines = inputString.lines()
        val shapes = mutableListOf<Present>()
        var currentShape = mutableListOf<List<Char>>()
        val instructions = mutableListOf<Instruction>()
        inputLines.forEach { line ->
            if(line.startsWith('#') || line.startsWith('.')) {
                // add to shape
                currentShape.add(line.toList())
            }
            else if(line == "") {
                // end of shape
                val present = Present(currentShape, currentShape.flatMap { it }.count { it == PART_OF_SHAPE })
                shapes.add(present)
                currentShape = mutableListOf()
            }
            else if(line.contains('x')) {
                // instruction row
                val (width, height, quantities) = regionsRegex.find(line)!!.destructured
                val element = Instruction(width.toInt(), height.toInt(), quantities.split(" ").map { it.toInt() })
                instructions.add(element)
            }
        }

        var regionsWhereShapesFit = 0
        for(instr in instructions) {
            val surface = instr.height * instr.width
            val spaceNeeded = instr.quantities.reduceIndexed { index, acc, quantity ->
                acc + quantity * shapes[index].area
            }
            val u = (spaceNeeded / surface.toDouble())
            if(surface >= spaceNeeded && u <= 0.8) {
                regionsWhereShapesFit++
            }
        }
        return regionsWhereShapesFit
    }

    override fun part2(inputString: String): Any {
        // Free star :)
        return 0
    }
}