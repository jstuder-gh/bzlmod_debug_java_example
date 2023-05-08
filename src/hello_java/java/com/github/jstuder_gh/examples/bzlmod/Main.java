package com.github.jstuder_gh.examples.bzlmod;

import com.google.common.collect.ImmutableList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        var fromGuava = ImmutableList.of("Hello", "World!");
        var fromJre = List.of("Hello", "World!");

        if (!fromJre.equals(fromGuava)) {
            throw new RuntimeException("This is less than ideal");
        }

        System.out.println(fromGuava.toString());
    }

}
