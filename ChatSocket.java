import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.ActionListener;

public class ChatSocket extends Thread {

    private Socket socket;
    private String message = null;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    public ChatSocket(Socket s) {
        this.socket = s;
        try{
            this.bufferedReader = new BufferedReader(new InputStreamReader(s.getInputStream(),"UTF-8"));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(s.getOutputStream(),"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace;
        }
    }

    @Override
    public void run() {
        try{
            String line = null;
            while((line = bufferedReader.readLine()) != null){
                if(!line.equals("-1")){
                    message += line;
                }else{
                    delMessage(message);
                    line = null;
                    message = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                MainWindow.getMainWindow().setShowMsg(this.username + "login out!");
                MainWindow.getMainWindow().removeOfflineUsers(this.username);
                
            }
        }
    }
}