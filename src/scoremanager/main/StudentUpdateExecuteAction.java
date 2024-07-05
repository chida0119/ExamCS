package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {
	//オーバーライド
	public void execute(HttpServletRequest request,
			HttpServletResponse response)throws Exception{

	//処理内容(シーケンス図から)

		int ent_year = Integer.parseInt(request.getParameter("ent_year"));
		String no = request.getParameter("no");
		String name = request.getParameter("name");
		String class_num = request.getParameter("class_num");
		String si_attend = request.getParameter("si_attend");
		boolean attend = false;

		//在学フラグON
		if(si_attend=="on"){
			attend = true;
		}

		//保存するデータをStudentにセット
		Student stu = new Student();
		stu.setEntYear(ent_year);
		stu.setNo(no);
		stu.setName(name);
		stu.setClassNum(class_num);
		stu.setAttend(attend);

		//変更内容の保存
		StudentDao sDao = new StudentDao();
		sDao.save(stu);

		//JSPへフォワード
		request.getRequestDispatcher("student_update_done.jsp").forward(request, response);

	}
}
