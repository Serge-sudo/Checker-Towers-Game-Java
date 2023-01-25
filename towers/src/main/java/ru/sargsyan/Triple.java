package ru.sargsyan;

/**
 * tuple with 3 object, perhaps with different types
 *
 * @param <T> first object
 * @param <U> second object
 * @param <Z> third object
 */
public class Triple<T, U, Z> {
    /**
     * first obj
     */
    private final T first;
    /**
     * second obj
     */
    private final U second;
    /**
     * third obj
     */
    private final Z third;

    /**
     * Triple constructor
     *
     * @param f first obj
     * @param s second obj
     * @param t third obj
     */
    public Triple(T f, U s, Z t) {
        this.first = f;
        this.second = s;
        this.third = t;
    }

    /**
     * first el
     * @return T el
     */
    public T getFirst() {
        return first;
    }

    /**
     * second el
     * @return U el
     */
    public U getSecond() {
        return second;
    }

    /**
     * third el
     * @return Z el
     */
    public Z getThird() {
        return third;
    }
}
