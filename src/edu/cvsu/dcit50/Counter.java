package edu.cvsu.dcit50;

/**
 *
 * @author rlvillacarlos
 */
public class Counter implements Comparable<Counter> {
    private int count;

    public boolean increment(){
        if(this.count < Integer.MAX_VALUE){
            this.count++;
            return true;
        }
        return false;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int hashCode() {
        return this.count;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Counter other = (Counter) obj;
        return this.count == other.count;
    }

    @Override
    public String toString() {
        return String.format("Counter: %d", this.count);
    }
    
    @Override
    public int compareTo(Counter o) {
        if (this.count > o.count){
            return 1;
        }else if(this.count < o.count){
            return -1;
        }
        return 0;
    }
}
