package com.rookieohy.returns;

import cn.hutool.core.lang.Pair;

public class TestPair {
    public static void main(String[] args) {
        Pair<String, String> pair = new Pair<>("key", "value");
        System.out.println(pair.getKey());
        System.out.println(pair.getValue());
    }
}
