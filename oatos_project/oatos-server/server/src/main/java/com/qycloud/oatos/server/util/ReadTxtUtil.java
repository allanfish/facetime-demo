package com.qycloud.oatos.server.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.conlect.oatos.dto.status.CommConstants;

public class ReadTxtUtil {

	private final static String enterpriseRegistrationMailPath = "property/enterpriseRegistrationMail.html";
	private final static String enterpriseRegistrationMailPathEn = "property/enterpriseRegistrationMail_en.html";
	private final static String enterpriseUserMailPath = "property/enterpriseUserMail.html";
	private final static String enterpriseUserMailPathEn = "property/enterpriseUserMail_en.html";
	private final static String shareLinkMailPath = "property/shareLinkMail.html";
	private final static String shareLinkMailPathEn = "property/shareLinkMail_en.html";

	private static String enterpriseRegistrationMailText = null;
	private static String enterpriseRegistrationMailTextEn = null;
	private static String enterpriseUserMailText = null;
	private static String enterpriseUserMailTextEn = null;
	private static String shareLinkMailText = null;
	private static String shareLinkMailTextEn = null;
	
	private final static String enterpriseRegistrationMailPathNet = "property/enterpriseRegistrationMailNet.html";
	private final static String enterpriseUserMailPathNet = "property/enterpriseUserMailNet.html";
	private final static String shareLinkMailPathNet = "property/shareLinkMailNet.html";

	private static String enterpriseRegistrationMailTextNet = null;
	private static String enterpriseUserMailTextNet = null;
	private static String shareLinkMailTextNet = null;

	/**
	 * get buddy invitation mail text
	 * @return
	 */
	public static String getBuddyInviteMailText() {
		return "";
	}
	
	/**
	 * get registration mail text
	 * @return
	 */
	public static String getVerifyMailText() {
		return "";
	}

	/**
	 * get retrieve password mail text
	 * @return
	 */
	public static String getRetrievePasswordMailText() {
		return "";
	}

	/**
	 * get enterprise mail text
	 * 
	 * @return
	 */
	public static String getEnterpriseRegistrationMailText(String locale, boolean isNet) {
		if (isNet) {
	        if (enterpriseRegistrationMailTextNet == null) {
	        	enterpriseRegistrationMailTextNet = getTemplate(enterpriseRegistrationMailPathNet);
            }
	        return enterpriseRegistrationMailTextNet;
        } else {
			if (locale != null && locale.indexOf("en") != -1) {
				if (enterpriseRegistrationMailTextEn == null) {
					enterpriseRegistrationMailTextEn = getTemplate(enterpriseRegistrationMailPathEn);
		        }
				return enterpriseRegistrationMailTextEn;
            } else {
        		if (enterpriseRegistrationMailText == null) {
        			enterpriseRegistrationMailText = getTemplate(enterpriseRegistrationMailPath);
                }
        		return enterpriseRegistrationMailText;
			}
		}
	}

	/**
	 * get enterprise user mail text
	 * @return
	 */
	public static String getEnterpriseUserMailText(String locale, boolean isNet) {
		if (isNet) {
	        if (enterpriseUserMailTextNet == null) {
	        	enterpriseUserMailTextNet = getTemplate(enterpriseUserMailPathNet);
            }
	        return enterpriseUserMailTextNet;
        } else {
			if (locale != null && locale.indexOf("en") != -1) {
				if (enterpriseUserMailTextEn == null) {
					enterpriseUserMailTextEn = getTemplate(enterpriseUserMailPathEn);
		        }
				return enterpriseUserMailTextEn;
            } else {
        		if (enterpriseUserMailText == null) {
        			enterpriseUserMailText = getTemplate(enterpriseUserMailPath);
                }
        		return enterpriseUserMailText;
			}
		}
	}

	/**
	 * get share link mail text
	 * @return
	 */
	public static String getShareLinkMailText(String locale, boolean isNet) {
		if (isNet) {
	        if (shareLinkMailTextNet == null) {
	        	shareLinkMailTextNet = getTemplate(shareLinkMailPathNet);
            }
	        return shareLinkMailTextNet;
        } else {
			if (locale != null && locale.indexOf("en") != -1) {
				if (shareLinkMailTextEn == null) {
					shareLinkMailTextEn = getTemplate(shareLinkMailPathEn);
		        }
				return shareLinkMailText;
            } else {
        		if (shareLinkMailText == null) {
        			shareLinkMailText = getTemplate(shareLinkMailPath);
                }
        		return shareLinkMailText;
			}
		}
	}
	
	private static String getTemplate(String templateName) {
		
		String template = null; 
	    
			try {
				InputStream inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(templateName);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, CommConstants.CHARSET_UTF_8));
				StringBuilder buffer = new StringBuilder();
				char[] chars = new char[1024];
				while (reader.read(chars) != -1) {
					buffer.append(new String(chars)); // 将读到的内容添加到 buffer 中
					chars = new char[1024];
				}
				
				template = buffer.toString().trim();
				BllLogger.getLogger().info(template);
				inputStream.close();
				reader.close();
            }
            catch (Exception ex) {
            	template = null;
            	BllLogger.getLogger().error("read " + templateName + " error", ex);
            }
        
		return template;
    }

}
