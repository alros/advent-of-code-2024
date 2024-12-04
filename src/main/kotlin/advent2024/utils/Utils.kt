package advent2024.utils

import java.io.InputStream

class Utils {

    companion object {
        fun readFile(folderName: String, fileName: String): String {
            val inputStream: InputStream =
                ClassLoader.getSystemClassLoader().getResourceAsStream("$folderName/$fileName")
                    ?: throw RuntimeException("file $folderName/$fileName not found")
            return inputStream.bufferedReader().use { it.readText() }
        }
    }
}