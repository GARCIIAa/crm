package org.crm.service;

import java.util.List;

import org.crm.mapper.CustomerMapper;
import org.crm.pojo.Customer;
import org.crm.pojo.QueryVo;
import org.crm.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public Page<Customer> queryCustomerByQueryVo(QueryVo queryVo) {
		// 设置查询条件,从哪一条数据开始查
		queryVo.setStart((queryVo.getPage() - 1) * queryVo.getRows());

		// 查询数据结果集
		List<Customer> list = this.customerMapper.queryCustomerByQueryVo(queryVo);
		// 查询到的数据总条数
		int total = this.customerMapper.queryCountByQueryVo(queryVo);

		// 封装返回的page对象
		Page<Customer> page = new Page<>(total, queryVo.getPage(), queryVo.getRows(), list);

		return page;
	}
	
	@Override
	public Customer queryCustomerById(Long id) {
		Customer customer = this.customerMapper.queryCustomerById(id);
		return customer;
	}
	@Override
	public void updateCustomerById(Customer customer) {
		this.customerMapper.updateCustomerById(customer);
	}
}
