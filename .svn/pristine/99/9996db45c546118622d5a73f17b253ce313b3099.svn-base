package com.conlect.oatos.utils;

import com.conlect.oatos.dto.client.EnterpriseDTO;
import com.conlect.oatos.http.PojoMapper;

public class MailTest {

	public static void main(String[] args) {
		EnterpriseDTO e = new EnterpriseDTO();
		e.setEnterpriseName("test");
		String str = PojoMapper.toJson(e);

		// String str =
		// "{'nonce':null,'hashKey':null,'agent':'ios','enterpriseId':0,'enterpriseName':'hank','phone':'fadfsafd','fax':null,'address':'fdasfdsafds','postcode':null,'website':null,'adminPassword':'123456','maxEmployees':0,'logo':null,'skin':null,'employeePassword':null,'productKey':'NJNSO-YHT7N-3OIJV-T6HLH-5ABWR','cloudDiskIp':null,'diskSize':0,'registerTime':null,'expirationTime':null,'mobile':'34432','mail':'dfasfds','contact':'fdasfd','shareDiskVersion':0,'personalDiskSize':0,'personDiskUsed':0,'maxShareLinkDownload':0,'shareLinkDownCount':0,'status':null,'locale':null,'net':false,'payStatus':null,'entModule':null}";
		EnterpriseDTO entDTO = PojoMapper.fromJsonAsObject(str,
				EnterpriseDTO.class);
		String i = "2";
		System.out.print(i);
	}

}
