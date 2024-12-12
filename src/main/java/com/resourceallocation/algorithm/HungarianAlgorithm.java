package com.resourceallocation.algorithm;

import java.util.Arrays;

public class HungarianAlgorithm {
    public int[] assignResources(int[][] costMatrix, double[] weights, int[][]... factorMatrices) {
        int n = costMatrix.length;
        int[] rowCover = new int[n];
        int[] colCover = new int[n];
        int[] assignment = new int[n];
        Arrays.fill(assignment, -1);

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

        // Step 2: Subtract the row minimum from each row
        for (int i = 0; i < n; i++) {
            int rowMin = Arrays.stream(scoringMatrix[i]).min().orElse(0);
            for (int j = 0; j < n; j++) {
                scoringMatrix[i][j] -= rowMin;
            }
        }

        // Step 3: Subtract the column minimum from each column
        for (int j = 0; j < n; j++) {
            int colMin = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                if (scoringMatrix[i][j] < colMin) {
                    colMin = scoringMatrix[i][j];
                }
            }
            for (int i = 0; i < n; i++) {
                scoringMatrix[i][j] -= colMin;
            }
        }

        // Step 4: Perform initial allocation using the Hungarian Algorithm
        boolean done = false;
        while (!done) {
            done = true;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (scoringMatrix[i][j] == 0 && rowCover[i] == 0 && colCover[j] == 0) {
                        assignment[i] = j;
                        rowCover[i] = 1;
                        colCover[j] = 1;
                        done = false;
                        break;
                    }
                }
            }
        }

        // Step 5: Validate and adjust assignments if needed
        for (int i = 0; i < n; i++) {
            if (assignment[i] != -1) {
                int assignedResource = assignment[i];
                // Check availability or other factors from the matrices
                boolean isValid = true;
                for (int k = 0; k < factorMatrices.length; k++) {
                    if (factorMatrices[k][i][assignedResource] == 0) {
                        isValid = false;
                        break;
                    }
                }
                if (!isValid) {
                    // Reassign to a valid resource
                    int minCost = Integer.MAX_VALUE;
                    int newAssignment = -1;
                    for (int j = 0; j < n; j++) {
                        boolean validAlternative = true;
                        for (int k = 0; k < factorMatrices.length; k++) {
                            if (factorMatrices[k][i][j] == 0) {
                                validAlternative = false;
                                break;
                            }
                        }
                        if (validAlternative && scoringMatrix[i][j] < minCost) {
                            minCost = scoringMatrix[i][j];
                            newAssignment = j;
                        }
                    }
                    assignment[i] = newAssignment;
                }
            }
        }

        return assignment;
    }
}