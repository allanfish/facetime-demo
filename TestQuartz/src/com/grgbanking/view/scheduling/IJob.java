package com.grgbanking.view.scheduling;

import java.io.Serializable;


/**
 * 
 * Quartz 与 Spring 集成时，自定义的Job可以拥有Spring的上下文，
 * 因此定义了该接口，自定义的Job需要实现该接口，并实现executeInternal的task，
 * 
 * 这样解决了Quartz 与Spring 在集群环境下，可以不需要序列化，
 * 只需要在executeInternal获取Spring 上下文中的target job bean.
 * 调用其相关的处理函数，来处理任务

 */
public interface IJob extends Serializable{

	/**
	 * 处理任务的核心函数
	 * 
	 * @param cxt Spring 上下文
	 */
	void executeInternal();

}
