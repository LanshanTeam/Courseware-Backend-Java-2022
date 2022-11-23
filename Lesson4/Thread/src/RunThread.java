import java.util.concurrent.Callable;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: 创建线程的几个方式
 * @author: YxYL
 * @create: 2022-11-22 20:00
 **/

public class RunThread {

    static class MyRunable implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程跑起来了!!!");
        }
    }


    static class MyCallable implements Callable<String> {
        private int n;

        public MyCallable(int n) {
            this.n = n;
        }
        
        @Override
        public String call() {
            int sum = 0;
            for (int i = 0; i <= n; i++) {
                sum += i;
            }
            return "子线程执行的结果是：" + sum;
        }
    }


    public static void main(String[] args) {

        MyRunable runable = new MyRunable();
        runable.run();
        MyCallable callable = new MyCallable(50);
        System.out.println("callable.call() = " + callable.call());

        new Thread(
                () -> {
                    for (int i = 0; i < 10; i++) {
                        System.out.println("子线程执行输出" + i);
                    }
                }
        ).start();
    }
}
