package com.mjbmall.elasticsearch.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.mjbmall.elasticsearch.core.until.AppConst;

/**
 * 商铺管理Entity
 */
public class Store implements Serializable {
	
	private static final long serialVersionUID = 1040114042085091023L;
	
	/***
	 * 检索分词字段
	 */
	/** 店铺ID*/
	private String id;
	/** 店铺名称*/
	private String storeName;
	/** 机构名称*/
	private String officeName;
	/** 机构ID*/
	private String officeId;
	/** 地区ID*/
	private String areaId;
	/** 地区名称*/
	private String areaName;
	/** 店铺类型ID*/
	private String typeId;
	/** 店铺类型*/
	private String typeName ;
	/** 店铺位置*/
	private String address;
	/** 是否是vip*/
	private String isvip;
	/**店铺关键字*/ 
	private String keywords;
	/**描述*/ 
	private String description;		
	
	private String gradeId;				// 店铺等级
	private String gradeName ;			// 等级名称
	private String accountId;			// 会员ID
	private String phone;				// 电话号码
	private String longitude;			// 店铺精度
	private String latitude;			// 店铺纬度
	private String locationId;			// 店铺所在地
	private String logo;				// 店铺logo
	private String banner;				// 店铺横幅
	private String avatar;				// 店铺头像
	private String credit;				// 店铺信用
	private String descredit;			// 描述相符度
	private String servicecredit;		// 服务态度分数
	private String deliverycredit;		// 发货速度分数
	private int collect;				// 被收藏次数
	private String lsown;				// 是否自营（0：否；1：是）
	private String vrcodePrefix;		// 商家兑换码前缀
	private String showInNav;			// 是否显示在首页的导航条上
	private String license; 			// 营业执照电子版
	private String org;					// 组织机构电子版
	private String taxpayer;			// 一般纳税人电子版
	private int stars ;					// 星级
	private String isElectronic;		// 是否是电商

	
	/**
	 * 店铺附加信息
	 */
	/** 店铺状态（10：新建；20：审核中；30：审核通过；40：审核不通过；50：冻结）[filter]*/ 
	private String status;
	/** 开店时间 [filter]*/ 
	private String startTime;
	/** 店铺有效期（店铺开始时间）[filter]*/ 
	private String availFrom;
	/** 店铺有效期（店铺终止时间）[filter]*/ 
	private String availTo;
	
	/**
	 * 业务基础字段
	 */
	private String remarks;		// 备注
	private String createBy;	// 创建者
	private Date createDate;	// 创建日期
	private String updateBy;	// 更新者
	private Date updateDate;	// 更新日期
	/** 删除标记（0：正常；1：删除；2：审核）  [filter]*/ 
	private String delFlag;
	
	public Store() {
		super();
	}
	
	public String getIsvip() {
		return isvip;
	}

	public void setIsvip(String isvip) {
		this.isvip = isvip;
	}

	public String getIsElectronic() {
		return isElectronic;
	}

	public void setIsElectronic(String isElectronic) {
		this.isElectronic = isElectronic;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	
	public String getLogo() {
		return StringUtils.isNotBlank(this.logo) ? (AppConst.IMG_URL + this.logo) : null;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}
	
	public String getAvatar() {
		return StringUtils.isNotBlank(this.avatar) ? (AppConst.IMG_URL + this.avatar) : null;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}
	
	public String getDescredit() {
		return descredit;
	}

	public void setDescredit(String descredit) {
		this.descredit = descredit;
	}
	
	public String getServicecredit() {
		return servicecredit;
	}

	public void setServicecredit(String servicecredit) {
		this.servicecredit = servicecredit;
	}
	
	public String getDeliverycredit() {
		return deliverycredit;
	}

	public void setDeliverycredit(String deliverycredit) {
		this.deliverycredit = deliverycredit;
	}
	
	public String getLsown() {
		return lsown;
	}

	public void setLsown(String lsown) {
		this.lsown = lsown;
	}
	
	public String getVrcodePrefix() {
		return vrcodePrefix;
	}

	public void setVrcodePrefix(String vrcodePrefix) {
		this.vrcodePrefix = vrcodePrefix;
	}
	
	public String getShowInNav() {
		return showInNav;
	}

	public void setShowInNav(String showInNav) {
		this.showInNav = showInNav;
	}
	
	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLicense() {
		return StringUtils.isNotBlank(this.license) ? (AppConst.IMG_URL + this.license) : null;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getOrg() {
		return StringUtils.isNotBlank(this.org) ? (AppConst.IMG_URL + this.org) : null;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getTaxpayer() {
		return StringUtils.isNotBlank(this.taxpayer) ? (AppConst.IMG_URL + this.taxpayer) : null;
	}

	public void setTaxpayer(String taxpayer) {
		this.taxpayer = taxpayer;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getStatus() {
		if(StringUtils.isNotBlank(status) && "30".equals(status)) {
			status = "70" ;
		}
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getAvailFrom() {
		return availFrom;
	}

	public void setAvailFrom(String availFrom) {
		this.availFrom = availFrom;
	}

	public String getAvailTo() {
		return availTo;
	}

	public void setAvailTo(String availTo) {
		this.availTo = availTo;
	}

	public String getOfficeName() {
		return officeName;
	}

	public void setOfficeName(String officeName) {
		this.officeName = officeName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getCollect() {
		return collect;
	}

	public void setCollect(int collect) {
		this.collect = collect;
	}

	public int getStars() {
		return stars;
	}

	public void setStar(int stars) {
		this.stars = stars;
	}
}