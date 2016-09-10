package springScheduling.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author worker
 * @created 10.09.16.
 */
@Async
@Service
public class AsyncService {
    public void simpleTask(int n){
        System.out.println("Start simple task "+n+" - "+ Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stop simple task "+n+" - "+ Thread.currentThread().getName());
    }
    public Future<String> futureTask(String s){
        System.out.println("Start future task "+s+" - "+ Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stop future task  "+s+" - "+ Thread.currentThread().getName());
        return new AsyncResult<>("Hello "+ s);
    }
}
