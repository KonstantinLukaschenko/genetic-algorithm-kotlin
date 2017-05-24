import java.lang.Math.random

fun main(args : Array<String>) {

    val population = (1..100).map { (1..10).map { if (random() < 0.5) 0 else 1 } }

    val algorithm = GeneticAlgorithm(
            population,
            score = { it.sum().toDouble() },
            cross = { it.first.mapIndexed { index, i -> if (random() < 0.5) i else it.second[index] } },
            mutate = { it.map { if (random() < 0.9) it else if (random() < 0.5) 0 else 1 } },
            select = ::fitnessProportionateSelection
    )

    val result = algorithm.run()

    print("Best individual: ")
    result.forEach { print(it) }
}