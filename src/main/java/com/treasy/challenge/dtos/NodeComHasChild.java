package com.treasy.challenge.dtos;

import com.treasy.challenge.dao.Node;

public class NodeComHasChild extends Node {
	public boolean getHasChildren() {
		return hasChildren;
	}
	public void setHasChildren(boolean hasChildren) {
		this.hasChildren = hasChildren;
	}
    private boolean hasChildren;

    public NodeComHasChild( Node n )
    {
    	this.setCode(n.getCode());
    	this.setDescription(n.getDescription());
    	this.setDetail(n.getDetail());
    	this.setId(n.getId());
    	this.setParentId(n.getParentId());
    }
}
