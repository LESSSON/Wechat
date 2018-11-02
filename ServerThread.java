import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class ServerThread implements Runnable {
    // 定义当前线程所处理的Socket
    private Socket socket = null;
    // 该线程所处理的Socket对应的输入流
    private BufferedReader bufferedReader = null;
    private int index = 0;

    public ServerThread(Socket socket, Integer i) {
        try {
            this.socket = socket;
            // 获取该socket对应的输入流
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            String content = null;
            // 采用循环不断地从Socket中读取客户端发送过来的数据
            while ((content = bufferedReader.readLine()) != null) {
                if (index == 0) {
                    String[] m = content.split("/");
                    MyServer.sta.put(m[0], MyServer.i);
                    index = -1;
                } else {
                    String[] mes = content.split("/");
                    if (MyServer.sta.containsKey(mes[0])) {
                        Socket s = MyServer.IdSocket.get(MyServer.sta.get(mes[0]));
                        // 获取该socket对应的输出流
                        PrintStream printStream = new PrintStream(socket.getOutputStream());
                        // 向该输出流中写入要发送的内容
                        printStream.println(packMessage(content));
                    } else {
                        // 若没有正确的接受者的操作
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 对发送的内容进行处理（可能涉及缓存处理）
    private String packMessage(String content) {
        String result = null;
        result = content;
        return result;
    }

}