package com.mjbmall.elasticsearch.domain;

import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import com.mjbmall.elasticsearch.core.until.AppConst;

/**
 * 商品管理Entity
 */
public class Product implements Serializable{
	
	private static final long serialVersionUID = 48557539142255149L;

	/***
	 * 检索分词字段
	 */
	/** 商品名称*/ 
	private String id;
	/** 商品名称*/ 
	private String mjbProductName; 
	/** 商铺编号*/
	private String mjbStoreId;  
	/** 商铺名称*/
    private String mjbStoreName; 
    /** 商铺类别编号*/ 
    private String mjbCatalogId; 
    /**商铺类别名  */ 
	private String mjbCatalogName;
    /** 品牌ID*/ 
	private String brandId;
    /** 品牌名称  */ 
	private String brandName; 
    /** 商品简介 */ 
	private String introduce; 
    /** 搜索关键词 */ 
	private String searchKey;   
    /** 页面标题 */ 
	private String title;
	/** 页面描述 */ 
	private String description;
    /** 页面关键词 */ 
	private String keywords;
	/** 商品状态   [filter]*/ 
	private String status;
	
	private String price; 			// 定价
	private String newPrice; 		// 现价
	private String debateCash; 		// 商品可抵扣额度
	private String debate; 			// 商品可抵扣折扣
	private String picture;			// 小图片地址
	private int favorite; 			// 被收藏量
	private String score; 			// 赠送积分
	private String maxPicture; 		// 大图片地址
	private String isNew; 			// 是否新品
	private String sale; 			// 是否特价
	private String activityId; 		// 绑定的活动ID
	private String giftId; 			// 绑定的礼品ID
	private int hit; 				// 浏览次数
	private String unit; 			// 商品单位
	private String isTimePromotion; // 限时促销
	private String productHtml; 	// 商品介绍               
	private String images; 			// 商品图片集
	private String imglist;
	private String sellCount; 		// 销售数量
	private String stock; 			// 剩余库存量
	private String version; 		// 版本
	private String isRecommend;		// 推荐
	private int collect; 			// 被收藏次数
	private int stars; 				// 星级
	private String img1; 			// 轮播图片1
	private String img2; 			// 轮播图片2
	private String img3; 			// 轮播图片3
	private String img4; 			// 轮播图片4
	private String img5; 			// 轮播图片5
	private String shareMoney; 		// 分享佣金
	private String style;			// 款式
	private String standard;		// 规格
	private String postfee; 		// 邮费
	
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
	
	public Product(){
		super();
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getPostfee() {
		return postfee;
	}

	public void setPostfee(String postfee) {
		this.postfee = postfee;
	}

	public String getShareMoney() {
		return shareMoney;
	}

	public void setShareMoney(String shareMoney) {
		this.shareMoney = shareMoney;
	}

	public String getDebate() {
		return debate;
	}

	public void setDebate(String debate) {
		this.debate = debate;
	}

	public int getFavorite() {
		return favorite;
	}

	public void setFavorite(int favorite) {
		this.favorite = favorite;
	}

	public String getDebateCash() {
		return debateCash;
	}

	public void setDebateCash(String debateCash) {
		this.debateCash = debateCash;
	}

	public String getMjbStoreId() {
		return mjbStoreId;
	}

	public void setMjbStoreId(String mjbStoreId) {
		this.mjbStoreId = mjbStoreId;
	}

	public String getMjbCatalogId() {
		return mjbCatalogId;
	}

	public void setMjbCatalogId(String mjbCatalogId) {
		this.mjbCatalogId = mjbCatalogId;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(String newPrice) {
		this.newPrice = newPrice;
	}

	public String getPicture() {
		return StringUtils.isNotBlank(this.picture) ? (AppConst.IMG_URL + this.picture) : null;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getMaxPicture() {
		return StringUtils.isNotBlank(this.maxPicture) ? (AppConst.IMG_URL + this.maxPicture) : null;
	}

	public void setMaxPicture(String maxPicture) {
		this.maxPicture = maxPicture;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getGiftId() {
		return giftId;
	}

	public void setGiftId(String giftId) {
		this.giftId = giftId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getIsTimePromotion() {
		return isTimePromotion;
	}

	public void setIsTimePromotion(String isTimePromotion) {
		this.isTimePromotion = isTimePromotion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductHtml() {

		return productHtml;
	}

	public void setProductHtml(String productHtml) {
		this.productHtml = productHtml;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getSellCount() {
		return sellCount;
	}

	public void setSellCount(String sellCount) {
		this.sellCount = sellCount;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getMjbCatalogName() {
		return mjbCatalogName;
	}

	public void setMjbCatalogName(String mjbCatalogName) {
		this.mjbCatalogName = mjbCatalogName;
	}

	public String getMjbStoreName() {
		return mjbStoreName;
	}

	public void setMjbStoreName(String mjbStoreName) {
		this.mjbStoreName = mjbStoreName;
	}

	public String getMjbProductName() {
		return mjbProductName;
	}

	public void setMjbProductName(String mjbProductName) {
		this.mjbProductName = mjbProductName;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public String getImg1() {
		return StringUtils.isNotBlank(this.img1) ? (AppConst.IMG_URL + this.img1) : null;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return StringUtils.isNotBlank(this.img2) ? (AppConst.IMG_URL + this.img2) : null;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return StringUtils.isNotBlank(this.img3) ? (AppConst.IMG_URL + this.img3) : null;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public String getImg4() {
		return StringUtils.isNotBlank(this.img4) ? (AppConst.IMG_URL + this.img4) : null;
	}

	public void setImg4(String img4) {
		this.img4 = img4;
	}

	public String getImg5() {
		return StringUtils.isNotBlank(this.img5) ? (AppConst.IMG_URL + this.img5) : null;
	}

	public void setImg5(String img5) {
		this.img5 = img5;
	}

	public int getCollect() {
		return collect;
	}

	public void setCollect(int collect) {
		this.collect = collect;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getImglist() {
		return imglist;
	}

	public void setImglist(String imglist) {
		this.imglist = imglist;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}
}