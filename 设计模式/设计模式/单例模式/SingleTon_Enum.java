public class SingleTon_Enum
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
                    SingleTon singleTon2 = SingleTon.SINGLETON;
                    System.out.println(singleTon2.hashCode());
                }
            }.start();
        }
    }
}

//单例模式的饿汉式实现之一---枚举的方式
enum SingleTon
{
	SINGLETON;
}