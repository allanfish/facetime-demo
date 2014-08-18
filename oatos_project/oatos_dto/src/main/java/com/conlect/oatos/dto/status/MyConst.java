/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conlect.oatos.dto.status;

public interface MyConst {

	String DEFAULT_DOMAIN_NAME = "conlect.oatOS.";

	/**
	 * <code>yyyy-MM-dd HH:mm:ss</code>, e.g. <code>2011-01-01 09:00:00</code>
	 */
	String DATE_FORMAT_STD = "yyyy-MM-dd HH:mm:ss";

	String STR_DATE_FORMAT = "yyyy-MM-dd";
	/**
	 * <code>yyyy/MM/dd HH:mm:ss</code>, e.g. <code>2011/01/01 09:00:00</code>
	 */
	String DATE_FORMAT_STD_SLASH = "yyyy/MM/dd HH:mm:ss";
	/**
	 * <code>yyyy-M-d</code>, e.g. <code>2011-1-1</code>
	 */
	String DATE_FORMAT_SHORT = "yyyy-M-d";

	/**
	 * <code>HH:mm:ss</code>, e.g. <code>09:01:01</code>
	 */
	String DATE_FORMAT_HMS = "HH:mm:ss";
	/**
	 * <code>mm:ss</code>, e.g. <code>01:01</code>
	 */
	String DATE_FORMAT_MS = "mm:ss";

	String POST_PARAM_NAME = "message";

	// 服务器名称
	String AppService = "AppService";
	String DocConvertService = "DocConvertService";
	String FileManagerService = "FileManagerService";
	String FileCacheService = "FileCacheService";
	String SendMessage = "SendMessage";
	String UPLOAD_SERVLET_MAPPING = "fileupload.gupld";

	// temp path
	String TEMP_PATH = "temp/";
	String BUDDIES_CSV = "buddies_csv";
	String FEEDBACK_FILE = "feedback_file";

	// accept upload image types
	String[] uploadImageTypes = new String[] { "JPG", "JPEG", "PNG", "BMP",
			"GIF", };

	/**
	 * For regular expression validation
	 */
	String REG_FOR_ENTNAME = ".{2,}";
	String REG_FOR_MAIL = "^[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,4}$";
	String REG_FOR_MAIL2 = "[\\w._%+-]+@[\\w.-]+\\.[A-Za-z]{2,4}";
	String REG_FOR_MAIL_ACCOUNT_NAME = "[\\w._%+-]+";
	String REG_FOR_MAIL_ACCOUNT_PASSWORD = "^.{6,20}";
	String REG_FOR_MSN_MAIL = "^[\\w._%+-]+@(msn|hotmail).[A-Za-z]{2,4}$";
	String REG_FOR_YAHOO_MAIL = "^[\\w._%+-]+@yahoo.[A-Za-z]{2,4}$";
	String REG_FOR_GOOGLE_MAIL = "^[\\w._%+-]+@(gmail|googlemail).[A-Za-z]{2,4}$";
	String REG_FOR_NOT_NULL = ".+";
	String REG_FOR_USER_ACCOUNT = "^[a-zA-Z][\\w_.-]{2,19}$";
	String REG_FOR_FILE_NAME = "^[^\\/:*?\\u0022<>|]{1,250}";
	String REG_FOR_VERIFY_CODE = "^[a-zA-Z0-9]{4}$";
	String REG_FOR_MOBILE_NUMBER = "^([0-9]{1,3}[\\s\\-]?)?[0-9]{8,}$";
	String REG_FOR_NUM = "^\\d+$";
	String REG_FOR_SERIAL = "^[a-zA-Z0-9]{5}";
	String REG_FOR_LIMIT30 = ".{1,30}";

	String SYS_MSG_ICON = "images/tip32.png";
	// default user image
	String DEFAULT_USER_IMAGE = "images/defaultAvatar64man.png";
	String DEFAULT_WOMAN_IMAGE = "images/defaultAvatar64woman.png";

