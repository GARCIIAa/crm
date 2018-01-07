package org.crm.service;

import java.util.List;

import org.crm.mapper.BaseDictMapper;
import org.crm.pojo.BaseDict;
import org.crm.utils.SystemServiceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BaseDictServiceImpl implements BaseDictService {

	@Autowired
	private BaseDictMapper baseDictMapper;

	@Override
	//此处为AOP拦截Service记录异常信息。方法不需要加try-catch  
	@SystemServiceLog(description = "查询字典")
	public List<BaseDict> queryBaseDictByDictTypeCode(String dictTypeCode) {

		List<BaseDict> list = this.baseDictMapper.queryBaseDictByDictTypeCode(dictTypeCode);
		return list;
	}
}

