/*
Ӳ�Ҷһ����⣺��� ���ø�����Ӳ������coins���ж����ַ����ܹ�������s
�����⣺���ڽ��j��˵������ǰi��Ӳ�����ж��ٷ���
״̬ת�Ʒ��̣�
          f(i-1,j)                                    ;if(coin[i]>j)˵��ֻҪ����coin[i]�ͳ����ˣ������ã����﷽���϶�����һ������ͬ��
f(i,j) =  f(i-1,j)+1                                  ;if(coin[i]=j)˵���µ�Ӳ�Ҹպõ��ڽ���ô��Ҫô��Ҫô���ӣ�����ֻ��һ�ַ��������Ӻ�֮ǰһ��
          ��0-k->j/coin[i])���f(i-1, j - k*coin[i])   ;�������ڿ��ԼӶ��coin[i],����ÿ��һ�������϶�Ӧʣ�µķ���������
    ����Ͳ����˱߽����� j==0||i==0ʱȫ��Ϊ�� i==1��j%coin[i] ^ 1

����ÿһ�ж��õ�����֮ǰ�����ݣ����Կ��Ե������������Ļ���ֻҪһά����͹�����״̬��
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
     * ��ȫ�������ķ���
     *
     * @param coins ��Ӧ��Ӳ�ҽ��
     * @param s     �ܵĽ��
     * @return ����ȫ���ķ���
     */
    public int change(int[] coins, int s)
    {
        //����״̬����
        int[] dp = new int[s + 1];

        //��һ�ο϶���0�����Դ�i == 1��ʼ  ��i==0ʱû��������������� 0 ��ʵ���� 1
        for (int i = 0; i < coins.length; i++)
        {
            for (int j = s; j >= 1; j--)
            {
                //���i == 1�������ܲ�����������
                if (i == 0)
                {
                    dp[j] = (j % coins[i]) ^ 1;
                }
                else
                {
                    //�ݹ鹫ʽ
//                    if (coins[i] > j)
//                        //�൱�ڲ��������Բ���Ҫ�ı�
//                        dp[j] = dp[j];
                    if (coins[i] == j)
                    {
                        dp[j] += 1;
                    }
                    else if (coins[i] < j)
                    {
                        int sum = 0;
                        //���
                        for (int k = 0; k <= j / coins[i]; k++)
                        {
                            sum += dp[j - coins[i] * k];
                        }
                        //������Ըպ���������������һ�ַ���
                        if (j % coins[i] == 0)
                        {
                            sum++;
                        }
                        //��ͺ�ֵ
                        dp[j] = sum;
                    }
                }
            }
        }
        //dp����������ˣ�
        return dp[s];
    }
}
