package kr.ac.koreatech.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.ac.koreatech.book.dto.BookDTO;

/**
 * Servlet implementation class BookListServlet
 */
@WebServlet("/list")
public class BookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 입력
		String keyword = request.getParameter("keyword");
		String tmp = request.getParameter("callback");
		
		// 2. 로직 처리(DB 처리를 포함)
		// Tomcat Webserver가 제공하는 DBCP방법으로 데이터베이스를 이용
		// 2-1. Driver Loading 
		// 2-2. 접속
		Connection con = common.DBTemplate.getConnection();
		
		// 2-3. PreparedStatement
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<BookDTO> list = new ArrayList<BookDTO>();
		
		//삭제할때를 위해 책번호도 가져온다.
		String sql = "select bimgurl, btitle, bauthor, bprice, bisbn from book where btitle like ?";
	
		try {
			
			pstmt = con.prepareStatement(sql);	
			pstmt.setString(1, "%" + keyword + "%");
		
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				//자바의 ArrayList 배열을 사용하고 그안에 책 1권에 대한 정보가 들어가있는 객체를 여러개 집어넣음
				BookDTO entity = new BookDTO();//VO, DO, DTO, Entity
				entity.setBisbn(rs.getString("bisbn"));
				entity.setBtitle(rs.getString("btitle"));
				entity.setBimgurl(rs.getString("bimgurl"));
				entity.setBprice(rs.getString("bprice"));
				entity.setBauthor(rs.getString("bauthor"));
				
				list.add(entity);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//닫아주기
		common.DBTemplate.close(rs);
		common.DBTemplate.close(pstmt);
		common.DBTemplate.close(con);
		
		
		//3. 출력하자
		
		response.setContentType("text/plain; charset=utf8");
		//3-2 출력하기위한 데이터 연결통호인 stream을 만들어야함!
		PrintWriter out = response.getWriter();
	
		//3-4
		//유식한 방법(라이브러리 이용 - JACKSON)
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
		
		System.out.println("ok");
		
		out.println(tmp + "(" + json + ")");
		//3-4 데이터 출력을 정확히 처리하기 위해 flush
		out.flush();
		//3-5 사용된 리소스 해제
		out.close();
				
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
