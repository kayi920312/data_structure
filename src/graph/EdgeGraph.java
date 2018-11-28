package graph;

import java.util.Arrays;

/**
 * 边集数组类
 * 用于图的最小生成树 Kruskal 算法
 * @author kayi9
 *
 */
public class EdgeGraph {
	/**
	 * 顶点数
	 */
	private Integer vertexNum;
	
	/**
	 * 边数
	 */
	private Integer arcNum;
	
	/**
	 * 顶点数组
	 */
	private String[] vertex;
	
	/**
	 * 边集数组
	 */
	private EdgeType[] edge;
	
	public EdgeGraph() {}
	
	public EdgeGraph(String[] vertex, EdgeType[] edge) {
		this.vertex = vertex;
		this.edge = edge;
		this.vertexNum = vertex.length;
		this.arcNum = edge.length;
	}

	/**
	 * kruskal 算法
	 * @param edgeGraph 边集数组类
	 */
	public void kruskal(EdgeGraph edgeGraph) {
		Integer[] parent = new Integer[edgeGraph.getVertexNum()];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = -1;
		}
		
		for (int num = 0, i = 0; i < edgeGraph.arcNum; i++) {
			Integer vex1 = findRoot(parent, edgeGraph.getEdge()[i].getFrom());
			Integer vex2 = findRoot(parent, edgeGraph.getEdge()[i].getTo());
			if (!vex1.equals(vex2)) {
				System.out.println(vex1 + ", " + vex2);
				parent[vex2] = vex1;
				num++;
				System.out.println("num: " + num);
				if (num == edgeGraph.getVertexNum() - 1) break;
			}
		}
		
		System.out.println(Arrays.toString(parent));
	}
	
	/**
	 * 找出顶点 vertex 的双亲
	 * @param parent
	 * @param vertex
	 * @return
	 */
	public Integer findRoot(Integer[] parent, Integer vertex) {
		Integer t = vertex;
		if (parent[t] > -1) {
			t = findRoot(parent, parent[t]);
		} 
		return t;
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

	public String[] getVertex() {
		return vertex;
	}

	public void setVertex(String[] vertex) {
		this.vertex = vertex;
	}

	public EdgeType[] getEdge() {
		return edge;
	}

	public void setEdge(EdgeType[] edge) {
		this.edge = edge;
	}

	@Override
	public String toString() {
		return "Kruskal [vertexNum=" + vertexNum + ", arcNum=" + arcNum + ", vertex=" + Arrays.toString(vertex)
				+ ", edge=" + Arrays.toString(edge) + "]";
	}
	
	public static void main(String[] args) {
		String[] vertex = {"v0", "v1", "v2", "v3", "v4", "v5"};
		EdgeType[] edge = {
			new EdgeType(1, 4, 12),
			new EdgeType(2, 3, 17),
			new EdgeType(0, 5, 19),
			new EdgeType(2, 5, 25),
			new EdgeType(3, 5, 25),
			new EdgeType(4, 5, 26),
			new EdgeType(0, 1, 34),
			new EdgeType(3, 4, 38),
			new EdgeType(0, 2, 46)
		};
		EdgeGraph edgeGraph = new EdgeGraph(vertex, edge);
//		System.out.println(kruskal.toString());
		edgeGraph.kruskal(edgeGraph);
	}
}

/**
 * 边集
 * @author kayi9
 *
 */
class EdgeType {
	/**
	 * 边的源顶点
	 */
	private Integer from;
	
	/**
	 * 边的目的顶点
	 */
	private Integer to;
	
	/**
	 * 边的权重
	 */
	private Integer weight;
	
	public EdgeType() {}
	
	/**
	 * 
	 * @param from 边的源顶点
	 * @param to 边的目的顶点
	 * @param weight 边的权重
	 */
	public EdgeType(Integer from, Integer to, Integer weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "EdgeType [from=" + from + ", to=" + to + ", weight=" + weight + "]";
	}
	
}