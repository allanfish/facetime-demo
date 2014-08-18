package com.qycloud.oatos.server.domain.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.client.pay.BuyServiceDTO;
import com.conlect.oatos.dto.client.pay.BuyServiceListDTO;
import com.conlect.oatos.dto.client.pay.CurrentServiceDTO;
import com.conlect.oatos.dto.client.pay.CurrentServiceListDTO;
import com.conlect.oatos.dto.client.pay.EntPayDTO;
import com.conlect.oatos.dto.client.pay.OrderDTO;
import com.conlect.oatos.dto.client.pay.OrderItemDTO;
import com.conlect.oatos.dto.client.pay.OrderItemListDTO;
import com.conlect.oatos.dto.client.pay.ServiceTypeDTO;
import com.conlect.oatos.dto.client.pay.ServiceTypeListDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.EnterpriseStatus;
import com.conlect.oatos.dto.status.ErrorType;
import com.conlect.oatos.dto.status.ModuleStatus;
import com.conlect.oatos.dto.status.OrderItemStatus;
import com.conlect.oatos.dto.status.OrderItemType;
import com.conlect.oatos.dto.status.OrderStatus;
import com.conlect.oatos.dto.status.OrderType;
import com.conlect.oatos.utils.DateUtils;
import com.qycloud.oatos.server.dao.EntModuleMapper;
import com.qycloud.oatos.server.dao.EnterpriseMapper;
import com.qycloud.oatos.server.dao.OrderMapper;
import com.qycloud.oatos.server.dao.ProductKeyMapper;
import com.qycloud.oatos.server.dao.ShareFileMapper;
import com.qycloud.oatos.server.dao.UserMapper;
import com.qycloud.oatos.server.domain.entity.EntModule;
import com.qycloud.oatos.server.domain.entity.Enterprise;
import com.qycloud.oatos.server.domain.entity.OrderInfo;
import com.qycloud.oatos.server.domain.entity.OrderItem;
import com.qycloud.oatos.server.domain.entity.ProductKey;
import com.qycloud.oatos.server.domain.entity.ServiceStatus;
import com.qycloud.oatos.server.domain.entity.ServiceType;
import com.qycloud.oatos.server.util.LogicException;

/**
 * 
 */
@Service("OrderLogic")
public class OrderLogic {

	@Autowired
	private SequenceLogic sequence;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private EnterpriseMapper enterpriseMapper;

	@Autowired
	private ShareFileMapper shareFileMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ProductKeyMapper productKeyMapper;
	
	@Autowired
	private EntModuleMapper entModuleMapper;

	/**
	 * 获取企业服务信息
	 */
	public EntPayDTO getUserPayInfo(long entId) {
		EntPayDTO userPay = new EntPayDTO();

		// get service types and price
		List<ServiceType> serviceTypes = orderMapper.getServiceTypes();
		List<ServiceTypeDTO> typeDTOs = new ArrayList<ServiceTypeDTO>();
		for (ServiceType t : serviceTypes) {
			typeDTOs.add(t.toServiceTypeDTO());
		}
		userPay.setServiceTypes(typeDTOs);

		// get service status
		ServiceStatus serviceStatus = orderMapper.getServiceStatus(entId);
		if (serviceStatus != null) {
			userPay.setServiceStatus(serviceStatus.toServiceStatusDTO());
		}

		if (entId > 0) {
			Enterprise ent = enterpriseMapper.getEnterpriseById(entId);
			if (ent != null) {
				userPay.setEntId(ent.getEnterpriseId());
				userPay.setEntName(ent.getEnterpriseName());
				userPay.setRemainEntDiskSize(ent.getDiskSize() - shareFileMapper.getFileSizeSumByEntId(entId));
				userPay.setRemainPersonDiskSize(ent.getPersonalDiskSize() - userMapper.getUserCountByEntId(entId)
				        * CommConstants.PERSONAL_DISK_SIZE);
				userPay.setRemainUserSize(ent.getMaxEmployees() - userMapper.getUserCountByEntId(entId));
				userPay.setRemainShareLinkSize(ent.getMaxShareLinkDownload() - ent.getShareLinkDownCount());
			}
		}
		else {
			userPay.setEntId(0);
			userPay.setEntName("深圳企业云");
			userPay.setRemainEntDiskSize(CommConstants.ENTERPRISE_DISK_SIZE);
			userPay.setRemainPersonDiskSize(CommConstants.PERSONAL_DISK_SIZE);
			userPay.setRemainUserSize(CommConstants.DEFAULT_MAX_EMPLOYEES);
			userPay.setRemainShareLinkSize(CommConstants.SHARE_LINK_DOWN_SIZE);
		}
		return userPay;
	}

