public class SingleTon_LazyMan_ThreadNoSafe
{
	public static void main(String[] args)
    {
        for (int i = 0; i < 10; i++)
        {
            //创建10个线程，来创建多个对象，看会不会出现线程错误
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

//单例模式的懒汉式实现之一--- 线程不安全的
class SingleTon
{
    //通过设置静态常量，可以实现该类只有一个对象
    private static SingleTon instance;

    //构造器私有化，放置new新的对象
    private SingleTon()
    {}

    //通过一个静态方法，来返回独有的对象
    public static SingleTon getInstance()
    {
        if (instance == null)
            instance = new SingleTon();
        return instance;
    }
}