package com.imooc.o2o.service.impl;

import java.io.IOException;
import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.o2o.dao.HeadLineDao;
import com.imooc.o2o.domain.HeadLine;
import com.imooc.o2o.service.HeadLineService;

@Service
public class HeadLineServiceImpl implements HeadLineService {
	@Autowired
	private HeadLineDao headLineDao;

	@Override
	/**
	 * 查询头条列表
	 */
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) throws IOException {

		return headLineDao.queryHeadLine(headLineCondition);
	}

}