	/**
	 * 支付完成
	 */
	@Transactional
	public String payComplete(OrderDTO orderInfo) {
		OrderInfo order = orderMapper.getOrderInfoByTradeNo(orderInfo.getTradeNo());
		if (order == null) {
			throw new LogicException(ErrorType.errorOrderNotExist);
		}
		if (OrderStatus.UNPAY.name().equals(order.getStatus())
				|| OrderStatus.PAY_FAIL.name().equals(order.getStatus())) {
			if (OrderStatus.PAY_FAIL.name().equals(orderInfo.getStatus())) {
				// 支付失败
				order.setStatus(OrderStatus.PAY_FAIL.name());
			}
			else {
				// 支付成功
				order.setStatus(OrderStatus.PAY_OK.name());

				// 修改企业服务状态，开通服务
				updateServiceStatus(order);
			}
			// 修改订单支付状态
			orderMapper.updateOrderStatus(order);
        }
		return CommConstants.OK_MARK;
	}

	/**
	 * 修改企业服务状态，开通服务
	 * 
	 * @param order
	 */
	private void updateServiceStatus(OrderInfo order) {
		if (OrderType.NORMAL.name().equals(order.getType())) {
			// 普通订单，第一次购买，为企业设置正式的产品key
			setProductKey(order.getEntId());
		}

		// 企业服务状态信息
		ServiceStatus serviceStatus = orderMapper.getServiceStatus(order.getEntId());
		boolean add = false;
		if (serviceStatus == null) {
			add = true;
			serviceStatus = new ServiceStatus(order.getEntId());
		}

		// 当前订单购买的服务信息
		List<OrderItem> orderItems = orderMapper.getOrderItemsByOrderId(order.getOrderId());
		for (OrderItem item : orderItems) {
			if (OrderItemType.ENT_DISK.name().equals(item.getServiceName())) {
				// 企业网盘
				if (OrderType.RENEW.name().equals(order.getType())) {
					// 续费
					if (isExpired(serviceStatus.getEntDiskEndDate())) {
						// 服务已过期
						serviceStatus.setEntDiskBuyYear(item.getBuyYear());
						serviceStatus.setEntDiskStartDate(new Date());
						serviceStatus.setEntDiskEndDate(DateUtils.addYears(new Date(), item.getBuyYear()));
						serviceStatus.setEntDiskPrice(item.getPrice());
					}
					else {
						// 服务未过期
						serviceStatus.setEntDiskBuyYear(serviceStatus.getEntDiskBuyYear() + item.getBuyYear());
						serviceStatus.setEntDiskEndDate(DateUtils.addYears(serviceStatus.getEntDiskEndDate(),
						        item.getBuyYear()));
						serviceStatus.setEntDiskPrice(serviceStatus.getEntDiskPrice() + item.getPrice());
					}
				}
				else {
					// 普通订单，变更订单
					serviceStatus.setEntDiskAmount(item.getAmount());
					serviceStatus.setEntDiskBuyYear(item.getBuyYear());
					serviceStatus.setEntDiskStartDate(new Date());
					serviceStatus.setEntDiskEndDate(DateUtils.addYears(new Date(), item.getBuyYear()));
					serviceStatus.setEntDiskPrice(item.getPrice());
				}
			}
			else if (OrderItemType.PERSON_DISK.name().equals(item.getServiceName())) {
				// 个人网盘
				if (OrderType.RENEW.name().equals(order.getType())) {
					// 续费
					if (isExpired(serviceStatus.getPersonalDiskEndDate())) {
						// 服务已过期
						serviceStatus.setPersonalDiskBuyYear(item.getBuyYear());
						serviceStatus.setPersonalDiskStartDate(new Date());
						serviceStatus.setPersonalDiskEndDate(DateUtils.addYears(new Date(), item.getBuyYear()));
						serviceStatus.setPersonalDiskPrice(item.getPrice());
					}
					else {
						// 服务未过期
						serviceStatus
						        .setPersonalDiskBuyYear(serviceStatus.getPersonalDiskBuyYear() + item.getBuyYear());
						serviceStatus.setPersonalDiskEndDate(DateUtils.addYears(serviceStatus.getPersonalDiskEndDate(),
						        item.getBuyYear()));
						serviceStatus.setPersonalDiskPrice(serviceStatus.getPersonalDiskPrice() + item.getPrice());
					}
				}
				else {
					// 普通订单，变更订单
					serviceStatus.setPersonalDiskAmount(item.getAmount());
					serviceStatus.setPersonalDiskBuyYear(item.getBuyYear());
					serviceStatus.setPersonalDiskStartDate(new Date());
					serviceStatus.setPersonalDiskEndDate(DateUtils.addYears(new Date(), item.getBuyYear()));
					serviceStatus.setPersonalDiskPrice(item.getPrice());
				}
			}
			else if (OrderItemType.USER_ADD.name().equals(item.getServiceName())) {
				// 购买用户数
				if (OrderType.RENEW.name().equals(order.getType())) {
					// 续费
					if (isExpired(serviceStatus.getUserEndDate())) {
						// 服务已过期
						serviceStatus.setUserBuyYear(item.getBuyYear());
						serviceStatus.setUserStartDate(new Date());
						serviceStatus.setUserEndDate(DateUtils.addYears(new Date(), item.getBuyYear()));
						serviceStatus.setUserPrice(item.getPrice());
					}
					else {
						// 服务未过期
						serviceStatus.setUserBuyYear(serviceStatus.getUserBuyYear() + item.getBuyYear());
						serviceStatus.setUserEndDate(DateUtils.addYears(serviceStatus.getUserEndDate(),
						        item.getBuyYear()));
						serviceStatus.setUserPrice(serviceStatus.getUserPrice() + item.getPrice());
					}
				}
				else {
					// 普通订单，变更订单
					serviceStatus.setUserCount(item.getAmount());
					serviceStatus.setUserBuyYear(item.getBuyYear());
					serviceStatus.setUserStartDate(new Date());
					serviceStatus.setUserEndDate(DateUtils.addYears(new Date(), item.getBuyYear()));
					serviceStatus.setUserPrice(item.getPrice());
				}
			}
			else if (OrderItemType.SHARE_LINK.name().equals(item.getServiceName())) {
				// 外链
				serviceStatus.setShareLinkAmount(serviceStatus.getShareLinkAmount() + item.getAmount());
				serviceStatus.setShareLinkPrice(serviceStatus.getShareLinkPrice() + item.getPrice());
			}
		}

		// 更新企业服务状态
		if (add) {
			orderMapper.insertServiceStatus(serviceStatus);
		}
		else {
			orderMapper.updateServiceStatus(serviceStatus);
		}
		// 开通企业服务
		activeEntService(serviceStatus);
	}

