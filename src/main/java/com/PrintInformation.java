package com;



/**
 * The PrintInformation interface defines methods for printing details about genetic algorithm
 * generations. It is intended to help visualize or debug the progress of genetic algorithm simulations
 * by providing detailed outputs of the generation's best individuals and the entire population.
 */
public interface PrintInformation {

    /**
     * Prints information about the best individual of a specific generation. This method is intended 
     * to provide quick insights into the performance of the genetic algorithm at each generation, 
     * highlighting the best results achieved.
     * @param {@link Individual}
     * 
     * @param generation the generation number to which the best individual belongs.
     * @param bestIndividual the best individual of that generation, determined by some fitness criteria.
     */
     void printTheBestOfGeneration(int generation, Individual bestIndividual);

    /**
     * Prints the entire population of a specific generation. This method is useful for debugging
     * or detailed analysis, providing a snapshot of the entire population at a particular point
     * in the genetic algorithm's execution.
     * @param <Individual>
     * 
     * @param population an array of {@link Individual} objects representing the entire population
     *                   at the specified generation.
     * @param generation the generation number corresponding to the population being printed.
     */
     void printPopulation(Individual[] population, int generation);
}
