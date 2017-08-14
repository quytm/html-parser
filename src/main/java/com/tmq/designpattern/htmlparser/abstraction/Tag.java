package com.tmq.designpattern.htmlparser.abstraction;

import com.tmq.designpattern.htmlparser.visitor.IFinderVisitor;
import com.tmq.designpattern.htmlparser.visitor.IVisitor;

public abstract class Tag {

    public String tagName = "_NO_NAME_";
    private String id = "";

    public abstract void accept(IVisitor visitor);
    public abstract void accept(IFinderVisitor visitor);

    // -----------------------------------------------

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
