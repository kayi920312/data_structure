package graph;
/**
 * 图的最小路径算法 迪杰斯特拉（Dijkstra）算法
 * @author kayi9
 *
 */
public class Dijkstra {

	public static void main(String[] args) {
		int firstIndex = 1;
		String[] vertexData = {"v0", "v1", "v2", "v3","v4"};
		Integer[][] arcData = {
				{0, 10, Integer.MAX_VALUE, 30, 100},
				{Integer.MAX_VALUE, 0, 50, Integer.MAX_VALUE, Integer.MAX_VALUE},
				{Integer.MAX_VALUE, Integer.MAX_VALUE, 0, Integer.MAX_VALUE, 10},
				{Integer.MAX_VALUE, Integer.MAX_VALUE, 20, 0, 60},
				{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
		};
		MGraph mGraph = new MGraph(vertexData, arcData, vertexData.length, 7);
//		System.out.println(mGraph.toString());
		
		int vertexNum = mGraph.getVertexNum();
		Integer[] dist = new Integer[vertexNum];
		String[] path = new String[vertexNum];
		Integer[] s = new Integer[vertexNum];
				
		for (int i = 0; i < vertexNum; i++) {
			dist[i] = mGraph.getArc()[firstIndex][i];
			if (dist[i] != Integer.MAX_VALUE) {
				path[i] = mGraph.getVertex()[firstIndex] + mGraph.getVertex()[i];
			} else {
				path[i] = "";
			}
		}
		
		s[0] = firstIndex;
		dist[firstIndex] = 0;
		int num = 1;
		
		while (num < vertexNum) {
			// 找出第一个dist不为0的点的下标k，则先置dist[k]为最小值
			int k = 0;
			for (int i = 0; i < vertexNum; i++) {
				if (dist[i] != 0) {
					k = i;
					break;
				}
			}
			for (int i = 0; i < vertexNum; i++) {
				if ((dist[i] != 0) && (dist[i] < dist[k])) {
					k = i;
				}
			}
//			System.out.println("k=" + k + ", dist[k]=" + dist[k] + ", path[k]=" + path[k]);
			s[num++] = k;
			for (int i = 0; i < vertexNum; i++) {
				Integer newLen = dist[k] + mGraph.getArc()[k][i];
				if (newLen > 0) {
					if (dist[i] > newLen) {
						dist[i] = newLen;
						path[i] = path[k] + mGraph.getVertex()[i];
					}
				}
				
			}
			dist[k] = 0;		
		}
		System.out.println();
		System.out.println();
		for (int i = 0; i < vertexNum; i++) {
			System.out.println(path[i]);
		}
	}

	
}
