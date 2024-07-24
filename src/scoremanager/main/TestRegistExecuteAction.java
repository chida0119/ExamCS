package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action{

	//オーバーライド
	public void execute(HttpServletRequest request,
			HttpServletResponse response)throws Exception{

	//処理内容(シーケンス図から)ログインユーザーの取得
		HttpSession session = request.getSession();//セッション開始
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー
	//ログインユーザの学校を取得
		School school = teacher.getSchool();

		//入力された得点用マップ
		Map<String,String> inputPoints = new HashMap<>();
		//成績リストを初期化
		List<Test>gradeList = new ArrayList<>();

	//リクエストパラメーターの取得
		//入力された科目コード取得
		String subjectCd = request.getParameter("subject_cd");
		//入力されたテスト回数を取得
		int num = Integer.parseInt(request.getParameter("num"));
		//入力された学生番号の一覧を取得
		String[] studentNoSet = request.getParameterValues("student_no_set[]");
		//科目コードから科目インスタンスを取得
		SubjectDao subjectDao = new SubjectDao();//科目Daoを初期化
		Subject subject = subjectDao.get(subjectCd,school);

	//成績データ保存※１人ずつ登録処理を実行
		//学生情報を１人ずつ取得し全件走査
		for(String studentNo:studentNoSet){
			//成績インスタンスを初期化
			Test test = new Test();
			//入力された「point_学生番号」の得点文字列を取得
			String pointStr = request.getParameter("point_"+studentNo);
			//得点用マップ
			inputPoints.put(studentNo,pointStr);

			//pointstrが空になるとtrueを返す

			if(pointStr.isEmpty()){
				continue;
			}

			//得点文字列を整数に変換
			int point = Integer.parseInt(pointStr);
			//成績インスタンスにセット
			StudentDao studentDao = new StudentDao();//学生Daoを初期化
			test.setNo(num);
			test.setPoint(point);
			test.setSchool(school);
			test.setStudent(studentDao.get(studentNo));
			test.setSubject(subject);
			gradeList.add(test);
		}

		//成績Daoを初期化
		TestDao testDao = new TestDao();
		//成績リストから成績を保存
		testDao.save(gradeList);

		//完了ページにフォワード
		request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);

		}
	}
