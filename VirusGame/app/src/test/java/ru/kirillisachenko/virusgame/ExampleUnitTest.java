package ru.kirillisachenko.virusgame;

import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        MathGenerator mathGenerator = new MathGenerator();
        for (int i = 0; i < 20; i++) {
            System.out.println(mathGenerator.getRandom(5, 0));
        }
    }


}