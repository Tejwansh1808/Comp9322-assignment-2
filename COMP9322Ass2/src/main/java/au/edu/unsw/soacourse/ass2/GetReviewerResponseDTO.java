package au.edu.unsw.soacourse.ass2;

import java.util.ArrayList;

public class GetReviewerResponseDTO {

	int status;
	ArrayList<ArrayList<String>> reviewerList=new ArrayList<ArrayList<String>>();
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ArrayList<ArrayList<String>> getReviewerList() {
		return reviewerList;
	}
	public void setReviewerList(ArrayList<ArrayList<String>> reviewerList) {
		this.reviewerList = reviewerList;
	}
	
	
}
