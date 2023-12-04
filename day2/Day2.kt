package day2

class CubeConundrumSolver(private val games: List<String>) {

    fun solve(): Int {
        val colors = listOf("red", "green", "blue")
        val bag = listOf(12, 13, 14)
        var sumOfPossibleGames = 0
        var currentGameId = 0

        for (game in games) {
            currentGameId++
            val setsOfCubes = game.substringAfter("Game $currentGameId: ").split(";")
            var isGamePossible = true

            for (cubesSet in setsOfCubes) {
                val cubes = cubesSet.trim().split(",")

                for (cube in cubes) {
                    val (countStr, color) = cube.trim().split(" ")
                    val count = countStr.toInt()
                    val colorIndex = colors.indexOf(color)

                    if (count > bag[colorIndex]) {
                        isGamePossible = false
                        break
                    }
                }

                if (!isGamePossible) {
                    break
                }
            }

            if (isGamePossible) {
                sumOfPossibleGames += currentGameId
            }
        }

        return sumOfPossibleGames
    }
}

fun main() {
    val input = Utils.readFile("day2Input.txt")
    val cubeConundrumSolver = CubeConundrumSolver(input)
    val result = cubeConundrumSolver.solve()
    println("Sum of IDs of possible games: $result")
}

