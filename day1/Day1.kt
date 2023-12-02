package day1

class CalibrationSolver(private val calibrationDocument: List<String>) {

    fun calculateSumOfCalibrationValues(): Int = calibrationDocument.sumOf { it.extractCalibrationValue() }

    private fun String.extractCalibrationValue(): Int {
        val firstDigit = findFirstDigit(this)
        val lastDigit = findLastDigit(this.reversed())
        return "$firstDigit$lastDigit".toIntOrNull() ?: 0
    }

    private fun findFirstDigit(str: String): Char {
        val spelledOutDigits = mapOf(
            "one" to '1',
            "two" to '2',
            "three" to '3',
            "four" to '4',
            "five" to '5',
            "six" to '6',
            "seven" to '7',
            "eight" to '8',
            "nine" to '9'
        )

        val numericalPosition = str.indexOfFirst { it.isDigit() }
        val spelledOutPositions = spelledOutDigits.keys
            .mapNotNull { key ->
                val position = str.indexOf(key)
                if (position != -1) Pair(key, position)
                else null
            }
            .minByOrNull { it.second }

        return when {
            numericalPosition != -1 && (spelledOutPositions == null || numericalPosition < spelledOutPositions.second) ->
                str[numericalPosition]
            spelledOutPositions != null ->
                spelledOutDigits[spelledOutPositions.first] ?: '0'
            else -> '0'
        }
    }

    private fun findLastDigit(reversedStr: String): Char {
        val spelledOutDigits = mapOf(
            "eno" to '1',
            "owt" to '2',
            "eerht" to '3',
            "ruof" to '4',
            "evif" to '5',
            "xis" to '6',
            "neves" to '7',
            "thgie" to '8',
            "enin" to '9'
        )

        val numericalPosition = reversedStr.indexOfFirst { it.isDigit() }
        val spelledOutPositions = spelledOutDigits.keys
            .mapNotNull { key ->
                val position = reversedStr.indexOf(key)
                if (position != -1) Pair(key, position)
                else null
            }
            .minByOrNull { it.second }

        return when {
            numericalPosition != -1 && (spelledOutPositions == null || numericalPosition < spelledOutPositions.second) ->
                reversedStr[numericalPosition]
            spelledOutPositions != null ->
                spelledOutDigits[spelledOutPositions.first] ?: '0'
            else -> '0'
        }
    }
}

fun main() {
    val calibrationInput = Utils.readFile("day1Input.txt")
    val calibrationSolver = CalibrationSolver(calibrationInput)
    val sumOfCalibrationValues = calibrationSolver.calculateSumOfCalibrationValues()

    println("Sum of calibration values: $sumOfCalibrationValues")
}
