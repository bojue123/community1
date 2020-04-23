package life.ajie.socket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatUiServer {
    private final String myName = "小李";// 我的姓名
    private final String friendName = "小王";// 对方姓名
    // 多行文本显示区
    private JTextArea textArea_show;


         // 多行文本输入
    private JTextArea textArea_input;  // 发送数据按钮
    private JButton btn_send;
    // PrintWriter是一个非常实用的输出流。PrintWriter是一种过滤流，也叫处理流， 能对字节流和字符流进行处理
    private PrintWriter printWriter;

    public void buildFrame() {
        JFrame chat_frame = new JFrame();
        chat_frame.setTitle("QQ聊天对话框");
        chat_frame.setSize(450, 480);
        chat_frame.setLocationRelativeTo(null);
        chat_frame.setLayout(new FlowLayout());

        // 设置显示区和输入区的大小
        textArea_show = new JTextArea(16, 35);
        textArea_input = new JTextArea(8, 35);
        btn_send = new JButton("发送数据");

        // 将显示文本域设置为不可编辑
        textArea_show.setEditable(false);
        chat_frame.add(textArea_show);
        chat_frame.add(textArea_input);
        chat_frame.add(btn_send);
        chat_frame.setVisible(true);
    }


        // 发送按钮绑定监听器
    public void btnSend() {
        btn_send.addActionListener(new ActionListener() {
            @Override    public void actionPerformed(ActionEvent e) {
                // 获取当前时间
                Date currTime = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // 获取输入区文本
                String info = textArea_input.getText();
                textArea_show.append(df.format(currTime) + " " + myName + "： " + info + "\r\n");     // 发送消息


        printWriter.println(info);     // 清空输入区
                 textArea_input.setText("");
            }
        });
    }

    public void socket() throws IOException {
        ServerSocket ss;
        try {    ss = new ServerSocket(9988);    // 等待连接 客户端
             Socket s = ss.accept();
             InputStreamReader isr = new InputStreamReader(s.getInputStream());
             BufferedReader br = new BufferedReader(isr);
             // 通过现有的 OutputStream 创建新的 PrintWriter，true代表能自动刷新。
            printWriter = new PrintWriter(s.getOutputStream(), true);

            // 读取从客户端法发来的信息
            while (true) {
                // 获取当前时间
                Date currTime = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // 读取从客户端发来的信息
                String info = br.readLine();
                // 在文本栏里显示
                textArea_show.append(df.format(currTime) + " " + friendName + ":" + info + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void executeProcess() throws IOException {   // 1 生成主框架界面
        buildFrame();   // 2 添加发送按钮监听
        btnSend();   // 3 socket通信
        socket();


            }

    public static void main(String[] args) throws IOException {
        ChatUiServer chatUiServer = new ChatUiServer();
        chatUiServer.executeProcess();  }
}
