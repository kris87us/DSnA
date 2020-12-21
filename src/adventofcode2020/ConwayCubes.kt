package adventofcode2020

import adventofcode2020.util.readFileIntoList

data class Quad<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
) {

    override fun toString(): String = "($first, $second, $third, $fourth)"
}

fun main() {
    val lines = readFileIntoList("/Users/gopaliyer/IdeaProjects/Practice/src/adventofcode2020/input/17.txt")

    val grid = mutableSetOf<Triple<Int, Int, Int>>()
    val grid2 = mutableSetOf<Quad<Int, Int, Int, Int>>()
    for (y in lines.indices) {
        for (x in lines[0].indices) {
            when (lines[x][y]) {
                '#' -> {
                    grid.add(Triple(x, y, 0))
                    grid2.add(Quad(x, y, 0, 0))
                }
            }
        }
    }

    val cycles = 6
    repeat(cycles) {
        part1(grid)
        part2(grid2)
    }

    println(grid.size)
    println(grid2.size)
}

fun part1(grid: MutableSet<Triple<Int, Int, Int>>) {
    val lastGrid = grid.toSet()

    val toEvaluate = mutableSetOf<Triple<Int, Int, Int>>()
    grid.forEach {
        for (i in -1..1) {
            for (j in -1..1) {
                for (k in -1..1) {
                    toEvaluate.add(Triple(it.first + i, it.second + j, it.third + k))
                }
            }
        }
    }
    grid.clear()

    toEvaluate.forEach {
        var active = 0
        for (i in -1..1) {
            for (j in -1..1) {
                for (k in -1..1) {
                    if (i == 0 && j == 0 && k == 0) {
                        continue
                    }
                    if (lastGrid.contains(Triple(it.first + i, it.second + j, it.third + k))) {
                        active++
                    }
                }
            }
        }

        when {
            lastGrid.contains(it) -> when (active) {
                2, 3 -> grid.add(it)
            }
            else -> when (active) {
                3 -> grid.add(it)
            }
        }
    }
}

private fun part2(grid: MutableSet<Quad<Int, Int, Int, Int>>) {
    val lastGrid = grid.toSet()

    val toEvaluate = mutableSetOf<Quad<Int, Int, Int, Int>>()
    grid.forEach {
        for (i in -1..1) {
            for (j in -1..1) {
                for (k in -1..1) {
                    for (l in -1..1) {
                        toEvaluate.add(Quad(it.first + i, it.second + j, it.third + k, it.fourth + l))
                    }
                }
            }
        }
    }
    grid.clear()

    toEvaluate.forEach {
        var active = 0
        for (i in -1..1) {
            for (j in -1..1) {
                for (k in -1..1) {
                    for (l in -1..1) {
                        if (i == 0 && j == 0 && k == 0 && l == 0) {
                            continue
                        }
                        if (lastGrid.contains(Quad(it.first + i, it.second + j, it.third + k, it.fourth + l))) {
                            active++
                        }
                    }
                }
            }
        }

        when {
            lastGrid.contains(it) -> when (active) {
                2, 3 -> grid.add(it)
            }
            else -> when (active) {
                3 -> grid.add(it)
            }
        }
    }
}