	/**
	 * 开通企业服务
	 * 
	 * @param status
	 */
	private void activeEntService(ServiceStatus status) {
		Enterprise ent = enterpriseMapper.getEnterpriseById(status.getEntId());
		EntModule entModule = entModuleMapper.getEntModuleByEntId(status.getEntId());
		if (status.getEntDiskAmount() != null && !isExpired(status.getEntDiskEndDate())) {
			entModule.setEnterpriseDiskStatus(ModuleStatus.PAY);
			ent.setDiskSize(CommConstants.ENTERPRISE_DISK_SIZE + status.getEntDiskAmount() * 1024 * 1024);
		}
		if (status.getPersonalDiskAmount() != null && !isExpired(status.getPersonalDiskEndDate())) {
			entModule.setPersonalDiskStatus(ModuleStatus.PAY);
			ent.setPersonalDiskSize(CommConstants.PERSONAL_DISK_SIZE * CommConstants.DEFAULT_MAX_EMPLOYEES
			        + status.getPersonalDiskAmount() * 1024 * 1024);
		}
		if (status.getUserCount() != null && !isExpired(status.getUserEndDate())) {
			entModule.setUserAddStatus(ModuleStatus.PAY);
			ent.setMaxEmployees(CommConstants.DEFAULT_MAX_EMPLOYEES + status.getUserCount());
		}
		if (status.getShareLinkAmount() != null) {
			ent.setMaxShareLinkDownload(CommConstants.SHARE_LINK_DOWN_SIZE + status.getShareLinkAmount() * 1024 * 1024);
		}

		ent.setPayStatus(OrderStatus.PAY_OK.name());
		ent.setStatus(EnterpriseStatus.Pay);
		enterpriseMapper.updateEntService(ent);
		entModuleMapper.updateEntModule(entModule);
	}

