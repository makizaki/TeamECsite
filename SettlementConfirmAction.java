package com.internousdev.rose.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.rose.dao.DestinationInfoDAO;
import com.internousdev.rose.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SettlementConfirmAction extends ActionSupport implements SessionAware {
	//	userId 受け取ってDestinationInfoDAOに送り、情報あればそのままSUCCESS
	private String userId;
	private List<DestinationInfoDTO> destinationInfolist;
	private Map<String, Object> session;
	String result;

	public String execute() throws SQLException {
		boolean loginFlg = Boolean.valueOf(String.valueOf(session.get("loginFlg")));
		if (loginFlg == false) {
			result = "loginError";
			return result;
		}

		// ログインしている場合は、ユーザーIDを取得する
		userId = session.get("userId").toString();
		DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
		destinationInfolist = destinationInfoDAO.getDestinationInfo(userId);

		return SUCCESS;

	}

	public List<DestinationInfoDTO> getDestinationInfolist() {
		return destinationInfolist;
	}

	public void setDestinationInfolist(List<DestinationInfoDTO> destinationInfolist) {
		this.destinationInfolist = destinationInfolist;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}