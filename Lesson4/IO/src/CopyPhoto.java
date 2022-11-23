import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: 复制图片Test
 * @author: YxYL
 * @create: 2022-11-22 09:05
 **/

public class CopyPhoto {
    public static void main(String[] args) throws IOException {
        
        
        // 1.创建流对象
        // 1.1 指定数据源
        FileInputStream fis = new FileInputStream("F:\\元帅的艺术.jpg");
        // 1.2 指定目的地
        FileOutputStream fos = new FileOutputStream("test_copy.jpg");

        // 2.读写数据
        // 2.1 定义数组
        byte[] b = new byte[1024];
        // 2.2 定义长度
        int len;
        // 2.3 循环读取
        while ((len = fis.read(b))!=-1) {
            // 2.4 写出数据
            fos.write(b, 0 , len);
        }
        // 3.关闭资源
        fos.close();
        fis.close();
    }
}
