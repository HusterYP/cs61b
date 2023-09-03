public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        if (word == null) {
            return null;
        }
        Deque deque = new ArrayDeque<Character>();
        for (Character c : word.toCharArray()) {
            deque.addLast(c);
        }
        return deque;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return isPalindrome(wordToDeque(word), cc);
    }

    private boolean isPalindrome(Deque<Character> deque, CharacterComparator cc) {
        if (deque == null || deque.isEmpty()) {
            return true;
        }
        Character head = deque.removeFirst();
        Character tail = deque.removeLast();
        if (head == null || tail == null) {
            return true;
        } else if (!cc.equalChars(head, tail)) {
            return false;
        }
        return isPalindrome(deque, cc);
    }

    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

    private boolean isPalindrome(Deque<Character> deque) {
        if (deque == null || deque.isEmpty()) {
            return true;
        }
        Character head = deque.removeFirst();
        Character tail = deque.removeLast();
        if (head == null || tail == null) {
            return true;
        } else if (head != tail) {
            return false;
        }
        return isPalindrome(deque);
    }
}
