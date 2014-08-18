package com.qycloud.oatos.server.domain.entity;

import java.text.DecimalFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.conlect.oatos.dto.client.pay.ServiceStatusDTO;
import com.conlect.oatos.utils.DateUtils;

/**
 * 企业服务状态
 * @author yang
 *
 */
public class ServiceStatus {
	/**
	 * 企业ID
	 */
	private long entId;

	/**
	 * 企业网盘购买容量，gb
	 */
	private Integer entDiskAmount;

	/**
	 * 企业网盘购买年限
	 */
	private int entDiskBuyYear;

	/**
	 * 企业网盘购买价格
	 */
	private double entDiskPrice;

	/**
	 * 企业网盘开始时间
	 */
	private Date entDiskStartDate = new Date();

	/**
	 * 企业网盘结束时间
	 */
	private Date entDiskEndDate = new Date();

	/**
	 * 个人网盘购买容量，gb
	 */
	private Integer personalDiskAmount = 0;

	/**
	 * 个人网盘购买年限
	 */
	private int personalDiskBuyYear;

	/**
	 * 个人网盘购买价格
	 */
	private double personalDiskPrice;

	/**
	 * 个人网盘开始时间
	 */
	private Date personalDiskStartDate = new Date();

	/**
	 * 个人网盘结束时间
	 */
	private Date personalDiskEndDate = new Date();

	/**
	 * 购买用户数
	 */
	private Integer userCount = 0;

	/**
	 * 购买用户年限
	 */
	private int userBuyYear;

	/**
	 * 用户购买价格
	 */
	private double userPrice;

	/**
	 * 用户开始时间
	 */
	private Date userStartDate = new Date();

	/**
	 * 用户结束时间
	 */
	private Date userEndDate = new Date();

	/**
	 * 购买外链流量,gb
	 */
	private Integer shareLinkAmount = 0;

	/**
	 * 外链流量购买价格
	 */
	private double shareLinkPrice;

	public static final DecimalFormat formatter = new DecimalFormat("#.##");

	public ServiceStatus() {
	}

	public static final double format(double src) {
		return Double.parseDouble(formatter.format(src));
	}

	public ServiceStatusDTO toServiceStatusDTO() {
		ServiceStatusDTO dto = new ServiceStatusDTO();
		BeanUtils.copyProperties(this, dto);

		if (entDiskAmount != null && entDiskAmount > 0) {
			int dc = DateUtils
					.dayCountBetween(entDiskStartDate, entDiskEndDate);
			int dr = DateUtils.dayCountBetween(new Date(), entDiskEndDate);
			dto.setEntDiskRemainDays(dr);
			double dp = entDiskPrice * dr / dc;
			dto.setEntDiskRemainPrice(format(dp));
		} else {
			dto.setEntDiskRemainDays(0);
			dto.setEntDiskRemainPrice(0);
		}
		if (personalDiskAmount != null && personalDiskAmount > 0) {
			int dc = DateUtils.dayCountBetween(personalDiskStartDate,
					personalDiskEndDate);
			int dr = DateUtils.dayCountBetween(new Date(), personalDiskEndDate);
			dto.setPersonalDiskRemainDays(dr);
			double dp = personalDiskPrice * dr / dc;
			dto.setPersonalDiskRemainPrice(format(dp));
		} else {
			dto.setPersonalDiskRemainDays(0);
			dto.setPersonalDiskRemainPrice(0);
		}
		if (userCount != null && userCount > 0) {
			int dc = DateUtils.dayCountBetween(userStartDate, userEndDate);
			int dr = DateUtils.dayCountBetween(new Date(), userEndDate);
			dto.setUserRemainDays(dr);
			double dp = entDiskPrice * dr / dc;
			dto.setUserRemainPrice(format(dp));
		} else {
			dto.setUserRemainDays(0);
			dto.setUserRemainPrice(0);
		}
		return dto;
	}

	public ServiceStatus(long entId) {
		this.entId = entId;
	}

	public long getEntId() {
		return entId;
	}

	public void setEntId(long entId) {
		this.entId = entId;
	}

	public Integer getEntDiskAmount() {
		return entDiskAmount;
	}

	public void setEntDiskAmount(Integer entDiskAmount) {
		this.entDiskAmount = entDiskAmount;
	}

	public Date getEntDiskStartDate() {
		return entDiskStartDate;
	}

	public void setEntDiskStartDate(Date entDiskStartDate) {
		this.entDiskStartDate = entDiskStartDate;
	}

	public Date getEntDiskEndDate() {
		return entDiskEndDate;
	}

	public void setEntDiskEndDate(Date entDiskEndDate) {
		this.entDiskEndDate = entDiskEndDate;
	}

	public Integer getPersonalDiskAmount() {
		return personalDiskAmount;
	}

	public void setPersonalDiskAmount(Integer personalDiskAmount) {
		this.personalDiskAmount = personalDiskAmount;
	}

	public Date getPersonalDiskStartDate() {
		return personalDiskStartDate;
	}

	public void setPersonalDiskStartDate(Date personalDiskStartDate) {
		this.personalDiskStartDate = personalDiskStartDate;
	}

	public Date getPersonalDiskEndDate() {
		return personalDiskEndDate;
	}

	public void setPersonalDiskEndDate(Date personalDiskEndDate) {
		this.personalDiskEndDate = personalDiskEndDate;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Date getUserStartDate() {
		return userStartDate;
	}

	public void setUserStartDate(Date userStartDate) {
		this.userStartDate = userStartDate;
	}

	public Date getUserEndDate() {
		return userEndDate;
	}

	public void setUserEndDate(Date userEndDate) {
		this.userEndDate = userEndDate;
	}

	public Integer getShareLinkAmount() {
		return shareLinkAmount;
	}

	public void setShareLinkAmount(Integer shareLinkAmount) {
		this.shareLinkAmount = shareLinkAmount;
	}

	public int getEntDiskBuyYear() {
		return entDiskBuyYear;
	}

	public void setEntDiskBuyYear(int entDiskBuyYear) {
		this.entDiskBuyYear = entDiskBuyYear;
	}

	public int getPersonalDiskBuyYear() {
		return personalDiskBuyYear;
	}

	public void setPersonalDiskBuyYear(int personalDiskBuyYear) {
		this.personalDiskBuyYear = personalDiskBuyYear;
	}

	public int getUserBuyYear() {
		return userBuyYear;
	}

	public void setUserBuyYear(int userBuyYear) {
		this.userBuyYear = userBuyYear;
	}

	public double getEntDiskPrice() {
		return entDiskPrice;
	}

	public void setEntDiskPrice(double entDiskPrice) {
		this.entDiskPrice = entDiskPrice;
	}

	public double getPersonalDiskPrice() {
		return personalDiskPrice;
	}

	public void setPersonalDiskPrice(double personalDiskPrice) {
		this.personalDiskPrice = personalDiskPrice;
	}

	public double getUserPrice() {
		return userPrice;
	}

	public void setUserPrice(double userPrice) {
		this.userPrice = userPrice;
	}

	public double getShareLinkPrice() {
		return shareLinkPrice;
	}

	public void setShareLinkPrice(double shareLinkPrice) {
		this.shareLinkPrice = shareLinkPrice;
	}

}