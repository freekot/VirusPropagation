import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class Network {
	private int numberOfNodes;

	private int numberOfEdges;
	private RealMatrix adjacencyMatrix;
	private HashMap<Integer, Node> nodeMap;
	
	public int getNumberOfNodes() {
		return numberOfNodes;
	}



	public void setNumberOfNodes(int numberOfNodes) {
		this.numberOfNodes = numberOfNodes;
	}



	public int getNumberOfEdges() {
		return numberOfEdges;
	}



	public void setNumberOfEdges(int numberOfEdges) {
		this.numberOfEdges = numberOfEdges;
	}



	public RealMatrix getAdjacencyMatrix() {
		return adjacencyMatrix;
	}



	public void setAdjacencyMatrix(RealMatrix adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
	}



	public HashMap<Integer, Node> getNodeMap() {
		return nodeMap;
	}



	public void setNodeMap(HashMap<Integer, Node> nodeMap) {
		this.nodeMap = nodeMap;
	}



	
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
				first.setNodeInfected(false);
				first.addNeighbor(node2);
				nodeMap.put(node1, first);
			}else{
				first = nodeMap.get(node1);
				first.addNeighbor(node2);
				nodeMap.put(node1, first);
			}
			if(!nodeMap.containsKey(node2)) {
				second = new Node(node2);
				second.setNodeInfected(false);
				second.addNeighbor(node1);
				nodeMap.put(node2, second);
			}else{
				second = nodeMap.get(node2);
				second.addNeighbor(node1);
				nodeMap.put(node2, second);
			}
		}
		System.out.println("Number of edges read = "+numberOfLines);
		System.out.println("Number of edges ="+numberOfEdges);
		System.out.println(adjacencyMatrix.getEntry(0, 1));
		br.close();
	}
	
	public void storeNode(Node cur){
		nodeMap.put(cur.getNodeId(), cur);
	}
	
	public void printEigenvalues(){

		//RealMatrix matrix = MatrixUtils.createRealMatrix(mat);
		EigenDecomposition ed = new EigenDecomposition(adjacencyMatrix);
		double [] eigValues = ed.getRealEigenvalues();
		for (int i = 0; i< eigValues.length; i++){
			System.out.println("eigValue [" + i + "] = " + eigValues[i] );
		}
	}
	
	public void seedInfection(double frac, int num_imm){
		Random generator = new Random();
		Node randomValue;
		double num_inf = frac*(numberOfNodes + num_imm);
		for (int i = 0; i<num_inf; i++){
			
			Map.Entry[] entries = (Entry[]) nodeMap.entrySet().toArray();
			randomValue = (Node) entries[generator.nextInt(entries.length)].getValue();
			randomValue.setNodeInfected(true);
			nodeMap.put(randomValue.getNodeId(), randomValue);
		}
		
	}
	


}
