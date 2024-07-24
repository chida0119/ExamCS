package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action{

	//オーバーライド
	public void execute(HttpServletRequest request,
			HttpServletResponse response)throws Exception{

	//処理内容(シーケンス図から)
		HttpSession session = request.getSession();//セッション開始
		Teacher teacher = (Teacher)session.getAttribute("user");

		// ユーザー情報からクラス番号を取得
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
		List<String> class_list = cNumDao.filter(teacher.getSchool());

		//科目データの取得
		SubjectDao sDao = new SubjectDao();//
		List<Subject> subject = sDao.filter(teacher.getSchool());//科目の一覧を取得

		//入学年度のでリストデータ取得
		LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得

		List<Integer> entYearSet = new ArrayList<>();
		//10年前から１年後まで年をリストに追加
		for(int i = year - 10;i < year + 1; i++){
			entYearSet.add(i);
		}


		//リクエストにデータをセット
		request.setAttribute("f1", entYearSet);//入学年度
		request.setAttribute("f2", class_list);//クラス一覧
		request.setAttribute("f3", subject);//科目一覧


		//学生成績の取得処理
		String entYearStr = request.getParameter("f1");//入学年度
		int entYear = Integer.parseInt(entYearStr); // 入学年度を整数に変換
		String classcode = request.getParameter("f2");//クラスコード
		String subjectcode = request.getParameter("f3");//科目取得
		SubjectDao  subDao = new SubjectDao();
		Subject sub = subDao.get(subjectcode,teacher.getSchool());

		//科目成績の取得処理
		TestListSubjectDao TLSbDao = new TestListSubjectDao();
		List<TestListSubject>tests = TLSbDao.filter(entYear, classcode, sub, teacher.getSchool());

		request.setAttribute("tests",tests);
		request.setAttribute("sub", sub);

		request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);





	}
}