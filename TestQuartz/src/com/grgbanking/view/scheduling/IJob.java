package com.grgbanking.view.scheduling;

import java.io.Serializable;


/**
 * 
 * Quartz �� Spring ����ʱ���Զ����Job����ӵ��Spring�������ģ�
 * ��˶����˸ýӿڣ��Զ����Job��Ҫʵ�ָýӿڣ���ʵ��executeInternal��task��
 * 
 * ���������Quartz ��Spring �ڼ�Ⱥ�����£����Բ���Ҫ���л���
 * ֻ��Ҫ��executeInternal��ȡSpring �������е�target job bean.
 * ��������صĴ�����������������

 */
public interface IJob extends Serializable{

	/**
	 * ��������ĺ��ĺ���
	 * 
	 * @param cxt Spring ������
	 */
	void executeInternal();

}
