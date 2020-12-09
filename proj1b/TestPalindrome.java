import org.junit.Test;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static OffByOne offByOne = new OffByOne();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome() {
        boolean test1 = palindrome.isPalindrome("");
        boolean test2 = palindrome.isPalindrome("a");
        boolean test3 = palindrome.isPalindrome("ab");
        boolean test4 = palindrome.isPalindrome("run");
        boolean test5 = palindrome.isPalindrome("aka");
        boolean test6 = palindrome.isPalindrome("shzlslzhs");
        boolean test7 = palindrome.isPalindrome("aa");
        assertTrue(test1);
        assertTrue(test2);
        assertFalse(test3);
        assertFalse(test4);
        assertTrue(test5);
        assertTrue(test6);
        assertTrue(test7);
    }

    @Test
    public void testisPalindromeoffbyone() {
        boolean test1 = palindrome.isPalindrome("",offByOne);
        boolean test2 = palindrome.isPalindrome("a",offByOne);
        boolean test3 = palindrome.isPalindrome("ab",offByOne);
        boolean test4 = palindrome.isPalindrome("ruq",offByOne);
        boolean test5 = palindrome.isPalindrome("aka",offByOne);
        boolean test6 = palindrome.isPalindrome("shzlslzhs",offByOne);
        boolean test7 = palindrome.isPalindrome("aa",offByOne);
        assertTrue(test1);
        assertTrue(test2);
        assertTrue(test3);
        assertTrue(test4);
        assertFalse(test5);
        assertFalse(test6);
        assertFalse(test7);
    }
}
