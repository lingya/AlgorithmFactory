package kmp;

/**
 * User: yanghua
 * Date: 5/6/13
 * Time: 9:23 PM
 * Copyright (c) 2013 yanghua. All rights reserved.
 */

/**
 * KMP算法练习
 * eg:在absdfsdc中查找sdf，返回:2(起始索引),查找abc,返回-1
 * 关键点:前缀函数
 * 资料:http://blog.csdn.net/yearn520/article/details/6729426
 */
public class KMP {

	public static int[] next(String desc) {

		if (null == desc || desc.isEmpty()) {
			throw new IllegalArgumentException("the arg:desc can not be empty");
		}

		int len = desc.length();
		int[] prefix = new int[len];

		prefix[0] = 0;

		for (int i = 1; i <= len - 1; i++) {
			int k = prefix[i - 1];

			while (desc.charAt(i) != desc.charAt(k) && k != 0) {
				k = prefix[k - 1];
			}

			if (desc.charAt(i) == desc.charAt(k)) {
				prefix[i] = k + 1;
			} else {
				prefix[i] = 0;
			}
		}

		return prefix;
	}

	public static int searchWithKMP(String originalStr, String targetStr){

		if (null==originalStr||null==targetStr){
			throw new IllegalArgumentException("the both args can not be null");
		}

		if (originalStr.length()<targetStr.length()){
			return -1;
		}

		int index=0;

		int[] nextArr=next(targetStr);

		for (int i=0;i<originalStr.length();i++){

			while (index>0 && originalStr.charAt(i)!=targetStr.charAt(index)){
				index=nextArr[index];
			}

			if (originalStr.charAt(i)==targetStr.charAt(index)){		//equal at i
				index++;

				if (index==targetStr.length())
					return i-index+1;
			}
		}

		return -1;
	}


	public static void main(String[] args) {
		String descStr = "ca";
		String originalStr="abcabdac";

		System.out.println(searchWithKMP(originalStr,descStr));
		int[] nextArr = next(descStr);

		for (int i = 0; i < descStr.length(); i++) {
			System.out.print(descStr.charAt(i) + "\t");
		}

		System.out.println();

		for (int j = 0; j < descStr.length(); j++) {
			System.out.print(String.valueOf(nextArr[j]) + "\t");
		}
	}


}
