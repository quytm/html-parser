package com.tmq.designpattern.htmlparser.item;

import com.tmq.designpattern.htmlparser.abstraction.PairedTag;
import com.tmq.designpattern.htmlparser.abstraction.Tag;
import com.tmq.designpattern.htmlparser.visitor.IFinderVisitor;
import com.tmq.designpattern.htmlparser.visitor.IVisitor;

public class DivTag extends PairedTag {

    {
        this.tagName = "div";
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void accept(IFinderVisitor visitor) {
        visitor.visitId(this);
    }

}
