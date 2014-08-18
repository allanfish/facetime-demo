package com.ams.test;

/**
 * 深圳老板通BEAN
 * 
 * @author yufei
 * 
 */
public class BossBean {

	private String id;
	private String pan;
	/** 代理行号 */
	private String ABId;
	/** 终端编号 */
	private String termId;
	private String srvStan;
	private String tranType;
	private String tranAmt;
	private String tranDate;
	private String tranFee;
	/** 消费金额, 等于交易金额+手续费 */
	private String consumeAmt;
	/** 传账类型 */
	private String vouncherType;
	/**
	 * 商户名称
	 */
	private String shopName;
	/**
	 * 商户编号
	 */
	private String shopNo;

	public BossBean() {
		super();
	}

	public BossBean(String id, String pan, String aBId, String termId,
			String srvStan, String tranType, String tranAmt, String tranDate,
			String tranFee, String consumeAmt, String vouncherType) {
		super();
		this.id = id;
		this.pan = pan;
		ABId = aBId;
		this.termId = termId;
		this.srvStan = srvStan;
		this.tranType = tranType;
		this.tranAmt = tranAmt;
		this.tranDate = tranDate;
		this.tranFee = tranFee;
		this.consumeAmt = consumeAmt;
		this.vouncherType = vouncherType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getABId() {
		return ABId;
	}

	public void setABId(String aBId) {
		ABId = aBId;
	}

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getSrvStan() {
		return srvStan;
	}

	public void setSrvStan(String srvStan) {
		this.srvStan = srvStan;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(String tranAmt) {
		this.tranAmt = tranAmt;
	}

	public String getTranDate() {
		return tranDate;
	}

	public void setTranDate(String tranDate) {
		this.tranDate = tranDate;
	}

	public String getTranFee() {
		return tranFee;
	}

	public void setTranFee(String tranFee) {
		this.tranFee = tranFee;
	}

	public String getConsumeAmt() {
		return consumeAmt;
	}

	public void setConsumeAmt(String consumeAmt) {
		this.consumeAmt = consumeAmt;
	}

	public String getVouncherType() {
		return vouncherType;
	}

	public void setVouncherType(String vouncherType) {
		this.vouncherType = vouncherType;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	@Override
	public String toString() {
		return "BossBean [id=" + id + ", pan=" + pan + ", ABId=" + ABId
				+ ", termId=" + termId + ", srvStan=" + srvStan + ", tranType="
				+ tranType + ", tranAmt=" + tranAmt + ", tranDate=" + tranDate
				+ ", tranFee=" + tranFee + ", consumeAmt=" + consumeAmt
				+ ", vouncherType=" + vouncherType + ", shopName=" + shopName
				+ ", shopNo=" + shopNo + "]";
	}

}
