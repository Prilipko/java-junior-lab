package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AroundDaoAspect {
    private static Logger log = LoggerFactory.getLogger(AroundDaoAspect.class);
    private Map<CacheKey, Object> cacheMap = new HashMap<>();

    public Object employeeAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        CacheKey key = new CacheKey(proceedingJoinPoint.getClass().getName(),
                proceedingJoinPoint.getSignature().getName(),
                proceedingJoinPoint.getArgs());
        if (cacheMap.containsKey(key)) {
            Object result = cacheMap.get(key);
            log.info("from cache: " + key + " = " + result);
            return result;
        }
        Object value = null;
        try {
            value = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if(value != null) {
            cacheMap.put(key, value);
        }
        return value;
    }

    static class CacheKey {
        private final String clazz;
        private final String signature;
        private final Object[] args;

        public CacheKey(String clazz, String signature, Object[] args) {
            this.clazz = clazz;
            this.signature = signature;
            this.args = args;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CacheKey cacheKey = (CacheKey) o;

            if (clazz != null ? !clazz.equals(cacheKey.clazz) : cacheKey.clazz != null) return false;
            if (signature != null ? !signature.equals(cacheKey.signature) : cacheKey.signature != null) return false;
            // Probably incorrect - comparing Object[] arrays with Arrays.equals
            return Arrays.equals(args, cacheKey.args);

        }

        @Override
        public int hashCode() {
            int result = clazz != null ? clazz.hashCode() : 0;
            result = 31 * result + (signature != null ? signature.hashCode() : 0);
            result = 31 * result + Arrays.hashCode(args);
            return result;
        }

        @Override
        public String toString() {
            return "CacheKey{" +
                    "clazz='" + clazz + '\'' +
                    ", signature='" + signature + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}