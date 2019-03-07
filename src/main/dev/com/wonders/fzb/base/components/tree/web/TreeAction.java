package com.wonders.fzb.base.components.tree.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.wonders.fzb.base.actions.BaseAction;
import com.wonders.fzb.base.components.tree.beans.TreeNode;
import net.sf.json.JSONArray;

@Namespace("/components")
@Controller
@Scope("prototype")
public class TreeAction extends BaseAction {
	
	private static final long serialVersionUID = -8464669963819426487L;
	
	private String initTree;
	
	private String id;
	
	@Action(value = "tree", results = {@Result(name = SUCCESS, location = "/WEB-INF/pages/testing/tree_demo.jsp")})
	public String execute() {
		List<TreeNode> trees = new LinkedList<TreeNode>();
		Random r = new Random();
		int baseCount = r.nextInt(10);
		for(int i = 0;i<baseCount;i++){
			TreeNode baseNode = new TreeNode();
			baseNode.setId(i+"");
			baseNode.setIsParent(r.nextInt(baseCount) < i);
			baseNode.setLabel("Base Node" + i);
			trees.add(baseNode);
		}
		
		initTree = JSONArray.fromObject(trees).toString();
		
		return SUCCESS;
	}
	
	@Action(value = "load")
	public void load() throws IOException{
		List<TreeNode> trees = new LinkedList<TreeNode>();
		Random r = new Random();
		int baseCount = r.nextInt(15);
		for(int i = 0;i<baseCount;i++){
			TreeNode baseNode = new TreeNode();
			baseNode.setId(i+"");
			baseNode.setIsParent(r.nextInt(baseCount) < i);
			baseNode.setLabel("Base Node "+(id + 1) + "'s Child Node" + i);
			baseNode.setPid(id);
			trees.add(baseNode);
		}
		
		PrintWriter out = response.getWriter();
		out.print(JSONArray.fromObject(trees).toString());
		out.flush();
	}
	
	private void dynamicDataGrenerator(){
		
	}

	public String getInitTree() {
		return initTree;
	}

	public void setInitTree(String initTree) {
		this.initTree = initTree;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}