	/**
	 * 为企业设置正式的产品key
	 * 
	 * @param entId
	 */
	private void setProductKey(long entId) {
		ProductKey key = productKeyMapper.getRandomPayedKey();
		if (key != null) {
			key.setUsed(true);
			productKeyMapper.updateKey(key);
			enterpriseMapper.updateProductKey(entId, key.getKey());
		}
	}

	/**
	 * 根据交易编号取订单信息
	 */
	public OrderDTO getOrderByTradeNo(String tradeNo) {
		// 取订单信息
		OrderInfo order = orderMapper.getOrderInfoByTradeNo(tradeNo);
		if (order == null)
			throw new LogicException(ErrorType.errorOrderNotExist);
		OrderDTO orderInfo = order.toOrderDTO();

		// 取订单项目
		List<OrderItemDTO> orderItems = new ArrayList<OrderItemDTO>();
		List<OrderItem> items = orderMapper.getOrderItemsByOrderId(order.getOrderId());
		for (OrderItem model : items) {
			orderItems.add(model.toOrderItemDTO());
		}
		orderInfo.setOrderItems(orderItems);
		return orderInfo;
	}

	/**
	 * 保存订单信息
	 */
	@Transactional
	public String saveOrder(OrderDTO orderInfo) {
		OrderInfo order = new OrderInfo(orderInfo);
		order.setOrderId(sequence.getNextId());
		order.setCreateDate(new Date());
		// 订单状态设置为未支付
		order.setStatus(OrderStatus.UNPAY.name());

		if (orderInfo.getType().equals(OrderType.NORMAL.name())) {
			// 普通订单
			order.setType(OrderType.NORMAL.name());
		}
		else if (orderInfo.getType().equals(OrderType.RENEW.name())) {
			// 续费
			order.setType(OrderType.RENEW.name());
		}
		else if (OrderType.CHANGE.name().equals(orderInfo.getType())) {
			// 变更
			order.setType(OrderType.CHANGE.name());
		}

		// 保存订单项目
		List<OrderItem> items = new ArrayList<OrderItem>();
		for (OrderItemDTO orderItem : orderInfo.getOrderItems()) {
			OrderItem item = new OrderItem(orderItem);
			item.setId(sequence.getNextId());
			item.setOrderId(order.getOrderId());
			item.setEntId(orderInfo.getEntId());
			items.add(item);

			// 计算总金额
			order.setPrice(order.getPrice() + item.getPrice());
			order.setOffset(order.getOffset() + item.getOffset());
			order.setSave(order.getSave() + item.getSave());
		}
		orderMapper.addOrderInfo(order);
		if (items.size() > 0) {
			orderMapper.insertOrderItems(items);
		}
		return CommConstants.OK_MARK;
	}

	/**
	 * 判断时间是否过期
	 * 
	 * @param time
	 * @return
	 */
	private boolean isExpired(Date time) {
		boolean e = false;
		if (time != null && time.before(new Date())) {
			e = true;
		}
		return e;
	}
	
