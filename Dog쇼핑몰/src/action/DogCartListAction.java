package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogCartListService;
import vo.ActionForward;
import vo.Cart;

public class DogCartListAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		DogCartListService dogCartListService = new DogCartListService();
		//주의 : 매개값으로 request 전송이유?session영역에 공유되어있는 장바구니목록객체(cartList)를 얻어오기위해
		ArrayList<Cart> cartList = dogCartListService.getCartLsit(request);
		
		/*JSP 762p : 총 금액 계산*/
		//지역변수 초기화
		int totalMoney = 0;//지불할 총 금액
		int money = 0;//장바구니 항목 하나에 대한 지불금액
		for (int i = 0 ; i < cartList.size() ; i++) {
			//장바구니에 불독1개,진돗개2개가 들어있다면 불독의가격 2000 * 1(수량) -> 진돗개 3000 * 2(수량)
			money = cartList.get(i).getPrice() * cartList.get(i).getQty();
			totalMoney = totalMoney + money;
		}
		
		//★dogCartList.jsp 뷰페이지로 총금액(totalMoney)과 전체 장바구니 목록(cartList)을 request영역에 공유하여
		request.setAttribute("totalMoney", totalMoney);
		request.setAttribute("cartList", cartList);
		
		ActionForward forward = new ActionForward("dogCartList.jsp", false);//디스패치방식으로 포워딩
		
		return forward;
	}

}
