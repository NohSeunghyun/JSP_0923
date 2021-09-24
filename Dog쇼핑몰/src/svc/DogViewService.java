//특정 개 상품의 상세 정보보기 요청을 처리하는 비즈니스로직을 구현하는 Service클래스
package svc;

import static db.JdbcUtil.*;

import java.sql.Connection;

import dao.DogDAO;
import vo.Dog;

public class DogViewService {
	//메서드 역할 : 해당 개상품의 조회수 1증가 + id로 조회한 개상품 정보를 Dog객체로 반환 
	public Dog getDogView(int id) {
		//1.커넥션 풀에서 Connection객체를 얻어와
		Connection con = getConnection();
		//2.싱글톤 패턴 : DogDAO객체 생성
		DogDAO dogDAO = DogDAO.getInstance();
		//3.DB작업에 사용될 Connection객체를 DogDAO의 멤버변수로 삽입하여 DB 연결
		dogDAO.setConnection(con);

		/*DogDAO의 해당 메서드를 호출하여 처리*/
		//해당 개 상품의 조회수 1증가
		int updateReadCount = dogDAO.updateReadCount(id);
		
		 /*(update,delete,insert)성공하면 commit 실패하면 rollback commit,rollback은 Service클래스에서 많이 사용(DAO에서 할 수도 있음)
		  * select는 제외*/
		 if (updateReadCount > 0) {
			 commit(con);
		 } else {
			 rollback(con);
		 }
		 
		 /*DogDAO의 해당 메서드를 호출하여 처리*/
		 //id로 조회한 개상품정보를 Dog객체로 반환
		 Dog dog = dogDAO.selectDog(id);
		 
		//4.해제
		close(con);//Connection객체 해제
		
		return dog;
	}
}
