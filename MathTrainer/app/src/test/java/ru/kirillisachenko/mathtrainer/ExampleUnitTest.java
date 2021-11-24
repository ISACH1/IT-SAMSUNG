package ru.kirillisachenko.mathtrainer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    MathGenerator mathGenerator = new MathGenerator();
    @Test
    public void test() {
         int random = mathGenerator.getRandom(10, -10);
        assert (-10 <= random && random <= 10);
    }
    @Test
    public void test2(){
        for (int i = 0; i <10 ; i++) {
            int a = mathGenerator.getRandom(100, - 100);
            int b = mathGenerator.getRandom(100, - 100);
            assert a!=b;


        }

    }
    @Test
    public void test3() {
        for (int i = 0; i < 10; i++) {
            int a = mathGenerator.getRandom(3, 1);
            System.out.println(a);
        }
    }
    @Test
    public void test4() {
        String s = "0.56";
        int a = Integer.parseInt(String.valueOf(s.charAt(0)));
        int result = (int) a + 1;
        System.out.println(s.charAt(0));
        System.out.println(result);
        }
    }
