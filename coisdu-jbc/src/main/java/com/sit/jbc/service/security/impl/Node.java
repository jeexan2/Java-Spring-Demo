package com.sit.jbc.service.security.impl;

import com.sit.jbc.domain.dto.security.TreeElement;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Geetanjali Oishe on 10/17/2018.
 */
public class Node<T> {

//    private TreeElement data = null;
    private T data = null;

    private List<Node<T>> children = new ArrayList<>();

    private Node<T> parent = null;

//    public Node(TreeElement data) {
//        this.data = data;
//    }

    public Node(T data) {
        this.data = data;
    }

    public Node<T> addChild(Node<T> child) {
        child.setParent(this);
        this.children.add(child);
        return child;
    }

    public void addChildren(List<Node<T>> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public List<Node<T>> getChildren() {
        return children;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

    public Node<T> getChild(String childToGet, String childType) {
        List<Node<T>> ch = this.getChildren();
        for (Node<T> eachChild : ch) {
//            if (eachChild.getData().equals(childToGet))
           String treeData = eachChild.getData().toString();
           if (treeData.contains(childToGet) && treeData.contains(childType))
                return eachChild;
        }
        return null;
    }
}
