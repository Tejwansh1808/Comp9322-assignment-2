package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class ShortListApplicantsRequestDTO {

	ArrayList<ArrayList<String>> shortListApplicantsList=new ArrayList<ArrayList<String>>();
	

	

	public ArrayList<ArrayList<String>> getShortListApplicantsList() {
		return shortListApplicantsList;
	}

	public void setShortListApplicantsList(
			ArrayList<ArrayList<String>> shortListApplicantsList) {
		this.shortListApplicantsList = shortListApplicantsList;
	}
}
