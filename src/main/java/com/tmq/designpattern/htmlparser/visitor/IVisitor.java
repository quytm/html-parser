package com.tmq.designpattern.htmlparser.visitor;

import com.tmq.designpattern.htmlparser.item.*;

public interface IVisitor {
    void visit(PlainText plainText);

    void visit(BoldTag boldTag);

//    void visit(HyperlinkTag hyperlinkTag);

    void visit(DivTag divTag);

    void visit(HtmlTag divTag);
}