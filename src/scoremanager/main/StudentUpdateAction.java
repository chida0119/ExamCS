package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {
	//オーバーライド
	public void execute(HttpServletRequest request,
			HttpServletResponse response)throws Exception{

	//処理内容(シーケンス図から)
		String no = request.getParameter("no");//変更対象の学生番号を取得

		StudentDao sDao = new StudentDao();//学生番号の取得
		Student stu = sDao.get(no);//変更対象の学生詳細データを取得

		HttpSession session = request.getSession();
		Teacher teacher  = (Teacher)session.getAttribute("user");
		School school = teacher.getSchool();
		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daを追加
		List<String> class_list = cNumDao.filter(school);//クラスの一覧を取得

		request.setAttribute("stu_date", stu);
		request.setAttribute("class_list", class_list);

		request.getRequestDispatcher("student_update.jsp").forward(request, response);
	}
}
