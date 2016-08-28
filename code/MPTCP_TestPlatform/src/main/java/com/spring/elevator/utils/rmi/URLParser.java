package com.spring.elevator.utils.rmi;

/**
 * Created by AlexJIANG on 8/14/16.
 */
public class URLParser {
    public static String parse(String url){
        String array[] = url.split("//");
        return array[1].split("/")[0];

    }
}
