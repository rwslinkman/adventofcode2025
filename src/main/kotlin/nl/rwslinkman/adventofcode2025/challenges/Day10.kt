package nl.rwslinkman.adventofcode2025.challenges

import nl.rwslinkman.adventofcode2025.AdventChallenge
import nl.rwslinkman.adventofcode2025.Puzzle
import java.util.*
import com.microsoft.z3.*

@Puzzle("day10/input.txt")
object Day10: AdventChallenge {

    private data class Procedure(val indicators: String, val buttons: List<List<Int>>, val joltages: List<Int>)

    override fun part1(inputString: String): Any {
        val regex = Regex("\\[([.#]*)] ([\\d,\\s)(]*)\\D([\\d,]*)\\D")
        val buttonsRegex = Regex("\\(([\\d,]+)\\)")
        val procedures = inputString.lines().map {
            val (switches, buttons, joltages) = regex.find(it)!!.destructured
            val buttonGroups = buttonsRegex.findAll(buttons.trim()).map { b ->
                b.groupValues[1].split(",").map { n -> n.toInt() }
            }.toList()
            val joltageGroup = joltages.split(",").map { n -> n.toInt() }
            Procedure(switches, buttonGroups, joltageGroup)
        }

        return procedures.sumOf { procedure ->
            val initialIndicators = ".".repeat(procedure.indicators.length)
            val queue = PriorityQueue<Pair<String, Int>>(Comparator.comparing { it.second })
            queue.add(initialIndicators to 0)
            val visited = mutableSetOf(initialIndicators)
            while(queue.isNotEmpty()) {
                val current = queue.poll()
                visited.add(current.first)
                if(current.first == procedure.indicators) {
                    return@sumOf current.second
                }

                val options = procedure.buttons.mapNotNull { button ->
                    val updatedIndicators = StringBuilder(current.first)
                    button.forEach { b ->
                        val current = updatedIndicators[b]
                        updatedIndicators[b] = if (current == '.') '#' else '.'
                    }
                    val next = Pair(updatedIndicators.toString(), current.second + 1)
                    if(visited.contains(next.first)) null else next
                }.toSet()
                queue.addAll(options)
            }
            return@sumOf Int.MAX_VALUE
        }
    }

    override fun part2(inputString: String): Any {
        val regex = Regex("\\[([.#]*)] ([\\d,\\s)(]*)\\D([\\d,]*)\\D")
        val buttonsRegex = Regex("\\(([\\d,]+)\\)")
        val procedures = inputString.lines().map {
            val (switches, buttons, joltages) = regex.find(it)!!.destructured
            val buttonGroups = buttonsRegex.findAll(buttons.trim()).map { b ->
                b.groupValues[1].split(",").map { n -> n.toInt() }
            }.toList()
            val joltageGroup = joltages.split(",").map { n -> n.toInt() }
            Procedure(switches, buttonGroups, joltageGroup)
        }

        return procedures.sumOf {
            solveZ3(it.buttons, it.joltages)
        }
    }

    private fun solveZ3(buttons: List<List<Int>>, targetJoltages: List<Int>): Long {
        val context = Context()
        val solver = context.mkOptimize()

        val xVars = Array(buttons.size) {
            context.mkIntConst("x$it")
        }
        for(i in xVars.indices) {
            solver.Add(context.mkGe(xVars[i], context.mkInt(0)))
        }

        targetJoltages.forEachIndexed { joltageIndex, joltage ->
            val sumExpr = buttons.mapIndexedNotNull { buttonIndex, button ->
                if(button.contains(joltageIndex)) {
                    xVars[buttonIndex]
                } else null
            }
            if(sumExpr.isNotEmpty()) {
                val allXs = context.mkAdd(*sumExpr.toTypedArray())
                val equation = context.mkEq(allXs, context.mkInt(joltage))
                solver.Add(equation)
            }
        }

        val total = context.mkAdd(*xVars)
        solver.MkMinimize(total)
        val result = solver.Check()

        if(result == Status.SATISFIABLE) {
            val model = solver.model
            val result = xVars.sumOf {
                model.eval(it, false).toString().toLong()
            }
            return result
        } else {
            return 0L
        }
    }


//    My solution that worked on the example, but could not finish on the real put
//    override fun part2(inputString: String): Any {
//        val regex = Regex("\\[([.#]*)] ([\\d,\\s)(]*)\\D([\\d,]*)\\D")
//        val buttonsRegex = Regex("\\(([\\d,]+)\\)")
//        val procedures = inputString.lines().map {
//            val (switches, buttons, joltages) = regex.find(it)!!.destructured
//            val buttonGroups = buttonsRegex.findAll(buttons.trim()).map { b ->
//                b.groupValues[1].split(",").map { n -> n.toInt() }
//            }.toList()
//            val joltageGroup = joltages.split(",").map { n -> n.toInt() }
//            Procedure(switches, buttonGroups, joltageGroup)
//        }
//
//        return procedures.sumOf { procedure ->
//
//            val initialIndicators = List(procedure.joltages.size) {
//                0
//            }.joinToString(",")
//            val queue = PriorityQueue<Pair<String, Int>>(Comparator.comparing { it.second })
//            queue.add(initialIndicators to 0)
//            val visited = mutableSetOf(initialIndicators)
//            while(queue.isNotEmpty()) {
//                val current = queue.poll()
//                visited.add(current.first)
//                val state = current.first.split(",").map { it.toInt() }
//                val comparison = state.zip(procedure.joltages)
//                val isFound = comparison.all { it.first == it.second }
//                if (isFound) {
//                    return@sumOf current.second
//                }
//
//                val options = procedure.buttons.mapNotNull { button ->
//                    val updatedCounters = state.toMutableList()
//                    button.forEach { b ->
//                        updatedCounters[b] = updatedCounters[b] + 1
//                    }
//
//                    val comparison = updatedCounters.zip(procedure.joltages)
//                    val stillLower = comparison.all { it.first <= it.second }
//                    if(!stillLower) {
//                        return@mapNotNull null
//                    }
//
//                    val stateKey = updatedCounters.joinToString(",")
//                    val next = Pair(stateKey, current.second + 1)
//                    if(visited.contains(next.first)) null else next
//                }.toSet()
//                queue.addAll(options)
//
//            }
//            return@sumOf Int.MAX_VALUE
//        }
//    }
}