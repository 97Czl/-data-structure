public class SingleTon_DoubleCheck
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

//单例模式的双重检查
class SingleTon
{
    //通过设置静态常量，可以实现该类只有一个对象
    /*
    从1.5开始，加了volatile关键字的引用，它的初始化就不能是：
    - a. 先分配内存，让instance指向这块内存 =====>此时由于时间片问题，可能会返回未创建对象的地址空间，出现bug
    - b. 在内存中创建对象

    而应该是：
    - a.在内存中创建对象
    - b.让instance指向这个对象.

    这种形式，也就避免了无序性问题。
     */
    private static volatile SingleTon instance;

    //构造器私有化，放置new新的对象
    private SingleTon()
    {}

    //通过一个静态方法，来返回独有的对象
    public static SingleTon getInstance()
    {
        if (instance == null)
        {
            //如果需要创建，首先利用 synchronized同步
            synchronized(SingleTon.class)
            {
                if (instance == null)
                    instance = new SingleTon();
            }
        }
        return instance;
    }
}