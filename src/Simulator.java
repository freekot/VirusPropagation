import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Simulator {
	
	static String netFileName;
	static String inputFileName;
	static int numberOfSteps;
	static double beta;
	static double delta;
	static double numberOfVaccines;
	private static BufferedReader in;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//input.txt is the only argument and should contain
		// 1. input of network file name
		// 2. probabilities beta and delta
		// 3. to run simulation?
		// 4. the number of time steps
		inputFileName = args[0];
		in = new BufferedReader(new FileReader(inputFileName));
		readInput(inputFileName);
		
		//Populate the Adjacency Matrix and create "Nodes"
		Network net = new Network (netFileName);
		
		//runSimulation(net);

	}
	
	private static void readInput(String inputFileName) throws IOException {
		
		//Read in the static.network file name
		String line = in.readLine();
		String[] tokens = line.split(" ");
		netFileName = tokens[2];
		System.out.println(netFileName);
		
		//Read in transmission and healing probabilities
		line = in.readLine();
		tokens = line.split(" ");
		beta = Double.parseDouble(tokens[2]);
		line = in.readLine();
		tokens = line.split(" ");
		delta = Double.parseDouble(tokens[2]);	
		
		//Read in the number of vaccines
		line = in.readLine();
		tokens = line.split(" ");
		numberOfVaccines = Double.parseDouble(tokens[2]);
		
		in.close();
	}
	
	private static void runSimulation(Network net){
		
	}

}
