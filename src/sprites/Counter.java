package sprites;
/**
 * @author Dor Huri
 * this class is for counter which count objects.
 */
public class Counter {
    private int val; //the value of the counter
    /**
     * this method is the constructor method for class Counter.
     * @param value counter
     */
    public Counter(int value) {
        this.val = value;
    }
    // add number to current count.
    /**
     * this method add number to current count.
     * @param number the number we add to counter
     */
    public void increase(int number) {
        this.val += number;
    }
    // subtract number from current count.
    /**
     * this method subtract number from current count.
     * @param number the number we add to counter
     */
    public void decrease(int number) {
        this.val -= number;
    }
    // get current count.
    /**
     * this method get current count.
     * @return the current count
     */
    public int getValue() {
        return this.val;
    }
}
