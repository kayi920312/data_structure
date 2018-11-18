package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图 邻接表
 * @author kayi9
 *
 */
public class ALGraph {
	
	/**
	 * 顶点数
	 */
	private Integer vertexNum;
	
	/**
	 * 边数
	 */
	private Integer arcNum;
	
	/**
	 * 存放顶点的数组
	 */
	private VertexNode[] adjList;
	
	/**
	 * 顶点访问标记数组
	 */
	private Integer[] visited;
	
	public ALGraph() {}
	
	/**
	 * 参数构造邻接表
	 * @param vertexData 顶点数据
	 * @param arcData 边数据
	 * @param vertexNum 顶点数
	 * @param arcNum 边数
	 */
	public ALGraph(String[] vertexData, Integer[][] arcData, Integer vertexNum, Integer arcNum) {
		
		this.vertexNum = vertexNum;
		this.arcNum = arcNum;
		this.adjList = new VertexNode[vertexNum];
		this.visited = new Integer[vertexNum];
		
		// 初始化顶点表
		VertexNode vertexTemp = null;
		for (int i = 0; i < vertexNum; i++) {
			vertexTemp = new VertexNode(vertexData[i], null);
			adjList[i] = vertexTemp;
			visited[i] = 0;
		}
		
		// 初始化边表
		ArcNode arcTemp = null;
		for (int i = 0; i < arcNum; i++) {
			arcTemp = new ArcNode(arcData[i][1], adjList[arcData[i][0]].getFirstEdge());
			adjList[arcData[i][0]].setFirstEdge(arcTemp);
		}
		
	}
	
	/**
	 * 深度优先遍历
	 * @param vertexIndex 开始遍历的顶点的下标
	 */
	public void DFSTraverse(Integer vertexIndex) {
		
		System.out.print(adjList[vertexIndex].getVertex() + " ");
		visited[vertexIndex] = 1;
		ArcNode arcNode = adjList[vertexIndex].getFirstEdge();
		while (arcNode != null) {
			if (visited[arcNode.getAdjvex()] == 0) {
				DFSTraverse(arcNode.getAdjvex());
			}
			arcNode = arcNode.getNext();
		}
	}
	
	/**
	 * 广度优先遍历
	 * @param vertexIndex 开始遍历的顶点的下标
	 */
	public void BFSTaverse(Integer vertexIndex) {
		Queue<Integer> queue = new LinkedList<Integer>();
		System.out.print(adjList[vertexIndex].getVertex());
		visited[vertexIndex] = 1;
		queue.add(vertexIndex);
		while (!queue.isEmpty()) {
			Integer temp = queue.poll();
			ArcNode arcNode = adjList[temp].getFirstEdge();
			while (arcNode != null) {
				if (visited[arcNode.getAdjvex()] == 0) {
					System.out.print(", " + adjList[arcNode.getAdjvex()].getVertex());
					visited[arcNode.getAdjvex()] = 1;
					queue.add(arcNode.getAdjvex());
				}
				arcNode = arcNode.getNext();
			}
		}
	}
	
	public Integer getVertexNum() {
		return vertexNum;
	}

	public void setVertexNum(Integer vertexNum) {
		this.vertexNum = vertexNum;
	}

	public Integer getArcNum() {
		return arcNum;
	}

	public void setArcNum(Integer arcNum) {
		this.arcNum = arcNum;
	}

	public VertexNode[] getAdjList() {
		return adjList;
	}

	public void setAdjList(VertexNode[] adjList) {
		this.adjList = adjList;
	}

	public Integer[] getVisited() {
		return visited;
	}

	public void setVisited(Integer[] visited) {
		this.visited = visited;
	}

	@Override
	public String toString() {
		return "ALGraph ["
				+ "vertexNum=" + vertexNum + ", "
				+ "arcNum=" + arcNum + ", "
				+ "adjList=" + Arrays.toString(adjList) + ", "
				+ "visited=" + Arrays.toString(visited) + "]";
	}
	
	public static void main(String[] args) {
		String[] vertexData = {"v0", "v1", "v2", "v3"};
		Integer[][] arcData = {
				{0, 1}, {0, 3}, 
				{1, 0}, {1, 3}, {1, 2},
				{2, 1},
				{3, 0}, {3, 1}
		};
		ALGraph alGraph = new ALGraph(vertexData, arcData, vertexData.length, arcData.length);
//		System.out.println(alGraph.toString());
		alGraph.BFSTaverse(3);
	}
}

/**
 * 邻接表 边表结点
 * @author kayi9
 *
 */
class ArcNode {
	
	/**
	 * 邻接点域，存顶点的邻接点在顶点表中的下标
	 */
	private Integer adjvex;
	
	/**
	 * 边表中下一个结点
	 */
	private ArcNode next;
	
	public ArcNode() {}
	
	/**
	 * 参数构造边表结点
	 * @param adjvex 邻接点域
	 * @param next 边表中下一个结点
	 */
	public ArcNode(Integer adjvex, ArcNode next) {
		this.adjvex = adjvex;
		this.next = next;
	}

	public Integer getAdjvex() {
		return adjvex;
	}

	public void setAdjvex(Integer adjvex) {
		this.adjvex = adjvex;
	}

	public ArcNode getNext() {
		return next;
	}

	public void setNext(ArcNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "ArcNode [adjvex=" + adjvex + ", next=" + next + "]";
	}
	
}

/**
 * 邻接表 顶点表
 * @author kayi9
 *
 */
class VertexNode {
	
	/**
	 * 数据域，顶点的数据信息
	 */
	private String vertex;
	
	/**
	 * 顶点的第一个邻接点，即边表中的第一个结点
	 */
	private ArcNode firstEdge;
	
	public VertexNode() {}
	
	/**
	 * 参数构造顶点结点
	 * @param vertex 数据域，顶点的数据信息
	 * @param firstEdge 顶点的第一个邻接点，即边表中的第一个结点
	 */
	public VertexNode(String vertex, ArcNode firstEdge) {
		this.vertex = vertex;
		this.firstEdge = firstEdge;
	}

	public String getVertex() {
		return vertex;
	}

	public void setVertex(String vertex) {
		this.vertex = vertex;
	}

	public ArcNode getFirstEdge() {
		return firstEdge;
	}

	public void setFirstEdge(ArcNode firstEdge) {
		this.firstEdge = firstEdge;
	}

	@Override
	public String toString() {
		return "VertexNode [vertex=" + vertex + ", firstEdge=" + firstEdge + "]";
	}
	
}
