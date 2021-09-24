//새로운 개 상품 정보 등록 요청을 처리하는 Action클래스
package action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svc.DogRegistService;
import vo.ActionForward;
import vo.Dog;

//먼저 cos.jar를 lib안에 붙여넣기
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;//서버상에 A.txt파일이 있다면 그다음 A.txt파일을 업로드할땐 자동으로 A1.txt로 올라감
																//A.txt는 오리지널파일명 A1.txt는 시스템파일명
public class DogRegistAction implements Action {

	@Override
	public ActionForward exeute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String saveFolder = "/images";
		int maxSize = 1024*1024*5;//한번에 서버에 올릴 수 있는 최대용량. 5M로 제한한다면
					//1024*1024 = 1M
		
		//파일을 업로드할 서버상의 실제경로
		ServletContext context = request.getServletContext();
		String realFolder = context.getRealPath(saveFolder);
		
		MultipartRequest multi = new MultipartRequest(request, realFolder, maxSize, "UTF-8", new DefaultFileRenamePolicy());
		//서버상에 업로드된 파일이름을 얻어와
		//String imageFile = multi.getFilesystemName("imageFile");
		
		Dog dog = new Dog(0, //0인 이유?insert할 때 id값은 dog_seq.nextval로 자동 1증가
					multi.getParameter("kind"), 
					multi.getParameter("country"), 
					Integer.parseInt(multi.getParameter("price")), 
					Integer.parseInt(multi.getParameter("height")), 
					Integer.parseInt(multi.getParameter("weight")), 
					multi.getParameter("content"), 
					multi.getFilesystemName("image"), 
					0);//조회수 : 0부터 시작
		
		DogRegistService dogRegistService = new DogRegistService();
		//새로운 개 정보(dog)를 dog테이블에 insert
		boolean isRegistSuccess = dogRegistService.registDog(dog);
		
		if (isRegistSuccess) {//새 개상품 등록(insert)성공
			forward = new ActionForward("dogList.dog", true);//★주의 : 
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패');");
			out.println("history.back();");
			out.println("</script>");
		}
		return forward;
	}

}
