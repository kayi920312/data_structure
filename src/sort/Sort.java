package sort;

import java.util.Arrays;

/**
 * 排序算法
 * @author kayi9
 *
 */
public class Sort {
	
	/**
	 * 直接插入排序，有哨兵
	 * @param sourceData 排序的数据
	 * @param n 需要排序的个数，即有序区的元素个数，从0到n-1
	 */
	public void insertSort(int sourceData[], int n) {		
		for (int i = 2; i <= n; i++) {
			// 哨兵，记录要排序的数据，也可以防止在往回找时数组越界
			sourceData[0] = sourceData[i];
			int j;
			// 开始从有序区找一个适合sourceData[i]的位置
			// 从i-1这个位置开始往回找，不断把不适合的位置往后移一位，知道找到适合的位置，则把sourceData[0]放进去
			for (j = i - 1; sourceData[0] < sourceData[j]; j--) {
				sourceData[j + 1] = sourceData[j];
			}
			sourceData[j + 1] = sourceData[0];
		}
	}
	
	/**
	 * 直接插入排序，无哨兵
	 * @param sourceData 排序的数据
	 * @param n 需要排序的个数，即有序区的元素个数，从0到n-1
	 */
	public void insertSortNoSentry(int sourceData[], int n) {
		for (int i = 1; i < n; i++) {
			int j;
			// 缓存要插入的值
			int temp = sourceData[i];
			// 开始从有序区找一个适合sourceData[i]的位置
			// 从i-1这个位置开始往回找，不断把不适合的位置往后移一位，知道找到适合的位置，则把temp放进去
			for (j = i - 1; j >= 0 && temp < sourceData[j] ; j--) {
				sourceData[j + 1] = sourceData[j];
			}
			sourceData[j + 1] = temp;
		}
	}
	
	/**
	 * 希尔排序，对直接插入排序的一种优化
	 * @param sourceData 排序的数据
	 * @param n 需要排序的个数，即有序区的元素个数，从0到n-1
	 */
	public void shellSort(int sourceData[], int n) {
		// 取增量值
		for (int d = n/2; d >= 1; d = d/2) {
			int i,j;
			for (i = d+1; i <= n; i++) {
				sourceData[0] = sourceData[i];
				for (j = i - d; j > 0 && sourceData[0] < sourceData[j]; j = j - d) {
					// 找合适位置
					sourceData[j+d] = sourceData[j];
				}
				sourceData[j+d] = sourceData[0];
			}
		}
	}
	
	/**
	 * test
	 * @param args
	 */
	public static void main(String[] args) {
		int[] sourceData = {0, 3, 2, 6, 10, 5, 20, 4, 5};
		int[] sourceDataNoSentry = {3, 2, 6, 10, 5, 20, 4, 5};
//		new Sort().insertSort(sourceData, 8);
		new Sort().insertSortNoSentry(sourceDataNoSentry, 8);
		System.out.println(Arrays.toString(sourceDataNoSentry));
	}
}
