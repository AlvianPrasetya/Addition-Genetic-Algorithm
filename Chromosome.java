// a chromosome contains multiple genes constituting the problem
// in this case, if the problem is a+b+c = 30, a chromosome contains
// genes a, b, and c

import java.util.*;

public class Chromosome implements Comparable<Chromosome> {
	private static final int TARGET_VALUE = 30; // the target value a+b+c+... should achieve
	
	// attributes
	private int _numGenes;
	private Gene[] _genomes;
	private int _fitnessValue;
	
	// default Chromosome constructor
	public Chromosome(int numGenes){
		_numGenes = numGenes;
		_genomes = new Gene[numGenes];
		Random rand = new Random();
		
		// initialize Gene(s)
		for (int i=0; i<numGenes; i++){
			_genomes[i] = new Gene(rand.nextInt(TARGET_VALUE));
		}
		
		evaluateFitness();
	}
	
	// num of genes accessor
	public int getNumGenes(){
		return _numGenes;
	}
	
	// gene modifier
	public void setGeneAtId(int id, Gene newGene){
		_genomes[id] = newGene;
	}
	
	// gene accessor
	public Gene getGeneById(int id){
		return _genomes[id];
	}
	
	// fitness value accessor
	public int getFitnessValue(){
		return _fitnessValue;
	}
	
	// target value (const) accessor, for use from another class
	public int getTargetValue(){
		return TARGET_VALUE;
	}
	
	// evaluates the fitness of a specified Chromosome
	public void evaluateFitness(){
		int totalValue = 0;
		int targetValue = TARGET_VALUE;
		
		for (int i=0; i<_numGenes; i++){
			totalValue += _genomes[i].getInfo();
		}
		
		_fitnessValue =  (_numGenes - 1) * targetValue - Math.abs(targetValue - totalValue);
	}
	
	public void printGenes(){
		System.out.print("Genes in this chromosome:");
		for (int i=0; i<_numGenes; i++){
			System.out.print(" " + getGeneById(i).getInfo());
		}
		System.out.println("; Fitness value: " + _fitnessValue);
	}
	
	@Override
	// ascending fitness value comparator -> for use in Chromosome sorting
	public int compareTo(Chromosome compareChromosome){
		int compareFitness = ((Chromosome) compareChromosome).getFitnessValue();
		
		return this._fitnessValue - compareFitness; 
	}
}