	// cookie key
	/**
	 * 使用使用LDAP域认证
	 */
	String COOKIE_LDAP_AUTH = "la";
	String COOKIE_USER_ACCOUNT = "ua";
	String COOKIE_ENTERPRISE_NAME = "en";
	String COOKIE_USER_TOKEN = "ut";
	String COOKIE_KEEP_SIGN_IN = "ksi";
	String COOKIE_SEARCH_ENGINE = "se";
	String COOKIE_USE_HTTPS = "uh";
	String COOKIE_SIGN_IN = "si";
	String COOKIE_LOGIN_FROM_REGISTER = "lr";
	String COOKIE_HTTP_TO_HTTPS = "hts";
	// sound setting
	String COOKIE_SOUND_STTING = "ss";
	// language setting
	String COOKIE_LANG = "l";
	// send message shortcut setting
	String COOKIE_SEND_MESSAGE_SHORTCUT = "ssm";
	// tips setting
	String COOKIE_TIPS_SHOW_LOGIN = "ts";
	// navigation direction
	String COOKIE_NAVIGATION_DIRECTION = "nd";
	// enterprise administrator sign in
	String COOKIE_ENTADMIN_TOKEN = "eat";
	// show admin beginner guide on login
	String COOKIE_SHOW_ADMIN_GUIDE = "sag";
	String COOKIE_SHOW_ADMIN_SETTING_GUIDE = "sasg";
	String COOKIE_SHOW_ADMIN_SETTING_GUIDE_1 = "sasg_1";
	// enterprise notice folders
	String COOKIE_ENTERPRISE_DISK_NOTICE_FOLDERS = "edfn_";
	// enterprise notice folders setting tip
	String COOKIE_ENTERPRISE_DISK_NOTICE_SETTING_TIP = "ednst_";
	String COOKIE_APP_VERSION = "avk";

	// when yahoo, google, msn return a url, it contains a notic key.
	String NOTICE_KEY = "from";
	String VERIFIER_KEY = "oauth_verifier";
	String YAHOO_CONSUMER_KEY = "dj0yJmk9MjN5VmJaMWhVcGlRJmQ9WVdrOVdXMXJVemw2TjJNbWNHbzlNVEF4TnpjM05UYzJNZy0tJnM9Y29uc3VtZXJzZWNyZXQmeD03NQ--";
	String YAHOO_CONSUMER_SECRET = "3faa60d8bd14b01a0c19365fcc8ec57efea369d4";
	// String GOOGLE_CONSUMER_KEY = "matthiaskaeppler.de";
	// String GOOGLE_CONSUMER_SECRET =
	// "etpfOSfQ4e9xnfgOJETy4D56";
	String GOOGLE_CONSUMER_KEY = "965477734990.apps.googleusercontent.com";
	String GOOGLE_CONSUMER_SECRET = "F89tBnGb-5nckTBBoWdmh9we";

	// main
	String CLIENT_TOKEN = "ClientToken";
	String USER_INFO = "UserInfo";
	String BUDDY_LIST = "BuddyList";
	String BUDDY_GROUP_LIST = "BuddyGroupList";
	String USER_ID = "userId";
	String DISK_FOLDER_LIST = "DISK_FOLDER_LIST";
	String DISK_FILE_LIST = "DISK_FILE_LIST";
	String EMPLOYEE_INFO = "EmployeeInfo";
	String COLLEAGUE_LIST = "ColleagueList";
	String CUSTOMER_LIST = "CustomerList";
	/**
	 * 企业网盘文件列表
	 */
	String SHARE_FILE_LIST = "SHARE_FILE_LIST";

	String CLOUD_DISK_IP = "CLOUD_DISK_IP";
	String ENTERPRISE_INFO = "ENTERPRISE_INFO";
	String DEPARTMENT_LIST = "DEPARTMENT_LIST";
	// pop browser window
	String CHAT_POP_OUT_PARA_ID = "buddy";
	String POPOUT_PARAM_KEY = "k";
	String POPOUT_PARAM_TOKEN = "t";
	String POPOUT_PARAM_CALL = "c";
	String POPOUT_PARAM_CALLID = "ci";
	// token
	String HOME_PAGE = "home";
	String MAIN_PAGE = "main";
	String LOGIN_PAGE = "login";
	String ACTIVE_PAGE = "activate";

	long NONE_DEPARTMENT_ID = 0;
	long BLACK_LIST_DEPARTMENT_ID = -1;
	long USUALCONTACT_LIST_DEPARTMENT_ID = -2;

	long HOME_FOLDER_ID = 0;
	long RECYCLE_BIN_ID = -1;

	String PARAM_MESSAGE = "PARAM_MESSAGE";
	String PARAM_ELEMENT = "PARAM_ELEMENT";

	String QUER_KEY_CLIENT_ID = "clientId";

	String CONTACT_US_URL = "http://qiao.baidu.com/?module=default&controller=webim&action=index&siteid=1517623&groupid=0&groupname=%E9%BB%98%E8%AE%A4%E5%88%86%E7%BB%84";

	String PRICING_URL = "http://app.oatos.com/pay/price";
}
