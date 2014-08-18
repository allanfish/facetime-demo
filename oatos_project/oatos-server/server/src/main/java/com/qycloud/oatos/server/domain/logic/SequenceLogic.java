package com.qycloud.oatos.server.domain.logic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qycloud.oatos.server.dao.SequenceMapper;
import com.qycloud.oatos.server.domain.entity.Sequence;

@Service("SequenceLogic")
public class SequenceLogic {

	// 应用层缓存的序列号数量，可以提升序列号发放的性能，不用频繁更新数据库。
	private int defaultCacheSize = 10;
	private String GLOBAL_ID = "global_id";
	// 缓存 Sequence 对象
	private static Map<String, Sequence> sequenceMap = new HashMap<String, Sequence>();

	@Autowired
	private SequenceMapper sequenceMapper;

	@Transactional
	public synchronized long getNextId(String name) {
		Sequence sequence;
		
		// 判断应用层是否有指定名称的 Sequence 对象
		if (sequenceMap.containsKey(name)) {
			sequence = sequenceMap.get(name);

			// 如果超出序列缓存边界，则重新从数据库初始化一个新的序列对象
			if (sequence.getNextId() >= sequence.getDbValue()) {
				sequence = initSequence(name);
			}
		} else {
			// 应用层没有指定名称的 Sequence 对象， 从数据库初始化一个。
			sequence = initSequence(name);
		}

		// nextId 加一
		sequence.setNextId(sequence.getNextId() + 1);

		return sequence.getNextId();
	}

	private synchronized Sequence initSequence(String name) {
		// 使用数据库记录填充 Sequence 对象
		Sequence sequence = (Sequence) sequenceMapper.getSequenceByName(name);

		if (sequence == null) {
			throw new RuntimeException("Error: could not get sequence,  sequence name: " + name);
		}

		// 设置序列缓存大小
		sequence.setCacheSize(defaultCacheSize);
		sequence.setDbValue(sequence.getNextId());

		// 在应用层保持序列对象，如果序列对象还有缓存的ID, 则不需要访问数据库
		sequenceMap.put(name, sequence);

		// 使用 dbValue 的值更新数据库中序列号
		long maxCacheValue = sequence.getDbValue() + defaultCacheSize;
		sequence.setDbValue(maxCacheValue);
		sequenceMapper.updateSequence(new Sequence(name, sequence.getDbValue()));
		return sequence;
	}

	public long getNextId() {
		return getNextId(GLOBAL_ID);
	}

}
