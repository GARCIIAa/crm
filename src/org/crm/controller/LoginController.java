package org.crm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.crm.pojo.SysUser;
import org.crm.service.CheckCodeService;
import org.crm.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Controller
public class LoginController {
	@Autowired
	CheckCodeService checkCodeService;
	@Autowired
	SysUserService sysuserService;

	@RequestMapping("/picturecheckcode")
	public void sendCode(HttpSession session, HttpServletResponse response) throws IOException {
		// 验证码图片格式
		String format = "png";
		// 设置图片格式
		response.setContentType("image/" + format);
		// 禁止浏览器缓存图片
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String code = checkCodeService.sendCheckCode(response.getOutputStream(), format);
		session.setAttribute("code", code);
	}

	@RequestMapping("/checkcode")
	public void checkCode(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		String value = request.getParameter("param");
		JSONObject jb = new JSONObject();
		if (value.equals(request.getSession().getAttribute("code"))) {
			jb.put("info", "验证码输入正确");
			jb.put("status", "y");
		} else {
			jb.put("info", "验证码输入错误");
			jb.put("status", "n");
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jb.toString());
	}

	@RequestMapping("/login")
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		JSONObject jb = new JSONObject();
		SysUser su = sysuserService.loginSysUser(Long.parseLong(id), password);
		if (su != null) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", id);
			jb.put("info", "登录成功！");
			jb.put("status", "y");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jb.toString());
		} else {// Login failed
			jb.put("info", "用户ID或者密码错误");
			jb.put("status", "n");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jb.toString());
		}
	}
	@RequestMapping("/logoff")
	public void logoff(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		HttpSession session = request.getSession();
		session.setAttribute("userId", null);
		session.setAttribute("code", null);
		response.sendRedirect(request.getContextPath());
	}
}
