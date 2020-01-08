package com.steamedfish.design.demo.behavior.iterator;

/**
 * 〈迭代器模式〉<br>
 * 提供一种顺序访问聚合对象元素的方法，并且不暴露聚合对象的内部表示。
 *
 * @author steamedfish
 * @create 2020/1/8
 * @since 1.0.0
 */
public interface Iterator<Item> {

    boolean hasNext();

    Item next();
}
