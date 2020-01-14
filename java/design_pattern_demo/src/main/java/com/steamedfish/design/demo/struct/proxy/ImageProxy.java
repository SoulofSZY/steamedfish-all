package com.steamedfish.design.demo.struct.proxy;

import com.steamedfish.design.demo.struct.proxy.impl.HighResolutionImage;

/**
 * 〈代理模式〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class ImageProxy implements Image {
    private HighResolutionImage highResolutionImage;

    public ImageProxy(HighResolutionImage highResolutionImage) {
        this.highResolutionImage = highResolutionImage;
    }

    @Override
    public void showImage() {
        while (!highResolutionImage.isLoad()) {
            try {
                System.out.println("Temp Image: " + highResolutionImage.getWidth() + " " + highResolutionImage.getHeight());
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        highResolutionImage.showImage();
    }
}