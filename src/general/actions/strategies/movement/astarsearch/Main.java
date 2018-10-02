package general.actions.strategies.movement.astarsearch;

import java.util.List;

import general.Vector;

/**
 * Test AStar Search
 */


public class Main {
	public static void main(String[] args) {
		int obstacleMap[][] = {
				{ 0, 1, 0, 0, 0 }, 
				{ 0, 0, 0, 0, 0 },
				{ 0, 1, 0, 1, 0 }, 
				{ 0, 1, 0, 1, 0 }, 
				{ 0, 1, 1, 1, 0 },
				{ 0, 1, 0, 0, 0 }, 
				{ 0, 0, 0, 1, 0 }};

		
		AStarGraph testGraph = new AStarGraph(obstacleMap[0].length, obstacleMap.length, obstacleMap);
		System.out.println("Initializing heuristic");
		AStarHeuristic heuristic = new AnyDirectionHeuristic();
		AStarSearch aStar = new AStarSearch(testGraph, heuristic);
		System.out.println("Calculating shortest path...");
		
		//ArrayList<Point> = is what gets returned 
		List<Vector> points = aStar.shortestPath();

		//Print out path for testing purposes
		for (Vector each : points) {
			System.out.print("(" + (int) each.getX() + ", " + (int) each.getY()  + ")");
			System.out.print(" -> ");
		}
		System.out.println();
	}
}