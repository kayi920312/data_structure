package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 邻接矩阵
 * @author kayi9
 *
 */
public class MGraph {
	
	/**
	 * 顶点数据数组
	 */
	private String[] vertex;
	
	/**
	 * 顶点访问标记数组
	 */
	private Integer[] visited;
	
	/**
	 * 边数据二维数组
	 */
	private Integer[][] arc;
	
	/**
	 * 顶点数
	 */
	private Integer vertexNum;
	
	/**
	 * 边数
	 */
	private Integer arcNum;
	
	public MGraph() {}
	
	/**
	 * 参数构造邻接矩阵
	 * @param vertex 顶点数据数组
	 * @param arc 边数据二维数组
	 * @param vertexNum 顶点数
	 * @param arcNum 边数
	 */
	public MGraph(String[] vertex, Integer[][] arc, Integer vertexNum, Integer arcNum) {
		this.vertex = vertex;
		this.arc = arc;
		this.vertexNum = vertexNum;
		this.arcNum = arcNum;
		this.visited = new Integer[vertexNum];
		// 初始化为全部未访问
		for (int i = 0; i < vertexNum; i++) {
			visited[i] = 0;
		}
	}

	/**
	 * 深度遍历
	 * @param vertexIndex 开始遍历的顶点的下标
	 */
	public void DFSTraverse(Integer vertexIndex) {
		System.out.println(vertex[vertexIndex]);
		visited[vertexIndex] = 1; 
		for (int i = 0; i < vertexNum; i++) {
			if (arc[vertexIndex][i] == 1 && visited[i] == 0) {
				DFSTraverse(i);
			}
		}
	}
	
	/**
	 * 广度遍历
	 * @param vertexIndex 开始遍历的顶点的下标
	 */
	public void BFSTraverse(Integer vertexIndex) {
		Queue<Integer> queue = new LinkedList<Integer>();
		System.out.println(vertex[vertexIndex]);
		visited[vertexIndex] = 1;
		queue.add(vertexIndex);
		Integer temp = null;
		while (!queue.isEmpty()) {
			temp = queue.poll();
			for (int i = 0; i < vertexNum; i++) {
				if (arc[temp][i] == 1 && visited[i] == 0) {
					System.out.println(vertex[i]);
					visited[i] = 1;
					queue.add(i);
				}
			}
		}
	}
	
	public String[] getVertex() {
		return vertex;
	}
	
	public void setVertex(String[] vertex) {
		this.vertex = vertex;
	}

	public Integer[][] getArc() {
		return arc;
	}

	public void setArc(Integer[][] arc) {
		this.arc = arc;
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

	@Override
	public String toString() {
		return "MGraph [vertex=" + Arrays.toString(vertex) + ", arc=" + Arrays.toString(arc) + ", vertexNum="
				+ vertexNum + ", arcNum=" + arcNum + "]";
	}
	
	public static void main(String[] args) {
		String[] vertexData = {"v0", "v1", "v2", "v3"};
		Integer[][] arcData = {
				{0, 1, 0, 1},
				{1, 0, 1, 1},
				{0, 1, 0, 0},
				{1, 1, 0, 0}
		};
		MGraph mGraph = new MGraph(vertexData, arcData, vertexData.length, 7);
//		System.out.println(mGraph.toString());
//		mGraph.DFSTraverse(3);
		mGraph.BFSTraverse(3);
	}
}
