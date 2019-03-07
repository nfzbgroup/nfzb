package com.wonders.fzb.framework.services.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.fzb.base.beans.Page;
import com.wonders.fzb.base.exception.FzbDaoException;
import com.wonders.fzb.framework.beans.Contact;
import com.wonders.fzb.framework.dao.ContactDao;
import com.wonders.fzb.framework.services.ContactService;
@Service("contactService")
@Transactional
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	@Qualifier("contactDao")
	private ContactDao contactDao;
	
	@Override
	public void addContact(Contact contact) {
		// TODO Auto-generated method stub
		contactDao.addContact(contact);
	}

	@Override
	public void delContact(Contact contact) {
		// TODO Auto-generated method stub
		contactDao.delContact(contact);
	}

	@Override
	public void updateContact(Contact contact) {
		// TODO Auto-generated method stub
		contactDao.updateContact(contact);
	}

	@Override
	public Contact findById(String contactId) {
		// TODO Auto-generated method stub
		return contactDao.findById(contactId);
	}

	@Override
	public Page<Contact> findContactPage(Map<String, Object> condMap, Map<String, String> sortMap, int pageNo,
			int pageSize, String moduleId) throws FzbDaoException {
		// TODO Auto-generated method stub
		return contactDao.findContactPage(condMap, sortMap, pageNo, pageSize, moduleId);
	}

	@Override
	public List<Contact> findByList(Map<String, Object> condMap, Map<String, String> sortMap, String moduleId) {
		// TODO Auto-generated method stub
		return (List<Contact>)contactDao.findByList(condMap, sortMap, moduleId);
	}

}
