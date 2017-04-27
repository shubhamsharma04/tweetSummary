package com.tweetCorpusStats.driver;

import java.util.*;


public class Solution {

	public static void main(String[] args) {
		int a [] = new int[5];
		int k0;
	
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Integer> pair = (Entry<Integer, Integer>) it.next();
		}
	}
	
	static int countPairs(int k, int[] a) {
        int count = 0;
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i : a){
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            } else {
                map.put(i,1);
            }
        }
        
        for(Entry<Integer,Integer> entry : map.entrySet()){
            int redSum = k - entry.getKey();
            if(map.containsKey(redSum)){
                count+=entry.getValue();
            }
        }
    return count/2;
    }

}
