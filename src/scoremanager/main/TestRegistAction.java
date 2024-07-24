package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action{

	//オーバーライド
	public void execute(HttpServletRequest request,
			HttpServletResponse response)throws Exception{

	//処理内容(シーケンス図から)ログインユーザーの取得
		HttpSession session = request.getSession();//セッション開始
		Teacher teacher = (Teacher)session.getAttribute("user");//ログインユーザー


		// ユーザー情報からクラス番号を取得
		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
		List<String> class_list = cNumDao.filter(teacher.getSchool());

		//科目データの取得
		SubjectDao sDao = new SubjectDao();//
		List<Subject> subjects = sDao.filter(teacher.getSchool());//科目の一覧を取得

		//入学年度のでリストデータ取得
		LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得

		List<Integer> entYearSet = new ArrayList<>();
		//10年前から１年後まで年をリストに追加
		for(int i = year - 10;i < year + 1; i++){
			entYearSet.add(i);
		}

		//全二回ぶんのテスト回数をリストに追加
		List<Integer>numSet = new ArrayList<>();
		for(int i = 1;i<=2;i++){
			numSet.add(i);
		}

		//リクエストにデータをセット
		request.setAttribute("ent_year_set", entYearSet);//入学年度
		request.setAttribute("class_num_set", class_list);//クラス一覧
		request.setAttribute("subjects", subjects);//科目一覧
		request.setAttribute("num_set",numSet);//回数


		//リクエストパラメーターの取得
		String entYearStr = request.getParameter("f1");
		String classNum = request.getParameter("f2");
		String subject =  request.getParameter("f3");
		String test_num = request.getParameter("f4");

		if(entYearStr != null && classNum != null && subject != null && test_num != null){
			//すべてのパラメーターが選択されている場合
			if(!entYearStr.equals("0") && !classNum.equals("0") && !subject.equals("0")  && !test_num.equals("0")){
				System.out.println("全パラメータ取得されています!");

				Subject subject_set = sDao.get(subject,teacher.getSchool());
				int entYear = Integer.parseInt(entYearStr);
				int num = Integer.parseInt(test_num);
				TestDao testDao = new TestDao();

				List<Test> tests = testDao.filter(entYear,classNum,subject_set,num,teacher.getSchool());
				request.setAttribute("num",num);///リクエストに回数をセット
				request.setAttribute("subject", subject_set);//リクエストに科目をセット 7月11日修正
				request.setAttribute("tests", tests);//リクエストに成績をセット
			}
		}else{
			Map<String,String> errors = new HashMap<>();//エラーメッセージ
			//未選択がある場合はエラーメッセージを表示して画面再表示
			errors.put("filter","入学年度とクラスと科目と回数を選択してください");
			request.setAttribute("errors", errors);
		}

		request.getRequestDispatcher("test_regist.jsp").forward(request, response);

	}
}
