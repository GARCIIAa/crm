package org.crm.service;

import java.util.List;

import org.crm.mapper.BaseDictMapper;
import org.crm.pojo.BaseDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseDictServiceImpl implements BaseDictService {

	@Autowired
	private BaseDictMapper baseDictMapper;

	@Override
	public List<BaseDict> queryBaseDictByDictTypeCode(String dictTypeCode) {

		List<BaseDict> list = this.baseDictMapper.queryBaseDictByDictTypeCode(dictTypeCode);
		return list;
	}
}

