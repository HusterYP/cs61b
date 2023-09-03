import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution();

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                studentDeque.addLast(i);
                solution.addLast(i);
            } else {
                studentDeque.addFirst(i);
                solution.addFirst(i);
            }
        }

        assertEquals(getSequence(solution), studentDeque.size(), solution.size());

        for (int i = 0; i < 10; i += 1) {
            assertFalse(getSequence(solution), studentDeque.isEmpty());
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                String sequence = getSequence(solution);
                Integer source = studentDeque.removeFirst();
                Integer target = solution.removeFirst();
                assertEquals(sequence, source, target);
            } else {
                String sequence = getSequence(solution);
                Integer source = studentDeque.removeLast();
                Integer target = solution.removeLast();
                assertEquals(sequence, source, target);
            }
        }
    }

    private String getSequence(ArrayDequeSolution<Integer> solution) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < solution.size(); i++) {
            result.append(solution.get(i));
            if (i != solution.size() - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}
