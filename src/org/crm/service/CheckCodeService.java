package org.crm.service;

import java.io.OutputStream;

public interface CheckCodeService {
	public String sendCheckCode(OutputStream os,String format);

}
