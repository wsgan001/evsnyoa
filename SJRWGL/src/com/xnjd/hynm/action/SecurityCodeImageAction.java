package com.xnjd.hynm.action;

import java.io.ByteArrayInputStream;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.xnjd.hynm.util.SecurityCode;
import com.xnjd.hynm.util.SecurityImage;

/**
 * 提供图片验证码
 * @version 1.0 2012/08/22
 * @author dongliyang
 */
@SuppressWarnings("serial")
public class SecurityCodeImageAction extends ActionSupport implements SessionAware{
    
	private String VCode;
	private boolean flag;
    //Struts2中Map类型的session
    private Map<String, Object> session;
    
    //图片流
    private ByteArrayInputStream imageStream;

    public ByteArrayInputStream getImageStream() {
        return imageStream;
    }

    public void setImageStream(ByteArrayInputStream imageStream) {
        this.imageStream = imageStream;
    }

    
    public String validateCode(){
    	String codeString=session.get("SESSION_SECURITY_CODE").toString();
    	if(VCode.equals(codeString))flag=true;
    	else flag=false;
    	//System.out.println(VCode);
    	 return SUCCESS;
    }
    
    public String execute() throws Exception {
        //如果开启Hard模式，可以不区分大小写
//        String securityCode = SecurityCode.getSecurityCode(4,SecurityCodeLevel.Hard, false).toLowerCase();
        
        //获取默认难度和长度的验证码
        String securityCode = SecurityCode.getSecurityCode();
        imageStream = SecurityImage.getImageAsInputStream(securityCode);
        //放入session中
        session.put("SESSION_SECURITY_CODE", securityCode);
        return SUCCESS;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

	public String getVCode() {
		return VCode;
	}

	public void setVCode(String vCode) {
		VCode = vCode;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

}