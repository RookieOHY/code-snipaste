package com.rookieohy.returns;

import org.apache.commons.lang3.tuple.Triple;

/**
 * @author 20413
 */
public class TestTriple {
    public static void main(String[] args) {
        Triple<String, Integer, Boolean> triple = Triple.of("John", 25, true);
        System.out.println("Name: " + triple.getLeft());
        System.out.println("Age: " + triple.getMiddle());
        System.out.println("IsMale: " + triple.getRight());
    }
}
