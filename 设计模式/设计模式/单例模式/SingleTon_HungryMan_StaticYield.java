public class SingleTon_HungryMan_StaticYield
{
    public static void main(String[] args)
    {
        //���Դ�����������
        SingleTon singleTon1 = SingleTon.getInstance();
        SingleTon singleTon2 = SingleTon.getInstance();

        System.out.println(singleTon1 == singleTon2);
    }
}

//����ģʽ�Ķ���ʽʵ��֮һ---��̬����
class SingleTon
{
    //ͨ�����þ�̬����������ʵ�ָ���ֻ��һ������
    private final static SingleTon singleTon = new SingleTon();

    //������˽�л�������new�µĶ���
    private SingleTon()
    {}

    //ͨ��һ����̬�����������ض��еĶ���
    public static SingleTon getInstance()
    {
        return singleTon;
    }
}