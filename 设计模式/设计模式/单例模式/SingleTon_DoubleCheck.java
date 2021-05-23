public class SingleTon_DoubleCheck
{
	public static void main(String[] args)
    {
        for (int i = 0; i < 10; i++)
        {
            //����10���̣߳�������������󣬿��᲻������̴߳���
            new Thread()
            {
                @Override
                public void run() {
                    SingleTon singleTon2 = SingleTon.getInstance();
                    System.out.println(singleTon2.hashCode());
                }
            }.start();
        }
    }
}

//����ģʽ��˫�ؼ��
class SingleTon
{
    //ͨ�����þ�̬����������ʵ�ָ���ֻ��һ������
    /*
    ��1.5��ʼ������volatile�ؼ��ֵ����ã����ĳ�ʼ���Ͳ����ǣ�
    - a. �ȷ����ڴ棬��instanceָ������ڴ� =====>��ʱ����ʱ��Ƭ���⣬���ܻ᷵��δ��������ĵ�ַ�ռ䣬����bug
    - b. ���ڴ��д�������

    ��Ӧ���ǣ�
    - a.���ڴ��д�������
    - b.��instanceָ���������.

    ������ʽ��Ҳ�ͱ��������������⡣
     */
    private static volatile SingleTon instance;

    //������˽�л�������new�µĶ���
    private SingleTon()
    {}

    //ͨ��һ����̬�����������ض��еĶ���
    public static SingleTon getInstance()
    {
        if (instance == null)
        {
            //�����Ҫ�������������� synchronizedͬ��
            synchronized(SingleTon.class)
            {
                if (instance == null)
                    instance = new SingleTon();
            }
        }
        return instance;
    }
}