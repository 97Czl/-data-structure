public class ReverseDependence
{
    public static void main(String[] args)
    {
        HuiPu hp = new HuiPu();

        //第一种实现方式：利用接口传递
        OpenAndClass oac = new OpenAndClass();
        oac.open(hp);

//        //第二种实现方式：利用构造器实现
//        OpenAndClass oac = new OpenAndClass(hp);
//        oac.open();

//        //第三种方法：利用setter实现
//        OpenAndClass oac = new OpenAndClass();
//        oac.setItv(hp);
//        oac.open();
    }
}

//第一种实现方式：利用接口传递
class OpenAndClass implements IOpenAndClose
{
    @Override
    public void open(ITV itv)
    {
        itv.play();
    }
}
//执行动作的接口
interface IOpenAndClose
{
    //需要一个主体来实现
    void open(ITV itv);
}

////第二种实现方式：利用构造器实现
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
////执行动作的接口
//interface IOpenAndClose
//{
//    //需要一个主体来实现
//    void open();
//}

////第三种方法：利用setter实现
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
////执行动作的接口
//interface IOpenAndClose
//{
//    //需要一个主体来实现
//    void open();
//}

//实现了ITV的电视类
class HuiPu implements ITV
{
    @Override
    public void play() {
        System.out.println("惠普开始播放");
    }
}


//具体的电视类的接口
interface ITV
{
    void play();
}

