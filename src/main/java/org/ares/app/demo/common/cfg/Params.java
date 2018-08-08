package org.ares.app.demo.common.cfg;

/*@PropertySource("classpath:ares.properties")*/
public class Params {

	public static final String RET_KEY_OF_MESSAGE="message";
	public static final String RET_KEY_OF_CODE="code";
	public static final String KEY_OF_AUTH_USER="UserName";
	
	public static final String REGISGER_URL="/api/user/regisger";
	public static final String ACCESS_DENY_URL="/accessDenied";
	
	public static final String API_URL="/api/**";
	public static final String OTHER_API_URL="/action/**";
	
	public static final String JS_URL="/js/**";
	public static final String CSS_URL="/css/**";
	public static final String IMG_URL="/images/**";
	public static final String FAVICON_ICO_URL="/**/favicon.ico";
	public static final String H2_DB_CONSOLE="/h2-console/**";
	
	public static final String SAND_SIMULATE_URL1="/transportservice/action";
	public static final String SAND_SIMULATE_URL2="/simusand/api/v2";
	public static final String ALL_SUB_PATH="/**";
		
	public static final String LOGIN_URL="/login";
	public static final String LOGIN_PAGE_URL="/userlogin";
	public static final String LOGOUT_URL="/logout";
	public static final String INDEX_URL="/";
	
	public static final String ROLE_ADMIN="admin";
	public static final String ROLE_AUSER="adv_user";
	public static final String ROLE_NUSER="nor_user";
	
	public static final String CLIENT_REQUEST_IP="client_request_ip";
	
	public static final String ROLE_ADMIN_LABEL="超级管理员";
	public static final String ROLE_AUSER_LABEL="一般管理员";
	public static final String ROLE_NUSER_LABEL="普通用户";
	
	public static final String KEY_RESULT_OF_SRV="RESULT";
	public static final String KEY_ERRMSG_OF_SRV="ERRMSG";
	public static final String KEY_RESULT_OF_SAND="result";
	public static final String KEY_ROWS_OF_SRV="ROWS_DETAIL";
	
	public static final String MSG_SUCCESS_OF_SRV="成功";
	public static final String MSG_FAILED_OF_SRV="失败";
	public static final String CODE_SUCCESS_OF_SRV="S";
	public static final String CODE_FAILED_OF_SRV="F";
	public static final String MSG_SUCCESS_OF_SAND="ok";
	public static final String MSG_FAILED_OF_SAND="failed";
	 
	public static final String RET_ERR_CODE_NORMALEXCEPTION="105";
	public static final String RET_ERR_CODE_SIGNEXCEPTION="108";
	
	public static final String ERR_MSG_USERNAME_INVALID="username is invalid";
	public static final String ERR_MSG_USER_NOT_AUTH="user is not auth";
	public static final String ERR_MSG_USER_NOT_APPROVE="user is not approve";
	public static final String ERR_MSG_REQUEST_BODY_EMPTY="request body is empty";
	public static final String ERR_MSG_ACCESS_SANDTABLE_FAILED="access sand_table failed,maybe param lose or timeout";
	public static final String ERR_MSG_SANDTABLE_NULL="sand_table response is null";
	public static final String ERR_MSG_REQUEST_PARAM_LOSE="request param is lose";
	public static final String ERR_MSG_PARAM_VALUE="param value is invalid";
	public static final String ERR_MSG_REQUEST_ADDR="request ip is invalid";
	public static final String ERR_MSG_NOT_FOUND_ENTITY="not found this id";
	public static final String ERR_MSG_MONEY_LE_ZERO="param money must be greater then 0";
	public static final String ERR_MSG_MONEY_NOT_SUFF="not sufficlent funds";
	
}
