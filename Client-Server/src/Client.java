import java.io.*;
import java.net.*;

public class Client {

    public static void main(String args[]) {
            try(Socket socket = new Socket("localhost", 3456); //соединение с сервером localhost, порт 3456
                InputStream sin = socket.getInputStream();
                OutputStream sout = socket.getOutputStream();

                DataInputStream in = new DataInputStream(sin);
                DataOutputStream out = new DataOutputStream(sout);

                BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter fout = new BufferedWriter( new FileWriter("D:/Java/Laba13/fout.txt"));) {

                String line = null;
                System.out.println("Enter the password");
                while(true){
                    line = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                    System.out.println("Sending password to the server...");
                    out.writeUTF(line); // отсылаем введенную строку текста серверу.
                    out.flush();
                    line = in.readUTF(); // ждем пока сервер отошлет строку текста.
                    System.out.println("The server sent me this : " + line);
                    if(line.equals("Connected")){
                        System.out.println("Start writing into file.");
                        while (true){
                            line = in.readUTF();
                            if(line.equals("eof")) break;
                            fout.write(line + "\r\n");
                            System.out.println("The server sent me this : " + line);
                        }
                        fout.close();
                    }
                    System.out.println("The server sent me this : " + line);
                    System.out.println("Enter the password");
                    System.out.println();
                }
            } catch (UnknownHostException e) { // на тот случай если сервер не будет найден
                e.printStackTrace();
            } catch (IOException e) { // на тот случай если возникнет ошибка при обмене по сети
                e.printStackTrace();
            }
    }
}

