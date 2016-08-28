package com.spring.elevator.utils.rmi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by AlexJIANG on 8/14/16.
 */
public class URLParserTest {
    @Test
    public void testURLParser(){
        assertEquals("139.59.189.180",URLParser.parse("http://139.59.189.180/Package-HTML/HTML/index-shop.html"));
    }

}