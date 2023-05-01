/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fistappwhitnetbeans.zarchetagame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author ivail
 */
public class DiceGame {
    private List<List<Integer>> diceData;
    private List<List<Integer>> diceDateGaus;
    private List<Integer> sums;
    private Map<Integer,Integer> sumMap;
    private Map<Integer,Integer> gause;
    private double[] doubleSumsArray;
    private int nThrows;
    private int nDise;
    private int maxSums;
    private int[] dd;
    private Map<Integer,Integer> sumsCount;

    public DiceGame(int nThrows, int nDise) {
        this.nThrows = nThrows;
        this.nDise = nDise;
        diceData = new LinkedList<>();
        sums = new LinkedList<>();
        gause=new HashMap<>();
        diceDateGaus= new LinkedList<>();
        sumsCount=new HashMap<>();
        sumMap=new HashMap<>();
    }
    
    
    public void playGame() {
        for(int i=0;i<nDise;i++){
            createDiceData(i+1);
        }
        setMaxSums();
    }

    private void setMaxSums(){
        maxSums=nDise*6;
    }
    
    private void createDiceData(int kDise) {         
        Random dice = new Random();
        List<Integer> data = new LinkedList<>();
        List<Integer> throwsDice = new LinkedList<>();
        for (int i = 0; i < nThrows; i++) {
            for (int j = 0; j < kDise; j++) {
               throwsDice.add(dice.nextInt(1, 6));
            }
            data.add(dice.nextInt(1,6));
        }
        this.diceData.add(data); 
    }


    public List<List<Integer>> getDiceData() {
        return diceData;
    }
    
    public void printDiceData(){
        System.out.println(diceData);
    }
    
    public void setSums(){
       // int totalElements = outerListSize * innerListSize;
        int totalElements = nDise * nThrows;
        Map<Integer,List<Integer>> diceMap = new HashMap();
        List<Integer> indexes=new ArrayList();
        
        for(int diceThrowCounter=0;diceThrowCounter<nThrows;diceThrowCounter++){
            indexes=new ArrayList();
            for(int diceCount=0;diceCount<nDise;diceCount++){
                indexes.add(diceData.get(diceCount).get(diceThrowCounter));
            }
            diceMap.put(diceThrowCounter, indexes);
        }
        
        //System.out.println(diceMap);  
        
        for (List<Integer> sublist : diceMap.values()) {
            int sum=0;
            for(Integer i:sublist){
                sum=sum+i;
            }
            sums.add(sum);
        }   
        
       // System.out.println(sums);
       
        for (int i = nDise; i <= nDise * 6; i++) {
            sumMap.put(i, 0);
        }       
        int j=0; 
       
       for(Integer i:sums){
           int val = (int)sumMap.get(i);
           val++;
           sumMap.put(i,val);
        }
        System.out.println(sumMap);
    }

////
    
    public void createGauseData() {         
        //HashMap hm = new HashMap<Integer, Integer>();
	int numDice = nDise;
        for (int i = numDice; i <= numDice * 6; i++) {
            gause.put(i, 0);
        }
        double stdDev = 0.0;
        double sum = 0.0;
        double mean = 0.0;
        int c = 0;
        for (int i = numDice; i <= numDice * 6; i++) {
            sum = sum + i;
            c++;//counter
        }
        mean = sum / c;
        for (int i = numDice; i <= numDice * 6; i++) {
            stdDev += Math.pow(i - mean, 2);
        }
        stdDev = Math.sqrt(stdDev / c);
        System.out.println("sum:"+ sum + " mean:"+mean + " stdDev:"+stdDev);
        for (int i = 0; i < nThrows; i++) {
            Random r = new Random();
            int result = (int)Math.round(gauss2(r, mean, stdDev / 2.5, numDice, numDice * 6));
            int val = (int)gause.get(result);
            val++;
            gause.put(result, val);
        }
        
        
        
        for (int i = numDice; i <= numDice * 6; i++) {
            System.out.println(i + " " + gause.get(i));
        }
        //gause=hm;
    }

    public static double gauss2(Random r, double mean, double stdDev, double minRange, double maxRange) {
       
        Random random = r;
        double gaussianNum = random.nextGaussian() * stdDev + mean;
        
        
        while(gaussianNum < minRange || gaussianNum > maxRange) {
            gaussianNum = random.nextGaussian() * stdDev + mean;
        }
        
        //System.out.println("Gaussian Number: " + gaussianNum);
        return gaussianNum;
    }
    
    //
    public int[] gauseToArray(){
        int[] answer = new int[gause.size()];
        Set<Map.Entry<Integer, Integer>> entries = gause.entrySet();
        int i=0;
        for(Map.Entry<Integer, Integer> entry : entries){
            answer[i]=entry.getValue();
            i++;
        }
        //System.out.println("Gause Array:"+answer);
        for(int j=0;j<gause.size();j++){
            System.out.println(answer[j]);
        }
        return answer;
    }
    /*
    public double[] getSumsDoubleArray(){
        double[] dd = null;
        int j=0;
        for(Integer i:sums){
            dd[j]=(double)i;
            j++;
        }
        return dd;
    }
    
    
    public int[] getSumsIntArray(){
        int[] array = new int[sums.size()];
        for(int i = 0; i < sums.size(); i++) array[i] = sums.get(i);
        return array;
    }
    
    public int[] getGauseIntArray(){
        int[] array = new int[gause.size()];
        for(int i = 0; i < gause.size(); i++) array[i] = gause.get(i);
        return array;
    }
    */ 

    int[] sumsToArray() {
        int[] answer = new int[sumMap.size()];
        Set<Map.Entry<Integer, Integer>> entries = sumMap.entrySet();
        int i=0;
        for(Map.Entry<Integer, Integer> entry : entries){
            answer[i]=entry.getValue();
            i++;
        }
        //System.out.println("Gause Array:"+answer);
        for(int j=0;j<sumMap.size();j++){
            System.out.println(answer[j]);
        }
        return answer;
    }


}
