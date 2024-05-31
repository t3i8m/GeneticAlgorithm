package com;
/**
 * The FindBest interface provides a method for finding the best individual 
 * in an array of Individuals. "Best" is context-dependent and should be 
 * defined based on specific implementation details such as fitness evaluation.
 * 
 * This interface is designed to be implemented by classes that specialize 
 * in various ways of determining the most optimal or fit individual within 
 * a population, potentially using different criteria or algorithms.
 */
public interface FindBest {

    /**
     * Finds and returns the best individual from the provided population of individuals.
     * The criteria for "best" are implementation-specific and should be clearly 
     * documented by the implementing class. 
     *
     * @param population An array of {@link Individual} objects representing the population 
     *                   from which the best individual is to be selected.
     * @return The best individual from the population based on the implemented criteria, 
     *         or null if the population is empty or null.
     */
    Individual findBestIndividual(Individual[] population);
}
