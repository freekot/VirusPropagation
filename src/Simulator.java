
public class Simulator {
	
	static String netFileName;
	static String inputFileName;
	int numberOfSteps;
	double beta;
	double detla;
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//input.txt is the only argument and should contain
		// 1. input of network file name
		// 2. probabilities beta and delta
		// 3. to run simulation?
		// 4. the number of time steps
		inputFileName = args[0];

		readInput(inputFileName);
		
		
		Network net = new Network (netFileName);
		runSimulation(net);

	}
	
	private static void readInput(String inputFileName){
		
	}
	
	private static void runSimulation(Network net){
		
	}

}
