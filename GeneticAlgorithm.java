// a genetic algorithm contains several chromosomes to solve the problem
// in this case, if the problem is a+b+c = 30,
// this class contains several combinations of chromosome(a, b, c) used to solve the problem

import java.util.*;

public class GeneticAlgorithm {
	private static final int NUM_GENES = 5; // genes in each chromosome
	private static final int MUTATION_RATE = 1000; // per-part-million
	
	// attributes
	private int _numChromosomes;
	private Chromosome[] _testChromosome;
	
	// default GA constructor
	public GeneticAlgorithm(int numChromosomes){
		_numChromosomes = numChromosomes;
		_testChromosome = new Chromosome[numChromosomes];
		
		// initialize Chromosome(s)
		for (int i=0; i<numChromosomes; i++){
			_testChromosome[i] = new Chromosome(NUM_GENES);
		}
	}

	// chromosome accessor
	public Chromosome getChromosomeById(int id){
		return _testChromosome[id];
	}
	
	// total fitness value function, adds up all chromosomes' fitness values
	public int getTotalFitnessValue(){
		int totalFitness = 0;
		
		for (int i=0; i<_numChromosomes; i++){
			totalFitness += getChromosomeById(i).getFitnessValue();
		}
		
		return totalFitness;
	}
	
	// sort the chromosomes by their respective fitness
	public void sortByFitnessValue(){
		Arrays.sort(_testChromosome);
	}
	
	// GA step #1: generate offsprings based on roulette wheel method
	public void generateOffspring(){
		Chromosome[] tempOffspring = new Chromosome[_numChromosomes];
		Random rand = new Random();
		
		for (int i=0; i<_numChromosomes; i++){
			Chromosome parent1 = getRandomChromosome();
			Chromosome parent2 = getRandomChromosome();
			int breakPoint = rand.nextInt(NUM_GENES+1);
			
			//System.out.println("breakPoint = " + breakPoint);
			
			// crossover
			tempOffspring[i] = crossBreed(parent1, parent2, breakPoint);
			
			// re-evaluate fitness value
			tempOffspring[i].evaluateFitness();
		}
		
		_testChromosome = tempOffspring;
	}
	
	// roulette wheel method
	public Chromosome getRandomChromosome(){
		int prefixRoulette = 0;
		Random rand = new Random();
		
		int randomRouletteNum = rand.nextInt(getTotalFitnessValue());
		for (int i=0; i<_numChromosomes; i++){
			if (prefixRoulette <= randomRouletteNum && randomRouletteNum < (prefixRoulette + _testChromosome[i].getFitnessValue())){
				return _testChromosome[i];
			}
			
			prefixRoulette += _testChromosome[i].getFitnessValue();
		}
		
		return null; // return null for error-checking
	}
	
	// GA step #2: cross-breed 2 parents, resulting in a single offspring
	// pre-condition: parent1 and parent2 must contain the same number of genes
	public Chromosome crossBreed(Chromosome parent1, Chromosome parent2, int breakPoint){
		Chromosome offspring = new Chromosome(NUM_GENES);
		
		for (int i=0; i<NUM_GENES; i++){
			if (i < breakPoint){
				offspring.setGeneAtId(i, parent1.getGeneById(i));
			}
			else {
				offspring.setGeneAtId(i, parent2.getGeneById(i));
			}
		}
		
		return offspring;
	}
	
	// GA step #3: allow for mutations to occur
	public void mutate(){
		Random rand = new Random();
		
		for (int i=0; i<_numChromosomes; i++){
			for (int j=0; j<NUM_GENES; j++){
				if ((rand.nextInt(1000000)) < MUTATION_RATE){
					_testChromosome[i].setGeneAtId(j, new Gene(rand.nextInt(_testChromosome[i].getTargetValue()+1)));
					_testChromosome[i].evaluateFitness(); // re-evaluate fitness value
				}
			}
		}
	}
	
	public void printChromosomes(){
		for (int i=0; i<_numChromosomes; i++){
			getChromosomeById(i).printGenes();
		}
	}
}
