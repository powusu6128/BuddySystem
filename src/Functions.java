
public class Functions {
	
	public static boolean isPowerOfTwo(long number) {
        if(number <=0){
            throw new IllegalArgumentException("number: " + number + ". Number cannot be neagative.");
        }
        if ((number & -number) == number) {
            return true;
        }
        return false;
    }
	
    public static int log2(long num){
    	return (int) (Math.log(num)/Math.log(2));
    }
}
