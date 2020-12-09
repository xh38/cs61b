import org.junit.Assert;
import org.junit.Test;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testequalChars(){
        boolean test1 = offByOne.equalChars('a','b');
        boolean test2 = offByOne.equalChars('b','a');
        boolean test3 = offByOne.equalChars('r','q');
        boolean test4 = offByOne.equalChars('a','a');
        boolean test5 = offByOne.equalChars('a','e');
        boolean test6 = offByOne.equalChars('z','a');
        Assert.assertTrue(test1);
        Assert.assertTrue(test2);
        Assert.assertTrue(test3);
        Assert.assertFalse(test4);
        Assert.assertFalse(test5);
        Assert.assertFalse(test6);

    }
}
