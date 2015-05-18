// main class
// test-run class for addition Genetic Algorithm

public class TestRun {
	private static final int NUM_CHROMOSOMES = 1000; // chromosomes in the GA simulation
	private static final int NUM_ITERATIONS = 1000; // number of GA iterations
	
	public static void main(String[] args) throws InterruptedException {
		GeneticAlgorithm testGA = new GeneticAlgorithm(NUM_CHROMOSOMES);
		
		// initial check
		testGA.printChromosomes();
		
		// Genetic Algorithm iterations
		for (int i=0; i<NUM_ITERATIONS; i++){
			testGA.generateOffspring();
			testGA.mutate();
		}
		
		// final sort and printing of results
		testGA.sortByFitnessValue();
		testGA.printChromosomes();
	}
}
