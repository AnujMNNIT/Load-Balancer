
package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nikita
 */
public class WorkerTask2 implements Runnable
{
    private Socket loadBalancerSocket;
    WorkerTask2(Socket loadBalancerSocket)
    {
        this.loadBalancerSocket = loadBalancerSocket;
    }
    @Override
    public void run() 
    {
            BufferedWriter lbWriter = null;
        try {
            lbWriter = new BufferedWriter(new OutputStreamWriter(loadBalancerSocket.getOutputStream(), StandardCharsets.UTF_8));
           
        } catch (IOException ex) {
            Logger.getLogger(WorkerTask2.class.getName()).log(Level.SEVERE, null, ex);
        }
            BufferedReader lbReader = null;
        try {
            lbReader = new BufferedReader(new InputStreamReader(loadBalancerSocket.getInputStream(), StandardCharsets.UTF_8));
           
        } catch (IOException ex) {
            Logger.getLogger(WorkerTask2.class.getName()).log(Level.SEVERE, null, ex);
        }
            BigInteger bg = null;
        try {
            String s=lbReader.readLine();
            System.out.println(s);
            bg = new BigInteger(s);
        } catch (IOException ex) {
            Logger.getLogger(WorkerTask2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            bg=Primetest.count(bg);
            lbWriter.write(bg.toString()+ "\n");
        } catch (IOException ex) {
            Logger.getLogger(WorkerTask2.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {       
            lbWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(WorkerTask2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
