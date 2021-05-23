public class SingleTon_StaticInnerClass
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

//静态内部类
class SingleTon
{
    //静态内部类
    private static final class SingleTonInstance
    {
        //内部必须是静态成员变量，保证唯一性
        //静态内部类用到的时候才会加载，且加载的时候
        private static final SingleTon INSTANCE = new SingleTon();
    }

    //构造器私有化，放置new新的对象
    private SingleTon()
    {}

    //通过一个静态方法，来返回独有的对象
    public static SingleTon getInstance()
    {
        return SingleTonInstance.INSTANCE;
    }
}