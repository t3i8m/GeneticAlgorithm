
# Genetic Algorithm: Modeling Evolution

This repository contains the implementation of a Genetic Algorithm (GA) designed to evolve a random string into the target string **"HELLO WORLD"**. The project also includes an analysis of various parameters influencing the algorithm's efficiency. Paper - https://drive.google.com/file/d/1ggh4R8mjI9Wj5kvNzXB2FtNWfBa3BVOU/view?usp=sharing

---

## Overview

The Genetic Algorithm simulates evolution to optimize the string-matching process. The algorithm initializes with a population of random strings, evaluates their fitness, and applies genetic operators (selection, crossover, mutation) to produce successive generations.

---

## Algorithm Steps

1. **Population Initialization**
   - Generate a population of random strings.
   - Example: `"REBLO RGCYD"`

2. **Fitness Evaluation**
   - Compare each string to the target `"HELLO WORLD"`.
   - Fitness is calculated as

3. **Selection**
   - Use **Tournament Selection** to choose parents

4. **Crossover**
   - Combine parents to create offspring
5. **Mutation**
   - Introduce random changes for diversity
6. **Repeat**
   - Create new generations until a perfect match is found or a limit is reached.

---

## Parameter Experiments

1. **Population Size**
   - **Small Population:** Takes more generations to converge due to limited diversity.
   - **Large Population:** Finds solutions faster with a broader gene pool.

   Example Observations:
   - Small Population (100): ~70 Generations
   - Large Population (1000): ~6 Generations

2. **Mutation Rate**
   - **Low Rate (1%):** Faster convergence due to stable transmission of traits.
   - **High Rate (50%):** Slower convergence due to excessive randomness.

3. **Excluding Crossover**
   - Without crossover, traits cannot propagate effectively.
   - Even with a high mutation rate (30%), solutions take significantly longer.

4. **Excluding Mutation**
   - Algorithm fails without mutation as it gets stuck at suboptimal solutions.
   - Mutation introduces randomness needed to escape local optima.

---


print(f"Best solution: {best_solution}")
