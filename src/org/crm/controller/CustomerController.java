package org.crm.controller;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.crm.exceptions.ConversionNotSupportedException;
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
	
	@RequestMapping("/list")
	public String queryCustomerList(QueryVo queryVo, Model model) throws ConversionNotSupportedException,SQLException {
		
		throw new ConversionNotSupportedException();
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

}
