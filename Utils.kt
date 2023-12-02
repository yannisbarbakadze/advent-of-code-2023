
import java.io.File

object Utils {

    /**
     * Returns a new BufferedReader for reading the contents of the file.
     * Once we have a BufferedReader, we can read all the lines in it
     */
    fun readFile(fileName: String): List<String> =
        File("/Users/ybarmpakantze/AndroidStudioProjects/advent-of-code-2023/assets/$fileName").bufferedReader().readLines()

    fun readText(fileName: String): String =
        File("/Users/ybarmpakantze/AndroidStudioProjects/advent-of-code-2023/assets/$fileName").bufferedReader().readText()
}