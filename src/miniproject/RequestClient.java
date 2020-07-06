
package miniproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ThreadLocalRandom; 
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author anuj
 */
class RequestClient implements Runnable{
    private Socket loadBalancerSocket;
    RequestClient(Socket loadBalancerSocket){
        this.loadBalancerSocket = loadBalancerSocket;
    }
    @Override
    public void run() {
      //  System.out.println("Hello");
         BufferedWriter lbWriter = null;
        try {
            lbWriter = new BufferedWriter(new OutputStreamWriter(loadBalancerSocket.getOutputStream(), StandardCharsets.UTF_8));
        } catch (IOException ex) {
            Logger.getLogger(RequestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
         BufferedReader lbReader = null;
        try {
            lbReader = new BufferedReader(new InputStreamReader(loadBalancerSocket.getInputStream(), StandardCharsets.UTF_8));
        } catch (IOException ex) {
            Logger.getLogger(RequestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
         int mod=(int)Math.pow(10, new Random().nextInt(8));
         int rand = ThreadLocalRandom.current().nextInt()%mod;
         if(rand<0)
             rand=Math.abs(rand);
         if(rand<2)
             rand=1000;
         
        try {
            lbWriter.write(Integer.toString(rand)+ "\n");
           //  System.out.println("Hello");
        } catch (IOException ex) {
            Logger.getLogger(RequestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            lbWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(RequestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        String answer = null;
        try {
            answer = lbReader.readLine();
           //  System.out.println("Hello");
        } catch (IOException ex) {
            Logger.getLogger(RequestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
         System.out.println("Number of Prime Numbers between 1 to "+Integer.toString(rand)+" is "+ answer);
       
    }
}
