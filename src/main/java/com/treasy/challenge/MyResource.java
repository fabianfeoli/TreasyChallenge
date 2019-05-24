package com.treasy.challenge;

import java.util.List;
 
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
 
import com.treasy.challenge.dtos.NodeArray;
import com.treasy.challenge.dtos.NodeComHasChild;
import com.treasy.challenge.dtos.NodeSemId;
import com.treasy.challenge.dao.Node;
import com.treasy.challenge.dao.NodeDAO;
 
 
@Path("/node")
public class MyResource {

	@POST
    @Consumes("application/json")
    public Long addNode(NodeSemId bean){
		Node _node = new Node();
		_node.setCode(bean.getCode());
        _node.setDescription(bean.getDescription());
        _node.setDetail(bean.getDetail());
        _node.setParentId(bean.getParentId());
        NodeDAO dao = new NodeDAO();
        return dao.addNode(_node);
    }

    @PUT
    @Consumes("application/json")
    public int updateNode(Node bean){
        NodeDAO dao = new NodeDAO();
        return dao.updateNode(bean);
    }

    @GET
    @Produces("application/json")
    public List<NodeArray> getNodes() {
        System.out.println("Get ALL"); 
        NodeDAO dao = new NodeDAO();
        return dao.getNodes();
    }
 
    
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public List<NodeComHasChild> getNodes(@PathParam("id") int id){
    	NodeDAO dao = new NodeDAO();
        return dao.getNodes(id);
    }
}
 