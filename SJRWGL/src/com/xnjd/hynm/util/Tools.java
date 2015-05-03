package com.xnjd.hynm.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

/** 工具类 */
public class Tools {
	//创建日期格式
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	//创建中国的货币格式
	private static NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.CHINA);	

	/** CKeditor内置的分页字符串 */
	//private static String ck_separator="<div style=\"page-break-after: always;\"><span style=\"display: none;\">&nbsp;</span></div>";
	
	/** 取得指定图片的宽度与高度 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map getPicWidthHeight(String filename){
		Map map = new HashMap();
		try {
			BufferedImage sourceImg = javax.imageio.ImageIO.read(new FileInputStream(filename));
			map.put("width", sourceImg.getWidth());
			map.put("height", sourceImg.getHeight());
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
/*	*//** 是否没有指定的操作权限 *//* 
	public static boolean isDisable(Admin admin, int option) {
		if(admin==null){
			return true;
		}else{
			if (admin.getPrivileges().substring(0, 1).equals("1"))
				return false;
			else {
				if (admin.getPrivileges().substring(option - 1, option).equals("1"))
					return false;
				else
					return true;
			}			
		}
	}
	
	*//** 是否拥有指定的操作权限 *//* 
	public static boolean isEnable(Admin admin, int option) {
		if(admin==null){
			return false;
		}else{
			if (admin.getPrivileges().substring(0, 1).equals("1"))
				return true;
			else {
				if (admin.getPrivileges().substring(option - 1, option).equals("1"))
					return true;
				else
					return false;
			}			
		}
	}*/
	
	/** 取得随机主文件名 */
	public synchronized static String getRndFilename(){
		return String.valueOf(System.currentTimeMillis());
	}
	
	/** 取得指定文件的文件扩展名 */
	public synchronized static String getFileExtName(String filename){
		int p = filename.indexOf(".");
		return filename.substring(p);
	}
	
	/** 验证上传文件的类型是否合法 fileType:1-图片 2-视频*/
	public synchronized static boolean isEnableUploadType(String filename){
		String enableExtNames = null;
		if(filename==null||filename.isEmpty())return false;
		else
		{
		int p = filename.indexOf(".");
		String fileExtName = filename.substring(p).toLowerCase();
	//图片文件类型
	    enableExtNames=".doc,.docx,.pdf,.txt,.jpg,.png,.psd,.bmp,.xls";
		if (enableExtNames.indexOf(fileExtName)!=-1)return true;
		else return false;

		}
	}	
	
	
    /** 截取指定字节数的字符串,且确保汉字不被拆分 */
	public static String cutString(String text, int textMaxChar){   
        int size,index;   
        String result = null;  
        if(textMaxChar<=0){   
        	result= text;   
        }else{   
            for(size=0,index=0;index<text.length()&&size<textMaxChar;index++){   
                size += text.substring(index,index+1).getBytes().length;   
            }   
            result = text.substring(0,index);   
        }  
        return result;   
    }
	
    /** 按yyyy-MM-dd格式格式化日期 */
	public static String formatDate(Date date){   
		if (date==null){
			return "";
		}else{
			return df.format(date);
		}
    }
	
  
	
	/** 取得格式化后的中国货币字符串 */
	public static String formatCcurrency(double money){
		return currencyFormat.format(money);   		
	}
	
	public static String randomPwd(){
		final char[] CHARS = { '1','2', '3', '4', '5', '6', '7', '8',
			'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k', 'l', 'm',
			'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		Random random = new Random();// 随机类
			StringBuffer buffer = new StringBuffer();
			for (int i = 0; i < 6; i++) {
				buffer.append(CHARS[random.nextInt(CHARS.length)]);
			}
			return buffer.toString();
	}
	 /**
	   * 去掉字符串里面的html代码。<br>
	   * 要求数据要规范，比如大于小于号要配套,否则会被集体误杀。
	   * 
	   * @param content
	   *          内容
	   * @return 去掉后的内容
	   */
	  public static String stripHtml(String content) {
	    // <p>段落替换为换行
	    content = content.replaceAll("<p .*?>", "/r/n");
	    // <br><br/>替换为换行
	    content = content.replaceAll("<br//s*/?>", "/r/n");
	    // 去掉其它的<>之间的东西
	    content = content.replaceAll("//<.*?>", "");
	    // 还原HTML
	    // content = HTMLDecoder.decode(content);
	    return content;
	  }
	  
	  /**
	     * 递归删除目录下的所有文件及子目录下所有文件
	     * @param dir 将要删除的文件目录
	     * @return boolean Returns "true" if all deletions were successful.
	     *                 If a deletion fails, the method stops attempting to
	     *                 delete and returns "false".
	     */
	    public static boolean deleteDir(File dir) {
	        if (dir.isDirectory()) {
	            String[] children = dir.list();//递归删除目录中的子目录下
	            for (int i=0; i<children.length; i++) {
	                boolean success = deleteDir(new File(dir, children[i]));
	                if (!success) {
	                    return false;
	                }
	            }
	        }
	        // 目录此时为空，可以删除
	        return dir.delete();
	    }
	    /**
	     * 计算两个日期之差
	     * @throws ParseException 
	     */
	    public static int TwoDateIntervalDay(Date smDate,Date bdDate) throws ParseException{
	    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    	smDate = df.parse(df.format(smDate));
	    	bdDate = df.parse(df.format(bdDate));
	    	Calendar calendar = Calendar.getInstance();
	    	calendar.setTime(smDate);
	    	long smTimes = calendar.getTimeInMillis();
	    	calendar.setTime(bdDate);
	    	long bdTimes = calendar.getTimeInMillis();
	    	long betweenDays=(bdTimes-smTimes)/(1000*3600*24);  
	    	return Integer.parseInt(String.valueOf(betweenDays));
	    }
}
