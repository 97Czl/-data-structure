public class ReverseDependence
{
    public static void main(String[] args)
    {
        HuiPu hp = new HuiPu();

        //��һ��ʵ�ַ�ʽ�����ýӿڴ���
        OpenAndClass oac = new OpenAndClass();
        oac.open(hp);

//        //�ڶ���ʵ�ַ�ʽ�����ù�����ʵ��
//        OpenAndClass oac = new OpenAndClass(hp);
//        oac.open();

//        //�����ַ���������setterʵ��
//        OpenAndClass oac = new OpenAndClass();
//        oac.setItv(hp);
//        oac.open();
    }
}

//��һ��ʵ�ַ�ʽ�����ýӿڴ���
class OpenAndClass implements IOpenAndClose
{
    @Override
    public void open(ITV itv)
    {
        itv.play();
    }
}
//ִ�ж����Ľӿ�
interface IOpenAndClose
{
    //��Ҫһ��������ʵ��
    void open(ITV itv);
}

////�ڶ���ʵ�ַ�ʽ�����ù�����ʵ��
//class OpenAndClass implements IOpenAndClose
//{
//    private ITV itv;
//    public OpenAndClass(ITV itv)
//    {
//        this.itv = itv;
//    }
//
//    public void open() {
//        this.itv.play();
//    }
//}
////ִ�ж����Ľӿ�
//interface IOpenAndClose
//{
//    //��Ҫһ��������ʵ��
//    void open();
//}

////�����ַ���������setterʵ��
//class OpenAndClass implements IOpenAndClose
//{
//    private ITV itv;
//
//    public void setItv(ITV itv)
//    {
//        this.itv = itv;
//    }
//
//    public void open() {
//        this.itv.play();
//    }
//}
////ִ�ж����Ľӿ�
//interface IOpenAndClose
//{
//    //��Ҫһ��������ʵ��
//    void open();
//}

//ʵ����ITV�ĵ�����
class HuiPu implements ITV
{
    @Override
    public void play() {
        System.out.println("���տ�ʼ����");
    }
}


//����ĵ�����Ľӿ�
interface ITV
{
    void play();
}

