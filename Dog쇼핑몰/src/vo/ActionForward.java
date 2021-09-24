/* 교재 p575 - bean
 * 3.포워딩 정보를 저장할 수 있는 ActionForward 클래스
 * 컨트롤러에서 클라이언트의 각 요청을 받아서 처리한 후
 * 최종적으로 뷰 페이지(jsp)로 포워딩 처리 시
 * 이동할 뷰 페이지의 url과 포워딩 방식(디스패치나 리다이렉트)이 필요하다.
 * 이 두 정보를 편리하게 다루기 위해서 ActionForward클래스를 설계
 */

package vo;

public class ActionForward {
	//컨트롤러에서 요청처리 후 포워딩 될 최중 뷰 페이지 url이 저장
	private String path;
	
	//포워딩 방식 저장. false이면 디스패치(기존요청), true이면 리다이렉트(새요청)
	private boolean Redirect;
	
	//매개변수가 없는 생성자
	public ActionForward() {}
	
	//매개변수가 있는 생성자
	public ActionForward(String path, boolean Redirect) {
		super();
		this.path = path;
		this.Redirect = Redirect;
	}
	
	//getter, setter
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isRedirect() {
		return Redirect;
	}
	public void setRedirect(boolean Redirect) {
		this.Redirect = Redirect;
	}
}
