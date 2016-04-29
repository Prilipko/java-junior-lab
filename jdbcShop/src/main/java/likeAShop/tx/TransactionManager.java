package likeAShop.tx;

import java.util.concurrent.Callable;


public abstract class TransactionManager extends MyDataSource {
    public abstract <T> T doInTx(Callable<T> operation) throws Exception;
}
