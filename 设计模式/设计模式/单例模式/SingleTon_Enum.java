public class SingleTon_Enum
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
                    SingleTon singleTon2 = SingleTon.SINGLETON;
                    System.out.println(singleTon2.hashCode());
                }
            }.start();
        }
    }
}

//����ģʽ�Ķ���ʽʵ��֮һ---ö�ٵķ�ʽ
enum SingleTon
{
	SINGLETON;
}