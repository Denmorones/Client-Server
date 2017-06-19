import java.io.*;

public class Function2 {
    public static void main(String args[]) {
        int a, d, x1, x2, h ;
        double b, c;
        double y;

        try{
            FileInputStream in = new FileInputStream("D:/Java/Laba13/in.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            BufferedWriter out = new BufferedWriter( new FileWriter("D:/Java/Laba13/out2.txt"));
            String strLine;
            while ((strLine = br.readLine()) != null){
                String[] array = strLine.split(" ");
                a = Integer.parseInt(array[0]);
                b = Double.parseDouble(array[1]);
                c = Double.parseDouble(array[2]);
                d = Integer.parseInt(array[3]);
                x1 = Integer.parseInt(array[4]);
                x2 = Integer.parseInt(array[5]);
                h = Integer.parseInt(array[6]);

                for(int x = x1; x <= x2; x += h) {
                    y = a * Math.pow(x, 2) + b * x + c * Math.pow(x, -2) + d;
                    System.out.println("x = " + x + ", y = " + y);
                    String str = "x = " + x + ", y = " + y + "\n";
                    out.write(str + "\r\n");
                }
                out.write("******\r\n");
                System.out.println("******");
            }
            in.close();
            out.close();
        }catch (IOException e){
            System.out.println("Ошибка");
        }
    }
}

