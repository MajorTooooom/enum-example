import demo2.Week;
import org.junit.Test;

/**
 * @ClassName test1
 * @Description TODO
 * @Author 多罗罗丶
 * @Date 2020/8/28 0028 11:27
 * @Version 1.0
 */
public class test2 {

    @Test
    public void test2() {
        System.out.println(Week.MONDAY.getCode());
        System.out.println(Week.WEDNESDAY.getCode());
        System.out.println(Week.THURSDAY.getCode());
        System.out.println(Week.FRIDAY.getCode());
        System.out.println(Week.SATURDAY.getCode());
        System.out.println(Week.SUNDAY.getCode());
    }

    @Test
    public void test_2() {
        System.out.println(Week.MONDAY);
        System.out.println(Week.WEDNESDAY);
        System.out.println(Week.THURSDAY);
        System.out.println(Week.FRIDAY);
        System.out.println(Week.SATURDAY);
        System.out.println(Week.SUNDAY);
    }



}
