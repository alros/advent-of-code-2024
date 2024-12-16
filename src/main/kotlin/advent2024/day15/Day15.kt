package advent2024.day15

import com.madgag.gif.fmsware.AnimatedGifEncoder
import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileOutputStream


abstract class MapObject(val objects: List<MapObject>, var pos: Pair<Int, Int>, val symbol: String) {
    abstract fun move(map: List<MapObject>, mov: Movement, testOnly: Boolean = false): Boolean

    open fun collide(otherPos: Pair<Int, Int>): Boolean = pos.first == otherPos.first && pos.second == otherPos.second

    override fun toString(): String {
        return "$pos -> $symbol"
    }

}

open class MovableMapObject(objects: List<MapObject>, pos: Pair<Int, Int>, symbol: String) :
    MapObject(objects, pos, symbol) {
    override fun move(map: List<MapObject>, mov: Movement, testOnly: Boolean): Boolean {
        val newI = pos.first + mov.change.first
        val newJ = pos.second + mov.change.second
        val collisions = objects.stream()
            .filter { it.collide(Pair(newI, newJ)) }
            .toList()
        if (collisions.isEmpty()) {
            if (!testOnly) {
                pos = Pair(newI, newJ)
            }
            return true
        }

        if (collisions.stream().map {
                it.move(map, mov, testOnly = true)
            }.filter { !it }.findFirst().isEmpty) {
            collisions.stream().forEach { it.move(map, mov) }
            if (!testOnly) {
                pos = Pair(newI, newJ)
            }
            return true
        }

        return false
    }
}

class Robot(objects: List<MapObject>, pos: Pair<Int, Int>) : MovableMapObject(objects, pos, "@")

class Box(objects: List<MapObject>, pos: Pair<Int, Int>) : MovableMapObject(objects, pos, "O")

class LargeBox(objects: List<MapObject>, pos: Pair<Int, Int>) : MovableMapObject(objects, pos, "[]") {
    override fun move(map: List<MapObject>, mov: Movement, testOnly: Boolean): Boolean {
        val newI = pos.first + mov.change.first
        val newJL = pos.second + mov.change.second
        val newJR = pos.second + mov.change.second + 1
        val collisions = objects.stream()
            .filter { (it.collide(Pair(newI, newJL)) || it.collide(Pair(newI, newJR))) && it != this }
            .toList()
        if (collisions.isEmpty()) {
            if (!testOnly) {
                pos = Pair(newI, newJL)
            }
            return true
        }
        if (collisions.stream().map { it.move(map, mov, testOnly = true) }.filter { !it }.findFirst().isEmpty) {
            if (!testOnly) {
                collisions.stream().forEach { it.move(map, mov) }
                pos = Pair(newI, newJL)
            }
            return true
        }

        return false
    }

    override fun collide(otherPos: Pair<Int, Int>): Boolean =
        pos.first == otherPos.first && (pos.second == otherPos.second || pos.second + 1 == otherPos.second)

}

class Wall(objects: List<MapObject>, pos: Pair<Int, Int>) : MapObject(objects, pos, "#") {
    override fun move(map: List<MapObject>, mov: Movement, testOnly: Boolean): Boolean {
        return false
    }
}

class Input(val map: List<MapObject>, val movements: CharArray, val robot: Robot, val mapSize: Pair<Int, Int>)

enum class Movement(val ch: Char, val change: Pair<Int, Int>) {
    NORTH('^', Pair(-1, 0)),
    SOUTH('v', Pair(1, 0)),
    WEST('<', Pair(0, -1)),
    EAST('>', Pair(0, 1));

    companion object FromCh {
        fun fromCh(ch: Char): Movement {
            return Movement.values().toList().stream().filter { it.ch == ch }.findFirst().get()
        }
    }
}

fun solveStep1(input: String): Long {
    return solveStep(input, 1)
}

fun solveStep2(input: String, render: Boolean = false): Long {
    return solveStep(input, 2, render = render)
}

