package com.qycloud.oatos.server.domain.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conlect.oatos.dto.client.BlockDTO;
import com.conlect.oatos.dto.client.BlocksDTO;
import com.qycloud.oatos.server.dao.BlockListMapper;
import com.qycloud.oatos.server.domain.entity.Block;

/**
 * 阻止联系人服务
 * @author yang
 *
 */
@Service("BlockListLogic")
public class BlockListLogic {

	@Autowired
	private BlockListMapper blockListMapper;

	/**
	 * 用户取阻止通信的用户id
	 * @param userId
	 * @return
	 */
	public BlockDTO getBlockListByUserId(long userId) {
		BlockDTO blockDTO = new BlockDTO(userId);
		List<Block> blockModels = blockListMapper.getBlockListByUserId(userId);
		if (blockModels != null) {
			for (Block blockModel : blockModels) {
		        blockDTO.getBlockUserList().add(blockModel.getBlockUserId());
	        }
        }
		return blockDTO;
	}

	/**
	 * 用户设置阻止通信的用户id
	 * @param blockDTO
	 */
	@Transactional
	public void setBlockListByUserId(BlockDTO blockDTO) {
		if (blockDTO.getBlockUserList() != null && blockDTO.getBlockUserList().size() > 0) {
			for (Long blockUserId : blockDTO.getBlockUserList()) {
		        blockListMapper.addBlock(new Block(blockDTO.getUserId(), blockUserId));
	        }
        }
	}

	/**
	 * 取企业阻止通信列表(不可见)
	 * @param userId
	 * @return
	 */
    public BlockDTO getBlocksByUserId(long userId) {
		BlockDTO blockDTO = new BlockDTO(userId);
	    List<Block> blockList = blockListMapper.getEnterpriseBlocksByUserId(userId);
	    for (Block b : blockList) {
        	long blockUserId = b.getBlockUserId();
            if (b.getUserId() == userId) {
            	blockUserId = b.getBlockUserId();
            } else if (b.getBlockUserId() == userId) {
            	blockUserId = b.getUserId();
			}
            if (userId != blockUserId) {
            	blockDTO.getBlockUserList().add(blockUserId);
            }
        }
	    return blockDTO;
    }

    /**
     * 企业设置阻止通信列表(不可见)
     * @param blocksDTO
     */
	@Transactional
    public void updateBlocks(BlocksDTO blocksDTO) {
		for (Long userId : blocksDTO.getUserList()) {
	        blockListMapper.deleteEnterpriseBlockByUserId(userId);
	        if (blocksDTO.getBlockUserList() != null && blocksDTO.getBlockUserList().size() > 0) {
				List<Block> blockModels = new ArrayList<Block>();
				for (Long blockUserId : blocksDTO.getBlockUserList()) {
					Block block = new Block(userId, blockUserId, false);
			        blockModels.add(block);
		        }
				// set new block list
				blockListMapper.addBlockList(blockModels); 
            }
        }
	}

	/**
	 * 删除阻止
	 * @param blockDTO
	 */
	@Transactional
    public void deleteBlock(BlockDTO blockDTO) {
	    if (blockDTO.getBlockUserList() != null) {
	        for (long blockUserId : blockDTO.getBlockUserList()) {
	            Block blockModel = new Block(blockDTO.getUserId(), blockUserId, true);
	            blockListMapper.deleteBlock(blockModel);
            }
        }
    }

	/**
	 * 删除阻止与被阻止
	 * @param blockDTO
	 */
	@Transactional
    public void deleteEnterpriseBlock(BlockDTO blockDTO) {
	    if (blockDTO.getBlockUserList() != null) {
	        for (long blockUserId : blockDTO.getBlockUserList()) {
	            Block blockModel = new Block(blockDTO.getUserId(), blockUserId, false);
	            blockListMapper.deleteEnterpriseBlock(blockModel);
            }
        }
    }

	/**
	 * 用户取被阻止通信的用户id
	 * @param userId
	 * @return
	 */
    public BlockDTO getBlockedByListByUserId(long userId) {
		BlockDTO blockDTO = new BlockDTO(userId);
		List<Block> blockModels = blockListMapper.getBlockedByListByUserId(userId);
		if (blockModels != null) {
			for (Block blockModel : blockModels) {
		        blockDTO.getBlockUserList().add(blockModel.getUserId());
	        }
        }
		return blockDTO;
    }

}
