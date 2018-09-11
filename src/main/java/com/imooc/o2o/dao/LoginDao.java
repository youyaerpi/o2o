package com.imooc.o2o.dao;

import com.imooc.o2o.domain.PersonInfo;

public interface LoginDao {
	PersonInfo queryShopByUsername(String name);

}
