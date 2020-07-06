
package Server;

import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author anuj
 */

public class Primetest {

    private static int size=15;
    private static Random r=new Random();
    private static BigInteger one=new BigInteger("1");
    private static BigInteger two=new BigInteger("2");
    private static BigInteger three=new BigInteger("3");
    private static BigInteger six=new BigInteger("6");
    private static BigInteger five=new BigInteger("5");
    public static BigInteger count(BigInteger num)
    {
        BigInteger ans=one;
        for(BigInteger i=three;i.compareTo(num)<=0;i=i.add(one))
        {
            if(isprime(i)==true)
            {
                ans=ans.add(one);
            }
        }
        return ans;
    }   
    public static boolean isprime(BigInteger n)
        {
           
            if(n.compareTo(one)<=0)
            {
                return false;
            }
            if(n.compareTo(three)<=0)
            {
                return true;
            }
          
            if(n.mod(two).equals(BigInteger.ZERO)||n.mod(three).equals(BigInteger.ZERO))
            {
                return false;
            }
           for(BigInteger i=five; i.multiply(i).compareTo(n)<=0;i=i.add(six))
            {
                if(n.mod(i).equals(BigInteger.ZERO)||n.mod(i.add(two)).equals(BigInteger.ZERO))
                {
                  return false; 
                }
            }
             return true;
        }
   
 }
