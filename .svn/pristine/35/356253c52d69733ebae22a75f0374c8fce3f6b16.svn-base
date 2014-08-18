package com.qycloud.oatos.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qycloud.oatos.server.domain.entity.Enterprise;

/**
 * 企业服务接口
 * @author yang
 * 
 */
public interface EnterpriseMapper {

	/**
	 * 新建企业
	 * @param enterprise
	 */
	void addEnterprise(Enterprise enterprise);

	/**
	 * 修改企业信息
	 * @param enterprise
	 */
	void updateEnterprise(Enterprise enterprise);

	/**
	 * 根据企业ID获取企业信息
	 * @param enterpriseId
	 * @return
	 */
	Enterprise getEnterpriseById(long enterpriseId);

	/**
	 * 根据企业名获取企业信息
	 * @param enterpriseName
	 * @return
	 */
	Enterprise getEnterpriseByName(String enterpriseName);

	/**
	 * 修改企业管理员登录密码
	 * @param enterpriseId
	 * @param newPassword
	 */
	void changeAdminPassword(@Param("enterpriseId") long enterpriseId,
			@Param("adminPassword") String newPassword);

	/**
	 * 获取所有企业信息
	 * @return
	 */
	List<Enterprise> getAllEnterprise();

	/**
	 * 修改产品key
	 * @param enterpriseId
	 * @param productKey
	 */
	void updateProductKey(@Param("enterpriseId") long enterpriseId,
			@Param("productKey") String productKey);

	/**
	 * 更新共享链接下载流量
	 * 
	 * @param entId
	 */
	void updateShareLinkDownCount(@Param("enterpriseId") long entId,
			@Param("fileSize") long fileSize);

	/**
	 * 更新用户购买的服务
	 * 
	 * @param enterprise
	 */
	void updateEntService(Enterprise enterprise);

	/**
	 * 修改购买状态
	 * @param enterpriseId
	 * @param payStatus
	 */
	void updatePayStatus(@Param("enterpriseId") long enterpriseId,
			@Param("payStatus") String payStatus);

	/**
	 * 删除企业
	 * @param enterpriseId
	 * @test
	 */
	void deleteEnterprise(long enterpriseId);

}
