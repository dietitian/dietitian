package zkl.dietitian.entity.log;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 网站访问日志
 * @author zkl
 *
 */
@Entity
@Table(name="access_log")
public class AccessLog {
	@Id
	@GenericGenerator(name="uuidGenerator",strategy="uuid")
	@GeneratedValue(generator="uuidGenerator")
	private String id;
	//当前请求访问时间
	private String visitTime;
	//日期型请求访问时间
	private Long  visitTime2;
	//远程客户端的IP主机地址
	private String userHostAddress;
	//远程客户端的DNS名称
	private String userHostName;
	//当前请求的绝对URI
	private String urlAbsoluteUri;
	//当前请求的URL相对应的物理文件路径
	private String physicalPath;
	//客户端浏览器的原始用户代理信息
	private String userAgent;
	//客户端使用的HTTP数据传输方法
	private String httpMethod;
	//客户端语言首选项的排序字符
	private String userLanguages;
	
	//客户端主机的实例名
	private String urlHost;
	//当前URI的端口号
	private String urlPort;
	//当前输入流中的字节数
	private Integer totalBytes;
	//客户端发送的内容长度（以字节计）
	private Integer contentLength;
	//当前请求是否来自本地计算机
	private String isLocal;
	//浏览器的名称和主（整数）版本号
	private String browserType;
	//浏览器的完整版本号（包括整数和小数）
	private String browserVersion;
	//客户端使用的操作系统平台名称
	private String browserPlatform;
	//浏览器是否为测试版
	private String browserBeta;
	//浏览器是否支持ActiveX控件
	private String browserActiveXControls;
	//浏览器是否支持Cookie
	private String browserCookies;
	//浏览器是否为Web爬行遍历搜索引擎
	private String browserCrawler;
	//浏览器支持的EcmaScript主版本号
	private String browserJavaScript;
	//浏览器是否支持通过HTTP接收XML
	private String browserSupportsXmlHttp;
	//浏览器支持的输入类型
	private String browserInputType;
	//浏览器显示的近似宽度（单位像素）
	private Integer browserScreenPixelsWidth;
	//浏览器显示的近似高度（单位像素）
	private Integer browserScreenPixelsHeight;
	//客户端上次请求（该请求链接当前的URL）的绝对URI
	private String urlReferrerAbsoluteUri;
	//对UrlReferrerAbsoluteUri字段进行zh-cn或utf-9解
	private String urlReferrerAbsoluteUriDecode;
	//客户端上次请求（该请求链接当前的URL）的DNS名称
	private String urlReferrerHostName;
	//浏览器是否支持包括多个窗口的卡片组
	private String canCombineFormsInDeck;
	//浏览器是否为已识别的移动设备
	private String isMobileDevice;
	//已知移动设备制造商的名称
	private String mobileDeviceManufacturer;
	
	//已知移动设备的型号名
	private String mobileDeviceModel;
	//移动设备上软键的数目
	private String numberOfSoftkeys;
	//内容字符的编码
	private Integer contentEncoding;
	//浏览器显示的近似深度（单位像素）
	private String screenBitDepth;
	//访问Web站点
	private String website;
	//记录当前访客的惟一Cookies值
	private String webCookies;
	
	
	
