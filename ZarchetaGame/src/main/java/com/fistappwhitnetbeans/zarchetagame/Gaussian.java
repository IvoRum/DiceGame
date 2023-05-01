/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fistappwhitnetbeans.zarchetagame;

/**
 *
 * @author ivail
 */
public class Gaussian {
      private double mean;
    private double standardDeviation;
    
    public Gaussian(double mean, double standardDeviation) {
        this.mean = mean;
        this.standardDeviation = standardDeviation;
    }
    
    public double probabilityDensityFunction(double x) {
        double numerator = Math.exp(-(Math.pow(x - mean, 2)) / (2 * Math.pow(standardDeviation, 2)));
        double denominator = standardDeviation * Math.sqrt(2 * Math.PI);
        return numerator / denominator;
    }
    
    public double cumulativeDistributionFunction(double x) {
        double z = (x - mean) / standardDeviation;
        return (1.0 / 2.0) * (1.0 + erf(z / Math.sqrt(2)));
    }
    
    private double erf(double z) {
        double t = 1.0 / (1.0 + 0.5 * Math.abs(z));
        double ans = 1 - t * Math.exp(-z*z - 1.26551223 + t * (1.00002368 +
                t * (0.37409196 + t * (0.09678418 + t * (-0.18628806 +
                t * (0.27886807 + t * (-1.13520398 + t * (1.48851587 +
                t * (-0.82215223 + t * (0.17087277))))))))));
        if (z >= 0) {
            return ans;
        } else {
            return -ans;
        }
    }
}
