package Main;

import GameBeans.GameBean;
import GameDB.CountDao;

public class MainClass {
	public static void main(String[] args) {
		GameBean bean = new GameBean();
		bean.setGameCount(1);
		bean.setGameName("shottgun");
		bean.setGameNumber(1);
		
		CountDao dao = CountDao.getDao();
		
		dao.setGameBean(bean);
		dao.insertDB();
		
		
	}
}
