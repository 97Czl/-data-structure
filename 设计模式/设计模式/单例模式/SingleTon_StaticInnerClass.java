public class SingleTon_StaticInnerClass
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

//��̬�ڲ���
class SingleTon
{
    //��̬�ڲ���
    private static final class SingleTonInstance
    {
        //�ڲ������Ǿ�̬��Ա��������֤Ψһ��
        //��̬�ڲ����õ���ʱ��Ż���أ��Ҽ��ص�ʱ��
        private static final SingleTon INSTANCE = new SingleTon();
    }

    //������˽�л�������new�µĶ���
    private SingleTon()
    {}

    //ͨ��һ����̬�����������ض��еĶ���
    public static SingleTon getInstance()
    {
        return SingleTonInstance.INSTANCE;
    }
}