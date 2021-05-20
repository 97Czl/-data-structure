public class OpenClosePriciple
{
    public static void main(String[] args)
    {
        DrawShape ds = new DrawShape();

        //使用者完全都是封闭的，只提供使用接口
        ds.drawAShape(new Circle());
        ds.drawAShape(new Rectangle());
    }
}

//面向使用者的类
class DrawShape
{
    public void drawAShape(Shape shape)
    {
        shape.draw();
    }
}

//面向提供方的类，需要扩展新功能的时候，可以直接添加即可
interface Shape
{
    void draw();
}

//扩展功能，只要继承类即可，即对提供方扩展开放，对使用方修改关闭
class Circle implements Shape
{
    @Override
    public void draw() {
        System.out.println(" 绘制圆形 ");
    }
}
class Triangle implements Shape
{
    @Override
    public void draw() {
        System.out.println(" 绘制三角形 ");
    }
}
class Rectangle implements Shape
{
    @Override
    public void draw() {
        System.out.println(" 绘制矩形 ");
    }
}