package com.conlect.oatos.dto.status.url;

import com.conlect.oatos.dto.client.UsualContactDTO;
import com.conlect.oatos.dto.client.UsualContactGroupDTO;
import com.conlect.oatos.dto.client.UsualContactGroupAndUserDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 常用联系人REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>设置常用联系人
 * <li>取消常用联系人
 * <li>常用联系人分组服务
 * <li>获取联系人信息
 * </ul>
 * 
 * @author yang
 * 
 */
public interface UsualContactUrl {

	/**
	 * <p>
	 * 设置常用联系人
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link UsualContactDTO} 常用联系人DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link CommConstants#OK_MARK } OK
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String setUsualContact = "/sc/usualContact/setUsualContact";

	/**
	 * <p>
	 * 取消常用联系人
	 * </p>
	 * <p>
	 * <b>参数：</b>{@link UsualContactDTO} 常用联系人DTO
	 * </p>
	 * <p>
	 * <b>正常返回：</b>{@link CommConstants#OK_MARK } OK
	 * </p>
	 * <b>异常返回：</b><br>
	 * <ul>
	 * <li>{@link ErrorType#error500}服务器内部错误
	 * </ul>
	 */
	String deleteUsualContact = "/sc/usualContact/deleteUsualContact";

	/**
	 * <p>
	 * 创建常用联系人分组
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link UsualContactGroupDTO}的JSON：分组信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：创建成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String addUsualContactGroup = "/sc/usualContact/addUsualContactGroup";

	/**
	 * <p>
	 * 修改常用联系人分组信息
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link UsualContactGroupDTO}的JSON：分组信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：修改成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String updateUsualContactGroup = "/sc/usualContact/updateUsualContactGroup";

	/**
	 * <p>
	 * 删除常用联系人分组
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link UsualContactGroupDTO}的JSON：分组信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：删除成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String deleteUsualContactGroup = "/sc/usualContact/deleteUsualContactGroup";

	/**
	 * <p>
	 * 获取常用联系人分组列表及对应组联系人详细信息
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link UsualContactGroupAndUserDTO}的JSON：常用联系人分组列表及联系人详细信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getUsualContactGroupAndUserByUserId = "/sc/usualContact/getUsualContactGroupAndUserByUserId";

	/**
	 * <p>
	 * 变更常用联系人的分组
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link UsualContactDTO}的JSON：常用联系人信息
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#OK_MARK}：修改成功
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String updateUsualContact = "/sc/usualContact/updateUsualContact";

	/**
	 * <p>
	 * 获取常用联系人分组列表
	 * </p>
	 * 
	 * <b>参数：</b>
	 * <ul>
	 * <li>用户id
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link UsualContactGroupsDTO}的JSON：常用联系人分组列表及联系人详细信息
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String getUsualContactGroupsByUserId = "/sc/usualContact/getUsualContactGroupsByUserId";
}
