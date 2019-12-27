package com.szy.lamda.concurrent.forkjoin;

import com.szy.lamda.concurrent.forkjoin.ForkJoinSumCalculator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/7/5
 * @since 1.0.0
 */
@Slf4j
public class TestForkJoin {


    @Test
    public void testForkJoinOne() throws Exception {
        System.out.println(forkJoinSum(100));
    }


    public static long forkJoinSum(long n) throws Exception {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinSumCalculator forkJoinSumCalculator = new ForkJoinSumCalculator(numbers);
        Long result = new ForkJoinPool().invoke(forkJoinSumCalculator);
       // TimeUnit.SECONDS.sleep(10);
        return result;
    }
}