package com.tmq.designpattern.htmlparser.item;

import com.tmq.designpattern.htmlparser.abstraction.Tag;
import com.tmq.designpattern.htmlparser.visitor.IFinderVisitor;
import com.tmq.designpattern.htmlparser.visitor.IVisitor;

public class PlainText extends Tag{

    {
        this.tagName = "";
    }

    private String text;

    public PlainText() {
        this.text = "";
    }

    public PlainText(String text) {
        this.text = text;
    }

    public void accept(IVisitor visitor) {
        visitor.visit(this);
    }

    public String getText() {
        return text;
    }

    @Override
    public void accept(IFinderVisitor visitor) {
        visitor.visitId(this);
    }
}
