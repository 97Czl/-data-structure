public class SingleTon_HungryMan_StaticCode
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

//����ģʽ�Ķ���ʽʵ��֮һ---��̬����
class SingleTon
{
    //ͨ�����þ�̬����������ʵ�ָ���ֻ��һ������
    private static SingleTon singleTon;

	//��̬������ʼ��
	static{
		 singleTon = new SingleTon();
	}

    //������˽�л�������new�µĶ���
    private SingleTon()
    {}



    //ͨ��һ����̬�����������ض��еĶ���
    public static SingleTon getInstance()
    {
        return singleTon;
    }
}