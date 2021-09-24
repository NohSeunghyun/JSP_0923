package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartSearchService;
import vo.ActionForward;
import vo.Cart;

public class DogCartSearchAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DogCartSearchService dogCartSearchService = new DogCartSearchService();
		
		//String타입을 int타입으로 변환 이유? 비교연산자 사용을 위해
		int StartMoney = Integer.parseInt(request.getParameter("startMoney"));
		int endMoney = Integer.parseInt(request.getParameter("endMoney"));
		
		//ArrayList<Cart> searchCartList = dogCartSearchService.getCartSearchList(StartMoney, endMoney, request);
		ArrayList<Cart> cartList = dogCartSearchService.getCartSearchList(StartMoney, endMoney, request);
		
		/*JSP 768p : 검색한 항목에 대한 총 금액 계산*/
		//지역변수 초기화
		int totalMoney = 0;//검색한 항목의 지불할 총 금액
		int money = 0;//검색한 항목 하나에 대한 지불금액
		for (int i = 0 ; i < cartList.size() ; i++) {
			money = cartList.get(i).getPrice() * cartList.get(i).getQty();
			totalMoney = totalMoney + money;
		}
		
		//★dogCartList.jsp 뷰페이지로 총금액(totalMoney)만 request영역에 공유하여
		request.setAttribute("totalMoney", totalMoney);
		//request.setAttribute("searchCartList", searchCartList);//주의 : 공유안함 이유?
		
		ActionForward forward = new ActionForward("dogCartList.jsp", false);//디스패치방식으로 포워딩
		
		return forward;
	}

}
