# Genetic Algorithm in Kotlin

This is a basic implementation of a [Genetic Algorithm](https://en.wikipedia.org/wiki/Genetic_algorithm) in 
[Kotlin](https://kotlinlang.org), that is capable to generate solutions for optimization
and search problems relying on bio-inspired operations such as crossover, mutation and selection.

## Example Usage
The following example showcases a simple usage of the algorithm. It will create a random population of genes, 
represented as a list with a uniform distribution of 0s (disease genome) and 1s (health genome) and evolve the 
genes towards a healthy population.

```kotlin
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
```

### Output
```
Best individual: 1111111111
Process finished with exit code 0
```