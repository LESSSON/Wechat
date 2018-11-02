import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

//import com.sun.xml.internal.bind.v2.runtime.Name;

public class MyServer {
    // 定义ServerSocket的端口号
    private static final int SOCKET_PORT = 50000;

    // 使用ArrayList存储所有的Socket
    // public static ArrayList<ManageClients> socketList = new
    // ArrayList<ManageClients>();
    public static HashMap<Integer, Socket> IdSocket = new HashMap<Integer, Socket>();
    public static Integer i = 0; // 记录客户端数量
    public static HashMap<String, Integer> sta = new HashMap<String, Integer>();// 记录用户名字与ID的键值对

    public void initMyServer() {
        try {
            // 创建一个ServerSocket，用于监听客户端Socket的连接请求
            ServerSocket serverSocket = new ServerSocket(SOCKET_PORT); // 创建一个ServerSocket，用于监听客户端Socket的连接请求
            // Socket socket = null;
            System.out.println("服务器启动！");
            // 循环监听等待客户端的链接
            while (true) {
                // 每当接受客户端的Socket请求，服务器端也相应的创建一个Socket
                Socket socket = serverSocket.accept(); // 每当接收到客户端的Socket请求，服务器端也相应的创建一个Socket
                i++;
                IdSocket.put(i, socket);

                // String ip = socket.getLocalAddress().getHostAddress();//
                // 创建线程并启动
                // ServerThread st = new ServerThread(socket, ip);
                new Thread(new ServerThread(socket, i)).start();
                // ManageClients.addClients(ip, st);// 将该线程添加到ManageClients类中
                // int port = socket.getPort();
                // System.out.println("连接上的客户端ip:"+ ip + ",端口号:" + port);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        MyServer myServer = new MyServer();
        myServer.initMyServer();
    }

}
