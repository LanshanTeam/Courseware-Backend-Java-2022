import java.util.concurrent.*;

/**
 * @program: Courseware-Backend-Java-2022
 * @description: 线程池
 * @author: YxYL
 * @create: 2022-11-22 20:07
 **/

public class TheadPool {
    public static void main(String[] args) {


        ExecutorService pools = new ThreadPoolExecutor(
                3,//核心线程个数 corePoolSize
                5,//最大线程量 maximumPoolSize
                8,//最大时间 keepAliveTime
                TimeUnit.SECONDS,//
                new ArrayBlockingQueue<>(6),//任务队列
                Executors.defaultThreadFactory(),//线程工厂
                new ThreadPoolExecutor.AbortPolicy());//任务拒绝策略

        pools.execute(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println(Thread.currentThread() + "起飞！！！" + "第" + i + "次");
            }
        });

        pools.execute(() -> {
            for (int i = 0; i < 50; i++) {
                System.out.println(Thread.currentThread() + "GGGGGG" + "第" + i + "次");
            }
        });

        pools.shutdown();
        pools.shutdownNow();

    }
}
