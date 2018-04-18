package ec;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.BuyDataBeans;
import beans.ItemDataBeans;
import dao.UserDAO;
import dao.UserDataDao;

/**
 * 購入履歴画面
 * @author d-yamaguchi
 *
 */
@WebServlet("/UserBuyHistoryDetail")
public class UserBuyHistoryDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			int userId = (int) session.getAttribute("userId");

			String id = request.getParameter("id");
			System.out.println(id);




			ArrayList<BuyDataBeans> bdbList = (ArrayList<BuyDataBeans>) UserDAO.getBuyData(userId);
			ArrayList<ItemDataBeans> idbList = (ArrayList<ItemDataBeans>) UserDataDao.getBuyItemData(userId);
			BuyDataBeans bdb = UserDataDao.getBuyData(userId);
			BuyDataBeans bdb2 = UserDataDao.getBuyData2(userId, id);

			ArrayList<ItemDataBeans> idbList2 = (ArrayList<ItemDataBeans>) UserDataDao.getBuyData(userId,id);






			request.setAttribute("bdbList", bdbList);
			request.setAttribute("idbList", idbList);
			request.setAttribute("bdb", bdb);
			request.setAttribute("bdb2", bdb2);
			request.setAttribute("idbList2", idbList2);





			request.getRequestDispatcher(EcHelper.USER_BUY_HISTORY_DETAIL_PAGE).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			session.setAttribute("errorMessage", e.toString());
			response.sendRedirect("Error");
		}
	}

}
