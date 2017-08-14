package com.tmq.designpattern.htmlparser.visitor;

import com.tmq.designpattern.htmlparser.abstraction.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagContainer {

    private List<Tag> tagList;

    public TagContainer() {
        this.tagList = new ArrayList<Tag>();
    }

    public TagContainer(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public void accept(IVisitor visitor) {
        for (int i = tagList.size()-1; i >=0 ; i--) {
            tagList.get(i).accept(visitor);
        }
//        for (Tag item : tagList) {
//            item.accept(visitor);
//        }
    }

    public void accept(IFinderVisitor visitor) {
        for (int i = tagList.size()-1; i >=0 ; i--) {
            tagList.get(i).accept(visitor);
        }
//        for (Tag item : tagList) {
//            item.accept(visitor);
//        }
    }

    public void add(Tag tag) {
        this.tagList.add(tag);
    }
}
