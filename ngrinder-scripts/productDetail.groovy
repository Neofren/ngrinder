import static net.grinder.script.Grinder.grinder
import static org.junit.Assert.*
import static org.hamcrest.Matchers.*
import net.grinder.script.GTest
import net.grinder.script.Grinder
import net.grinder.scriptengine.groovy.junit.GrinderRunner
import net.grinder.scriptengine.groovy.junit.annotation.BeforeProcess
import net.grinder.scriptengine.groovy.junit.annotation.BeforeThread
import static net.grinder.util.GrinderUtils.* // You can use this if you're using nGrinder after 3.2.3
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Test
import org.junit.runner.RunWith

import java.util.Random

import org.ngrinder.http.HTTPRequest
import org.ngrinder.http.HTTPRequestControl
import org.ngrinder.http.HTTPResponse
import org.ngrinder.http.cookie.Cookie
import org.ngrinder.http.cookie.CookieManager

//@RunWith(GrinderRunner)
class TestRunner {
	public static GTest banner
	public static GTest serviceAreas
	public static GTest productGroups
	public static GTest products
	public static GTest pickupZones
	public static GTest mainPopUp
	public static GTest productDetail
	public static HTTPRequest request
	public static Map<String, String> headers = [:]
	public static Map<String, Object> params = [:]
	public static List<Cookie> cookies = []


	@BeforeProcess
	public static void beforeProcess() {
//		HTTPRequestControl.setConnectionTimeout(300000)

		banner = new GTest(1, "banner")
		serviceAreas = new GTest(2, "serviceAreas")
		productGroups = new GTest(3, "productGroups")
		products = new GTest(4, "products")
		pickupZones = new GTest(5, "pickupZones")
		mainPopUp = new GTest(6, "mainPopup")
		productDetail = new GTest(7, "productDetail")

		request = new HTTPRequest()
	}


	@BeforeThread
	public void beforeThread() {
		banner.record(this, "banner")
		serviceAreas.record(this, "serviceAreas")
		productGroups.record(this, "productGroups")
		products.record(this, "products")
		pickupZones.record(this, "pickupZones")
		mainPopUp.record(this, "mainPopUp")
		productDetail.record(this, "productDetail")

		grinder.statistics.delayReports = true
	}


	@Before
	public void before() {
		request.setHeaders("X-SERVICE-ID" : "O19anP0GQoaZcnK1indJpw")
		//CookieManager.addCookies(cookies)
	}


	@Test
	public void banner() {
		//상단배너
		HTTPResponse res0 = request.GET("https://flex-api.42dot.io/api/promotions/screen-management/SRCG000002")
	}


	@Test
	public void serviceAreas() {
		//서비스지역 필터
		HTTPResponse res1 = request.GET("https://flex-api.42dot.io/api/products/service-areas")
	}


	@Test
	public void productGroups() {
		//상품그룹목록
		HTTPResponse res2 = request.GET("https://flex-api.42dot.io/api/product-groups")
	}


	@Test
	public void products() {
		//상품목록
		HTTPResponse res3 = request.GET("https://flex-api.42dot.io/api/product-groups/7/products")
	}


	@Test
	public void pickupZones() {
		//픽업존 목록
		HTTPResponse res4 = request.GET("https://flex-api.42dot.io/api/services/pickup-zones")
	}


	@Test
	public void mainPopUp() {
		//메인팝업
		HTTPResponse res5 = request.GET("https://flex-api.42dot.io/api/promotions/screen-management/SRCG000003")
	}


	@Test
	public void productDetail() {
		def productList = [
		218, 219, 223, 400, 322, 324, 325, 314, 222, 309, 307, 308, 224, 228, 229, 304, 318, 317, 320, 221, 302, 303, 305, 310, 316, 315, 306, 323, 311, 326 
		]

		def url = "https://flex-api.42dot.io/api/products/"

		def random = new Random()
		def selectedNumber = productList[random.nextInt(productList.size())]
		url += "${selectedNumber}"

		HTTPResponse response = request.GET(url)
	}
}