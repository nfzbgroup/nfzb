package com.wonders.fzb.framework.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.Contact;

public interface ContactService {
	/*
	 * 增
	 * @param Contact
	 */
	public void addContact(Contact contact);
	
	/*
	 * 删
	 * @param Contact
	 */
	public void delContact(Contact contact);
	
	/*
	 * 改
	 * @param Contact
	 */
	public void updateContact(Contact contact);
	
	/*
	 * 查
	 * @param contactId 
	 */
	public Contact findById(String contactId);
	
	/*
	 * 分页查询
	 * @param condMap 查询条件
	 * @param sortMap 排序条件
	 * @param pageNo 页数
	 * @param pageSize 大小
	 * @param moduleId 确定系统
	 */
	public Page<Contact> findContactPage(Map<String,Object>condMap,Map<String,String>sortMap,
			int pageNo,int pageSize,String moduleId) throws FzbDaoException;
	
	/*
	 * 条件查询
	 * @param condMap 查询条件
	 * @param sortMap  排序条件
	 * @param moduleId  确定系统
	 */
	public List<Contact> findByList(Map<String,Object>condMap,Map<String,String>sortMap,String moduleId);
}
