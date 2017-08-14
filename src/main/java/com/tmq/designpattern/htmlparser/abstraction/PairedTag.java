package com.tmq.designpattern.htmlparser.abstraction;

import com.tmq.designpattern.htmlparser.visitor.FinderVisitor;
import com.tmq.designpattern.htmlparser.visitor.IFinderVisitor;
import com.tmq.designpattern.htmlparser.visitor.TagContainer;
import com.tmq.designpattern.htmlparser.visitor.HtmlVisitor;

import java.util.List;


public abstract class PairedTag extends Tag {

    protected TagContainer tagContainer;
    protected HtmlVisitor htmlVisitor;
    protected FinderVisitor finderVisitor;

    public PairedTag() {
        this.tagContainer = new TagContainer();
        this.htmlVisitor = new HtmlVisitor();
        this.finderVisitor = new FinderVisitor();
    }

    public String getInnerContent() {
        tagContainer.accept(htmlVisitor);
        return htmlVisitor.printOutput();
    }

    public List<Tag> getInnerTags(String id) {
        finderVisitor.setId(id);
        tagContainer.accept(finderVisitor);
        return finderVisitor.getOutPut();
    }

    public void add(Tag tag) {
        tagContainer.add(tag);
    }

}
