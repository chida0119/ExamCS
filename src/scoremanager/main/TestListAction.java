package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action{

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


		request.getRequestDispatcher("test_list.jsp").forward(request, response);





	}
}