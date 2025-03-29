package org.knit.solutions.task19;

public class MergingSorter {
    public void mergeWithSorting(int[] nums1, int[] nums2, int n, int m) {
        // Для прохода по первому
        int i = 0;
        // Для прохода по второму
        int j = 0;
        // Для записи в первом массиве
        int k = 0;

        while (i < m) {
            if (nums1[i] > nums2[j]) {
                int temp = nums1[k];
                nums1[k] = nums2[j];
                nums2[j] = temp;
                if (j < (nums2.length - 1) && nums2[j] > nums2[j + 1]) {
                    j++;
                }
            }
            i++;
            k++;
        }

        i = 0;
        k = 0;
        j = 0;
        while (k < m) {
            if (nums1[k] == 0) {
                nums1[k] = nums2[j];
                j++;
            } else if (nums1[k] > nums2[j]) {
                int temp = nums1[k];
                nums1[k] = nums2[j];
                nums2[j] = temp;
            }
            k++;
        }

        for (int l = 0; l < m; l++) {
            nums1[k] = nums2[l];
            k++;
        }
    }

    public void printArray(int[] arr) {
        for (int element : arr) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
