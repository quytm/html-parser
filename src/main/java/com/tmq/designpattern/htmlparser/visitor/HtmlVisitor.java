package com.tmq.designpattern.htmlparser.visitor;

import com.tmq.designpattern.htmlparser.item.BoldTag;
import com.tmq.designpattern.htmlparser.item.DivTag;
import com.tmq.designpattern.htmlparser.item.HtmlTag;
import com.tmq.designpattern.htmlparser.item.PlainText;
import org.apache.commons.lang3.StringUtils;

public class HtmlVisitor implements IVisitor {

    private StringBuilder data;

    public HtmlVisitor() {
        this.data = new StringBuilder();
    }

    public void visit(PlainText plainText) {
        data.append(plainText.getText());
    }

    public void visit(BoldTag boldTag) {
        data.append(StringUtils.isEmpty(boldTag.getId()) ? "<b>" : "<b id='" + boldTag.getId() + "'>")
                .append(boldTag.getInnerContent())
                .append("</b>")
                .append("\n");
    }

//    public void visit(HyperlinkTag hyperlinkTag) {
//        data.append("<a href='")
//                .append(hyperlinkTag.getUrl())
//                .append("'>")
//                .append(hyperlinkTag.getText())
//                .append("</a>");
//    }


    public void visit(DivTag divTag) {
        data.append(StringUtils.isEmpty(divTag.getId()) ? "<div>" : "<div id='" + divTag.getId() + "'>")
                .append(divTag.getInnerContent())
                .append("</div>")
                .append("\n")
        ;
    }

    public void visit(HtmlTag divTag) {
        data.append("<html>")
                .append("\n\n")
                .append(divTag.getInnerContent())
                .append("\n")
                .append("</html>");
    }

    public String printOutput() {
        String output = data.toString();
        data = new StringBuilder();
        return output;
    }
}