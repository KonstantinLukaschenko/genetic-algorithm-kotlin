import java.lang.Math.random

/**
 * Implementation of a basic genetic algorithm, that is capable to generate solutions for optimization and search
 * problems relying on bio-inspired operations such as crossover, mutation and selection.
 *
 * @param T the type of an individual.
 * @property population a collection of individuals to start optimization.
 * @property score a function which scores the fitness of an individual. Higher fitness is better.
 * @property cross a function which implements the crossover of two individuals resulting in a child.
 * @property mutate a function which mutates a given individual.
 * @property select a function which implements a selection strategy of an individuals from the population.
 */
class GeneticAlgorithm<T>(
        var population: Collection<T>,
        val score: (individual: T) -> Double,
        val cross: (parents: Pair<T, T>) -> T,
        val mutate: (individual: T) -> T,
        val select: (scoredPopulation: Collection<Pair<Double, T>>) -> T) {

    /**
     * Returns the best individual after the given number of optimization epochs.
     *
     * @param epochs number of optimization epochs.
     * @property mutationProbability a value between 0 and 1, which defines the mutation probability of each child.
     */
    fun run(epochs: Int = 1000, mutationProbability: Double = 0.1): T {
        var scoredPopulation = population.map { Pair(score(it), it) }.sortedByDescending { it.first }

        for (i in 0..epochs)
            scoredPopulation = scoredPopulation
                    .map { Pair(select(scoredPopulation), select(scoredPopulation)) }
                    .map { cross(it)}
                    .map { mutate(it) }
                    .map { if (random() <= mutationProbability) mutate(it) else it}
                    .map { Pair(score(it), it) }
                    .sortedByDescending { it.first }

        return scoredPopulation.first().second
    }
}