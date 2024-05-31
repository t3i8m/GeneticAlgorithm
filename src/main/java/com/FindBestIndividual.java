package com;


/**
 * Implements the {@link FindBest} interface to find the best individual in a population of {@link Individual}s.
 * This implementation uses a heap sort algorithm to sort the population and then selects the best individual 
 * based on the sort order, assuming the best individual comes first after sorting.
 * 
 * The class also includes a commented-out alternative method for finding the best individual by iterating 
 * through the population and comparing each individual's fitness, which might be more efficient if only 
 * the best individual is needed without sorting the entire population.
 */

public class FindBestIndividual implements FindBest {

    /**
     * Finds the best individual from the given population by first sorting the population using a heap sort
     * and then returning the first individual from the sorted array.
     * This method assumes that the sorting puts the best individual at the beginning of the array.
     *
     * @param population An array of {@link Individual} objects representing the population from which
     *                   the best individual is to be selected.
     * @return The best individual from the sorted population, or null if the population is empty.
     */
    public Individual findBestIndividual(Individual[] population) {
        HeapSort.sort(population);
        return population[0];
    }

    /**
     * An alternative method for finding the best individual by directly comparing the fitness of each 
     * individual in the population. This method does not sort the entire population, which can be more
     * efficient if sorting is not required for other purposes.
     *
     * This method is commented out because the current implementation uses heap sort for demonstration
     * purposes. To use this method, uncomment it and comment out the current implementation above.
     *
     * @param population An array of {@link Individual} objects representing the population from which
     *                   the best individual is to be selected.
     * @return The best individual based on fitness comparison, or null if the population is empty.
     */
    // public Individual findBestIndividual(Individual[] population) {
    //     Individual best = population[0];
    //     for (Individual individual : population) {
    //         if (individual.getFitness() > best.getFitness()) {
    //             best = individual;
    //         }
    //     }
    //     return best;
    // }
}
