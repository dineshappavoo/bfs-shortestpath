/**
 * 
 */
package com.bfs.shortestpath.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;


/**
 * @author Dany
 *
 */
public class BFSShortestPath {



	public static Graph constructGraph()
	{		
		int noOfVertices,noOfEdges;
		Graph graph=null;

		int u, v;
		Scanner scanner=new Scanner(System.in);
		while(scanner.hasNext())
		{
			noOfVertices=scanner.nextInt();
			noOfEdges=scanner.nextInt();


			graph=new Graph(noOfVertices);
			for(int i=0;i<noOfEdges;i++)
			{
				u=scanner.nextInt();
				v=scanner.nextInt();
				graph.addEdge(u, v);
				graph.addEdge(v, u);
			}
			break;
		}
		//graph.printGraph();
		return graph;
	}

	/**
	 * doBFSShortestPath(G, s, t):
	 * 	L <- Empty
	 *  visited <- Empty
	 *  Q <- Empty
	 *  S <- Empty
	 *  
	 *  Q <- add(Q, s)
	 *  S <- add(S, s)
	 *  visited[s] <- true
	 *  while(!Empty(Q))
	 *  	u <- poll(Q)
	 *  	for each vertex v in Adj[u]
	 *  		if visited[v]!=true
	 *  			Q <- add(Q,v)
	 *  			S <- add(S,v)
	 *  			visited[v] = true
	 *  			if u == dest
	 *  				break
	 *  currentSrc=t
	 *  L <- add(L,t)
	 *  while(!Empty(S))
	 *  	u <= poll(S)
	 *  	if isNeighbor(G,currentSrc,u)
	 *  		L <- add(L, u)
	 *  		currentSrc = node;
	 *  		if node == source
	 *  			break;
	 *  return L
	 *  		
	 * 
	 * 
	 * @param graph
	 * @param source
	 * @param dest
	 * @return
	 */

	public static ArrayList<Integer> doBFSShortestPath(Graph graph, int source, int dest)
	{
		ArrayList<Integer> shortestPathList = new ArrayList<Integer>();
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();

		if (source == dest)
			return null;
		Queue<Integer> queue = new LinkedList<Integer>();
		Stack<Integer> pathStack = new Stack<Integer>();

		queue.add(source);
		pathStack.add(source);
		visited.put(source, true);

		while(!queue.isEmpty())
		{
			int u = queue.poll();
			ArrayList<Integer> adjList = graph.getOutEdges(u);

			for(int v : adjList)
			{
				if(!visited.containsKey(v))
				{
					queue.add(v);
					visited.put(v, true);
					pathStack.add(v);
					if(u == dest)
						break;
				}
			}
		}


		//To find the path
		int node, currentSrc=dest;
		shortestPathList.add(dest);
		while(!pathStack.isEmpty())
		{
			node = pathStack.pop();
			if(graph.isNeighbor(currentSrc, node))
			{
				shortestPathList.add(node);
				currentSrc = node;
				if(node == source)
					break;
			}
		}

		return shortestPathList;
	}


	/**
	 * 
	 * Method to find the shortest path
	 */
	public static void findShortestPath()
	{

		Graph graph = constructGraph();
		Scanner scanner = new Scanner(System.in);
		int source = scanner.nextInt();
		int dest = scanner.nextInt();

		ArrayList<Integer> shortestPathList =  doBFSShortestPath(graph, source, dest);

		System.out.print("[ ");
		for(int node : shortestPathList)
		{
			System.out.print(node+" ");
		}
		System.out.print("]");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		BFSShortestPath.findShortestPath();
	}

}
