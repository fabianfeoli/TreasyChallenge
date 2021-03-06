package com.treasy.challenge.dao;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Index;

@Entity
@Table(indexes = { @Index(columnList = ("parentId"),name="IDX_PARENT",unique=false) })
public class Node {
 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @Column
    private String code;
    
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Column
    private String description;
    
    @Column
    private int parentId;
    
    @Column
    private String detail;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
}