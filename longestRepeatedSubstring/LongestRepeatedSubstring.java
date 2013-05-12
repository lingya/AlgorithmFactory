package longestRepeatedSubstring;

/**
 * User: yanghua
 * Date: 5/12/13
 * Time: 10:51 AM
 * Copyright (c) 2013 yanghua. All rights reserved.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 问题：出现不止一次的最长substring(substring可重复)
 * eg:"abcabcaacb" -> "abca"
 * 	  "aababa" -> "aba"
 * 思路：利用KMP算法的前缀函数(next函数)，查找一个字符串的所有后缀子串的max(next函数中最大的那一位)
 * 复杂度：O(n的平方）
 * 优化思路: suffix tree/array
 */
public class LongestRepeatedSubstring {

	//每个后缀子串的next函数中的最大值与子串的startIndex的hash table
	private static Map<Integer,Integer> maxValPerNextFuncMap;

	/**
	 * find max redundant value of suffix string
	 * @param searchingStr the suffix string
	 * @param startIndex the suffix substring's start index
	 * @return current suffix string's max redundant value
	 */
	private static int findMaxRepeatedValueOfSuffixStr(String searchingStr, int startIndex){
		if (null==searchingStr||searchingStr.isEmpty()){
			throw new IllegalArgumentException("the arg:suffixStr can not be null or empty");
		}

		if (startIndex<0 ||startIndex>searchingStr.length()){
			throw new IllegalArgumentException("the arg:startIndex is illegal");
		}

		int maxVal=0;

		int l=searchingStr.length()-startIndex;
		int[] prefixArr=new int[l];
		int k;

		prefixArr[0]=0;

		//the charAt(index) the index is relative to searchingStr
		for (int i=1;i<l;i++){
			k=prefixArr[i-1];
			while (searchingStr.charAt(startIndex+i)!=searchingStr.charAt(startIndex+k) && k!=0){
				k=k-1;
			}

			if (searchingStr.charAt(startIndex+i)==searchingStr.charAt(startIndex+k)){
				prefixArr[i]=k+1;
			}else {
				prefixArr[i]=0;
			}

			if (maxVal<prefixArr[i])
				maxVal=prefixArr[i];
		}

		return maxVal;
	}

	/**
	 * get max redundant substring
	 * @param searchingStr the searching string
	 */
	private static void getMaxRepeatedStr(String searchingStr){

		if (null==searchingStr||searchingStr.isEmpty()){
			throw new IllegalArgumentException("the arg:searchingStr can not be null or empty");
		}

		maxValPerNextFuncMap=new HashMap<Integer, Integer>(searchingStr.length());

		int indexOfMaxVal=-1;
		int maxVal=-1;

		for (int i=0;i<searchingStr.length();i++){
			int maxValPreNextFunc=findMaxRepeatedValueOfSuffixStr(searchingStr,i);
			maxValPerNextFuncMap.put(i,maxValPreNextFunc);
			if (maxVal<maxValPreNextFunc){
				maxVal=maxValPreNextFunc;
				indexOfMaxVal=i;
			}
		}

		if (indexOfMaxVal!=-1){
			int maxLengthOfSearchingStr=maxValPerNextFuncMap.get(indexOfMaxVal);
			int maxStrStartIndex=indexOfMaxVal;
			int maxStrEndIndex=maxStrStartIndex+maxLengthOfSearchingStr;
			System.out.println("the str is :"+searchingStr.substring(maxStrStartIndex,maxStrEndIndex));
		}

	}

	public static void main(String[] args) {
		String testStr="abababa";
		getMaxRepeatedStr(testStr);
	}

}
