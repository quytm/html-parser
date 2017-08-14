package com.tmq.designpattern.htmlparser;

import com.tmq.designpattern.htmlparser.abstraction.Tag;
import com.tmq.designpattern.htmlparser.item.BoldTag;
import com.tmq.designpattern.htmlparser.item.DivTag;
import com.tmq.designpattern.htmlparser.item.PlainText;
import com.tmq.designpattern.htmlparser.scanner.Scanner;
import com.tmq.designpattern.htmlparser.visitor.HtmlVisitor;
import com.tmq.designpattern.htmlparser.visitor.FinderVisitor;
import com.tmq.designpattern.htmlparser.visitor.TagContainer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String tc1 = "<div id='container'>Tran Minh Quy</div>";
        String tc2 = "<div>Tran Minh Quy <b id='container'>Quydz</b> </div>";
        String tc3 = "<div id='container'>Tran Minh Quy</div> <b id='container'>Quydz</b>";

        String tc4 = "<div>Tran Minh Quy</div> " +
                "<b>Quydz</b>" +
                "<div>12" +
                "     <div>34</div>" +
                "     <p>saf</p>" +
                "</div>";

        testScanner(tc1);
        testScanner(tc2);
        testScanner(tc3);
        testScanner(tc4);
    }

    private static void testShow() {
        List<Tag> input = new ArrayList<Tag>();

        BoldTag boldTag = new BoldTag();
        boldTag.add(new PlainText("Hello bold tag"));
        input.add(boldTag);

        input.add(new DivTag());

        DivTag divTag = new DivTag();
        divTag.add(new BoldTag());
        DivTag divTagChild = new DivTag();
        divTagChild.add(new BoldTag());
        divTagChild.add(new BoldTag());
        divTag.add(divTagChild);
        input.add(divTag);

        TagContainer tagContainer = new TagContainer(input);

        HtmlVisitor htmlVisitor = new HtmlVisitor();
        tagContainer.accept(htmlVisitor);
        System.out.println(htmlVisitor.printOutput());
    }

    private static void testScanner(String input) {
        Scanner scanner = new Scanner();

        scanner.parse(input);
        Tag root = scanner.getRoot();

        HtmlVisitor htmlVisitor = new HtmlVisitor();
        root.accept(htmlVisitor);

        System.out.println("----------------------");
        System.out.println(htmlVisitor.printOutput());

        FinderVisitor finderVisitor = new FinderVisitor();
        finderVisitor.setId("container");
        root.accept(finderVisitor);

        System.out.println("----------\nFind by Id = 'container':\n");
        HtmlVisitor printer = new HtmlVisitor();
        List<Tag> findResult = finderVisitor.getOutPut();
        if (findResult.isEmpty()) {
            System.out.println("Not found!");
        } else {
            for (Tag tag : findResult) {
                tag.accept(printer);
                System.out.println(printer.printOutput());
            }
        }
    }
}
