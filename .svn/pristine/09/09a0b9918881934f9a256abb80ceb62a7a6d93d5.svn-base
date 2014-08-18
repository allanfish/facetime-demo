package com.qycloud.oatos.filemanager.util;

import java.util.Date;

import com.conlect.oatos.dto.client.MessageDTO;
import com.conlect.oatos.dto.client.ShareFileNewMessageDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.MessageType;
import com.conlect.oatos.dto.status.MyConst;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;

public class FileProxy {

	public static void sendShareFileNewMessage(String token, ShareFileNewMessageDTO fileNewDTO) {
		try {
			// send message
			MessageDTO message = new MessageDTO();
			message.setMessageType(MessageType.ShareFileNew);
			message.setMessageBody(PojoMapper.toJson(fileNewDTO));
			message.setSender(fileNewDTO.getUserId());
			message.setReceiver(fileNewDTO.getEnterpriseId());
			message.setSendDate(new Date());
			sendMessage(message, token);
		}
		catch (Exception ex) {
			Logs.getLogger().error(token, ex);
		}
	}

	public static String sendMessage(MessageDTO message, String token) {
		String postData = PojoMapper.toJson(message);
		
		String url = ConfigUtil.getValue(MyConst.SendMessage);
		XhrProxy.getInstance().post(url, token, postData);
		return CommConstants.OK_MARK;
	}
}
