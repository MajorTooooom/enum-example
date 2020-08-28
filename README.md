<h1 align="center">彻底弄清楚Enum学习</h1>

> enum是如何表示我们想要的数值的？



# 基础模型

```java
/**
 * 基础模型
 */
public enum Week {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
```

测试

```java
    @Test
    public void test1() {
        System.out.println(Week.MONDAY);
        System.out.println(Week.WEDNESDAY);
        System.out.println(Week.THURSDAY);
        System.out.println(Week.FRIDAY);
        System.out.println(Week.SATURDAY);
        System.out.println(Week.SUNDAY);
    }
```

打印结果

```java
MONDAY
WEDNESDAY
THURSDAY
FRIDAY
SATURDAY
SUNDAY
```

可以看出打印的结果就是我们定义。

> 如果我希望每一个对应一个数字呢？因为我们的目的是像以前那些实现这样子：
>
> ​    public static final int MONDAY =1;     public static final int TUESDAY=2;     public static final int WEDNESDAY=3;     public static final int THURSDAY=4;     public static final int FRIDAY=5;     public static final int SATURDAY=6;     public static final int SUNDAY=7;



# 改造构造函数

```java
/**
 * 构造函数改造
 */
public enum Week {

    MONDAY(1, "星期一"),
    TUESDAY(2, "星期二"),
    WEDNESDAY(3, "星期三"),
    THURSDAY(4, "星期四"),
    FRIDAY(5, "星期五"),
    SATURDAY(6, "星期六"),
    SUNDAY(7, "星期日");//当想往枚举类加方法的时候这个后面必须有分号

    private int code;
    private String name;

    Week(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
```

测试：

```java
@Test
public void test2() {
    System.out.println(Week.MONDAY.getCode());
    System.out.println(Week.WEDNESDAY.getCode());
    System.out.println(Week.THURSDAY.getCode());
    System.out.println(Week.FRIDAY.getCode());
    System.out.println(Week.SATURDAY.getCode());
    System.out.println(Week.SUNDAY.getCode());
}
//打印结果
1
3
4
5
6
7
```

用get出来的方法也太low了，再次改造：

# toString()改造

为了达到直接点就能出数字的目的：

```java
@Override
public String toString() {
    return this.getCode() + "";
}
```

这样当我们**System.out.println(Week.SUNDAY);**的时候，实际调用的是对应的toString方法：System.out.println(**Week.SUNDAY.toString()**);

目的达成。



# 原理分析



这里强烈推荐[《深入理解Java枚举类型(enum)_zejian的博客-CSDN博客》](https://blog.csdn.net/javazejian/article/details/71333103?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param)的文章，此老哥利用反编译Java虚拟机生成Enum的类文件看出了原理，这里说几个个人认为比较关键的点；

>实际上在使用关键字enum创建枚举类型并编译后，编译器会为我们生成一个相关的类，这个类继承了Java API中的java.lang.Enum类，也就是说通过关键字enum创建枚举类型在编译后事实上也是一个类类型而且该类继承自java.lang.Enum类。

玄乎？原理如此，之所以难理解，就是JDK级别的封装而已，既然如此，看看生成了啥：

```java
final class Week extends Enum{
    //私有化了构造器
    private Day(String s, int i) {
        super(s, i);
    }
    //前面定义的7种枚举实例都生成了其对应的私有变量，就是其类型本身
    public static final Week MONDAY;
    //省略其他6个.....
    
    //静态代码块直接加载
    static {
        //实例化枚举实例
        MONDAY = new Day("MONDAY", 0);
        //...
        $VALUES = (new Day[]{
                MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
        });
    }

}
```

可以看出我们定义的枚举类型实际会变成一个其对应类型的成员属性而已，即：

```java
//转换前
public enum Week {
    MONDAY
}

//转换后
final class Week extends Enum{
    public static final Week MONDAY;
}
```



还有其他的高级用法暂时还没有探究











# 其他

参考资料：[(2条消息)深入理解Java枚举类型(enum)_zejian的博客-CSDN博客](https://blog.csdn.net/javazejian/article/details/71333103?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param)

