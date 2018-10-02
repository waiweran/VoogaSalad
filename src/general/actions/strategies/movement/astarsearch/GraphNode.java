package general.actions.strategies.movement.astarsearch;

import java.util.ArrayList;
import java.util.List;

import general.Id;
import general.Vector;
import general.entities.GameEntity;
/**
 * GraphNode for each GameEntity in current GameState 
 * @author Ashka Stephen
 */
public class GraphNode implements Comparable<GraphNode> {
	private AStarGraph graph;
	private Id myID;
	private GameEntity myEntity;
	private double distanceFromStart;
	private double distanceFromGoal;
    private boolean isStart;
    private boolean isEnd;
    private boolean isBarrier;
	private int xLocation;
	private int yLocation;
	private GraphNode prevNode;

	public GraphNode(GameEntity ge, int xLoc, int yLoc, AStarGraph myGraph ){
		this.myEntity = ge;
		this.isStart = false;
		this.isEnd = false;
		this.isBarrier = false;
		this.distanceFromStart = Integer.MAX_VALUE;
		this.distanceFromGoal = Integer.MIN_VALUE;
		this.xLocation = xLoc;
		this.yLocation = yLoc;
		this.graph = myGraph;
	}

	/**
	 * @param setID for the current GraphNode
	 */
	public void setGraphNodeID(Id setID) {
		myID = setID;}

	/**
	 * @return UUID of current GraphNode
	 */
	public Id getGraphNodeID(){
		return myID;}	

	/**
	 * @return Point of current GraphNode
	 */
	public Vector getPoint() {
		return new Vector(xLocation,yLocation);}

	/**
	 * @param dist Distance that needs to be traveled to reach the Goal
	 */
	public void setDistancefromGoal(double dist) {
		this.distanceFromGoal = dist;}

	/**
	 * @return get distance from the current Node to the goal
	 */
	public double getDistancefromGoal(){
		return distanceFromGoal;}

	/**
	 * @param dist from the start location to the current Node
	 */
	public void setDistanceFromStart(double dist) {
		this.distanceFromStart = dist;}

	/**
	 * @return get distance from the current Node to the goal
	 */
	public double getDistancefromStart(){
		return distanceFromStart;}

	/**
	 * @return boolean if the GraphNode is a barrier
	 */
	public boolean isBarrier() {
		return isBarrier;}

	/**
	 * Set barriers in the Graph such that search will avoid them
	 */
	public void setBarrier(boolean isObstical) {
		this.isBarrier = isObstical;}

	/**
	 * @return If the GraphNode is the starting location for the search
	 */
	public boolean isStart() {
		return isStart;}

	/**
	 * Set the start GraphNode location 
	 */
	public void setStart(boolean isStart) {
		this.isStart = isStart;}

	/**
	 * @return If the GraphNode is the ending location for the search
	 */
	public boolean isEnd() {
		return isEnd;}

	/**
	 * Set the end GraphNode location 
	 */
	public void setEnd(boolean isGoal) {
		this.isEnd = isGoal;}

	/**
	 * @param
	 * @return 
	 */
	public GameEntity getEntity(){
		return this.myEntity;}

	/**
	 * Gets the parent GraphNode
	 */
	public GraphNode getParentNode() {
		return prevNode;}

	/**
	 * Sets the parent GraphNode
	 */
	public void setParentNode(GraphNode thisNode) {
		this.prevNode =  thisNode;}

	/**
	 * @param
	 * @return 
	 */
	public int getX() {
		return this.xLocation;}

	/**
	 * @param
	 * @return 
	 */
	public int getY() {
		return this.yLocation;}

	/**
	 * @param
	 * @return 
	 */
	public List<GraphNode> getNeighbors(){
		ArrayList<GraphNode> neighbors = new ArrayList<GraphNode>();
		neighbors.add(graph.getGraphNode(xLocation, yLocation));
		int row = xLocation ; int col = yLocation;
		final int[] rowOffset= {0, 0, -1, 1, -1, -1, 1, 1}; 
		final int[] colOffset = {-1, 1, 0, 0, -1, 1, -1, 1};
		for (int i = 0; i < rowOffset.length; i++){
			int resultRow = row + rowOffset[i], resultCol = col + colOffset[i];
			if((graph.isValidPosition(resultRow, resultCol)) && !(graph.getGraphNode(resultRow, resultCol).isBarrier)){
				neighbors.add(graph.getGraphNode(resultRow, resultCol));}}
		return neighbors;}

	/**
	 * Checks if two nodes are equivalent
	 * @param node to be compared to
	 */
	public boolean equals(GraphNode node) {
		return (node.xLocation == xLocation) && (node.yLocation == yLocation);}

	/**
	 * Compares 2 GraphNodes to see which is closest to the goal
	 * @param node to be compared to
	 */
	public int compareTo(GraphNode node) {
		double thisTotalDistance = this.getDistancefromStart() + this.getDistancefromGoal();
		double otherTotalDistance = node.getDistancefromGoal() + node.getDistancefromStart();
		if (thisTotalDistance < otherTotalDistance) {
			return -1;
		} else if (thisTotalDistance > otherTotalDistance) {
			return 1;
		} else {
    		return 0;
    	}}}