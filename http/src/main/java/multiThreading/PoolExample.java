package multiThreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Worker on 01.05.2015
 */
public class PoolExample {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService service =new ThreadPoolExecutor(0,4,1, TimeUnit.SECONDS,new ArrayBlockingQueue<>(10));
        List<Future<Integer>> futures = new ArrayList<>();
        List<Sum> callables = new ArrayList<>();
        for(int i=0;i<13;i++){
            callables.add(new Sum(i, 0));
//            futures.add(service.submit(new Sum(i, 0)));
        }
        futures = service.invokeAll(callables);
        for(Future<Integer> future :futures){
            System.out.println(future.get());
        }
    }

    private static class Sum implements Callable<Integer>{

        public Sum(int a, int b) {
            this.a = a;
            this.b = b;
        }

        private final int a;
        private final int b;

        @Override
        public Integer call() throws Exception {
            System.out.println(a + ":start");
            Thread.sleep(4000);
            System.out.println(a + ":stop");
            return a+b;
        }
    }
}
