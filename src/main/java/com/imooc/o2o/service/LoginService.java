package com.imooc.o2o.service;

import com.imooc.o2o.domain.PersonInfo;

public interface LoginService {
	
	PersonInfo checkLogin(String username,String password);

}
