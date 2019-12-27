package com.szy.lamda.io;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author sunzhengyu
 * @create 2019/8/14
 * @since 1.0.0
 */
public class TestFileCopy {


    /**
     * io类库
     *
     * @param source
     * @param des
     * @throws IOException
     */
    public static void copyFileByStream(File source, File des) throws
            IOException {
        try (InputStream is = new FileInputStream(source);
             OutputStream os = new FileOutputStream(des);) {
            byte[] bufer = new byte[1024];
            int length;
            while ((length = is.read(bufer)) > 0) {
                os.write(bufer, 0, length);
            }
        }
    }


    /**
     * nio类库 基于transferTo/transferFrom
     *
     * @param source
     * @param des
     * @throws IOException
     */
    public static void copyFileByChannel(File source, File des) throws
            IOException {
        try (FileChannel sourceChannel = new FileInputStream(source)
                .getChannel();
             FileChannel targetChannel = new FileOutputStream(des).getChannel
                     ();) {
            for (long count = sourceChannel.size(); count > 0; ) {
                long transferred = sourceChannel.transferTo(
                        sourceChannel.position(), count, targetChannel);
                sourceChannel.position(sourceChannel.position() + transferred);
                count -= transferred;
            }
        }
    }


    /**
     * wins
     * 60M
     * 流模式copy,耗时：6 s
     * NIO类库copy,耗时：3 s
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        File src = new File("C:\\zsofe\\bd_netdisk_down\\src.zip");
        File target1 = new File("C:\\ztemp\\tar1.zip");
        File target2 = new File("C:\\ztemp\\tar2.zip");
        System.out.println("文件拷贝对比开始");
        long start = System.nanoTime();
        copyFileByStream(src, target1);
        System.out.println("流模式copy,耗时：" + TimeUnit.SECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS));

        start = System.nanoTime();
        copyFileByChannel(src, target2);
        System.out.println("NIO类库copy,耗时：" + TimeUnit.SECONDS.convert(System.nanoTime() - start, TimeUnit.NANOSECONDS));

    }

}