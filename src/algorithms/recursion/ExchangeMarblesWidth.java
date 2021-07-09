package algorithms.recursion;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Exchange marbles problem with a width first solution which is a lot more effective than depth first.
 * The problem: To have an equal amount of each color of marbles, how many changes is needed based on the initial
 * values?
 *
 * @author mIngemarsson
 *
 */
public class ExchangeMarblesWidth {

    public static int exchangeMarbles(int red, int white, int blue){
        Queue<State> q = new LinkedList<>();
        State t = new State(red,white,blue,0);
        while(t.swaps<=15 && !t.equal) {
            int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE, third = Integer.MAX_VALUE;
            if(t.red>t.white)
                q.offer(new State(t.red-1,t.white+1,t.blue+2, t.swaps+1));
            if(t.white>t.blue)
                q.offer(new State(t.red+4,t.white-1,t.blue+3, t.swaps+1));
            if(t.blue>t.red)
                q.offer(new State(t.red+1,t.white+3,t.blue-1, t.swaps+1));
            t=q.poll();
        }
        return t.swaps;
    }

    private static class State {
        public int swaps ,red ,white, blue;
        public boolean equal;
        public State(int red, int white, int blue, int n){
            this.red = red;
            this.white = white;
            this.blue = blue;
            this.equal = red==white && white==blue;
            this.swaps = n;
        }
    }

    public static void main(String[] args){
        int nbrOfChanges = exchangeMarbles(3,2,2);
        System.out.println("Number of changes: " + nbrOfChanges);
    }
}