	/**
	 * 通过企业ID获得企业购买服务列表
	 * @param entId
	 * @return
	 */
	public BuyServiceListDTO getBuyServiceList(long entId){
		
		ServiceStatus serviceStatus = orderMapper.getServiceStatus(entId);
		
		List<BuyServiceDTO> bys = new ArrayList<BuyServiceDTO>();
		if(serviceStatus!=null){
			BuyServiceDTO ent = new BuyServiceDTO();
			BuyServiceDTO per = new BuyServiceDTO();
			BuyServiceDTO use = new BuyServiceDTO();
			BuyServiceDTO share = new BuyServiceDTO();
			
			if(serviceStatus.getEntDiskAmount()!=null){
				ent.setAmount(serviceStatus.getEntDiskAmount());
				ent.setBuyYear(serviceStatus.getUserBuyYear());
				ent.setStartDate(serviceStatus.getEntDiskStartDate());
				ent.setType(OrderItemType.ENT_DISK);
				SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
				String date = sdf.format(serviceStatus.getEntDiskEndDate());
				String nowDate = sdf.format(new Date());
				if(date.compareTo(nowDate)>=0){
					ent.setStatus(OrderItemStatus.ACTIVE);
				}else {
					ent.setStatus(OrderItemStatus.EXPIRED);
				}
				bys.add(ent);
			}
			if(serviceStatus.getPersonalDiskAmount()!=null){
				per.setAmount(serviceStatus.getPersonalDiskAmount());
				per.setBuyYear(serviceStatus.getPersonalDiskBuyYear());
				per.setStartDate(serviceStatus.getPersonalDiskStartDate());
				per.setType(OrderItemType.PERSON_DISK);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
				String date = sdf.format(serviceStatus.getEntDiskEndDate());
				String nowDate = sdf.format(new Date());
				if(date.compareTo(nowDate)>=0){
					ent.setStatus(OrderItemStatus.ACTIVE);
				}else {
					ent.setStatus(OrderItemStatus.EXPIRED);
				}
				bys.add(per);
			}
			if(serviceStatus.getUserCount()!=null){
				use.setAmount(serviceStatus.getUserCount());
				use.setBuyYear(serviceStatus.getUserBuyYear());
				use.setStartDate(serviceStatus.getUserStartDate());
				use.setType(OrderItemType.USER_ADD);
				
				SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd HH:mm");
				String date = sdf.format(serviceStatus.getEntDiskEndDate());
				String nowDate = sdf.format(new Date());
				if(date.compareTo(nowDate)>=0){
					ent.setStatus(OrderItemStatus.ACTIVE);
				}else {
					ent.setStatus(OrderItemStatus.EXPIRED);
				}
				bys.add(use);
			}
			if(serviceStatus.getShareLinkAmount()!=null){
				share.setAmount(serviceStatus.getShareLinkAmount());
			    share.setType(OrderItemType.SHARE_LINK);
			    bys.add(share);
			}
			
		}
		BuyServiceListDTO buyServices = new BuyServiceListDTO();
		buyServices.setList(bys);
		return buyServices;
	}
	
	public ServiceTypeListDTO getServicePriceList(){
		List<ServiceType> serviceTypeList = orderMapper.getServiceTypes();
		List<ServiceTypeDTO> serviceTypeDTOList = new ArrayList<ServiceTypeDTO>();
		for(ServiceType st:serviceTypeList){
			serviceTypeDTOList.add(st.toServiceTypeDTO());
		}
		ServiceTypeListDTO serviceTypes = new ServiceTypeListDTO();
		serviceTypes.setList(serviceTypeDTOList);
		return serviceTypes;
	}
	
	public OrderItemListDTO getOrderItemList(long entId){
		List<OrderItem> orderItemList = orderMapper.getOrderItemsByEntId(entId);
		List<OrderItemDTO> orderItemDTOList = new ArrayList<OrderItemDTO>();
		for(OrderItem oi:orderItemList){
			orderItemDTOList.add(oi.toOrderItemDTO());
		}
		OrderItemListDTO oDto = new OrderItemListDTO();
		oDto.setList(orderItemDTOList);
		return oDto;
	}
	
	/**
	 * 得到企业当前服务列表DTO
	 * 
	 * @param entId
	 * @return
	 */
	public CurrentServiceListDTO getCurrServiceList(long entId){
		ServiceStatus serviceStatus = orderMapper.getServiceStatus(entId);
		Enterprise enterprise = enterpriseMapper.getEnterpriseById(entId);
		
		CurrentServiceDTO entDto =  getEntDiskDTO(serviceStatus,enterprise);
		CurrentServiceDTO personDto = getPersonDiskDTO(serviceStatus,enterprise);
		CurrentServiceDTO userAddDto =  getUserAddDTO(serviceStatus,enterprise);
		CurrentServiceDTO shareDto = getShareLinkDTO(serviceStatus,enterprise);
		
		Map<String,CurrentServiceDTO> CurrentServiceDTOMap= new HashMap<String, CurrentServiceDTO>();
		CurrentServiceDTOMap.put("entDto", entDto);
		CurrentServiceDTOMap.put("personDto", personDto);
		CurrentServiceDTOMap.put("userAddDto", userAddDto);
		CurrentServiceDTOMap.put("shareDto", shareDto);
		
		CurrentServiceListDTO csDto = getCurrentServiceListDTO(CurrentServiceDTOMap);
		return csDto;

	}

