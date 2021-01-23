import java.util.*;

//������ ���ϸ������� ��ʾ̰���㷨
/*
	̰���㷨����ÿһ��������ж�ѡ�����ŵĽ�����������ս�ȡ�����ս�
	�����ǲ�һ���õ��ľ������Ž⣬���Ƕ��ǽ������Ž�Ľ����

	���������� ���ڶ���㲥վ ��ôѡ�����ٵĹ㲥վ �Ը������еĳ���
	K1	������ ��� �Ϻ�
	K2	���ݣ� ������ ����
	K3	�ɶ��� �Ϻ��� ����
	K4	�Ϻ��� ���
	K5	���ݣ� ����
	˼·������ÿһ��ѭ���У���õ�ǰ�����������Ĺ㲥����ӽ�ȥ��Ȼ��ÿһ��ѭ���������޳���һ�αȽ�ʱ�Ѿ���ӵĳ���
*/
public class GreedyAlgorithhm
{
    public static void main(String[] args)
    {
        //���屣��ÿһ���㲥վ�ͳ�������HashMap
        HashMap<String, HashSet<String>> broadcast = new HashMap<>();
        //----------------------------�������-----------------------------
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("����");
        hashSet1.add("���");
        hashSet1.add("�Ϻ�");
        broadcast.put("K1", hashSet1);
        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("����");
        hashSet2.add("����");
        hashSet2.add("����");
        broadcast.put("K2", hashSet2);
        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("�ɶ�");
        hashSet3.add("�Ϻ�");
        hashSet3.add("����");
        broadcast.put("K3", hashSet3);
        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("�Ϻ�");
        hashSet4.add("���");
        broadcast.put("K4", hashSet4);
        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("����");
        hashSet5.add("����");
        broadcast.put("K5", hashSet5);
        //-----------------------------------------------------------------

        //------------------------�㷨��ʽ��ʼ-----------------------------
        //����һ���������ս��������
        ArrayList<String> result = new ArrayList<String>();
        //�������еĳ��м�
        HashSet<String> allArea = new HashSet<String>();
        //����ÿһ�α���ʱ�ĳ��м�
        HashSet<String> temp = new HashSet<String>();
        //����ÿһ�ε����ŵĽ��
        int max = 0;
        String maxCity = null;
        //�����г�����ӽ�ȥ
        allArea.add("����");
        allArea.add("���");
        allArea.add("�Ϻ�");
        allArea.add("����");
        allArea.add("����");
        allArea.add("�ɶ�");
        allArea.add("����");
        allArea.add("����");

        //��ʼѭ��
        while (allArea.size() > 0)
        {
            //ÿһ�ο�ʼʱ��max���㣬����������һ�εĽ��������һ���޷��Ա�
            max = 0;
            //ÿһ�ν����еĹ㲥��Ӧ�ĳ��к͵�ǰʣ������г��м����жԱ�
            for (Map.Entry<String, HashSet<String>> entry : broadcast.entrySet())
            {
                //��ȡ��ǰ�ȽϵĹ㲥�����г���
                temp = entry.getValue();
                //��ȡ��ǰ�ظ��ĳ��е�����
                temp.retainAll(allArea);
                //�����ǰ�����ž�����ô����
                if (temp.size() > max)
                {
                    max = temp.size();
                    maxCity = entry.getKey();
                }
                //������ǣ���һ��
            }
            //ÿһ�����й㲥����������ȥ�����������е�ǰ�ĳ��У��Լ���ӳ���
            if (maxCity != null)
            {
                //��ӽ��
                result.add(maxCity);
                //ɾ�������а����ĳ���
                allArea.removeAll(broadcast.get(maxCity));
            }
        }

        //������
        System.out.println(result);
    }
}