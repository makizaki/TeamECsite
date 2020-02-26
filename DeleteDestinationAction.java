package com.internousdev.rose.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.rose.dao.DestinationInfoDAO;
import com.internousdev.rose.dto.DestinationInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteDestinationAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private List<DestinationInfoDTO> destinationInfolist = new ArrayList<DestinationInfoDTO>();
	private DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
	private String id;

	public String execute() throws SQLException  {
		String result;
		boolean loginFlg = Boolean.valueOf(String.valueOf(session.get("loginFlg")));
		if (loginFlg == false) {
			result = "loginError";
		}

		int res = 0;

		result = ERROR;
		DestinationInfoDAO destinationInfoDAO = new DestinationInfoDAO();
		res = destinationInfoDAO.deleteDestination(id);
		if (res > 0) {
			destinationInfolist = destinationInfoDAO.getDestinationInfo(session.get("userId").toString());
			result = SUCCESS;
		}
		return result;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public DestinationInfoDAO getDestinationInfoDAO() {
		return destinationInfoDAO;
	}

	public void setDestinationInfoDAO(DestinationInfoDAO destinationInfoDAO) {
		this.destinationInfoDAO = destinationInfoDAO;
	}

}