package nl.rwslinkman.adventofcode2025.challenges

import nl.rwslinkman.adventofcode2025.AdventChallenge
import nl.rwslinkman.adventofcode2025.Puzzle

@Puzzle("day06/input.txt")
object Day06: AdventChallenge {

    private data class MathProblem(val numbers: List<Long>, val operator: String)
    private const val SUM = "+"

    override fun part1(inputString: String): Any {
        val lines = inputString.lines()
        val fullWidth = lines.maxOf { it.length }
        val rowsWithNumbers = lines.size
        val tmp = mutableListOf<String>().apply { repeat(rowsWithNumbers) { add("") } }
        val homework = mutableListOf<MathProblem>()
        for(i in 0..<fullWidth) {

            val columnValues = mutableListOf<String>()
            for(r in 0..<rowsWithNumbers) {
                val cell = try { lines[r][i].toString() } catch(_: Exception) { "" }
                columnValues.add(cell)
            }

            if(columnValues.all { it == " "}) {
                val numbers = tmp.take(rowsWithNumbers - 1).map { it.toLong() }
                val problem = MathProblem(numbers, tmp.last())
                homework.add(problem)
                tmp.clear()
                tmp.apply { repeat(rowsWithNumbers) { add("") } }
            } else {
                for(r in 0..<rowsWithNumbers) {
                    val cell = try { lines[r][i].toString() } catch(_: Exception) { " " }
                    tmp[r] += if(cell != " ") cell else ""
                }
            }
        }
        return homework.sumOf {
            val part = if(it.operator == SUM) {
                it.numbers.sum()
            } else {
                it.numbers.fold(1L) { acc, number -> acc * number}
            }
            part
        }
    }

    override fun part2(inputString: String): Any {
        val lines = inputString.lines()
        val fullWidth = lines.maxOf { it.length }
        val rowsWithNumbers = lines.size
        val tmp = mutableListOf<String>().apply { repeat(rowsWithNumbers) { add("") } }
        val homework = mutableListOf<MathProblem>()
        for(i in 0..<fullWidth) {

            val columnValues = mutableListOf<String>()
            for(r in 0..<rowsWithNumbers) {
                val cell = try { lines[r][i].toString() } catch(_: Exception) { "" }
                columnValues.add(cell)
            }

            if(columnValues.all { it == " "}) {
                val columnNumbers = rotate(tmp)
                val problem = MathProblem(columnNumbers, tmp.last().trim())
                homework.add(problem)
                tmp.clear()
                tmp.apply { repeat(rowsWithNumbers) { add("") } }
            } else {
                for(r in 0..<rowsWithNumbers) {
                    val cell = try { lines[r][i].toString() } catch(_: Exception) { " " }
                    tmp[r] += cell
                }
            }
        }
        if(tmp.isNotEmpty()) {
            val columnNumbers = rotate(tmp)
            val problem = MathProblem(columnNumbers, tmp.last().trim())
            homework.add(problem)
        }
        return homework.sumOf {
            if(it.operator == SUM) {
                it.numbers.sum()
            } else {
                it.numbers.fold(1L) { acc, number -> acc * number}
            }
        }
    }

    private fun rotate(tmp: MutableList<String>): MutableList<Long> {
        val maxNumWidth = tmp.map { it.length }.max()
        val formatted = tmp.take(tmp.size - 1).map {
            it.padStart(maxNumWidth, ' ')
        }
        val columnNumbers = mutableListOf<Long>()
        for (c in 0..<maxNumWidth) {
            var colTmp = ""
            for (num in formatted) {
                colTmp += if (num[c] != ' ') num[c] else ""
            }
            columnNumbers.add(colTmp.toLong())
        }
        return columnNumbers
    }
}