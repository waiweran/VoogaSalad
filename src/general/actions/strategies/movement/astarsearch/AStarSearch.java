
package general.actions.strategies.movement.astarsearch;
/**
 * AStarSearch Algorithm Implementation
 * @author Ashka Stephen
 */

import java.util.ArrayList;
import java.util.List;

import general.Vector;
import general.attributes.Location;

public class AStarSearch {
	private AStarHeuristic heuristic;
	private AStarGraph map;
	private ArrayList<GraphNode> closedList;
	private ArrayList<GraphNode> openList;
	private ArrayList<Vector> shortestPath;
	
	public AStarSearch(AStarGraph graph, AStarHeuristic heuristic) {
		this.map = graph;
		this.heuristic = heuristic;
		closedList = new ArrayList<GraphNode>();
		openList = new ArrayList<GraphNode>();
	}

	public List<Vector> shortestPath(){
		int thisX = (int) map.getItemA().getAttribute("Location", Location.class).getValue().getX();
		int thisY = (int) map.getItemA().getAttribute("Location", Location.class).getValue().getY();
		int goalX = (int) map.getItemB().getReadOnlyAttribute("Location", Location.class).getValue().getX();
		int goalY = (int) map.getItemB().getReadOnlyAttribute("Location", Location.class).getValue().getY();

		map.setStartPoint(thisX, thisY);
		map.setGoalPoint(goalX, goalY);
		//TEST
		/*		if ((map.getGraphNode(goalX, goalY).getEntity().getAttribute("tag", Tag.class).equals("obstacle") || (map.getGraphNode(goalX, goalY).getEntity().getAttribute("tag", Tag.class).equals("collectible")))){
			return null;
		}*/

		map.getStartNode().setDistanceFromStart(0);
		closedList.clear();
		openList.clear();
		openList.add(map.getStartNode()); 

		while(openList.size() != 0 ){
			GraphNode current = openList.get(0);
			if(current.getGraphNodeID() == map.getGoalNode().getGraphNodeID()) {
				System.out.println("AStarSearch: Found target node " + current.getGraphNodeID());
				return newPath(current);
			}
			//TEST
			/*if((current.getPoint().getX() == map.getGoalNode().getPoint().getX()) &&(current.getPoint().getY() == map.getGoalNode().getPoint().getY()) ) {
				System.out.println("AStarSearch: Found target node ");
				return newPath(current);
        	}*/

			openList.remove(current);
			closedList.add(current);
			for(GraphNode neighbor : current.getNeighbors()){
				if(closedList.contains(neighbor)){
					continue;
				}
				if (!neighbor.isBarrier()) {
					double neighborDistanceFromStart = (current.getDistancefromStart() + map.distBetween(current, neighbor));
					if(!openList.contains(neighbor)) {
						neighbor.setParentNode(current);
						openList.add(neighbor);} 
					if (neighborDistanceFromStart < current.getDistancefromStart()) {
						neighbor.setParentNode(current);
						neighbor.setDistanceFromStart(neighborDistanceFromStart);
						neighbor.setDistancefromGoal(heuristic.getEstDistance(neighbor.getPoint(), map.getGoalNodePoint()));
					}
				}
			}
		}
		return null;
	}


	/**
	 * After target is found, construct the path
	 */
	private ArrayList<Vector> newPath(GraphNode curr) {		
		ArrayList<Vector> path = new ArrayList<Vector>();
		while(curr.getParentNode() != null) {
			path.add(0,curr.getPoint());
			curr = curr.getParentNode();
		}
		this.shortestPath = path;
		return path;
	}
}
