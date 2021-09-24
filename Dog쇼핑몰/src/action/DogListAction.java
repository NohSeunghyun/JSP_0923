//개 상품 목록보기 요청을 처리하는 Action클래스
package action;

import java.util.ArrayList;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogListService;
import vo.ActionForward;
import vo.Dog;

public class DogListAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		DogListService dogListService = new DogListService();
		ArrayList<Dog> dogList = dogListService.getDogList();
		//request영역에 dogList이름으로 개 상품목록 정보를 속성값으로 공유
		request.setAttribute("dogList", dogList);
		
		//JSP책 751p-두번째 그림(dogList.jsp) : 오늘 본 개 상품 목록 정보를 알기위해서는 개 하나의 상세정보 보기를 한 후 처리
		Cookie[] cookieArray = request.getCookies();
		ArrayList<String> todayImageList = new ArrayList<String>();
		
		if(cookieArray != null) {
			for (int i = 0 ; i < cookieArray.length ; i++) {
				if(cookieArray[i].getName().startsWith("today")) {/* 예)getName() : 푸들 클릭 - "today1", 진돗개 클릭 - "today3"
																   * 쿠키객체에 추가한 오늘본 개상품을 찾는다=>startsWith : 시작문자열이 today인것이 있으면 참
																   */
					todayImageList.add(cookieArray[i].getValue());//예)getValue() : "pu.jpg", "jin.jpg"
				}
			}
		}
		//request영역에 todayImageList이름으로 오늘 본 개상품 이미지 목록정보를 속성값으로 공유
		request.setAttribute("todayImageList", todayImageList);
		//dogList.jsp로 디스패치방식으로 화면전환(=포워딩)
		forward = new ActionForward("dogList.jsp", false);//false은 디스패치방식
		
		return forward;
	}
}