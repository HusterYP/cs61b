import static org.junit.Assert.*;

import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> studentDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                studentDeque.addLast(i);
                solution.addLast(i);
                result.append("addLast(").append(i).append(")").append("\n");
            } else {
                studentDeque.addFirst(i);
                solution.addFirst(i);
                result.append("addFirst(").append(i).append(")").append("\n");
            }
        }

        assertEquals(result.toString(), studentDeque.size(), solution.size());

        for (int i = 0; i < 10; i += 1) {
            assertFalse(result.toString(), studentDeque.isEmpty());
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                Integer source = studentDeque.removeFirst();
                Integer target = solution.removeFirst();
                result.append("removeFirst()").append("\n");
                assertEquals(result.toString(), source, target);
            } else {
                Integer source = studentDeque.removeLast();
                Integer target = solution.removeLast();
                result.append("removeLast()").append("\n");
                assertEquals(result.toString(), source, target);
            }
        }
    }

    private String getSequence(ArrayDequeSolution<Integer> solution) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < solution.size(); i++) {
            result.append(solution.get(i));
            if (i != solution.size() - 1) {
                result.append(",");
            }
        }
        return result.toString();
    }
}
