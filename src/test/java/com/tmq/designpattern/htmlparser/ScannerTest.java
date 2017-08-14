package com.tmq.designpattern.htmlparser;

import com.tmq.designpattern.htmlparser.scanner.Scanner;
import org.junit.Assert;
import org.junit.Test;

public class ScannerTest {

    @Test
    public void testSplitTagName() {
        Scanner scanner = new Scanner();
        String input = "<div>";
        String tagName = scanner.splitTagName(input);
        Assert.assertEquals("div", tagName);
    }

    @Test
    public void testSplitTagNameContainExtension() {
        Scanner scanner = new Scanner();
        String input = "adsf <div sdf> sdf";
        String tagName = scanner.splitTagName(input);
        Assert.assertEquals("div", tagName);
    }

    @Test
    public void testSplitIdExist() {
        Scanner scanner = new Scanner();
        String openTag = "<div id='container' sdfa>";
        String id = scanner.splitId(openTag);
        Assert.assertEquals("container", id);
    }
}
