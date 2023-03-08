package com.user.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.user.db.UserDAO;
import com.user.db.UserDTO;


public class UserInfoDeletePro implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_id = request.getParameter("user_id");
		String user_pass = request.getParameter("user_pass");
		
		UserDAO dao = new UserDAO();
		
		UserDTO dto = dao.UserCheck(user_id, user_pass);
		
		if(dto != null) {
			
			dao.deleteUserinfo(dto);
			
			HttpSession session =request.getSession();
			session.invalidate();
			
			ActionForward forward = new ActionForward();
			forward.setPath("./UserLoginForm.us");
			forward.setRedirect(true);
			return forward;
			
			}else{
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out=response.getWriter();
				out.print("<script>");
				out.print("alert('�삤?��?��?���땲�뜑');");
				out.print("history.back();");
				out.print("</script>");
				out.close();
				return null;
			}	
			
		}//
	}//