package model;

import java.time.LocalDate;

public class Issue {

	private int issue_id;
	private String issue_summary;
	private String ISSUE_DESCRIPTION;
	private int identified_by_cid;
	private int related_cd_id;
	private String status;
	private String priority;
	private String progress;
	private String resolution_summary;
	private String postedOn;
	private LocalDate resolvedOn;
	
	public Issue() {
		// TODO Auto-generated constructor stub
	}

	public Issue(int issue_id, String issue_summary, String iSSUE_DESCRIPTION, int identified_by_cid, int related_cd_id,
			String status, String priority, String progress, String resolution_summary, String postedOn,
			LocalDate resolvedOn) {
		super();
		this.issue_id = issue_id;
		this.issue_summary = issue_summary;
		ISSUE_DESCRIPTION = iSSUE_DESCRIPTION;
		this.identified_by_cid = identified_by_cid;
		this.related_cd_id = related_cd_id;
		this.status = status;
		this.priority = priority;
		this.progress = progress;
		this.resolution_summary = resolution_summary;
		this.postedOn = postedOn;
		this.resolvedOn = resolvedOn;
	}
	

	public int getIssue_id() {
		return issue_id;
	}

	public void setIssue_id(int issue_id) {
		this.issue_id = issue_id;
	}

	public String getIssue_summary() {
		return issue_summary;
	}

	public void setIssue_summary(String issue_summary) {
		this.issue_summary = issue_summary;
	}

	public int getIdentified_by_cid() {
		return identified_by_cid;
	}

	public void setIdentified_by_cid(int identified_by_cid) {
		this.identified_by_cid = identified_by_cid;
	}

	public int getRelated_cd_id() {
		return related_cd_id;
	}

	public void setRelated_cd_id(int related_cd_id) {
		this.related_cd_id = related_cd_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public String getResolution_summary() {
		return resolution_summary;
	}

	public void setResolution_summary(String resolution_summary) {
		this.resolution_summary = resolution_summary;
	}

	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String idate) {
		this.postedOn = idate;
	}

	public LocalDate getResolvedOn() {
		return resolvedOn;
	}

	public void setResolvedOn(LocalDate resolvedOn) {
		this.resolvedOn = resolvedOn;
	}
	
	public String getISSUE_DESCRIPTION() {
		return ISSUE_DESCRIPTION;
	}

	public void setISSUE_DESCRIPTION(String iSSUE_DESCRIPTION) {
		ISSUE_DESCRIPTION = iSSUE_DESCRIPTION;
	}

	
	

}
