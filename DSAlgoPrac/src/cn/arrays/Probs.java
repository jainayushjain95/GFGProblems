package cn.arrays;

import java.util.*;

public class Probs {

	public static int mod = 1000000007;
	
	public static void main(String[] args) {
		int[] arr = {1, 2, 3};
		List<List<Long>> queries = new ArrayList<List<Long>>();
		List<Long> q1 = new ArrayList<Long>();
		q1.add(1l);
		q1.add(5l);
		queries.add(q1);
		List<Integer> sol = sumInRanges(arr, arr.length, queries, 1);
		for(int x : sol) {
			System.out.println(sol);
		}
	}

	public static List<Integer> sumInRanges(int[] arr, int n, List<List<Long>> queries, int q) {
		List<Integer> sol = new ArrayList<Integer>();
		int[] psa = preparePrefixSumArray(arr);
		
		for(List<Long> query : queries) {
			
			long qLefti = query.get(0);
			long qRighti = query.get(1);
			
			int qLeft = (int)(query.get(0) % n);
			int qRight = (int)(query.get(1) % n);
			
			
			int sum = 0;
			int timesTotal = (int)((qRighti - qLefti + 1)/n);
			sum = timesTotal * psa[n - 1];
			
			if(sum >= mod) {
				sum = sum % mod;
			}
			
			if((qRighti - qLefti + 1) % n != 0) {
				if(qLeft <= qRight) {
					sum += psa[qRight] - (qLeft > 0 ? psa[qLeft - 1] : 0);
				} else {
					sum += psa[qRight] + psa[n - 1] - psa[qLeft - 1];
				}	
			}
			
			sol.add(sum);
		}
		
		return sol;
    }
	
	public static int[] preparePrefixSumArray(int[] arr) {
		int[] psa = new int[arr.length];
		psa[0] = arr[0];
		for(int i = 1;i < arr.length; i++) {
			psa[i] = arr[i] + psa[i - 1];
		}
		return psa;
	}
}
