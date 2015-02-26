package demo.data;

import org.springframework.stereotype.Repository;
import org.springside.modules.orm.hibernate.HibernateDao;

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
public class UserDao extends HibernateDao<User, Long>{
}