	public Long getVisitTime2() {
		return visitTime2;
	}
	public void setVisitTime2(Long visitTime2) {
		this.visitTime2 = visitTime2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public String getUserHostAddress() {
		return userHostAddress;
	}
	public void setUserHostAddress(String userHostAddress) {
		this.userHostAddress = userHostAddress;
	}
	public String getUserHostName() {
		return userHostName;
	}
	public void setUserHostName(String userHostName) {
		this.userHostName = userHostName;
	}
	public String getUrlAbsoluteUri() {
		return urlAbsoluteUri;
	}
	public void setUrlAbsoluteUri(String urlAbsoluteUri) {
		this.urlAbsoluteUri = urlAbsoluteUri;
	}
	public String getPhysicalPath() {
		return physicalPath;
	}
	public void setPhysicalPath(String physicalPath) {
		this.physicalPath = physicalPath;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getHttpMethod() {
		return httpMethod;
	}
	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}
	public String getUserLanguages() {
		return userLanguages;
	}
	public void setUserLanguages(String userLanguages) {
		this.userLanguages = userLanguages;
	}
	public String getUrlHost() {
		return urlHost;
	}
	public void setUrlHost(String urlHost) {
		this.urlHost = urlHost;
	}
	public String getUrlPort() {
		return urlPort;
	}
	public void setUrlPort(String urlPort) {
		this.urlPort = urlPort;
	}
	public Integer getTotalBytes() {
		return totalBytes;
	}
	public void setTotalBytes(Integer totalBytes) {
		this.totalBytes = totalBytes;
	}
	public Integer getContentLength() {
		return contentLength;
	}
	public void setContentLength(Integer contentLength) {
		this.contentLength = contentLength;
	}
	public String getIsLocal() {
		return isLocal;
	}
	public void setIsLocal(String isLocal) {
		this.isLocal = isLocal;
	}
	public String getBrowserType() {
		return browserType;
	}
	public void setBrowserType(String browserType) {
		this.browserType = browserType;
	}
	public String getBrowserVersion() {
		return browserVersion;
	}
	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}
	public String getBrowserPlatform() {
		return browserPlatform;
	}
	public void setBrowserPlatform(String browserPlatform) {
		this.browserPlatform = browserPlatform;
	}
	public String getBrowserBeta() {
		return browserBeta;
	}
	public void setBrowserBeta(String browserBeta) {
		this.browserBeta = browserBeta;
	}
	public String getBrowserActiveXControls() {
		return browserActiveXControls;
	}
	public void setBrowserActiveXControls(String browserActiveXControls) {
		this.browserActiveXControls = browserActiveXControls;
	}
	public String getBrowserCookies() {
		return browserCookies;
	}
	public void setBrowserCookies(String browserCookies) {
		this.browserCookies = browserCookies;
	}
	public String getBrowserCrawler() {
		return browserCrawler;
	}
	public void setBrowserCrawler(String browserCrawler) {
		this.browserCrawler = browserCrawler;
	}
	public String getBrowserJavaScript() {
		return browserJavaScript;
	}
	public void setBrowserJavaScript(String browserJavaScript) {
		this.browserJavaScript = browserJavaScript;
	}
	public String getBrowserSupportsXmlHttp() {
		return browserSupportsXmlHttp;
	}
	public void setBrowserSupportsXmlHttp(String browserSupportsXmlHttp) {
		this.browserSupportsXmlHttp = browserSupportsXmlHttp;
	}
	public String getBrowserInputType() {
		return browserInputType;
	}
	public void setBrowserInputType(String browserInputType) {
		this.browserInputType = browserInputType;
	}
	public Integer getBrowserScreenPixelsWidth() {
		return browserScreenPixelsWidth;
	}
	public void setBrowserScreenPixelsWidth(Integer browserScreenPixelsWidth) {
		this.browserScreenPixelsWidth = browserScreenPixelsWidth;
	}
	public Integer getBrowserScreenPixelsHeight() {
		return browserScreenPixelsHeight;
	}
	public void setBrowserScreenPixelsHeight(Integer browserScreenPixelsHeight) {
		this.browserScreenPixelsHeight = browserScreenPixelsHeight;
	}
	public String getUrlReferrerAbsoluteUri() {
		return urlReferrerAbsoluteUri;
	}
	public void setUrlReferrerAbsoluteUri(String urlReferrerAbsoluteUri) {
		this.urlReferrerAbsoluteUri = urlReferrerAbsoluteUri;
	}
	public String getUrlReferrerAbsoluteUriDecode() {
		return urlReferrerAbsoluteUriDecode;
	}
	public void setUrlReferrerAbsoluteUriDecode(String urlReferrerAbsoluteUriDecode) {
		this.urlReferrerAbsoluteUriDecode = urlReferrerAbsoluteUriDecode;
	}
	public String getUrlReferrerHostName() {
		return urlReferrerHostName;
	}
	public void setUrlReferrerHostName(String urlReferrerHostName) {
		this.urlReferrerHostName = urlReferrerHostName;
	}
	public String getCanCombineFormsInDeck() {
		return canCombineFormsInDeck;
	}
	public void setCanCombineFormsInDeck(String canCombineFormsInDeck) {
		this.canCombineFormsInDeck = canCombineFormsInDeck;
	}
	public String getIsMobileDevice() {
		return isMobileDevice;
	}
	public void setIsMobileDevice(String isMobileDevice) {
		this.isMobileDevice = isMobileDevice;
	}
	public String getMobileDeviceManufacturer() {
		return mobileDeviceManufacturer;
	}
	public void setMobileDeviceManufacturer(String mobileDeviceManufacturer) {
		this.mobileDeviceManufacturer = mobileDeviceManufacturer;
	}
	public String getMobileDeviceModel() {
		return mobileDeviceModel;
	}
	public void setMobileDeviceModel(String mobileDeviceModel) {
		this.mobileDeviceModel = mobileDeviceModel;
	}
	public String getNumberOfSoftkeys() {
		return numberOfSoftkeys;
	}
	public void setNumberOfSoftkeys(String numberOfSoftkeys) {
		this.numberOfSoftkeys = numberOfSoftkeys;
	}
	public Integer getContentEncoding() {
		return contentEncoding;
	}
	public void setContentEncoding(Integer contentEncoding) {
		this.contentEncoding = contentEncoding;
	}
	public String getScreenBitDepth() {
		return screenBitDepth;
	}
	public void setScreenBitDepth(String screenBitDepth) {
		this.screenBitDepth = screenBitDepth;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getWebCookies() {
		return webCookies;
	}
	public void setWebCookies(String webCookies) {
		this.webCookies = webCookies;
	}
	
}
