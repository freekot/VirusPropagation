import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class Network {
	private int numberOfNodes;
	private int numberOfEdges;
	private RealMatrix adjacencyMatrix;
	private HashMap<Integer, Node> nodeMap;
	
	public Network(String filename) throws IOException{
		nodeMap = new HashMap<Integer, Node>();
		readNetwork(filename);
	}
	
	public void readNetwork(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		String line = br.readLine();
		String[] tokens = line.split(" ");
		numberOfNodes = Integer.parseInt(tokens[0]);
		numberOfEdges = Integer.parseInt(tokens[1]);
		adjacencyMatrix = new Array2DRowRealMatrix(numberOfNodes, numberOfNodes);
		int numberOfLines = 0;
		while((line=br.readLine()) != null) {
			numberOfLines++;
			System.out.println("Reading line : "+line);
			tokens = line.split(" ");
			int node1 = Integer.parseInt(tokens[0]);
			int node2 = Integer.parseInt(tokens[1]);
			adjacencyMatrix.setEntry(node1, node2, 1);
			adjacencyMatrix.setEntry(node2, node1, 1);
			Node first;
			Node second;
			if(!nodeMap.containsKey(node1)) {
				first = new Node(node1);
				nodeMap.put(node1, first);
			}
			if(!nodeMap.containsKey(node2)) {
				second = new Node(node2);
				nodeMap.put(node2, second);
			}
		}
		System.out.println("Number of edges read = "+numberOfLines);
		System.out.println("Number of edges ="+numberOfEdges);
		System.out.println(adjacencyMatrix.getEntry(0, 1));
		br.close();
	}

}
