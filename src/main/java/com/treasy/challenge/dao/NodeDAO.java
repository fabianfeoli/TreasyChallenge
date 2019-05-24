package com.treasy.challenge.dao;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.math.BigInteger;
import com.treasy.challenge.dtos.NodeArray;
import com.treasy.challenge.dtos.NodeComHasChild;

public class NodeDAO {

	public Long addNode(Node bean){
        Session session = SessionUtil.getSession();        
        Transaction tx = session.beginTransaction();
        addNode(session, bean);        
        tx.commit();
        Long lastId = ((BigInteger) session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
        session.close();
        return lastId;
    }
    
    private void addNode(Session session, Node bean){
        Node _node = new Node();
        _node.setCode(bean.getCode());
        _node.setDescription(bean.getDescription());
        _node.setDetail(bean.getDetail());
        _node.setParentId(bean.getParentId());
        session.save(_node);
    }
    
    public int updateNode(Node bean){
        if(bean.getId() <=0)  
              return 0;  
        Session session = SessionUtil.getSession();
           Transaction tx = session.beginTransaction();
           String hql = "update Node set code = :code, description=:description, detail=:detail, parentId=:parentId where id = :id";
           Query query = session.createQuery(hql);
           query.setInteger("id", bean.getId());
           query.setString("code",bean.getCode());
           query.setString("description",bean.getDescription());
           query.setString("detail",bean.getDetail());
           query.setInteger("parentId", bean.getParentId());
           int rowCount = query.executeUpdate();
           System.out.println("Rows affected: " + rowCount);
           tx.commit();
           session.close();
           return bean.getId();
   }

    public List<NodeArray> getNodes(){
    	
    	List<NodeArray> ret = new LinkedList<NodeArray>();
    	Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Node where parentId = 0 or parentId is null");
        List<Node> nodes =  query.list();
        for (Iterator<Node> i=nodes.iterator(); i.hasNext(); ) 
        { 
        	NodeArray narr = new NodeArray( i.next() );
        	narr.addChild(getSiblings(narr.getId()));
            ret.add(narr);
        } 
        session.close();
        return ret;
    }
    
    public List<NodeComHasChild> getNodes(int parentId){
    	
    	List<NodeComHasChild> ret = new LinkedList<NodeComHasChild>();
    	Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Node where parentId = :id");
        query.setInteger("id", parentId);
        List<Node> nodes =  query.list();
        if (nodes.size() > 0) {
	        for (Iterator<Node> i=nodes.iterator(); i.hasNext(); ) 
	        { 
	        	NodeComHasChild nc = new NodeComHasChild(i.next()); 
	        	nc.setHasChildren(hasSiblings(nc.getId()));
	            ret.add(nc);
	        } 
        }
        session.close();
        return ret;
    }
    
    private boolean hasSiblings( int id ) {
    	LinkedList<NodeComHasChild> ret = new LinkedList<NodeComHasChild>();
    	Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Node where parentId = :id");
        query.setInteger("id", id);
        List<Node> nodes =  query.list();
        if (nodes.size() > 0) {
        	return true;
        }
        return false;
    }
    
	
    private List<NodeArray> getSiblings(int parentID)
    {
    	List<NodeArray> ret = new LinkedList<NodeArray>();
    	Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from Node where parentId = :id");
        query.setInteger("id",parentID); 
        List<Node> nodes =  query.list();
        if (nodes.size() > 0) {
	        for (Iterator<Node> i=nodes.iterator(); i.hasNext(); ) 
	        { 
	        	Node n = i.next();
	        	NodeArray narr = new NodeArray( n );
	        	narr.addChild(getSiblings(n.getId()));
	            ret.add(narr);
	        } 
        }
        session.close();
        return ret;
    }
}
