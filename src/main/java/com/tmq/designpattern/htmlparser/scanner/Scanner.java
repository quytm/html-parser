package com.tmq.designpattern.htmlparser.scanner;

import com.tmq.designpattern.htmlparser.abstraction.PairedTag;
import com.tmq.designpattern.htmlparser.abstraction.Tag;
import com.tmq.designpattern.htmlparser.item.BoldTag;
import com.tmq.designpattern.htmlparser.item.DivTag;
import com.tmq.designpattern.htmlparser.item.HtmlTag;
import com.tmq.designpattern.htmlparser.item.PlainText;
import org.apache.commons.lang3.StringUtils;

import java.util.Stack;

public class Scanner {

    private Stack<Tag> stack;
    private HtmlTag root;

    public Scanner() {
        root = new HtmlTag();
        stack = new Stack<Tag>();
    }

    public void parse(String input) {
        root.add(detectTypeOfTag(input));
    }

    public HtmlTag getRoot() {
        return root;
    }

    public Tag detectTypeOfTag(String input) {
        String tagName = splitTagName(input);

        if (StringUtils.isEmpty(tagName) && StringUtils.isNotEmpty(input)) {
            return new PlainText(input);
        }

        if (tagName.equals("div")) {
            DivTag divTag = new DivTag();
            divTag.setId(splitId(input));
            scanPairedTag(divTag, input);
            return divTag;
        }

        if (tagName.equals("b")) {
            BoldTag boldTag = new BoldTag();
            boldTag.setId(splitId(input));
            scanPairedTag(boldTag, input);
            return boldTag;
        }

        return new PlainText(input);
    }

    public String splitTagName(String input) {
        input = clearCloseTagOfPairedTag(input);

        String tagContent = splitOpenTag(input);
        if (tagContent.contains(" ")) {
            int spacePos = tagContent.indexOf(" ");
            tagContent = tagContent.substring(0, spacePos);
        }
        return tagContent;
    }

    public String splitOpenTag(String input) {
        int startPos = input.indexOf("<");
        int endPos = input.indexOf(">");

        if (startPos < 0 || endPos < 0) return "";

        return input.substring(startPos + 1, endPos);
    }

    public String splitId(String input) {
        String openTag = splitOpenTag(input);
        int idPos = openTag.indexOf("id='");
        if (idPos < 0) return "";

        int idPosEnd = openTag.indexOf("\'", idPos + 5);
        return openTag.substring(idPos + 4, idPosEnd);
    }


    private void scanPairedTag(PairedTag pairedTag, String input) {
        stack.push(pairedTag);

        int startContentPos = input.indexOf(">");
        int endContentPos = input.indexOf("<", input.indexOf("<") + 1);

        // Exclude PlainText
        String newInput = input.substring(endContentPos, input.length());

        // If have CloseTag: </div>, </b>
        if (newInput.indexOf("/") == 1) {
            stack.pop();
        }

        String plainText = input.substring(startContentPos + 1, endContentPos).trim();
        // Add new Tag into PairedTag if Stack is not empty
        if (!stack.isEmpty()) {
            pairedTag.add(detectTypeOfTag(newInput));
            // Add new Tag: PlainText
            if (StringUtils.isNotEmpty(plainText)) pairedTag.add(new PlainText(plainText));
        } else {
            // Add new Tag: PlainText
            if (StringUtils.isNotEmpty(plainText)) pairedTag.add(new PlainText(plainText));
            // Remove CloseTag then continue detecting
            newInput = newInput.substring(pairedTag.tagName.length() + 3, newInput.length());
            root.add(detectTypeOfTag(newInput));
        }
    }

    private String clearCloseTagOfPairedTag(String input) {
        while (true) {
            if (!stack.isEmpty() && input.indexOf("</" + stack.peek().tagName + ">") == 0) {
                input = input.substring(stack.peek().tagName.length() + 3, input.length()).trim();
                stack.pop();
            } else {
                return input;
            }
        }
    }
}