private fun solveStep(input: String, size: Int, render: Boolean = false): Long {
    val parsedInput = convertInput(input, size)
    val map = parsedInput.map
    val robot = parsedInput.robot
    val mapSize = parsedInput.mapSize

    val gifEncoder = if (render) initPrint() else null
    var lastPercentage = -1
    for ((iteration, movement) in parsedInput.movements.withIndex()) {
        if ((100 * iteration / parsedInput.movements.size)!=lastPercentage) {
            lastPercentage = 100 * iteration / parsedInput.movements.size
            println("${lastPercentage}%")
        }
        val mov = Movement.fromCh(movement)
        robot.move(map, mov)
        if (render && iteration < 600) {
            var mapStr = mapToString(mov, map, mapSize)
            addFrame(gifEncoder!!, mapStr, iteration.toString())
        }
    }

    if (render) {
        gifEncoder!!.finish()
    }

    return calculateGPS(map)
}


private fun calculateGPS(map: List<MapObject>): Long {
    return map.stream()
        .filter { it.symbol == "O" || it.symbol == "[]" }
        .map { it.pos.first * 100L + it.pos.second }
        .reduce(0L) { a, b -> a + b }
}

private fun convertInput(input: String, size: Int): Input {
    var parsingMap = true
    val map = mutableListOf<MapObject>()
    val movements = mutableListOf<String>()
    var robot: Robot? = null
    val lines = input.split("\n")
    var mapWidth = 0
    var mapHeigh = 0
    for (i in lines.indices) {
        if (lines[i].isEmpty()) {
            parsingMap = false
        } else if (parsingMap) {
            mapHeigh++
            val effectiveLine = lines[i]
                .replace("#", "#".repeat(size))
                .replace(".", ".".repeat(size))
                .replace("O", if (size == 1) "O" else "[]")
                .replace("@", "@" + ".".repeat(size - 1))
                .toCharArray()
            mapWidth = effectiveLine.size
            for (j in effectiveLine.indices) {
                if (effectiveLine[j] == '#') {
                    map.add(Wall(map, Pair(i, j)))
                } else if (effectiveLine[j] == 'O') {
                    map.add(Box(map, Pair(i, j)))
                } else if (effectiveLine[j] == '[') {
                    map.add(LargeBox(map, Pair(i, j)))
                } else if (effectiveLine[j] == '@') {
                    robot = Robot(map, Pair(i, j))
                    map.add(robot)
                }
            }
        } else {
            movements.add(lines[i])
        }
    }
    return Input(
        map,
        movements.stream().reduce { a, b -> a + b }.get().toCharArray(),
        robot!!,
        Pair(mapHeigh, mapWidth)
    )
}

//down here, it's all just to produce a gif

private fun initPrint(): AnimatedGifEncoder {
    val gifEncoder = AnimatedGifEncoder()
    gifEncoder.setQuality(1)
    gifEncoder.start(FileOutputStream(File("render/day15.gif")))
    gifEncoder.setDelay(25)
    gifEncoder.setRepeat(0)
    return gifEncoder
}


private fun mapToString(mov: Movement, map: List<MapObject>, size: Pair<Int, Int>): String {
    var str = StringBuilder("")
    for (i in 0..<size.first) {
        val objectsInLine = map.stream().filter { it.pos.first == i }.toList()
        var j = 0
        while (j < size.second) {
            val obj = objectsInLine.stream().filter { it.pos.second == j }.findFirst()
            val toPrint = if (obj.isEmpty) "." else obj.get().symbol
            str.append(toPrint)
            j += toPrint.length
        }
        str.append("\n")
    }
    return str.toString().replace("@", "" + mov.ch)
}

private fun addFrame(gifEncoder: AnimatedGifEncoder, map: String, iteration: String) {
    val size = 9
    val font = Font("Monospaced", Font.PLAIN, size)

    val width = map.split("\n")[0].length * size
    val height = map.split("\n").size * size
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val g2d = image.createGraphics()

    g2d.color = Color.BLACK
    g2d.fillRect(0, 0, width, height)
    g2d.font = font
    g2d.color = Color.GREEN

    g2d.drawString(iteration, size, size)
    for ((row, line) in map.split("\n").withIndex()) {
        g2d.drawString(line, size, size * 2 + row * size)
    }
    g2d.dispose()
    gifEncoder.addFrame(image)
}


