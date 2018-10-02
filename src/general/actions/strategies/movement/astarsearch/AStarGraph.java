package general.actions.strategies.movement.astarsearch;

import java.util.ArrayList;
import java.util.List;
/**
 * Takes current state and creates a Graph based on the current locations of all Game Entities.
 * @author Ashka Stephen
 */

import general.Vector;
import general.attributes.Location;
import general.entities.GameEntity;
import general.entities.ReadOnlyEntity;
import general.storage.GameState;

public class AStarGraph {	
	GameState state;	
	private int myMin_X;
	private int myMin_Y;
	private int myMax_X;
	private int myMax_Y;
	
	private ArrayList<ArrayList<GraphNode>> map;
    private int xLocationStart = 0;
    private int yLocationStart = 0;
    private int xLocationEnd = 0;
    private int yLocationEnd = 0;
    
    private GameEntity startEntity;
    private ReadOnlyEntity endEntity;
    private List<ReadOnlyEntity> allEntities;
    
    //TEST 
    private int[][] obs;
    
    /**
     * Default Constructor
     * Item A moves towards item B
     */
    public AStarGraph(List<ReadOnlyEntity> allEntities, GameEntity itemA, ReadOnlyEntity readOnlyEntity){
    	initGraphCreation(itemA, readOnlyEntity);
    	setNodes(allEntities);
    	this.startEntity = itemA; 
    	this.endEntity = readOnlyEntity; 
    	this.allEntities = allEntities; 
    }

    //testing
    public AStarGraph(int width, int height, List<ReadOnlyEntity> allEntities){
    	this.myMax_X = width;
    	this.myMax_Y = height;
    	//initGraphCreation();
    	setNodes(allEntities);
    }
    
    public GameEntity getItemA(){
		return startEntity;
    }

    
    public ReadOnlyEntity getItemB(){
		return endEntity;
    }
    /**
     * FOR TEST PURPOSES
     */
    public AStarGraph(int width, int height, int[][] obstacles){
    	this.myMax_X = width;
    	this.myMax_Y = height;
    	this.obs = obstacles;
    	//initGraphCreation();
    }

	/**
	 * Creates a graph of empty nodes and barriers based on the input
	 * TODO: revise for GameEntity implementation 
	 * @param
	 * @param
	 */
	private void initGraphCreation(GameEntity itemA, ReadOnlyEntity readOnlyEntity){
		map = new ArrayList<ArrayList<GraphNode>>();
		for (int x = myMin_X; x < myMax_X; x++) {
			map.add(new ArrayList<GraphNode>());
			for (int y = myMin_Y; y < myMax_Y; y++) {
				GraphNode node = new GraphNode(null, x,y, this);
				setObstacle();
				//TESTING purposes
				/*try{
					//node.setBarrier(obs[y][x]==1);
				} catch(Exception e) {
					System.out.println("Not valid obstacle location");
				}*/
				map.get(x).add(node);
			}}}

	/**
	 * Setting settings of all the Nodes
	 */
	private void setObstacle() {
		for(ReadOnlyEntity currEntity : this.allEntities){
			//get the node at the location of each entity
			GraphNode set = map.get((int) currEntity.getReadOnlyAttribute("Location", Location.class).getValue().getX()).get((int)currEntity.getReadOnlyAttribute("Location", Location.class).getValue().getY());
			set.setBarrier(!(currEntity.equals(startEntity)) || (currEntity.equals(endEntity)));
		}
	}


	/**
	 * Sets the GraphNodes to hold GameEntity information
	 */
	private void setNodes(List<ReadOnlyEntity> allEntities){
		//setting the size of the GraphNodes based on
		double x_min = Integer.MAX_VALUE;
		double y_min = Integer.MAX_VALUE;
		double x_max = Integer.MIN_VALUE;		
		double y_max = Integer.MIN_VALUE;		

		//getting greatest and least values for all sides
		for (ReadOnlyEntity curr: allEntities){
			//y u do this
			double xVal = curr.getReadOnlyAttribute("Location", Location.class).getValue().getX();
			double yVal = curr.getReadOnlyAttribute("Location", Location.class).getValue().getY();

			//better code doe
			if(xVal < x_min){ x_min = xVal;}
			if (xVal >  x_max){ x_max = xVal;}
			if (yVal < y_min){ y_min = yVal;}
			if (yVal > y_max){ y_max = yVal;}

			//should i be casting doe?
			this.myMin_X = (int) x_min;
			this.myMin_Y = (int) y_min;
			this.myMax_X = (int) x_max;
			this.myMax_Y = (int) y_max;
		}
	}

	/**
	 * Checks whether the position specified by (row, col) and wont won't cause ArrayOutOfBoundsException
	 * Assumption: each row of myGrid has the same number of columns
	 * @return true if the position is valid
	 */
	public boolean isValidPosition(int row, int col){
		return (!rowOutOfBounds(row)) && (!colOutOfBounds(col));}

	/**
	 * @param Row the integer representation of the row number
	 * @return true if row is out of bounds
	 */
	protected boolean rowOutOfBounds(int row){
		return ((row >= map.size()) || (row < 0));}

	/**
	 * @param Col the integer representation of the column number
	 * @return true if column is out of bounds
	 */
	protected boolean colOutOfBounds(int col){
		return (col >= map.get(0).size()) || (col < 0);}

	/**
	 * @return GraphNode located at point X,Y on the AStarGraph
	 */
	public GraphNode getGraphNode(int x, int y){
		return map.get(x).get(y);}
	
	/**
	 * @return all GraphNodes in the map
	 */
	public List<? extends List<GraphNode>> getGraphNodes() {
		return map;}

	/**
	 * @return Point of Goal GraphNode location
	 */
	public Vector getGoalNodePoint(){
		return new Vector(xLocationEnd, yLocationEnd);}

	/**
	 * Distance between 2 nodes
	 * @param firstNode original
	 * @param secondNode neighboring node 
	 * Distance is 1 if there is one "edge"connecting the nodes (i.e. same row or column)
	 */
	public double distBetween(GraphNode firstNode, GraphNode secondNode) {
		if (firstNode.getX() == secondNode.getX() || firstNode.getY() == secondNode.getY()){
			return 1;
		} else { 
			return Math.sqrt(2);}}

	/**
	 * Sets old starting position (if existing) to be false and resets starting node
	 */
	public void setStartPoint(int thisX, int thisY) {
		map.get(xLocationStart).get(yLocationStart).setStart(false);
		map.get(thisX).get(thisY).setStart(true);
		xLocationStart = thisX;
		yLocationStart = thisY;}

	/**
	 * Sets old goal (if existing) to be false and resets goal
	 */
	public void setGoalPoint(int thisX, int thisY) {
		map.get(xLocationEnd).get(yLocationEnd).setEnd(false);
		map.get(thisX).get(thisY).setEnd(true);
		xLocationEnd = thisX;
		yLocationEnd = thisY;}

	/**
	 * @return GraphNode StartNode the "follower" item
	 */
	public GraphNode getStartNode() {
		return map.get(xLocationStart).get(yLocationStart);}

	/**
	 * @return GraphNode GoalNode the Node which represents the item to be followed
	 */
	public GraphNode getGoalNode() {
		return map.get(xLocationEnd).get(yLocationEnd);}

	/**
	 * Resets all valid points and recreates graph
	 */
	public void clear() {
		xLocationStart = 0;
		yLocationStart = 0;
		xLocationEnd = 0;
		yLocationEnd = 0;
		initGraphCreation(this.startEntity, this.endEntity); 
	}}