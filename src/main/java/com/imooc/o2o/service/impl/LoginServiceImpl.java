package com.imooc.o2o.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.o2o.dao.LoginDao;
import com.imooc.o2o.domain.PersonInfo;
import com.imooc.o2o.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDao loginDao;

	@Override
	public PersonInfo checkLogin(String name, String password) {
		PersonInfo user = loginDao.queryShopByUsername(name);
		if (user != null && user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}

}
