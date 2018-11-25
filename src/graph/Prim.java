package graph;
/**
 * 图的最小生成树 Prim 算法
 * @author kayi9
 *
 */
public class Prim {
	public static void main(String[] args) {
		// 构建带权邻接图
		String[] vertexData = {"v0", "v1", "v2", "v3", "v4", "v5"};
		Integer[][] arcData = {
				{0, 34, 46, Integer.MAX_VALUE, Integer.MAX_VALUE, 19},
				{34, 0, Integer.MAX_VALUE, Integer.MAX_VALUE, 12, Integer.MAX_VALUE},
				{46, Integer.MAX_VALUE, 0, 17, Integer.MAX_VALUE, 25},
				{Integer.MAX_VALUE, Integer.MAX_VALUE, 17, 0, 38, 25},
				{Integer.MAX_VALUE, 12, Integer.MAX_VALUE, 38, 0, 26},
				{19, Integer.MAX_VALUE, 25, 25, 26, 0}
		};
		MGraph mGraph = new MGraph(vertexData, arcData, vertexData.length, 9);
		
		// 初始化候选最短边数组，默认先把v0放入候选数组
		ShortEdge[] shortEdges = new ShortEdge[mGraph.getVertexNum()];
		ShortEdge shortEdge = null;
		for (int i = 0; i< mGraph.getVertexNum(); i++) {
			shortEdge = new ShortEdge(0, arcData[0][i]);
			shortEdges[i] = shortEdge;
		}
		System.out.print("v0");
		// 在 shortEdges 中找出最短的边
		for (int i = 1; i < mGraph.getVertexNum(); i++) {
			int k = findMinEdge(shortEdges, mGraph.getVertexNum());
			System.out.print("->" + mGraph.getVertex()[k]);
			shortEdges[k].setLowcost(0);
			// 调整 shortEdges
			for (int j = 1; j < mGraph.getVertexNum(); j++) {
//				System.out.println("arc[" + k + "][" + j + "] = " + mGraph.getArc()[k][j] + ", short: " + shortEdges[j].getLowcost());
				if (mGraph.getArc()[k][j] < shortEdges[j].getLowcost()) {
					shortEdges[j].setLowcost(mGraph.getArc()[k][j]);
					shortEdges[j].setAdjvex(k);
				}
			}
		}
	}
	
	/**
	 * 从 shortEdges 中找出最短的边的邻接点下标
	 * @param shortEdges
	 * @param vertexNum
	 * @return
	 */
	public static int findMinEdge(ShortEdge[] shortEdges, Integer vertexNum) {
		int minIndex = 0;
		int minValue = 0;
		// 先找出第一个权值不为0的下标
		for (int i = 1; i < vertexNum; i++) {
			if (shortEdges[i].getLowcost() > 0) {
				minIndex = i;
				minValue = shortEdges[minIndex].getLowcost();
				break;
			}
		}
		for (int i = minIndex + 1; i < vertexNum; i++) {
			if (shortEdges[i].getLowcost() > 0 && shortEdges[i].getLowcost() < minValue) {
				minIndex = i;
				minValue = shortEdges[minIndex].getLowcost();
			}
		}
//		System.out.println("minIndex: " + minIndex + ", minValue: " + minValue);
		return minIndex;
	}
}

/**
 * 候选最短集元素类
 * @author kayi9
 *
 */
class ShortEdge {
	/**
	 * 候选最短边的邻接点下标
	 */
	private Integer adjvex;
	
	/**
	 * 候选最短边的权值
	 */
	private Integer lowcost;

	public ShortEdge() {}
	
	public ShortEdge(Integer adjvex, Integer lowcost) {
		this.adjvex = adjvex;
		this.lowcost = lowcost;
	}
	
	public Integer getAdjvex() {
		return adjvex;
	}

	public void setAdjvex(Integer adjvex) {
		this.adjvex = adjvex;
	}

	public Integer getLowcost() {
		return lowcost;
	}

	public void setLowcost(Integer lowcost) {
		this.lowcost = lowcost;
	}

	@Override
	public String toString() {
		return "ShortEdge [adjvex=" + adjvex + ", lowcost=" + lowcost + "]";
	}
	
}