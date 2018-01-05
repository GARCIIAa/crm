package org.crm.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private static final String CUSTOMER_LEVEL_TYPE = "006";
	private static final String CUSTOMER_FROM_TYPE = "002";
	private static final String CUSTOMER_INDUSTRY_TYPE = "001";
	@Autowired
	CustomerService customerService;
	@Autowired
	BaseDictService baseDictService;
	
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
	@RequestMapping("edit")
	@ResponseBody
	public Customer queryCustomerById(Long id) {
		Customer customer = this.customerService.queryCustomerById(id);
		return customer;
	}


}
