public class SingleTon_LazyMan_ThreadNoSafe
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

//����ģʽ������ʽʵ��֮һ--- �̲߳���ȫ��
class SingleTon
{
    //ͨ�����þ�̬����������ʵ�ָ���ֻ��һ������
    private static SingleTon instance;

    //������˽�л�������new�µĶ���
    private SingleTon()
    {}

    //ͨ��һ����̬�����������ض��еĶ���
    public static SingleTon getInstance()
    {
        if (instance == null)
            instance = new SingleTon();
        return instance;
    }
}