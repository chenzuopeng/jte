package demo.data;

import java.util.List;

import org.jsearch.JSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springside.modules.orm.Page;

import com.ffcs.j2eeframework.web.service.BaseService;

import demo.action.QueryDemoAction.UserSBean;

/**
 * 
 * 
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 Jan 19, 2011
 * @version 1.00.00
 * @history:
 * 
 */
@Repository
public class UserService extends BaseService<User> {

	@Autowired
	private UserDao userDao;

	@Override
	public void save(final User user) {
		this.userDao.save(user);
	}

	public List<User> getAll(final Page<User> page) {
		return this.userDao.getAll(page).getResult();
	}

	public void delete(final Long id) {
		this.userDao.delete(id);
	}

	public List<User> find(final Page<User> page, final String hql, final Object[] values) {
		return this.userDao.findPage(page, hql, values).getResult();
	}

	public User get(final Long id) {
		return this.userDao.get(id);
	}
	
	public List<User> findByBean(final Page<User> page,UserSBean bean){
		 JSearch jsearch=JSearch.getInstance("from User where ${0}",bean);
		 return this.userDao.findPageBy(page, jsearch.getExpression(), jsearch.getParamValues()).getResult();
	}

}
