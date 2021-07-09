package algorithms.recursion;

/**
 * Exchange marbles problem with a depth first recursive solution.
 * The problem: To have an equal amount of each color of marbles, how many changes is needed based on the initial
 * values?
 * n=15 has been set as a max depth.
 *
 * @author mIngemarsson
 */
public class ExchangeMarblesDepth {

    /**
     * This method takes the initial amount of marbles of each color.
     * @param red marbles can be replaced by 2 blue and 1 white marble
     * @param white marbles can be replaced by 3 blue and 4 red marbles
     * @param blue marbles can be replaced by 3 white and 1 red marble
     * @return Amount of exchanges needed for a equal amount of marbles
     */
    public static int exchangeMarbles(int red, int white, int blue){
        return exchangeMarbles(red,white,blue, 0);
    }

    private static int exchangeMarbles(int red, int white, int blue, int n){
        if(n>=15 || (red==white && white==blue)){
            return n;
        }else{
            int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE, third = Integer.MAX_VALUE;
            if(red>white)
                first = exchangeMarbles(red-1,white+1,blue+2, n+1);
            if(white>blue)
                second = exchangeMarbles(red+4,white-1,blue+3, n+1);
            if(blue>red)
                third= exchangeMarbles(red+1,white+3,blue-1, n+1);
            return Math.min(first, Math.min(second,third));
        }
    }

    public static void main(String[] args){
        int nbrOfChanges = exchangeMarbles(3,2,2);
        System.out.println("Number of changes: " + nbrOfChanges);
    }
}
