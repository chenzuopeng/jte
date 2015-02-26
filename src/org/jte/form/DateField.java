package org.jte.form;

import org.jte.constant.Type;
import org.jte.utils.Assert;

/**
 *
 * 此类表示一个日期域
 * @Copyright: Copyright (c) 2008 FFCS All Rights Reserved
 * @Company: 北京福富软件有限公司
 * @author 陈作朋 Jan 27, 2011
 * @version 1.00.00
 * @history:
 *
 */
public class DateField extends TextField {

	/**
	 *默认的时间格式
	 */
    public final static String DEFAULT_DATE_FMT="yyyy-MM-dd HH:mm:ss";

	/**
	 * 时间格式
	 */
	private String dateFmt=DEFAULT_DATE_FMT;

	/**
	 * 最小时间
	 */
	private String minDate;

	/**
	 * 最大时间
	 */
	private String maxDate;

	public DateField(){
		super();
	}

	public DateField(String dateFmt, String minDate, String maxDate) {
		setDateFmt(dateFmt);
		setMinDate(minDate);
		setMaxDate(maxDate);
	}

	@Override
	public Type getType() {
		return Type.DATEFIELD;
	}

	public String getDateFmt() {
		return this.dateFmt;
	}

	public void setDateFmt(String dateFmt) {
		Assert.notBlank(dateFmt);
		this.dateFmt = dateFmt;
	}

	public String getMinDate() {
		return this.minDate;
	}

	public void setMinDate(String minDate) {
		Assert.notBlank(dateFmt);
		this.minDate = minDate;
	}

	public String getMaxDate() {
		return this.maxDate;
	}

	public void setMaxDate(String maxDate) {
		Assert.notBlank(dateFmt);
		this.maxDate = maxDate;
	}

}
