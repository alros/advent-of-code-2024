package advent2024.day20

import com.madgag.gif.fmsware.AnimatedGifEncoder
import java.awt.BasicStroke
import java.awt.Color
import java.awt.Stroke
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileOutputStream
import java.security.SecureRandom
import java.util.stream.Stream
import kotlin.math.abs
import kotlin.random.Random

fun solveStep1(input: String, accept: (Int) -> Boolean): Int {
    val map = convertInput(input)
    calculateWeights(map)
    return findCheats(map, accept) { i: Int, j: Int ->
        listOf(Pair(i + 2, j), Pair(i - 2, j), Pair(i, j - 2), Pair(i, j + 2)).stream()
    }
}

fun solveStep2(input: String, accept: (Int) -> Boolean): Int {
    val map = convertInput(input)
    calculateWeights(map)
    return findCheats(map, accept) { i: Int, j: Int ->
        var candidates = mutableSetOf<Pair<Int, Int>>()
        for (y in 0..20) {
            for (x in 0..20) {
                if (x + y <= 20) {
                    candidates.add(Pair(i + y, j + x))
                    candidates.add(Pair(i - y, j + x))
                    candidates.add(Pair(i - y, j - x))
                    candidates.add(Pair(i + y, j - x))
                }
            }
        }
        candidates.stream()
    }
}

class Map(val map: List<IntArray>, val start: Pair<Int, Int>, val end: Pair<Int, Int>)

private fun convertInput(input: String): Map {
    var map = input.split("\n").stream()
        .filter { it.isNotEmpty() }
        .map {
            it.toCharArray().toList().stream()
                .mapToInt { ch ->
                    when (ch) {
                        '.' -> Integer.MAX_VALUE
                        '#' -> -1
                        'S' -> Integer.MAX_VALUE
                        'E' -> Integer.MAX_VALUE
                        else -> throw RuntimeException("$ch is invalid")
                    }
                }.toArray()
        }.toList()
    var start: Pair<Int, Int>? = null
    var end: Pair<Int, Int>? = null
    for ((i, line) in input.split("\n").withIndex()) {
        if (line.indexOf("S") >= 0) {
            start = Pair(i, line.indexOf("S"))
        }
        if (line.indexOf("E") >= 0) {
            end = Pair(i, line.indexOf("E"))
        }
    }
    map[start!!.first][start!!.second] = 0
    return Map(map, start!!, end!!)
}

fun calculateWeights(mapConf: Map): Int {
    var end = mapConf.end
    val map = mapConf.map
    var candidates = listOf(mapConf.start)
    val moves = listOf(Pair(1, 0), Pair(-1, 0), Pair(0, 1), Pair(0, -1))
    while (true) {
        val newCandidates = mutableListOf<Pair<Int, Int>>()
        for (candidate in candidates) {
            var cCost = map[candidate.first][candidate.second]
            if (candidate == end) {
                return cCost
            }
            cCost++
            moves.stream()
                .map { Pair(candidate.first + it.first, candidate.second + it.second) }
                .filter { map[it.first][it.second] != -1 }
                .filter { map[it.first][it.second] > cCost }
                .peek { map[it.first][it.second] = cCost }
                .forEach { newCandidates.add(it) }
        }
        candidates = newCandidates
    }
}

fun findCheats(mapConf: Map, accept: (Int) -> Boolean, cheatStream: (i: Int, j: Int) -> Stream<Pair<Int, Int>>): Int {
    val map = mapConf.map
    val cheats = mutableSetOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] >= 0) {
                val nc = cheatStream.invoke(i, j)
                    .filter { it.first >= 0 && it.second >= 0 && it.first < map.size && it.second < map[0].size }
                    .filter { map[it.first][it.second] != -1 }
                    .filter { accept(map[it.first][it.second] - map[i][j] - abs(i - it.first) - abs(j - it.second)) }
                    .toList()
                nc.forEach { cheats.add(Pair(Pair(i, j), Pair(it.first, it.second))) }
            }
        }
    }
    //visualise(map, cheats)
    return cheats.size
}

fun visualise(map: List<IntArray>, cheats: Set<Pair<Pair<Int, Int>, Pair<Int, Int>>>) {
    val size = 20
    val width = map[0].size * size
    val height = map.size * size
    var image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    var g2d = image.createGraphics()

    val gifEncoder = AnimatedGifEncoder()
    gifEncoder.setQuality(1)
    gifEncoder.start(FileOutputStream(File("render/day20.gif")))
    gifEncoder.setDelay(40)
    gifEncoder.setRepeat(0)

    g2d.color = Color.BLACK
    g2d.fillRect(0, 0, width, height)
    for (i in map.indices) {
        for (j in map[i].indices) {
            if (map[i][j] == -1) {
                g2d.color = Color.RED
                g2d.fillRect(j * size, i * size, size, size)
            }
        }
    }
    var r = SecureRandom()
    for ((i, cheat) in cheats.withIndex()) {
        g2d.color = Color(r.nextInt(255), r.nextInt(255), r.nextInt(255))
        g2d.stroke = BasicStroke(5.0f)
        g2d.drawLine(
            cheat.first.second * size + size / 2, cheat.first.first * size + size / 2,
            cheat.second.second * size + size / 2, cheat.second.first * size + size / 2
        )
    }
    g2d.dispose()
    gifEncoder.addFrame(image)

    gifEncoder.finish()
}
