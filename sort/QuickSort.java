package sort;

/**
 * 快速排序
 * User: yanghua
 * Date: 7/25/13
 * Time: 11:24 AM
 * Copyright (c) 2013 yanghua. All rights reserved.
 */
public class QuickSort {

    public static void quickSort(int[] a, int lowIndex, int highIndex){
        if (null==a || a.length==0){
            return;
        }

        if (lowIndex < highIndex){
            int middle=getMiddle(a, lowIndex, highIndex);

            //left : recursion
            quickSort(a, lowIndex, middle -1);

            //right : recursion
            quickSort(a, middle+1, highIndex);
        }

    }

    private static int getMiddle(int[]a, int lowIndex, int highIndex){
        int reference=a[lowIndex];

        while (lowIndex < highIndex){

            //from right -> left
            while (lowIndex < highIndex && a[highIndex] > reference){
                highIndex--;
            }

            a[lowIndex]=a[highIndex];

            //from left -> right
            while (lowIndex < highIndex && a[lowIndex] < reference){
                lowIndex++;
            }

            a[highIndex]=a[lowIndex];

        }

        a[lowIndex]=reference;

        return lowIndex;
    }

    public static void main(String[] args) {
        int[] a=new int[]{23,12,45,98,4,88,20,77,72};
        quickSort(a, 0, a.length-1);

        for(int item : a){
            System.out.println(item);
        }

    }
}
