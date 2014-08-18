package com.qycloud.oatos.convert.domain.logic;

import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.qycloud.oatos.convert.util.Logs;

/**
 * 文档转换任务
 * 
 * @author yang
 * 
 */
public abstract class ConvertTask implements Runnable, Comparable<ConvertTask> {

	private DocConvertDTO docDTO;

	public ConvertTask(DocConvertDTO docDTO) {
		this.docDTO = docDTO;
	}

	@Override
	public void run() {
		try {
			// 执行转换动作
			doConvert(docDTO);
		} catch (Exception ex) {
			Logs.getLogger().error(docDTO.getSourcePath() + "==convert to==" + docDTO.getTargetPath(), ex);
		}
	}

	/**
	 * 转换文档
	 * 
	 * @param docDTO
	 */
	protected abstract void doConvert(DocConvertDTO docDTO) throws Exception;

	public DocConvertDTO getDocDTO() {
		return docDTO;
	}

	@Override
	public int compareTo(ConvertTask o) {
		return docDTO.getPriorty() - o.getDocDTO().getPriorty();
	}

}
