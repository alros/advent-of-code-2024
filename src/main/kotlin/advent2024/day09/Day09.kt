package advent2024.day09

class DiskBlock(var fileId: Int, var fileSize: Int) {
    override fun toString(): String {
        return "$fileId / $fileSize"
    }
}

fun solveStep1(input: String): Long {
    val disk = convertInput(input)
    val compactedDisk = compact(disk)
    return checkSum(compactedDisk)
}

fun solveStep2(input: String): Long {
    val disk = convertInput(input)
    val compactedDisk = compactPreserveFiles(disk)
    return checkSum(compactedDisk)
}

private fun convertInput(input: String): MutableList<DiskBlock> {
    val inputAsInt = input.trim().split("").stream()
        .filter { it.isNotEmpty() }
        .mapToInt { it.toInt() }
        .toArray()
    var isFile = true
    var curFile = 0
    val disk = mutableListOf<DiskBlock>()
    for (i in inputAsInt) {
        for (j in 0..<i) {
            disk.add(DiskBlock(if (isFile) curFile else -1, i))
        }
        if (isFile) {
            curFile++
        }
        isFile = !isFile
    }
    return disk
}

private fun compact(disk: MutableList<DiskBlock>): MutableList<DiskBlock> {
    var start = 0
    var end = disk.size - 1
    while (start < end) {
        if (disk[end].fileId >= 0 && disk[start].fileId == -1) {
            disk[start].fileId = disk[end].fileId
            disk[end].fileId = -1
            end--
        }
        if (disk[start].fileId != -1) {
            start++
        }
        if (disk[end].fileId == -1) {
            end--
        }
    }
    return disk
}

private fun compactPreserveFiles(disk: MutableList<DiskBlock>): MutableList<DiskBlock> {
    var end = findLastFile(disk)
    while (end != -1) {
        var start = findFirstSpace(disk)
        var nextFileSize = disk[end].fileSize
        while (start < end) {
            if (disk[start].fileSize >= disk[end].fileSize) {
                for (j in 0..<disk[end].fileSize) {
                    disk[start + j].fileId = disk[end - j].fileId
                    disk[start + j].fileSize = disk[end - j].fileSize
                    disk[end - j].fileId = -1
                }
                fixEmptySpaces(disk)
                break
            } else {
                start = findFirstSpace(disk, start)
            }
        }
        end = findLastFile(disk, end - nextFileSize + 1)
    }

    return disk
}

private fun findFirstSpace(disk: List<DiskBlock>, startFrom: Int = -1): Int {
    for (j in startFrom + 1..<disk.size) {
        if (disk[j].fileId == -1) {
            return j
        }
    }
    return -1
}

private fun findLastFile(disk: List<DiskBlock>, startFrom: Int = disk.size): Int {
    for (j in startFrom - 1 downTo 0) {
        if (disk[j].fileId != -1) {
            return j
        }
    }
    return -1
}

private fun fixEmptySpaces(disk: MutableList<DiskBlock>) {
    var i = 0
    while (i < disk.size) {
        if (disk[i].fileId == -1) {
            var count = 0
            for (j in i..<disk.size) {
                if (disk[j].fileId == -1) {
                    count++
                } else {
                    break
                }
            }
            for (j in i..<i + count) {
                disk[j].fileSize = count
            }
            i += count
            continue
        }
        i++
    }
}

private fun checkSum(compactedDisk: List<DiskBlock>): Long {
    var sum: Long = 0
    for (i in compactedDisk.indices) {
        if (compactedDisk[i].fileId != -1) {
            sum += compactedDisk[i].fileId * i
        }
    }
    return sum
}