	private CurrentServiceListDTO getCurrentServiceListDTO(
			Map<String, CurrentServiceDTO> currentServiceDTOMap) {
		
		CurrentServiceDTO entDto = currentServiceDTOMap.get("entDto");
		CurrentServiceDTO personDto = currentServiceDTOMap.get("personDto");
		CurrentServiceDTO userAddDto = currentServiceDTOMap.get("userAddDto");
		CurrentServiceDTO shareDto = currentServiceDTOMap.get("shareDto");
		
		CurrentServiceListDTO csDto = new CurrentServiceListDTO();
		List<CurrentServiceDTO> currentServiceList = new ArrayList<CurrentServiceDTO>();
		currentServiceList.add(entDto);
		currentServiceList.add(shareDto);
		currentServiceList.add(personDto);
		currentServiceList.add(userAddDto);
		csDto.setList(currentServiceList);
		return csDto;
		
	}

	private CurrentServiceDTO getShareLinkDTO(ServiceStatus serviceStatus,
			Enterprise enterprise) {
		CurrentServiceDTO shareDto = new CurrentServiceDTO();
		shareDto.setType(OrderItemType.SHARE_LINK);
		if(serviceStatus!=null&&serviceStatus.getShareLinkAmount()!=null){
			shareDto.setBuySize(serviceStatus.getShareLinkAmount()*1024*1024);
			long shareFreeSize = enterprise.getMaxShareLinkDownload()-serviceStatus.getShareLinkAmount()*1024*1024;
			shareDto.setFreeSize(shareFreeSize);
		}else{
			shareDto.setBuySize(0);
			shareDto.setFreeSize(enterprise.getMaxShareLinkDownload());
		}
		shareDto.setUsedSize(enterprise.getShareLinkDownCount());
		return shareDto;
		
	}

	private CurrentServiceDTO getUserAddDTO(ServiceStatus serviceStatus,
			Enterprise enterprise) {
		Long personUsingAmount = userMapper.getUserCountByEntId(enterprise.getEnterpriseId());
		CurrentServiceDTO userAddDto = new CurrentServiceDTO();
		userAddDto.setType(OrderItemType.USER_ADD);
		userAddDto.setUsedSize(personUsingAmount);
		if(serviceStatus!=null&&serviceStatus.getUserCount()!=null){
			userAddDto.setBuySize(serviceStatus.getUserCount()*1024*1024);
			long userFreeSize = enterprise.getMaxEmployees()-serviceStatus.getUserCount();
			userAddDto.setFreeSize(userFreeSize);
		}else {
			userAddDto.setBuySize(0);
			userAddDto.setFreeSize(enterprise.getMaxEmployees());
		}
		return userAddDto;
		
	}

	private CurrentServiceDTO getPersonDiskDTO(ServiceStatus serviceStatus,
			Enterprise enterprise) {
		Long personSum = userMapper.getPersonDiskUseSizeSum(enterprise.getEnterpriseId());
		CurrentServiceDTO personDto = new CurrentServiceDTO();
		personDto.setType(OrderItemType.PERSON_DISK);
		personDto.setUsedSize(personSum);
		if(serviceStatus!=null&&serviceStatus.getPersonalDiskAmount()!=null){
			personDto.setBuySize(serviceStatus.getPersonalDiskAmount()*1024*1024);
			long personfreeSize = enterprise.getPersonalDiskSize() - serviceStatus.getPersonalDiskAmount();
			personDto.setFreeSize(personfreeSize);
		}else {
			personDto.setBuySize(0);
			personDto.setFreeSize(enterprise.getPersonalDiskSize());
		}
		return personDto;
		
	}

	private CurrentServiceDTO getEntDiskDTO(ServiceStatus serviceStatus, Enterprise enterprise) {
		Long entSum = shareFileMapper.getFileSizeSumByEntId(enterprise.getEnterpriseId());
		CurrentServiceDTO entDto = new CurrentServiceDTO();
		entDto.setType(OrderItemType.ENT_DISK);
		entDto.setUsedSize(entSum);
		if(serviceStatus!=null&&serviceStatus.getEntDiskAmount()!=null){
			entDto.setBuySize(serviceStatus.getEntDiskAmount()*1024*1024);
			long entfreeSize = enterprise.getDiskSize() -serviceStatus.getEntDiskAmount()*1024*1024;
			entDto.setFreeSize(entfreeSize);
		}else{
			entDto.setBuySize(0);
			entDto.setFreeSize(enterprise.getDiskSize());
		}
		return entDto;
	}
	
	
	

}
