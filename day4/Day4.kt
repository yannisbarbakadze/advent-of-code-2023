package day4

import kotlin.math.pow

class ScratchcardSolver(private val cards: List<String>) {

    fun calculatePoints(): Int {
        var totalPoints = 0

        for ((index, card) in cards.withIndex()) {
            // Split the card into parts
            val parts = card.split(" | ")

            if (parts.size != 2) {
                println("Error processing card at index $index: $card")
                continue
            }

            val (winningNumbers, yourNumbers) = parts

            val winningNumbersList = winningNumbers.split(" ").mapNotNull(String::toIntOrNull)
            val yourNumbersList = yourNumbers.split(" ").mapNotNull(String::toIntOrNull)

            var points = 0
            for (number in yourNumbersList) {
                if (number in winningNumbersList) {
                    points++
                }
            }

            val cardPoints = 2.0.pow(points.toDouble() - 1).toInt()
            totalPoints += cardPoints
        }

        return totalPoints
    }

    fun calculateTotalScratchcards(): Int {
        val scratchcards = MutableList(cards.size) { 1 }

        for (i in cards.indices) {
            val (winningNumbers, yourNumbers) = cards[i].split(" | ")
            val winningNumbersList = winningNumbers.split(" ").mapNotNull(String::toIntOrNull)
            val yourNumbersList = yourNumbers.split(" ").mapNotNull(String::toIntOrNull)

            val points = yourNumbersList.count { it in winningNumbersList }

            if (points > 0) {
                for (j in 1 until points + 1) {
                    if (i + j < cards.size) {
                        scratchcards[i + j] += scratchcards[i]
                    }
                }
            }
        }

        return scratchcards.sum()
    }
}

fun main() {
    val input = Utils.readFile("day4Input.txt")

    val scratchcardSolver = ScratchcardSolver(input)

    // Part 1
    val resultPart1 = scratchcardSolver.calculatePoints()
    println("Total points for the scratchcards (Part 1): $resultPart1")

    // Part 2
    val resultPart2 = scratchcardSolver.calculateTotalScratchcards()
    println("Total scratchcards obtained (Part 2): $resultPart2")
}

