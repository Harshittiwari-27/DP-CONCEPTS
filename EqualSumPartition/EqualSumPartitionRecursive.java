/*
=========================================================
            EQUAL SUM PARTITION (RECURSIVE)
=========================================================

Problem:
Given an array, determine whether it can be partitioned
into two subsets such that the sum of both subsets is equal.

Example:

arr = {1,5,11,5}

Subset1 = {1,5,5} = 11
Subset2 = {11} = 11

Answer = true

=========================================================
PATTERN
=========================================================

Equal Sum Partition
        ↓
Subset Sum
        ↓
0/1 Knapsack

=========================================================
TRICK
=========================================================

Step 1:
Find total sum.

Step 2:
If total sum is odd

return false

because:

Odd Number / 2 = impossible

Step 3:

Check whether a subset
with sum = totalSum/2 exists.

=========================================================
*/
package EqualSumPartition;



public class EqualSumPartitionRecursive {

    /*
    -----------------------------------------------------
    SUBSET SUM RECURSION
    -----------------------------------------------------

    Can we make "sum"
    using first n elements?
    */

    static boolean subsetSum(int[] arr, int sum, int n) {

        // Base Case 1

        if (sum == 0) {
            return true;
        }

        // Base Case 2

        if (n == 0) {
            return false;
        }

        // Choice Diagram

        if (arr[n - 1] <= sum) {

            // Include OR Exclude

            return subsetSum(
                    arr,
                    sum - arr[n - 1],
                    n - 1
            )
            ||
            subsetSum(
                    arr,
                    sum,
                    n - 1
            );

        }

        // Cannot Include

        return subsetSum(
                arr,
                sum,
                n - 1
        );
    }

    /*
    -----------------------------------------------------
    EQUAL SUM PARTITION
    -----------------------------------------------------
    */

    static boolean equalSumPartition(int[] arr) {

        int totalSum = 0;

        for (int num : arr) {
            totalSum += num;
        }

        // Odd sum cannot be partitioned equally

        if (totalSum % 2 != 0) {
            return false;
        }

        return subsetSum(
                arr,
                totalSum / 2,
                arr.length
        );
    }

    public static void main(String[] args) {

        int[] arr = {1, 5, 11, 5};

        boolean answer =
                equalSumPartition(arr);

        System.out.println(
                "Can be partitioned equally? "
                        + answer
        );
    }
}

/*
=========================================================
DRY RUN
=========================================================

Input:

arr = {1,5,11,5}

Total Sum = 22

Target = 22 / 2

Target = 11

Question becomes:

Can we find a subset
whose sum is exactly 11 ?

Possible:

{11}
or
{1,5,5}

Answer = TRUE

=========================================================
CHOICE DIAGRAM
=========================================================

subsetSum(arr,11,4)

Current Element = 5

               11
             /    \
         Take      Skip
         /           \
      Sum=6        Sum=11

Every element has two choices:

1) Include
2) Exclude

This is why it belongs
to 0/1 Knapsack.

=========================================================
TIME COMPLEXITY
=========================================================

O(2^N)

Reason:

Every element creates
two recursive calls.

=========================================================
SPACE COMPLEXITY
=========================================================

O(N)

Recursion Stack

=========================================================
REVISION FORMULA
=========================================================

Equal Sum Partition

↓

Total Sum

↓

If Odd
return false

↓

Subset Sum(totalSum / 2)

=========================================================
*/

