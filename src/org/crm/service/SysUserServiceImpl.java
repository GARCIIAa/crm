package org.crm.service;

import org.crm.mapper.SysUserMapper;
import org.crm.pojo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService{
	@Autowired
	private SysUserMapper sysuserMapper;
	
	@Override
	public SysUser loginSysUser(Long id, String password) {
		SysUser sysuser = sysuserMapper.loginSysUser(id,password);
		return sysuser;
	}

}
