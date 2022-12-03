/**
 * @program: Courseware-Backend-Java-2022
 * @description:
 * @author: YxYL
 * @create: 2022-11-27 20:27
 **/

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"run起来啦····");
    }


    public static void main(String[] args) {
        
        for (int i = 0; i < 10; i++) {
            new Thread(new MyRunnable()).start();
        }
        
    }
}
