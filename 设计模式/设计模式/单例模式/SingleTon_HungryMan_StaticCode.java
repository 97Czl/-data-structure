public class SingleTon_HungryMan_StaticCode
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

//单例模式的饿汉式实现之一---静态常量
class SingleTon
{
    //通过设置静态常量，可以实现该类只有一个对象
    private static SingleTon singleTon;

	//静态代码块初始化
	static{
		 singleTon = new SingleTon();
	}

    //构造器私有化，放置new新的对象
    private SingleTon()
    {}



    //通过一个静态方法，来返回独有的对象
    public static SingleTon getInstance()
    {
        return singleTon;
    }
}