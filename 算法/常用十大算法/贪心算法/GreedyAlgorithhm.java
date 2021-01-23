import java.util.*;

//这里用 集合覆盖问题 显示贪心算法
/*
	贪心算法是在每一步的求解中都选择最优的结果，以求最终解取得最终解
	（但是不一定得到的就是最优解，但是都是近似最优解的结果）

	这里问题是 对于多个广播站 怎么选择最少的广播站 以覆盖所有的城市
	K1	北京， 天津， 上海
	K2	广州， 北京， 深圳
	K3	成都， 上海， 杭州
	K4	上海， 天津
	K5	杭州， 大连
	思路就是在每一次循环中，求得当前包含城市最多的广播，添加进去，然后每一次循环结束，剔除下一次比较时已经添加的城市
*/
public class GreedyAlgorithhm
{
    public static void main(String[] args)
    {
        //定义保存每一个广播站和城市名的HashMap
        HashMap<String, HashSet<String>> broadcast = new HashMap<>();
        //----------------------------添加数据-----------------------------
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("天津");
        hashSet1.add("上海");
        broadcast.put("K1", hashSet1);
        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        broadcast.put("K2", hashSet2);
        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        broadcast.put("K3", hashSet3);
        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        broadcast.put("K4", hashSet4);
        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadcast.put("K5", hashSet5);
        //-----------------------------------------------------------------

        //------------------------算法正式开始-----------------------------
        //定义一个保存最终结果的链表
        ArrayList<String> result = new ArrayList<String>();
        //保存所有的城市集
        HashSet<String> allArea = new HashSet<String>();
        //保存每一次遍历时的城市集
        HashSet<String> temp = new HashSet<String>();
        //定义每一次的最优的结果
        int max = 0;
        String maxCity = null;
        //将所有城市添加进去
        allArea.add("北京");
        allArea.add("天津");
        allArea.add("上海");
        allArea.add("广州");
        allArea.add("深圳");
        allArea.add("成都");
        allArea.add("杭州");
        allArea.add("大连");

        //开始循环
        while (allArea.size() > 0)
        {
            //每一次开始时，max置零，否则会根据上一次的结果导致这一次无法对比
            max = 0;
	    maxCity = null;
            //每一次将所有的广播对应的城市和当前剩余的所有城市集进行对比
            for (Map.Entry<String, HashSet<String>> entry : broadcast.entrySet())
            {
                //获取当前比较的广播的所有城市
                temp = entry.getValue();
                //获取当前重复的城市的数量
                temp.retainAll(allArea);
                //如果当前是最优抉择，那么保存
                if (temp.size() > max)
                {
                    max = temp.size();
                    maxCity = entry.getKey();
                }
                //如果不是，下一个
            }
            //每一次所有广播遍历结束后，去除掉总链表中当前的城市，以及添加城市
            if (maxCity != null)
            {
                //添加结果
                result.add(maxCity);
                //删除掉所有包含的城市
                allArea.removeAll(broadcast.get(maxCity));
            }
        }

        //最后输出
        System.out.println(result);
    }
}
