package arrays.lc;

import java.util.HashMap;
import java.util.Map;

public class SparseVector {

	Map<Integer, Integer> hm;
	
	
	SparseVector(int[] nums) {
		hm = new HashMap<Integer, Integer>();
		for(int i = 0;i < nums.length; i++) {
			if(nums[i] > 0) {
				hm.put(i, nums[i]);
			}
		}
	}

	public int dotProduct(SparseVector vec) {
		int dotProduct = 0;
		for(int x : this.hm.keySet()) {
			if(vec.hm.containsKey(x)) {
				dotProduct += this.hm.get(x) * vec.hm.get(x);
			}
		}
		return dotProduct;
	}

	public static void main(String[] args) {
		int[] nums1 = {0,1,0,0,2,0,0};
		int[] nums2 = {1,0,0,0,3,0,4};
		
		SparseVector v1 = new SparseVector(nums1);
		SparseVector v2 = new SparseVector(nums2);
		int ans = v1.dotProduct(v2);
		System.out.println(ans);
		
	}

}
