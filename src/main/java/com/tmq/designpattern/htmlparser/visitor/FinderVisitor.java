package com.tmq.designpattern.htmlparser.visitor;

import com.tmq.designpattern.htmlparser.abstraction.PairedTag;
import com.tmq.designpattern.htmlparser.abstraction.Tag;
import com.tmq.designpattern.htmlparser.item.BoldTag;
import com.tmq.designpattern.htmlparser.item.DivTag;
import com.tmq.designpattern.htmlparser.item.HtmlTag;
import com.tmq.designpattern.htmlparser.item.PlainText;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by quytm on 8/11/2017.
 */
public class FinderVisitor implements IFinderVisitor {

    private String id;
    private List<Tag> tags;

    public FinderVisitor() {
        this.id = "";
        this.tags = new ArrayList<Tag>();
    }

    public void visit(BoldTag boldTag) {

    }

    public void visit(DivTag divTag) {

    }

    public void visit(HtmlTag divTag) {

    }

    public void visitId(PairedTag tag) {
        if (tag.getId().equals(id) && StringUtils.isNotEmpty(tag.getId())) {
            this.tags.add(tag);
        }
        tags.addAll(tag.getInnerTags(id));
    }

    public void visitId(PlainText plainText) {

    }

    public List<Tag> getOutPut() {
        return tags;
    }

    public void setId(String id) {
        this.id = id;
    }
}
