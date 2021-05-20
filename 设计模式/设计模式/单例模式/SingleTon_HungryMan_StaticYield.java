public class SingleTon_HungryMan_StaticYield
{
    public static void main(String[] args)
    {
        //尝试创建两个对象
        SingleTon singleTon1 = SingleTon.getInstance();
        SingleTon singleTon2 = SingleTon.getInstance();

        System.out.println(singleTon1 == singleTon2);
    }
}

//单例模式的饿汉式实现之一---静态常量
class SingleTon
{
    //通过设置静态常量，可以实现该类只有一个对象
    private final static SingleTon singleTon = new SingleTon();

    //构造器私有化，放置new新的对象
    private SingleTon()
    {}

    //通过一个静态方法，来返回独有的对象
    public static SingleTon getInstance()
    {
        return singleTon;
    }
}