package clone;

public class DeepClone implements Cloneable {

    private int a;   // 基本类型

    private int[] b; // 非基本类型

    // 重写Object.clone()方法,并把protected改为public
    @Override
    public Object clone() {
        DeepClone sc = null;
        try {
            sc = (DeepClone) super.clone();
            int[] t = sc.getB();
            int[] b1 = new int[t.length];
            for (int i = 0; i < b1.length; i++) {
                b1[i] = t[i];
            }
            sc.setB(b1);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return sc;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int[] getB() {
        return b;
    }

    public void setB(int[] b) {
        this.b = b;
    }

    // 深克隆，会new出新的非基本类型，两个对象持有不同的引用，不会相互影响
    public static void main(String[] args) throws CloneNotSupportedException {
        DeepClone c1 = new DeepClone();
        //对c1赋值
        c1.setA(100);
        c1.setB(new int[] { 1000 });

        System.out.println("克隆前c1:  a=" + c1.getA() + " b=" + c1.getB()[0]);
        //克隆出对象c2,并对c2的属性A,B,C进行修改
        DeepClone c2 = (DeepClone) c1.clone();
        //对c2进行修改
        c2.setA(50);
        int[] a = c2.getB();
        a[0] = 5;
        c2.setB(a);
        System.out.println("克隆后c1:  a=" + c1.getA() + " b=" + c1.getB()[0]);
        System.out.println("克隆后c2:  a=" + c2.getA() + " b[0]=" + c2.getB()[0]);
    }
}