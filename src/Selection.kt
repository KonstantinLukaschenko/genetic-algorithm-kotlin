import java.lang.Math.random

/**
 * Selects an individual randomly from the population.
 */
fun <T> randomSelection(scoredPopulation: Collection<Pair<Double, T>>): T {
    return scoredPopulation.elementAt((random() * scoredPopulation.size.toDouble()).toInt()).second
}

/**
 * Selects an individual randomly from fittest 50% of the population.
 */
fun <T> truncationSelection(scoredPopulation: Collection<Pair<Double, T>>): T {
    return scoredPopulation.elementAt((random() * scoredPopulation.size.toDouble() * 0.5).toInt()).second
}

/**
 * Selects an individual with a probability proportional to it's score. Also known as roulette wheel selection.
 */
fun <T> fitnessProportionateSelection(scoredPopulation: Collection<Pair<Double, T>>): T {
    var value = scoredPopulation.sumByDouble { it.first } * random()

    for ((fitness, individual) in scoredPopulation) {
        value -= fitness
        if (value <= 0) return individual
    }

    return scoredPopulation.last().second
}
