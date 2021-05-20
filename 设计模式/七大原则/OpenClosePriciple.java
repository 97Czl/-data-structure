public class OpenClosePriciple
{
    public static void main(String[] args)
    {
        DrawShape ds = new DrawShape();

        //ʹ������ȫ���Ƿ�յģ�ֻ�ṩʹ�ýӿ�
        ds.drawAShape(new Circle());
        ds.drawAShape(new Rectangle());
    }
}

//����ʹ���ߵ���
class DrawShape
{
    public void drawAShape(Shape shape)
    {
        shape.draw();
    }
}

//�����ṩ�����࣬��Ҫ��չ�¹��ܵ�ʱ�򣬿���ֱ����Ӽ���
interface Shape
{
    void draw();
}

//��չ���ܣ�ֻҪ�̳��༴�ɣ������ṩ����չ���ţ���ʹ�÷��޸Ĺر�
class Circle implements Shape
{
    @Override
    public void draw() {
        System.out.println(" ����Բ�� ");
    }
}
class Triangle implements Shape
{
    @Override
    public void draw() {
        System.out.println(" ���������� ");
    }
}
class Rectangle implements Shape
{
    @Override
    public void draw() {
        System.out.println(" ���ƾ��� ");
    }
}