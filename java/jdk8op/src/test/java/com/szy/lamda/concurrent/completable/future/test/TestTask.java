package com.szy.lamda.concurrent.completable.future.test;

import com.szy.lamda.concurrent.completable.future.Shop;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/7/9
 * @since 1.0.0
 */
public class TestTask {






    private List<Shop> shops = Arrays.asList(new Shop("BestShop"),
            new Shop("PaperShop"),
            new Shop("GoldShop"),
            new Shop("GoldShop1"),
            new Shop("GoldShop2"),
            new Shop("GoldShop3"),
            new Shop("GoldShop4"),
            new Shop("GoldShop5"),
            new Shop("MeatShop")

    );

    private final Executor executor = Executors.newFixedThreadPool(Math.min(10, 100),
                    new ThreadFactory() {
                        public Thread newThread(Runnable r) {
                            Thread t = new Thread(r);
                            t.setDaemon(true);
                            return t;
                        }
                    });

    @Test
    public void testSync() {
        long start = System.nanoTime();
        System.out.println(getPricesParallelStream("fish"));
        long duration = (System.nanoTime() - start) / 1_000_000;
        System.out.println("Done in " + duration + " msecs");
    }

    /**
     *  指定线程池
     * @param product
     * @return
     */
    private List<String> getPricesWithExcutor(String product) {
        List<CompletableFuture<String>> futurePrices = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product),executor)).collect(Collectors.toList());
        return futurePrices.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * CompletableFuture 01
     * @param product
     * @return
     */
    private List<String> getPricesCompleteableFuture(String product) {
        List<CompletableFuture<String>> futurePrices = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getName() + " price is " + shop.getPrice(product))).collect(Collectors.toList());

        return futurePrices.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }

    /**
     * 并行流  受限于线程池不可控
     *
     * @param product
     * @return
     */
    private List<String> getPricesParallelStream(String product) {
        return shops.parallelStream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
    }

    /**
     * 同步处理
     *
     * @param product
     * @return
     */
    private List<String> getPricesSync(String product) {
        return shops.stream().map(shop -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))).collect(Collectors.toList());
    }


    @Test
    public void testAsync01() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsync("apple iphone xxs");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");

        System.out.println("something else");

        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }
}