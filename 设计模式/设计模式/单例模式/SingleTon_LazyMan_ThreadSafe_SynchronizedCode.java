public class SingleTon_LazyMan_ThreadSafe_SynchronizedCode
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

    //通过一个静态方法，来返回独有的对象，加入了线程锁，保证同步安全 
    public static SingleTon getInstance()
    {
        if (instance == null)
		{
			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			//**********
			//由于已经进来了，所以并不能解决线程安全
			//**********
			synchronized(SingleTon.class)
			{
				instance = new SingleTon();
			}
		}
        return instance;
    }
}