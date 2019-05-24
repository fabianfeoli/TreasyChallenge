package com.treasy.challenge.dtos;

import java.util.LinkedList;
import java.util.List;

import com.treasy.challenge.dao.Node;

public class NodeArray extends Node {


	public List<NodeArray> getChildren() {
		return children;
	}

	private List<NodeArray> children;
	
	public NodeArray( Node bNode )
	{
		this.setCode(bNode.getCode());
		this.setDescription(bNode.getDescription());
		this.setDetail(bNode.getDetail());
		this.setParentId(bNode.getParentId());
		this.setId(bNode.getId());
		children = new LinkedList<NodeArray>();
	}
	
	public void addChild( Node child )
	{
		children.add(new NodeArray(child));
	}
	public void addChild( List<NodeArray> childs )
	{
		children.addAll(childs);
	}
}
