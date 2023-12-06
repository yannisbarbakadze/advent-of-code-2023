package day6

fun main() {
    val lines = Utils.readFile("day6Input.txt")

    val timeValues = lines[0].trim().split("\\s+".toRegex()).drop(1).map { it.toLong() }
    val distanceValues = lines[1].trim().split("\\s+".toRegex()).drop(1).map { it.toLong() }

    val numberOfRaces = timeValues.size

    for (raceNumber in 1..numberOfRaces) {
        val waysToWin = calculateWaysToBeatRecord(timeValues[raceNumber - 1], distanceValues[raceNumber - 1])
        println("Race $raceNumber: $waysToWin ways to beat the record")
    }

    val totalWays = (1..numberOfRaces)
        .map { calculateWaysToBeatRecord(timeValues[it - 1], distanceValues[it - 1]) }
        .reduce { acc, ways -> acc * ways }

    println("Total ways: $totalWays")
}

fun calculateWaysToBeatRecord(duration: Long, recordDistance: Long): Long {
    var waysToWin = 0L

    for (holdTime in 0L until duration) {
        val remainingTime = duration - holdTime
        val distance = holdTime * remainingTime

        if (distance > recordDistance) {
            waysToWin++
        }
    }

    return waysToWin
}

