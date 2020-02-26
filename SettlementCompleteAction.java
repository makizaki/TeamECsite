package com.internousdev.rose.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.rose.dao.CartInfoDAO;
import com.internousdev.rose.dao.PurchaseHistoryInfoDAO;
import com.internousdev.rose.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementCompleteAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private CartInfoDAO cartInfoDAO = new CartInfoDAO();
	private List<CartInfoDTO> cartInfoDTOList = new ArrayList<>();
	private int id;
	String result;
	String loginError;
	private PurchaseHistoryInfoDAO purchaseHistoryInfoDAO = new PurchaseHistoryInfoDAO();
	private String userId;

	public String execute() throws SQLException {
		String result;
		boolean loginFlg = Boolean.valueOf(String.valueOf(session.get("loginFlg")));
		if (loginFlg == false) {
			result = "loginError";
			return result;
		}

		int count = 0;
		result = ERROR;

		//		cartInfoDAOにuserId","宛先ID"を渡して処理後、必要な情報を取得してPurchaseHistoryInfoDAOに結果を渡す。
		cartInfoDTOList = cartInfoDAO.getCartInfo(session.get("userId").toString());
		for (CartInfoDTO cartInfoDTO : cartInfoDTOList) {
			count = purchaseHistoryInfoDAO.regist(
					session.get("userId").toString(),
					cartInfoDTO.getProductId(),
					cartInfoDTO.getProductCount(),
					cartInfoDTO.getPrice(),
					id);
			if (count > 0) {
				count = cartInfoDAO.deleteCart(String.valueOf(session.get("userId")));
				result = SUCCESS;

			}

		}
		return result;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}