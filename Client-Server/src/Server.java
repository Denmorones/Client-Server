
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String args[]) {

        String pass = "password"; // пароль для доступа на сервер
        try {

            ServerSocket ss = new ServerSocket(3456); //создаем серверный сокет
            System.out.println("Waiting...");
            Socket socket = ss.accept(); //переходим в состояние ожидания соединений от клиента
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            String line = null;

            while (true) {
                line = in.readUTF();
                System.out.println("The client sent me this line : " + line);
                if (line.equals(pass)) {
                    System.out.println("Client connected"); // если мы дошли до этой строки, значит снами соединился клиент
                    System.out.println("I'm sending...");
                    out.writeUTF("Connected");
                    out.flush();
                    System.out.println("I'm send");
                    try(FileInputStream fin = new FileInputStream("D:/Java/Laba13/in.txt");
                        BufferedReader br = new BufferedReader(new InputStreamReader(fin));) {
                        String strLine;
                        while ((strLine = br.readLine()) != null) {
                            out.writeUTF(strLine);
                            out.flush();
                        }
                        fin.close();
                    } catch (IOException e) {
                        System.out.println("Error");
                    }
                    out.writeUTF("eof");
                    out.flush();
                    System.out.println("Waiting for the next line...");
                    System.out.println();
                } else {
                    System.out.println("Client entered wrong password.");
                    out.writeUTF("Wrong password, please try again!");
                    out.flush();
                    System.out.println("Waiting for the next line...");
                    System.out.println();
                }
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}