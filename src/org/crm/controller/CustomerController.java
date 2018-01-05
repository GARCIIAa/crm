package org.crm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.crm.exceptions.ResourceNotFoundException;
import org.crm.pojo.BaseDict;
import org.crm.pojo.Customer;
import org.crm.pojo.QueryVo;
import org.crm.service.BaseDictService;
import org.crm.service.CustomerService;
import org.crm.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONException;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController{

	private static final String CUSTOMER_LEVEL_TYPE = "006";
	private static final String CUSTOMER_FROM_TYPE = "002";
	private static final String CUSTOMER_INDUSTRY_TYPE = "001";
	@Autowired
	CustomerService customerService;
	@Autowired
	BaseDictService baseDictService;
	
	@RequestMapping("*")
	public void test404() throws ResourceNotFoundException{
		throw new ResourceNotFoundException();
	}

	@RequestMapping("/list")
	public String queryCustomerList(QueryVo queryVo, Model model) {

		try {
			// 解决get请求乱码问题
			if (StringUtils.isNotBlank(queryVo.getCustName())) {
				queryVo.setCustName(new String(queryVo.getCustName().getBytes("ISO-8859-1"), "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 客户来源
		List<BaseDict> fromType = this.baseDictService.queryBaseDictByDictTypeCode(this.CUSTOMER_FROM_TYPE);
		// 所属行业
		List<BaseDict> industryType = this.baseDictService.queryBaseDictByDictTypeCode(this.CUSTOMER_INDUSTRY_TYPE);
		// 客户级别
		List<BaseDict> levelType = this.baseDictService.queryBaseDictByDictTypeCode(this.CUSTOMER_LEVEL_TYPE);

		// 把前端页面需要显示的数据放到模型中
		model.addAttribute("fromType", fromType);
		model.addAttribute("industryType", industryType);
		model.addAttribute("levelType", levelType);

		// 分页查询数据
		Page<Customer> page = this.customerService.queryCustomerByQueryVo(queryVo);
		// 把分页查询的结果放到模型中
		model.addAttribute("page", page);

		// 数据回显
		model.addAttribute("custName", queryVo.getCustName());
		model.addAttribute("custSource", queryVo.getCustSource());
		model.addAttribute("custIndustry", queryVo.getCustIndustry());
		model.addAttribute("custLevel", queryVo.getCustLevel());

		return "customer";
	}
	
	/**
	 * 根据id查询用户,返回json格式数据
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Customer queryCustomerById(Long id) {
		Customer customer = this.customerService.queryCustomerById(id);
		return customer;
	}
	@RequestMapping(value="/update",method = {RequestMethod.POST})
	public String updateCustomerInfo(Customer cus) {
		this.customerService.updateCustomerById(cus);
		return "customer";
	}
	
	@RequestMapping("/delete")
	public String deleteCustomerByID(Long id) {
		this.customerService.deleteCustomerById(id);
		return "customer";
	}
	
	@RequestMapping("/logoff")
	public void logoff(HttpServletRequest request, HttpServletResponse response) throws IOException, JSONException {
		HttpSession session = request.getSession();
		session.setAttribute("userId", null);
		session.setAttribute("code", null);
		response.sendRedirect(request.getContextPath());
	}
}
