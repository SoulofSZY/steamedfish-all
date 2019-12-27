package com.szy.lamda.concurrent.completable.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/7/9
 * @since 1.0.0
 */
public class Shop {

    private String name;

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice(String product) {
        return caculatePrice(product);
    }


    /**
     * 使用类方法supplyAsync构建
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync3(String product) {
        return CompletableFuture.supplyAsync(()->caculatePrice(product));
    }


    /**
     * 包含 导致 CompletableFuture 内发生问题的异常
     *
     * @param product
     * @return
     */
    public Future<Double> getPriceAsync2(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            try {
                double price = caculatePrice(product);
                futurePrice.complete(price);
            } catch (Exception ex) {
                futurePrice.completeExceptionally(ex);
            }

        }).start();
        return futurePrice;
    }


    public Future<Double> getPriceAsync(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = caculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    private double caculatePrice(String product) {
        delay();
        return Math.random() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}