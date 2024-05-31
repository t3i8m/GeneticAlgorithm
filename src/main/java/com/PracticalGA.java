package com;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import org.jfree.data.xy.XYSeries;


/**
 * Some very basic stuff to get you started. It shows basically how each
 * chromosome is built.
 * 
 * @author Jo Stevens
 * @version 1.0, 14 Nov 2008
 * 
 * @author Alard Roebroeck
 * @version 1.1, 12 Dec 2012
 * 
 */
public class PracticalGA {

    static final String TARGET = "HELLO WORLD";
    static char[] alphabet = new char[27];
    static Random generator = new Random(System.currentTimeMillis());

    /**
     * Calculates the fitness of a given chromosome based on how closely it matches the TARGET string.
     *
     * @param chromosome the chromosome to evaluate
     * @return the fitness score as a proportion of correct characters.
     */
    public static double calculateFitness(char[] chromosome) {
        int score = 0;
        for (int i = 0; i < chromosome.length; i++) {
            if (chromosome[i] == TARGET.charAt(i)) {
                score++;
            }
        }
        return (double) score / TARGET.length();
    }

    /**
     * Performs tournament selection from the given population to find the best individual based on fitness.
     *
     * @param population the array of individuals to select from
     * @param size the number of individuals to consider in each tournament
     * @return the best individual from the tournament
     */
    public static Individual tournamentSelection(Individual[] population, int size) {
        Individual best = population[generator.nextInt(population.length)];
        for (int i = 0; i < size; i++) {
            int randomIndex = generator.nextInt(population.length);
            Individual individual = population[randomIndex];
            if (individual.getFitness() > best.getFitness()) {
                best = individual;
            }
        }
        return best;
    }

    /**
     * Combines genes from two parents to create a new individual using a single crossover point.
     *
     * @param parent1 the first parent individual
     * @param parent2 the second parent individual
     * @return the new individual created from parents
     */
    public static Individual crossover(Individual parent1, Individual parent2) {
        char[] genom1 = parent1.getChromosome();
        char[] genom2 = parent2.getChromosome();
        char[] newGenom = new char[genom1.length];
        int crossPoint = generator.nextInt(genom1.length);
        for (int i = 0; i < crossPoint; i++) {
            newGenom[i] = genom1[i];
        }
        for (int i = crossPoint; i < genom2.length; i++) {
            newGenom[i] = genom2[i];
        }
        return new Individual(newGenom);
    }

    /**
     * Mutates a chromosome by randomly altering its genes at a given mutation rate.
     *
     * @param chromosome the chromosome to mutate
     * @param rate the mutation rate (probability of a gene mutating)
     */
    public static void mutate(char[] chromosome, double rate) {
        for (int i = 0; i < chromosome.length; i++) {
            if (generator.nextDouble() < rate) {
                chromosome[i] = alphabet[generator.nextInt(alphabet.length)];
            }
        }
    }

    /**
     * Main method to run the genetic algorithm simulation.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        // Manual input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter population size (e.g., 100): ");
        int popSize = scanner.nextInt(); 
        System.out.print("Enter mutation rate as a decimal (e.g., 0,01 for 1%): ");
        double mutationRate = scanner.nextDouble(); 
        System.out.print("Enter tournament size (e.g., half of the population size): ");
        int tournamentSize = scanner.nextInt(); 
        System.out.print("Enter maximum number of generations (e.g., 1000): ");
        int maxGenerations = scanner.nextInt(); 

        // Initialization of parameters and population
        // int popSize = 1000; // size of the population
        // double mutationRate = 0.01; // 1% chance of mutation
        // int tournamentSize = popSize / 2; // Tournament size for selection
        // int maxGenerations = 1000;

        int generation = 0; // generation counter
        Individual bestIndividual = null; // best individual at the current generation
        FindBest bestIndivid = new FindBestIndividual(); // finding the best individ in the population
        PrintInformation printInformation = new PrintInformationRealization(); // for the printing information
        Individual[] initialPopulation = new Individual[popSize]; // array of the population of the individuals
        XYSeries series = new XYSeries("Fitness Over Generations");

        // Initialize the alphabet
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet[c - 'A'] = c;
        }
        alphabet[26] = ' ';

        // Initialize the population with random characters
        for (int i = 0; i < popSize; i++) {
            char[] tempChromosome = new char[TARGET.length()];
            for (int j = 0; j < TARGET.length(); j++) {
                tempChromosome[j] = alphabet[generator.nextInt(alphabet.length)]; // Choose a random letter from the alphabet
            }
            initialPopulation[i] = new Individual(tempChromosome);
        }

        // Genetic algorithm loop
        while (generation < maxGenerations) {
            // Fitness evaluation
            for (Individual individual : initialPopulation) {
                individual.setFitness(calculateFitness(individual.getChromosome()));
            }

            // Population and best individual printing
            printInformation.printPopulation(initialPopulation, generation);
            bestIndividual = bestIndivid.findBestIndividual(initialPopulation);
            printInformation.printTheBestOfGeneration(generation, bestIndividual);
            series.add(generation, bestIndividual.getFitness());
            // Check for optimal solution
            if (bestIndividual.getFitness() == 1.0) {
                System.out.println("Optimal solution found in generation " + generation);
                break;
            }

            // Selection, crossover, and mutation to form new population
            Individual[] newPopulation = new Individual[popSize];
            for (int i = 0; i < popSize; i++) {
                Individual parent1 = tournamentSelection(initialPopulation, tournamentSize);
                Individual parent2 = tournamentSelection(initialPopulation, tournamentSize);
                Individual offspring = crossover(parent1, parent2);
                mutate(offspring.getChromosome(), mutationRate);
                newPopulation[i] = offspring;
            }


            initialPopulation = newPopulation;
            generation++;
        }
        ChartHelper.displayChart(series, String.format("Genetic Algorithm Performance (mutation rate %.2f, population size %d)", mutationRate, popSize));

    
		/**
		 * Some general programming remarks and hints:
		 * - A crucial point is to set each individual's fitness (by the setFitness() method) before sorting. When is an individual fit? 
		 * 	How do you encode that into a double (between 0 and 1)?
		 * - Decide when to stop, that is: when the algorithm has converged. And make sure you  terminate your loop when it does.
		 * - print the whole population after convergence and print the number of generations it took to converge.
		 * - print lots of output (especially if things go wrong).
		 * - work in an orderly and structured fashion (use tabs, use methods,..)
		 * - DONT'T make everything private. This will only complicate things. Keep variables local if possible
		 * - A common error are mistakes against pass-by-reference (this means that you pass the 
		 * 	address of an object, not a copy of the object to the method). There is a deepclone method included in the
		 *  Individual class.Use it!
		 * - You can compare your chromosome and your target string, using for eg. TARGET.charAt(i) == ...
		 * - Check your integers and doubles (eg. don't use ints for double divisions).
		 */
    }
}
