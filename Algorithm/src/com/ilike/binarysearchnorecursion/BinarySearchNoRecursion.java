package com.ilike.binarysearchnorecursion;

/**
 * 二分查找，非递归实现
 */
public class BinarySearchNoRecursion {

    public static void main(String[] args) {
        //测试
        int [] arr={1,3,8,10,11,67,100};
        int index=binarySearch(arr,100);
        System.out.println(index);

    }

    /**
     * 二分查找的非递归实现
     *
     * @param arr    待查找的数据
     * @param target 需要查找的数
     * @return 返回对应的下标，没找到返回-1
     */
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        int mid=0;
        while (left <= right) {
            //可以查找
            mid = (left + right) / 2;
            if(arr[mid]==target){
                return mid;
            }else if(arr[mid]>target){
                //向左查找
                right=mid-1;
            }else {
                //向右查找
                left=mid+1;
            }
        }
        return -1;
    }
}
