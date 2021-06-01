/*
硬币兑换问题：求出 利用给定的硬币数组coins，有多少种方案能够凑齐金额s
子问题：对于金额j来说，利用前i个硬币能有多少方案
状态转移方程：
          f(i-1,j)                                    ;if(coin[i]>j)说明只要加入coin[i]就超过了，不能用，这里方案肯定和上一个是相同的
f(i,j) =  f(i-1,j)+1                                  ;if(coin[i]=j)说明新的硬币刚好等于金额，那么就要么加要么不加，加了只有一种方案，不加和之前一样
          （0-k->j/coin[i])求和f(i-1, j - k*coin[i])   ;这里由于可以加多个coin[i],所以每加一个，加上对应剩下的方案数即可
    这里就产生了边界条件 j==0||i==0时全部为零 i==1是j%coin[i] ^ 1

由于每一行都用到的是之前的数据，所以可以倒着来，这样的话，只要一维数组就够保存状态了
 */

public class CoinChange
{

    public static void main(String[] args)
    {
        int[] coins = new int[]{};
        int s = 10;

        System.out.println(new CoinChange().change(coins, s));
    }

    /**
     * 求全部方案的方法
     *
     * @param coins 对应的硬币金额
     * @param s     总的金额
     * @return 返回全部的方案
     */
    public int change(int[] coins, int s)
    {
        //定义状态数组
        int[] dp = new int[s + 1];

        //第一次肯定是0，所以从i == 1开始  当i==0时没有意义所以这里的 0 其实就是 1
        for (int i = 0; i < coins.length; i++)
        {
            for (int j = s; j >= 1; j--)
            {
                //如果i == 1，计算能不能整除即可
                if (i == 0)
                {
                    dp[j] = (j % coins[i]) ^ 1;
                }
                else
                {
                    //递归公式
//                    if (coins[i] > j)
//                        //相当于不动，所以不需要改变
//                        dp[j] = dp[j];
                    if (coins[i] == j)
                    {
                        dp[j] += 1;
                    }
                    else if (coins[i] < j)
                    {
                        int sum = 0;
                        //求和
                        for (int k = 0; k <= j / coins[i]; k++)
                        {
                            sum += dp[j - coins[i] * k];
                        }
                        //如果可以刚好整除，可以再算一种方案
                        if (j % coins[i] == 0)
                        {
                            sum++;
                        }
                        //求和后赋值
                        dp[j] = sum;
                    }
                }
            }
        }
        //dp数组计算完了，
        return dp[s];
    }
}
