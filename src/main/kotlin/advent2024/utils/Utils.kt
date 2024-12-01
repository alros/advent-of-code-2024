package advent2024.utils

import java.io.InputStream

class Utils {

    companion object {
        fun readFile(folderName: String, fileName: String): String {
            val inputStream: InputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("$folderName/$fileName")
            return inputStream.bufferedReader().use { it.readText() }
        }
    }
}