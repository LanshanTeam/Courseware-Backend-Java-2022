/**
 * @program: Courseware-Backend-Java-2022
 * @description: 线程安全问题
 * @author: YxYL
 * @create: 2022-11-22 20:25
 **/

public class UnSafe {
    
    static class Counter {//定义一个资源类
        private int count;

        public void increase() {
            ++this.count;
        }

        public int getCount() {
            return this.count;
        }
    }

    public static class Test {
        
        private static final int CNT = 50000;
        private static final Counter counter = new Counter();

        public static void main(String[] args) throws InterruptedException {

            Thread thread1 = new Thread(() -> {
                for (int i = 0; i < CNT; i++) {
                    counter.increase();
                }
            });
            Thread thread2 = new Thread(() -> {
                for (int j = 0; j < CNT; j++) {
                    counter.increase();
                }
            });

            thread1.start();
            thread2.start();

            thread1.join();
            thread2.join();

            System.out.println(counter.getCount());
        }
    }

}
