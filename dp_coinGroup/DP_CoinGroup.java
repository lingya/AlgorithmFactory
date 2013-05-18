package dp_coinGroup;

/**
 * User: yanghua
 * Date: 5/18/13
 * Time: 5:31 PM
 * Copyright (c) 2013 yanghua. All rights reserved.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 问题：现有面值为1美分、2美分、5美分的硬币无限枚，求要组成价值为1美元，一共有多少种组合方式
 *
 * 思路：这是典型的DP，完全背包问题
 * 		假设已有 N-1 种面值的硬币，组合出价值为 M 的总组合数为 result[M]
 *      则，新增加一种面值的硬币 X ，我们需要改变价值大于 X 的所有价值的组合数；
 *      设某价值 Y>=x 则，在新增加一种面值为 X 的硬币后，构成价值 Y 的总组合数 result[Y]’
 *      result[Y]’=原result[Y]+result[Y-X]+result[Y-2X]+result[Y-3X]+....+result[Y-zX]  (确保Y-zX>=0)
 * 技巧：假设X为10，Y为35 则带入上面的公式为：
 * 		result[35]‘+=(result[25]+result[15]+result[5])
 * 	    因为程序运行的逻辑是先更新result[5]'=result[5]
 * 	    接着result[15]'+=result[5]';result[25]'+=result[15]'
 * 	    所以其实，result[25]'=result[25]+result[15]+result[5]
 * 	    可以得出result[35]'+=result[25]'
 * DP的状态以及状态转移方程：
 * 		设F(N,Y)为N种面值的硬币，组合出价值为Y的组合数
 * 		可以得出该问题的状态转移方程为：F(N,Y)=sigma(F[N-1,Y-k) 其中：k=0,X,2X,.....,zX(z=Y/X)
 */
public class DP_CoinGroup {

	private static int getGroupNum(int n, int[] coins){
		if (n<=0){
			throw new IllegalArgumentException("the arg:n is illegal");
		}

		if (null==coins||coins.length==0){
			throw new IllegalArgumentException("the arg:coin can not be null or empty");
		}

		Map<Integer,Integer> r=new HashMap<Integer, Integer>();
		r.put(0,1);

		for (int i=0;i<coins.length;i++)
			for (int j=0;j<=n;j++){
				if (!r.containsKey(j))
					r.put(j,0);

				if (!r.containsKey(j+coins[i]))
					r.put(j+coins[i],0);

				r.put(j+coins[i],r.get(j+coins[i])+r.get(j));
			}

		return r.get(n);

	}

	public static void main(String[] args) {
		int n=100;
		int[] coins={1,2,5};

		int groupNum=getGroupNum(n,coins);

		System.out.println("the total group num is:"+groupNum);
	}


}
