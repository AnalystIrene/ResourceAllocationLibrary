package com.resourceallocation.algorithm;

import java.util.Arrays;

public class AuctionAlgorithm {
    public int[] solveLargeDataset(int[][] costMatrix, double[] weights, int[][]... factorMatrices) {
        int n = costMatrix.length;
        int[] assignment = new int[n];
        double[] prices = new double[n];
        double epsilon = 1e-2; // Small value to adjust prices

        Arrays.fill(assignment, -1); // -1 indicates unassigned tasks

        // Step 1: Calculate the composite scoring matrix
        int[][] scoringMatrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Start with the costMatrix and add weighted contributions from factorMatrices
                double compositeScore = costMatrix[i][j] * weights[0];
                for (int k = 0; k < factorMatrices.length; k++) {
                    compositeScore += factorMatrices[k][i][j] * weights[k + 1];
                }
                scoringMatrix[i][j] = (int) compositeScore;
            }
        }

        boolean allAssigned = false;
        while (!allAssigned) {
            allAssigned = true;

            for (int task = 0; task < n; task++) {
                if (assignment[task] == -1) { // Unassigned task
                    allAssigned = false;

                    // Find the best and second-best resources for this task
                    double maxProfit = Double.NEGATIVE_INFINITY;
                    double secondMaxProfit = Double.NEGATIVE_INFINITY;
                    int bestResource = -1;

                    for (int resource = 0; resource < n; resource++) {
                        double profit = scoringMatrix[task][resource] - prices[resource];

                        if (profit > maxProfit) {
                            secondMaxProfit = maxProfit;
                            maxProfit = profit;
                            bestResource = resource;
                        } else if (profit > secondMaxProfit) {
                            secondMaxProfit = profit;
                        }
                    }

                    // Update the price of the best resource
                    prices[bestResource] += maxProfit - secondMaxProfit + epsilon;

                    // Reassign the resource to the current task
                    for (int i = 0; i < n; i++) {
                        if (assignment[i] == bestResource) {
                            assignment[i] = -1; // Unassign the previous task
                            break;
                        }
                    }
                    assignment[task] = bestResource;
                }
            }
        }

        return assignment;
    }
}