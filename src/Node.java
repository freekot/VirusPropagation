
public class Node {
	private int nodeId;
	private boolean isInfected;
	
	public Node(int nodeId){
		this.nodeId = nodeId;
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

}
