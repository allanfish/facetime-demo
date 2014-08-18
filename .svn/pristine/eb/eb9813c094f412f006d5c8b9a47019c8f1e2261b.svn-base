/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.qycloud.web.activemq;

import javax.jms.MessageConsumer;

import org.apache.activemq.MessageAvailableListener;
import org.eclipse.jetty.continuation.Continuation;

/*
 * Listen for available messages and wakeup any continuations.
 */
public class AjaxListener implements MessageAvailableListener {
	private Continuation continuation;

	AjaxListener(AjaxWebClient client, long maximumReadTimeout) {
	}

	public void access() {
	}

	public synchronized void setContinuation(Continuation continuation) {
		this.continuation = continuation;
	}

	public synchronized void onMessageAvailable(MessageConsumer consumer) {
		if (continuation != null) {
			/**
			 * 不要在这里接收消息，因为在Firefox浏览器下刷新页面时会继续之前的get请求，将导致混乱，从而导致部分些消息接收不到，
			 * 所以统一在 {@link MessageListenerServlet}下接收消息
			 */
			continuation.resume();
		}
	}
}
