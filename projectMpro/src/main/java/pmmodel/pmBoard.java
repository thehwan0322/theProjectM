package pmmodel;

import java.util.Date;

public class pmBoard {
	private int pmbnum;
	private String pmbwirter;
	private String pmbtitle;
	private String pmbcontent;
	private String pmbfile1;
	private String pmbfile2;
	private String pmbfile3;
	private String pmbfile4;
	private String pmbfile5;
	private int pmbref;

	private Date pmbregdate;
	private int pmbreadcnt;
	private String pmbboardid;

	public int getPmbnum() {
		return pmbnum;
	}

	public void setPmbnum(int pmbnum) {
		this.pmbnum = pmbnum;
	}

	public String getPmbwirter() {
		return pmbwirter;
	}

	public void setPmbwirter(String pmbwirter) {
		this.pmbwirter = pmbwirter;
	}

	public String getPmbtitle() {
		return pmbtitle;
	}

	public void setPmbtitle(String pmbtitle) {
		this.pmbtitle = pmbtitle;
	}

	public String getPmbcontent() {
		return pmbcontent;
	}

	public void setPmbcontent(String pmbcontent) {
		this.pmbcontent = pmbcontent;
	}

	public String getPmbfile1() {
		return pmbfile1;
	}

	public void setPmbfile1(String pmbfile1) {
		this.pmbfile1 = pmbfile1;
	}

	public String getPmbfile2() {
		return pmbfile2;
	}

	public void setPmbfile2(String pmbfile2) {
		this.pmbfile2 = pmbfile2;
	}

	public String getPmbfile3() {
		return pmbfile3;
	}

	public void setPmbfile3(String pmbfile3) {
		this.pmbfile3 = pmbfile3;
	}

	public String getPmbfile4() {
		return pmbfile4;
	}

	public void setPmbfile4(String pmbfile4) {
		this.pmbfile4 = pmbfile4;
	}

	public String getPmbfile5() {
		return pmbfile5;
	}

	public void setPmbfile5(String pmbfile5) {
		this.pmbfile5 = pmbfile5;
	}

	public int getPmbref() {
		return pmbref;
	}

	public void setPmbref(int pmbref) {
		this.pmbref = pmbref;
	}

	public Date getPmbregdate() {
		return pmbregdate;
	}

	public void setPmbregdate(Date pmbregdate) {
		this.pmbregdate = pmbregdate;
	}

	public int getPmbreadcnt() {
		return pmbreadcnt;
	}

	public void setPmbreadcnt(int pmbreadcnt) {
		this.pmbreadcnt = pmbreadcnt;
	}

	public String getPmbboardid() {
		return pmbboardid;
	}

	public void setPmbboardid(String pmbboardid) {
		this.pmbboardid = pmbboardid;
	}

	@Override
	public String toString() {
		return "pmBoard [pmbnum=" + pmbnum + ", pmbwirter=" + pmbwirter + ", pmbtitle=" + pmbtitle + ", pmbcontent="
				+ pmbcontent + ", pmbfile1=" + pmbfile1 + ", pmbfile2=" + pmbfile2 + ", pmbfile3=" + pmbfile3
				+ ", pmbfile4=" + pmbfile4 + ", pmbfile5=" + pmbfile5 + ", pmbref=" + pmbref + ", pmbregdate="
				+ pmbregdate + ", pmbreadcnt=" + pmbreadcnt + ", pmbboardid=" + pmbboardid + "]";
	}

}