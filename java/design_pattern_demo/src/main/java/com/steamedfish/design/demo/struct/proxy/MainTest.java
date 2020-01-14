package com.steamedfish.design.demo.struct.proxy;

import com.steamedfish.design.demo.struct.proxy.impl.HighResolutionImage;

import java.net.URL;

/**
 * 〈〉
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public class MainTest {

    public static void main(String[] args) throws Exception {
        String image = "http://image.jpg";
        URL url = new URL(image);
        HighResolutionImage highResolutionImage = new HighResolutionImage(url);
        ImageProxy imageProxy = new ImageProxy(highResolutionImage);
        imageProxy.showImage();
    }
}