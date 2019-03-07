package com.wonders.fzb.framework.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.dao.impl.BaseSupportDao;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.Contact;
import com.wonders.fzb.framework.dao.ContactDao;

@Repository("contactDao")
public class ContactDaoImpl extends BaseSupportDao implements ContactDao{

	@Override
	public void addContact(Contact contact) {
		// TODO Auto-generated method stub
		super.save(contact);
	}

	@Override
	public void delContact(Contact contact) {
		// TODO Auto-generated method stub
		super.delete(contact);
	}

	@Override
	public void updateContact(Contact contact) {
		// TODO Auto-generated method stub
		super.update(contact);
	}

	@Override
	public Contact findById(String contactId) {
		// TODO Auto-generated method stub
		return (Contact)super.load(contactId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<Contact> findContactPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize, String moduleId) throws FzbDaoException {
		// TODO Auto-generated method stub
		if(moduleId!=null&&"".equals(moduleId)) {
			condMap.put("moduleId", moduleId);
		}
		return super.findByPage(condMap, sortMap, pageNo, pageSize, "Contact");
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> findByList(Map<String,Object>condMap,Map<String,String>sortMap,String moduleId) {
		if(moduleId!=null&&"".equals(moduleId)) {
			condMap.put("moduleId", moduleId);
		}
		return (List<Contact>)super.findByList(condMap, sortMap, "Contact");
	}
}
