//특정 개 상품의 상세 정보보기 요청을 처리하는 Action클래스
package action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogViewService;
import vo.ActionForward;
import vo.Dog;

public class DogViewAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		DogViewService dogViewService = new DogViewService();
		int id = Integer.parseInt(request.getParameter("id"));
		//해당 개상품의 조회수 1증가 + id로 조회한 개상품 정보를 Dog객체로 반환
		Dog dog = dogViewService.getDogView(id);
		//request영역에 개 하나의 정보가 담긴 Dog객체를 속성으로 공유
		request.setAttribute("dog", dog);
		
		//★JSP책 753p-두번째그림(dogList.jsp) : 오늘 본 개상품 목록 정보를 알기위해서는 개 하나의 상세정보 보기를 한 후
		Cookie todayImageCookie = new Cookie("today"+id, dog.getImage());//ex)푸들클릭:"totay1,"pu.jpg"
		todayImageCookie.setMaxAge(60*60*24);//60*60*24초=>24시간 설정
		//★반드시 응답에 쿠키객체를 추가해야한다.
		response.addCookie(todayImageCookie);
		
		forward= new ActionForward("dogView.jsp", false);
		//forward.setPath("dogView.jsp");//비어있는 기본생성자로 할 경우. 기본값 false이므로 디스패치방식
		
		return forward;
	}

}
