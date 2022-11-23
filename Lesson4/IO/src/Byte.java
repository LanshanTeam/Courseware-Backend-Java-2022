import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: 字节流Test
 * @author: YxYL
 * @create: 2022-11-22 09:02
 **/

public class Byte {

    public static void main(String[] args) throws IOException {
        //根据文件夹的名字来创建对象
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\hello.txt");
        //往文件里面一个字节一个字节的写入数据
        fileOutputStream.write((int)'h');
        fileOutputStream.write((int)'e');
        fileOutputStream.write((int)'l');
        fileOutputStream.write((int)'l');
        fileOutputStream.write((int)'o');

        
        //入文件里面一个字节数组的写入文件
        String s = " world";
        fileOutputStream.write(s.getBytes());
        fileOutputStream.close();
        
        
        
        //传文件夹的名字来创建对象
        FileInputStream fileInputStream = new FileInputStream("F:\\hello.txt");
        int len = 0;
        //一个字节一个字节的读出数据
        while((len = fileInputStream.read()) != -1){
            System.out.println((char)len);
        }
        //关闭流
        fileInputStream.close();
        //通过File对象来创建对象
        fileInputStream = new FileInputStream("F:\\hello.txt");
        
        
        byte []bytes = new byte[1024];
        //一个字节数组的读出数据
        while ((len = fileInputStream.read(bytes)) != -1){
            for(int i = 0; i< len ; i++){
                System.out.print((char) bytes[i]);
            }
        }
        //关闭流
        fileInputStream.close();
    }
}
