package demo.data;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import demo.utils.DateHelper;

/**
 *
 *
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 Nov 19, 2010
 * @version 1.00.00
 * @history:
 *
 */
@Entity
@Table(name = "USER")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class User {

	private long id;

	private String email;

	private String login_name;

	private String name;

	private String plain_password;

	private String sha_password;

	private String status;

	private Date create_time;

	@SequenceGenerator(name = "generator", sequenceName = "SEQ_USER")
	@Id
	@GeneratedValue(strategy = SEQUENCE, generator = "generator")
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin_name() {
		return this.login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlain_password() {
		return this.plain_password;
	}

	public void setPlain_password(String plain_password) {
		this.plain_password = plain_password;
	}

	public String getSha_password() {
		return this.sha_password;
	}

	public void setSha_password(String sha_password) {
		this.sha_password = sha_password;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreate_time() {
		return this.create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Transient
	public String getCreateTimeToString() {
		return DateHelper.getTimeStamp(this.create_time);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
