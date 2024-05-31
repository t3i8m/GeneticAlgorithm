package com;



/**
 * Implementation of the {@link PrintInformation} interface that handles the printing of genetic algorithm 
 * generation information to the standard output. This class provides detailed output that can be used for 
 * monitoring or debugging purposes during the execution of a genetic algorithm.
 */
public class PrintInformationRealization implements PrintInformation {

    /**
     * 
     * Prints detailed information about the best individual of a specific generation. The output includes 
     * the generation number, the phenotype representation of the best individual, and its fitness value.
     * This method allows users to quickly understand the progress and effectiveness of the genetic 
     * algorithm at each generation.
     * @param <Individual>
     * @param {@link Individual}
     * 
     * @param generation the generation number of the best individual.
     * @param bestIndividual the best individual of the specified generation.
     */
    public void printTheBestOfGeneration(int generation, Individual bestIndividual) {
        System.out.println("The BEST in the generation " + generation + ": " + bestIndividual.genoToPhenotype() + 
                           " Fitness: " + bestIndividual.getFitness());
    }

    /**
     * Prints the entire population of a specific generation. Each individual's phenotype and fitness
     * are displayed, providing a comprehensive view of the population's diversity and fitness levels
     * at that stage of the algorithm. This method is particularly useful for debugging and analyzing 
     * the genetic diversity of the population.
     * 
     * @param population an array of {@link Individual} objects representing the entire population
     *                   at the specified generation.
     * @param generation the generation number corresponding to the population being printed.
     */
    @Override
    public void printPopulation(Individual[] population, int generation) {
        // Заголовок с номером поколения
        System.out.printf("\n\nPopulation at Generation %d:%n", generation);
        
        // Check if the population is empty or null
        if (population == null || population.length == 0) {
            System.out.println("No individuals in population.");
            return;
        }

        // Print column headers
        System.out.printf("%-20s %-10s%n", "Phenotype", "Fitness");

        // Print a separating line for clarity
        System.out.println("------------------------------------------");

        // Print each individual's phenotype and fitness
        for (Individual individual : population) {
            // Format and align the output to the left
            String phenotype = individual.genoToPhenotype();
            double fitness = individual.getFitness();
            System.out.printf("%-20s %-10.2f%n", phenotype, fitness);
        }

    }
    


}
