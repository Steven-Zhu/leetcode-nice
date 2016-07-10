package org.steven.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.lang.System.out;

public class FindKPairsBeatsAll {

    static int minValueIndex = 0;

    public static List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return new LinkedList<>();
        if (k > nums1.length * nums2.length) k = nums1.length * nums2.length;

        int[] store = new int[nums1.length];
        for (int i = 0; i < store.length; i++) store[i] = 0;

        List<int[]> result = new ArrayList<>(k);
        int count = 0;
        int index = 0;
        while (count < k) {
            if (store[index] >= nums2.length) index++;

            minValueIndex = index;
            solveIter(nums1, nums2, store, index, nums1[index] + nums2[store[index]]);

            result.add(new int[]{nums1[minValueIndex], nums2[store[minValueIndex]]});
            store[minValueIndex] = store[minValueIndex] + 1;
            count++;
        }
        return result;
    }

    static int solveIter(int[] nums1, int[] nums2, int[] store, int index, int min) {
        if (min > nums1[index] + nums2[store[index]]) {
            min = nums1[index] + nums2[store[index]];
            minValueIndex = index;
        }
        if (store[index] > 0 && index < store.length - 1) {
            min = solveIter(nums1, nums2, store, index + 1, min);
        }

        return min;
    }

}
