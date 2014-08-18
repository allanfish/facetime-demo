package com.qycloud.web.service.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.client.FileUploadResultDTO;
import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;
import com.qycloud.web.bean.jms.JmsSender;
import com.qycloud.web.utils.ConfigUtil;

@Component
public class FileMessageSender {

	@Autowired
	private JmsSender jmsSender;

	private static final XhrProxy HTTP = XhrProxy.getInstance();

	/**
	 * 发送文件上传完成消息给界面
	 * // 暂时没用
	 * @param uploadBean
	 * @param result
	 * @return
	 */
	public String sendUploadDoneMessage(FileUploadDTO uploadBean, String token) {
		FileUploadResultDTO resultDTO = new FileUploadResultDTO(uploadBean.getFileId(), uploadBean.getFileName(),
				uploadBean.getType(), CommConstants.OK_MARK);

		MessageDTO msg = new MessageDTO();
		msg.setMessageType(MessageType.FileUploadDone);
		msg.setReceiver(uploadBean.getUserId());
		msg.setMessageBody(PojoMapper.toJson(resultDTO));
		msg.setSender(0L);

		return HTTP.post(ConfigUtil.getValue(MyConst.SendMessage), token, PojoMapper.toJson(msg));
	}
}
