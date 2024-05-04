package com.rookieohy.lambda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestLambda {
    @Test
    public void m1(){
        List<String> strings = new ArrayList<>();
        strings.add("AA");
        strings.add("AA");
        strings.add("AA");
        strings.add("AA");
        strings.add("BB");
        strings.add("BB");
        strings.add("CC");
        strings = strings.stream().distinct().collect(Collectors.toList());
        System.out.println(strings);
    }

}
