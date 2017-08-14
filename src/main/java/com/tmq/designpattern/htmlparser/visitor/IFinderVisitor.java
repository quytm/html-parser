package com.tmq.designpattern.htmlparser.visitor;

import com.tmq.designpattern.htmlparser.abstraction.PairedTag;
import com.tmq.designpattern.htmlparser.abstraction.Tag;
import com.tmq.designpattern.htmlparser.item.BoldTag;
import com.tmq.designpattern.htmlparser.item.DivTag;
import com.tmq.designpattern.htmlparser.item.HtmlTag;
import com.tmq.designpattern.htmlparser.item.PlainText;

/**
 * Created by quytm on 8/11/2017.
 */
public interface IFinderVisitor {

//    void visit(BoldTag boldTag);
//
//    void visit(DivTag divTag);
//
//    void visit(HtmlTag htmlTag);

    void visitId(PairedTag tag);
    void visitId(PlainText plainText);
}
