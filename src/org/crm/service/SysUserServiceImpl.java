package org.crm.service;

import org.crm.mapper.SysUserMapper;
import org.crm.pojo.SysUser;
import org.crm.utils.SystemServiceLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SysUserServiceImpl implements SysUserService{
	@Autowired
	private SysUserMapper sysuserMapper;
	
	@Override
	public SysUser loginSysUser(Long id, String password) {
		SysUser sysuser = sysuserMapper.loginSysUser(id,password);
		return sysuser;
	}

}
