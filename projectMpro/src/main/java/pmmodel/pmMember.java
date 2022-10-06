package pmmodel;

import java.util.Date;

public class pmMember {
	private String pmid;
	private String pmpass;
	private String pmname;
	private Date pmbithday;
	private String pmnickname;
	private String pmphone;
	private String pmemail;
	private int pmarea;
	private String pmintroduce;
	private String pmprofilepicture;

	public String getPmid() {
		return pmid;
	}

	public void setPmid(String pmid) {
		this.pmid = pmid;
	}

	public String getPmpass() {
		return pmpass;
	}

	public void setPmpass(String pmpass) {
		this.pmpass = pmpass;
	}

	public String getPmname() {
		return pmname;
	}

	public void setPmname(String pmname) {
		this.pmname = pmname;
	}

	public Date getPmbithday() {
		return pmbithday;
	}

	public void setPmbithday(Date pmbithday) {
		this.pmbithday = pmbithday;
	}

	public String getPmnickname() {
		return pmnickname;
	}

	public void setPmnickname(String pmnickname) {
		this.pmnickname = pmnickname;
	}

	public String getPmphone() {
		return pmphone;
	}

	public void setPmphone(String pmphone) {
		this.pmphone = pmphone;
	}

	public String getPmemail() {
		return pmemail;
	}

	public void setPmemail(String pmemail) {
		this.pmemail = pmemail;
	}

	public int getPmarea() {
		return pmarea;
	}

	public void setPmarea(int pmarea) {
		this.pmarea = pmarea;
	}

	public String getPmintroduce() {
		return pmintroduce;
	}

	public void setPmintroduce(String pmintroduce) {
		this.pmintroduce = pmintroduce;
	}

	public String getPmprofilepicture() {
		return pmprofilepicture;
	}

	public void setPmprofilepicture(String pmprofilepicture) {
		this.pmprofilepicture = pmprofilepicture;
	}

	@Override
	public String toString() {
		return "pmMember [pmid=" + pmid + ", pmpass=" + pmpass + ", pmname=" + pmname + ", pmbithday=" + pmbithday
				+ ", pmnickname=" + pmnickname + ", pmphone=" + pmphone + ", pmemail=" + pmemail + ", pmarea=" + pmarea
				+ ", pmintroduce=" + pmintroduce + ", pmprofilepicture=" + pmprofilepicture + "]";
	}

}
