package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class StudentCreateExecite extends Action{
	//オーバーライド
	public void execute(HttpServletRequest request,
			HttpServletResponse response)throws Exception{

		HttpSession session =request.getSession(); //セッション開始
		//リクエストパラメーターの取得()
		int ent_year = Integer.parseInt(request.getParameter("ent_year"));//入学年度
		String no = request.getParameter("no");//学生番号
		String name = request.getParameter("name");//氏名
		String class_num = request.getParameter("class_num");//クラス番号

		//入学年度の未入力チェック
		if(ent_year == 0){
			request.setAttribute("no",no);
			request.setAttribute("name", name);
			request.setAttribute("class_num", class_num);

			//jspへフォワード
			request.getRequestDispatcher("StudentCreate.action").forward(request, response);
		}

		//Studentへ登録するデータをセット
		Student stu = new Student();

		stu.setEntYear(ent_year);
		stu.setNo(no);
		stu.setName(name);
		stu.setClassNum(class_num);
		stu.setAttend(true);
		stu.setSchool(((Teacher)session.getAttribute("user")).getSchool());

		StudentDao sDao = new StudentDao();
		boolean flag = sDao.save(stu);

		request.getRequestDispatcher("Student_create_done.jsp").forward(request, response);
	}
}
