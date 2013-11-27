
public class Network {
	private int numberOfNodes;
	private int numberOfEdges;
	private int[][] adjacencyMatrix;
	
	public Network(String filename){
		readNetwork(filename);
		adjacencyMatrix = new int[numberOfNodes][numberOfNodes];
	}
	
	public void readNetwork(String filename){
		
	}

}
