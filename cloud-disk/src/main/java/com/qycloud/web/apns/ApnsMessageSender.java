package com.qycloud.web.apns;

import java.util.Collection;
import java.util.List;

import com.conlect.oatos.utils.SysLogger;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsDelegate;
import com.notnoop.apns.ApnsNotification;
import com.notnoop.apns.ApnsService;
import com.notnoop.apns.DeliveryError;

public class ApnsMessageSender {

	private String identityFile;

	public ApnsMessageSender(String identityFile) {
		this.identityFile = identityFile;
	}

	public void send(List<String> deviceTokens, String message) {
		final ApnsDelegate delegate = new ApnsDelegate() {
			public void messageSent(final ApnsNotification message, final boolean resent) {
				System.out.println("Sent message " + message + " Resent: " + resent);
			}

			public void messageSendFailed(final ApnsNotification message, final Throwable e) {
				System.out.println("Failed message " + message);

			}

			public void connectionClosed(final DeliveryError e, final int messageIdentifier) {
				System.out.println("Closed connection: " + messageIdentifier + "\n   deliveryError " + e.toString());
			}

			public void cacheLengthExceeded(final int newCacheLength) {
				System.out.println("cacheLengthExceeded " + newCacheLength);

			}

			public void notificationsResent(final int resendCount) {
				System.out.println("notificationResent " + resendCount);
			}
		};
		ApnsService svc = null;
		try {
			svc = APNS.newService().withAppleDestination(true).withCert(identityFile, "oatos-push")
					.withSandboxDestination().withDelegate(delegate).build();
			svc.start();
			SysLogger.getOsLogger().info("[START] Sending APNS message");
			final String payload = APNS.newPayload().alertBody("我是余飞!").build();
			final Collection<? extends ApnsNotification> goodMsg = svc.push(deviceTokens, payload);
			for (ApnsNotification notify : goodMsg) {
				SysLogger.getOsLogger().info("[END ] APNS Message id: " + notify.getIdentifier());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (svc != null)
				svc.stop();
		}

	}
}
