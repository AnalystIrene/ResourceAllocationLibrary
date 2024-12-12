package com.resourceallocation.Optimization;

import java.util.Random;

public class MetaheuristicMethods {

    public void applySimulatedAnnealing(int[][] costMatrix) {
        Random random = new Random();
        double temperature = 10000;
        double coolingRate = 0.003;
        int[] currentSolution = generateRandomSolution(costMatrix.length);
        int[] bestSolution = currentSolution.clone();
        double bestCost = calculateCost(costMatrix, bestSolution);

        while (temperature > 1) {
            int[] newSolution = generateNeighborSolution(currentSolution);
            double newCost = calculateCost(costMatrix, newSolution);

            if (acceptanceProbability(bestCost, newCost, temperature) > random.nextDouble()) {
                currentSolution = newSolution.clone();
            }

            if (newCost < bestCost) {
                bestSolution = newSolution.clone();
                bestCost = newCost;
            }

            temperature *= (1 - coolingRate);
        }
    }

    private int[] generateRandomSolution(int size) {
        Random random = new Random();
        int[] solution = new int[size];
        for (int i = 0; i < size; i++) {
            solution[i] = random.nextInt(size);
        }
        return solution;
    }

    private int[] generateNeighborSolution(int[] solution) {
        Random random = new Random();
        int i = random.nextInt(solution.length);
        int j = random.nextInt(solution.length);
        int[] neighbor = solution.clone();
        int temp = neighbor[i];
        neighbor[i] = neighbor[j];
        neighbor[j] = temp;
        return neighbor;
    }

    private double acceptanceProbability(double bestCost, double newCost, double temperature) {
        if (newCost < bestCost) {
            return 1.0;
        }
        return Math.exp((bestCost - newCost) / temperature);
    }

    private double calculateCost(int[][] costMatrix, int[] solution) {
        double cost = 0;
        for (int i = 0; i < solution.length; i++) {
            cost += costMatrix[i][solution[i]];
        }
        return cost;
    }

    private boolean isSolutionValid(int[] solution, int[][]... factorMatrices) {
        for (int i = 0; i < solution.length; i++) {
            for (int[][] factorMatrix : factorMatrices) {
                if (factorMatrix[i][solution[i]] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
