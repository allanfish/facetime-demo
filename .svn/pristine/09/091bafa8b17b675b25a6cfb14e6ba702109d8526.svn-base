package com.qycloud.oatos.server.test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

import com.conlect.oatos.dto.client.BlockDTO;
import com.conlect.oatos.dto.client.BlocksDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.http.PojoMapper;

/**
 * BlockListService test
 * @author xiao.min
 *
 */
public class BlockListServiceTest extends BaseServiceTest {
	
	
//	 // 测试sequence 获取id 的时间
//	@Test
//	public void testSequence(){
//		for (int i = 0; i < 10000; i++) {
//			
//		long s1 = System.currentTimeMillis();
//		sequence.getNextId();
//		long s2 = System.currentTimeMillis();
//		long time = s2-s1;
//		if(time>10l){
//			System.out.println(time);
//			}
//		}
//	}
	
	
	
	/**
	 * 用户取阻止通信的用户列表(黑名单)
	 * pass
	 */
	@Test
	public void getBlockListByUserId(){
		System.out.println("=================用户取阻止通信的用户列表(黑名单)============");
	    //参数
		//方法
		String r = postData(RESTurl.getBlockListByUserId,"103527");
		//验证
		System.out.print(r);
		if("error500".equals(r)){
			fail(r);
		}
		BlockDTO blocks = PojoMapper.fromJsonAsObject(r, BlockDTO.class);
		
		assertEquals(blocks.getBlockUserList().size(), 2);
	}
	
	
	
	/**
	 *  管理员删除企业通信阻止(不可见)
	 *  pass
	 */
	@Test
	public void deleteEnterpriseBlock(){
		System.out.println("================= 管理员删除企业通信阻止(不可见)============");
	    //参数
		List<Long> blockList = new ArrayList<Long>();
		blockList.add(103570l);
		blockList.add(103587l);
		
		BlockDTO block = new BlockDTO();
		block.setUserId(103527);
		
		block.setBlockUserList(blockList);
		//方法
		String r = postData(RESTurl.deleteEnterpriseBlock,block);
		//验证
		System.out.print(r);
		if("error500".equals(r)){
			fail(r);
		}
		assertEquals(CommConstants.OK_MARK, r);
	}
	
	/**
	 * 用户取被那些同事设置为阻止通信
	 * pass
	 */
	@Test
	public void getBlockedByListByUserId(){
		System.out.println("=================用户取被那些同事设置为阻止通信============");
	    //参数
		//方法
		String r = postData(RESTurl.getBlockedByListByUserId ,"103527");
		//验证
		System.out.print(r);
		if("error500".equals(r)){
			fail(r);
		}
		//assertEquals(CommConstants.OK_MARK, r);
	}
	
	/**
	 *  取企业阻止通信列表(管理员设置不可见)
	 *  pass
	 */
	@Test
	public void getEnterpriseBlocksByUserId(){
		System.out.println("================= 取企业阻止通信列表(管理员设置不可见)============");
	    //参数
		String userId = "103527";
		//方法
		String r = postData(RESTurl.getEnterpriseBlocksByUserId ,userId);
		//验证
		System.out.print(r);
		if("error500".equals(r)){
			fail(r);
		}
		//assertEquals(CommConstants.OK_MARK, r);
	}
	
	/**
	 *  用户设置阻止通信的用户列表(黑名单)
	 *  pass
	 */
	@Test
	public void setBlockListByUserId(){
		System.out.println("================= 用户设置阻止通信的用户列表(黑名单)============");
	    //参数
		List<Long> blockList = new ArrayList<Long>();
		blockList.add(116255L);
		blockList.add(117184L);
		
		BlockDTO block = new BlockDTO();
		block.setUserId(userId3885);
		
		block.setBlockUserList(blockList);
		//方法
		String r = postData(RESTurl.setBlockListByUserId,block);
		//验证
		System.out.print(r);
		if("error500".equals(r)){
			fail(r);
		}
		assertEquals(CommConstants.OK_MARK, r);
		
		System.out.println("=================用户解除阻止通信的用户id============");
		String r1 = postData(RESTurl.deleteBlock,block);
		//验证
		System.out.print(r1);
		if("error500".equals(r1)){
			fail(r1);
		}
		assertEquals(CommConstants.OK_MARK, r1);
	}
	
	/**
	 * 管理员设置阻止通信列表(不可见)
	 */
	@Test
	public void updateEnterpriseBlocks (){
//		System.out.println("===============管理员设置阻止通信列表(不可见)============");
//	    //参数
//		List<Long> blockList = new ArrayList<Long>();
//		blockList.add(103570l);
//		blockList.add(103587l);
//		blockList.add(103520l);
//		
//		BlockDTO block = new BlockDTO();
//		block.setUserId(103510);
//		
//		block.setBlockUserList(blockList);
//		//方法
//		String r = postData(RESTurl.updateEnterpriseBlocks ,block);
//		//验证
//		System.out.println(r);
//		if("error500".equals(r)){
//			fail(r);
//		}
//		assertEquals(CommConstants.OK_MARK, r);
		
		
//		correct by jackhu	(reson:构造数据错误)	
		System.out.println("===============管理员设置阻止通信列表(不可见)============");
	    //参数
		List<Long> blockList = new ArrayList<Long>();
		blockList.add(user1);
		blockList.add(user2);
		
		List<Long> userIdList = new ArrayList<Long>();
		userIdList.add(userId3885);
		
		
		BlocksDTO blocksDTO = new BlocksDTO();
		blocksDTO.setBlockUserList(blockList);
		blocksDTO.setUserList(userIdList);
		//方法
		String r = postData(RESTurl.updateEnterpriseBlocks ,blocksDTO);
		//验证
		System.out.println(r);
		if("error500".equals(r)){
			fail(r);
		}
		assertEquals(CommConstants.OK_MARK, r);		
	}
	
	
}
