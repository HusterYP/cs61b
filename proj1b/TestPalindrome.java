import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

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
    public void testIsPalindromeV1() {
        assertTrue(palindrome.isPalindrome(null));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("Racecar"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertTrue(palindrome.isPalindrome("noon"));
    }

    @Test
    public void testIsPalindromeV2() {
        CharacterComparator comparator = new OffByOne();
        assertTrue(palindrome.isPalindrome(null, comparator));
        assertTrue(palindrome.isPalindrome("", comparator));
        assertTrue(palindrome.isPalindrome("flake", comparator));
        assertFalse(palindrome.isPalindrome("flakf", comparator));
    }
}
