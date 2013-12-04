import java.util.ArrayList;



public class Node {
	private int nodeId;
	private boolean isInfected;
	ArrayList<Integer> neighborIds;
	
	
	
	public ArrayList<Integer> getNeighborIds() {
		return neighborIds;
	}

	public void setNeighborIds(ArrayList<Integer> neighborIds) {
		this.neighborIds = neighborIds;
	}

	public Node(int nodeId){
		this.nodeId = nodeId;
		neighborIds = new ArrayList<Integer>();
	}
	
	public int getNodeId(){
		return nodeId;
	}
	
	public boolean isNodeInfected(){
		return isInfected;
	}
	
	public void setNodeInfected(boolean isInfected){
		this.isInfected = isInfected;
	}
	
	public void addNeighbor(int id){
		neighborIds.add(id);
	}



}
