package com.steamedfish.design.demo.struct.composite;

/**
 * 〈组合模式〉
 * 将对象组合成树形结构来表示“整体/部分”层次关系，允许用户以相同的方式处理单独对象和组合对象。
 *
 * @author steamedfish
 * @create 2020/1/14
 * @since 1.0.0
 */
public abstract class Component {

    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public void print() {
        print(0);
    }

    public abstract void print(int level);

    protected abstract void add(Component component);

    protected abstract void remove(Component component);
}