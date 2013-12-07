import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Simulator {
	
	static String netFileName;
	static String inputFileName;
	static int numberOfSteps;
	static double beta;
	static double delta;
	static double numberOfVaccines;
	private static BufferedReader in;
	public static int numberOfInfected;
	private static final int NUM_IMM_NODES = 200;
	private static final double INF_FRACTION = 0.1;
	private static int numToInfect;
	static int[] infected;
	static int[] avinfected;
	
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
		//long start = System.currentTimeMillis();
		//net.printEigenvalues();
		//long finish = System.currentTimeMillis();
		//long time = finish - start;
		//System.out.println("It took " + time/1000 + " seconds to get eigenvalues");
		infected = new int[numberOfSteps+1];
		avinfected = new int[numberOfSteps+1];
		
		int reps = 10;
		for (int i = 0; i<reps; i++){
			numberOfInfected=0;
			
			net.seedInfection(numToInfect);
			infected[0]=numberOfInfected;
			avinfected[0]=infected[0];
			System.out.println("Number of infected nodes is " + numberOfInfected);
			
			runSimulation(net);
			for (int j = 1; j<avinfected.length; j++){
				avinfected[j]=infected[j]/reps + avinfected[j];
			}
			net.reset();
		}
		
		writeInfected(avinfected);
		
		

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
		
		//Read in the number of steps
		line = in.readLine();
		tokens = line.split(" ");
		numberOfSteps = Integer.parseInt(tokens[2]);
		
		//Read number of infected nodes
		line = in.readLine();
		tokens = line.split(" ");
		numToInfect = Integer.parseInt(tokens[2]);
		
		in.close();
	}
	
	private static void runSimulation(Network net){
		
		
		
		double multiProb;
		double prob = Math.random();
		
		Node currentNode;
		
		for (int i = 0; i<numberOfSteps; i++){
			int countingNodes =0;
			int countingInfectedNodes =0;
			HashMap<Integer, Node> current = net.getNodeMap();
			Iterator it  = current.entrySet().iterator();
			
			while (it.hasNext()){
				countingNodes++;
				
				
				Map.Entry pairs = (Map.Entry)it.next();
				currentNode = (Node)pairs.getValue();
				//System.out.println("I am a node " + currentNode.getNodeId() + " and my neighbors are " + currentNode.getNeighborIds().toString());
				
				if (currentNode.isNodeInfected()){
					countingInfectedNodes++;
					//heal or not
					prob = Math.random();
					if (prob<delta){
						currentNode.setNodeInfected(false);
						numberOfInfected--;
						net.storeNode(currentNode);
					}
					//System.out.println("I am a node " + currentNode.getNodeId() + " and I infected #####################");
				}else{
					int num = currentNode.neighborIds.size();
					int id =0;
					int infCount =0;
					for (int n = 0; n<num;n++ ){
						id = currentNode.neighborIds.get(n);
						
						if( net.getNodeMap().get(id).isNodeInfected() == true){
							infCount++;
						}
					}
					//System.out.println("--------------------number of infected neighbors is = " + infCount);
					prob = Math.random();
					multiProb = 1-Math.pow((1-beta),infCount);
					if (prob < multiProb){
						currentNode.setNodeInfected(true);
						numberOfInfected++;
						net.storeNode(currentNode);
					}
					//System.out.println("I am a node " + currentNode.getNodeId() + " and I healthy");
					
					//1. check neighbors
					//2. get infected or not
				}
				
				


			} //end of while
			//System.out.println("Number of infected nodes is " + numberOfInfected + " The actual number of infected nodes was " + countingInfectedNodes);
			infected[i+1]= numberOfInfected;
			//System.out.println("number of nodes is "  + countingNodes);
			
			
			
			
			
		}//end of for simulation steps
		
		
		
		

	}
	
	public static void writeInfected(int[] infected){
		try{
			FileWriter fstream = new FileWriter("output.txt", true);
			BufferedWriter togo = new BufferedWriter(fstream);
			for (int i = 0; i< infected.length; i++){
				
				togo.write(infected[i]+ "\n"   );
			}

			
			togo.close();
			
		}catch (Exception e){//Catch exception if any
			  System.err.println("Error1: " + e.getMessage());
		}
	}
	

	

}
