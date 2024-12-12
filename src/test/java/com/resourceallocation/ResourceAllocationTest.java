package com.resourceallocation;

import com.resourceallocation.algorithm.AuctionAlgorithm;
import com.resourceallocation.algorithm.HungarianAlgorithm;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ResourceAllocationTest {

    @Test
    public void testHungarianAlgorithm() {
        int[][] costMatrix = {
                {10, 19, 8, 15},
                {10, 18, 7, 17},
                {13, 16, 9, 14},
                {12, 19, 8, 18}
        };

        int[][] availabilityMatrix = {
                {1, 1, 0, 1},
                {1, 0, 1, 1},
                {1, 1, 1, 0},
                {0, 1, 1, 1}
        };

        int[][] reliabilityMatrix = {
                {3, 5, 2, 4},
                {4, 1, 3, 5},
                {2, 4, 5, 3},
                {5, 3, 4, 2}
        };

        double[] weights = {1.0, 2.0, 0.5}; // Weights for cost, availability, and reliability respectively

        AuctionAlgorithm auctionAlgorithm = new AuctionAlgorithm();
        int[] assignment = auctionAlgorithm.solveLargeDataset(costMatrix, weights, availabilityMatrix, reliabilityMatrix);

        System.out.println("Assignment: " + Arrays.toString(assignment));

    }
}



