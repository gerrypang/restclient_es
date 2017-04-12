package com.mjbmall.elasticsearch.domain;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

/**
 * 商品管理Entity
 */
public class MjbProduct implements Serializable{
	
	private static final long serialVersionUID = 48557539142255149L;

	private String id;
    
    /** 商铺编号*/
    //@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String mjbStoreId;  
    
	/** 商铺名称*/
    private String mjbStoreName; 

    /** 商铺类别编号*/ 
    //@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
    private String mjbCatalogId; 
    
    /**商铺类别名  */ 
	private String mjbCatalogName;
    
    /** 商品名称*/ 
	private String mjbProductName; 
    
    /** 品牌ID*/ 
	//@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String mjbBrandId;
    
    /** 品牌名称  */ 
	private String mjbBrandName; 
    
    /** 商品简介 */ 
	private String introduce; 
    
    /** 搜索关键词 */ 
	private String searchKey;   
    
    /** 页面标题 */ 
	private String title;
    
    /** 页面关键词 */ 
	private String keywords;
    
    /** 删除标识 */ 
	private String delFlag;
    
	private String price; // 定价
	private String newPrice; // 现价
	private String picture; // 小图片地址
	private String score; // 赠送积分
	private String maxPicture; // 大图片地址
	private String isNew; // 是否新品
	private String sale; // 是否特价
	private String activityId; // 绑定的活动ID
	private String giftId; // 绑定的礼品ID
	private String hit; // 浏览次数
	private String unit; // 商品单位
	private String isTimePromotion; // 限时促销
	private String status; // 商品状态
	private String productHtml; // 商品介绍               
	private String images; // 商品图片集
	private String sellCount; // 销售数量
	private String stock; // 剩余库存量
	private String description; // 页面描述
	private String isRecommend; // 推荐
	private String order; // 排列顺序
	private int favorite; // 被收藏量
	private String imgList; // 轮播图片集
	private double longitude; // 纬度信息
	private double latitude; // 经度信息
	private String star; // 星级
	private String collect; // 被收藏次数
	private String userId; // 当前用户编号
	private String favoriteUserId; // 收藏用户编号
	private String isFavorite; // 是否收藏
	private int quantity; // 购买数量
	private String debateCash; // 商品可抵扣额度
	private String debate; // 商品可抵扣折扣
	private String storelogo;// 店铺logo
	private String flag;
	private String postfee; // 邮费
	private String style;// 款式
	private String standard;// 规格
	private String shareMoney; // 分享佣金

	public MjbProduct(){
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

	public String getStorelogo() {
		return storelogo;
	}

	public void setStorelogo(String storelogo) {
		this.storelogo = storelogo;
	}

	public String getImgList() {
		return imgList;
	}

	public void setImgList(String imgList) {
		this.imgList = imgList;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDebateCash() {
		return debateCash;
	}

	public void setDebateCash(String debateCash) {
		this.debateCash = debateCash;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	public String getMjbBrandId() {
		return mjbBrandId;
	}

	public void setMjbBrandId(String mjbBrandId) {
		this.mjbBrandId = mjbBrandId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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
		return picture;
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
		return maxPicture;
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

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
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

	public String getFavoriteUserId() {
		return favoriteUserId;
	}

	public void setFavoriteUserId(String favoriteUserId) {
		this.favoriteUserId = favoriteUserId;
	}

	public String getIsFavorite() {
		return StringUtils.isNotBlank(isFavorite) ? "1" : "0";
	}

	public void setIsFavorite(String isFavorite) {
		this.isFavorite = isFavorite;
	}

	public String getStar() {
		return StringUtils.isNotBlank(star) ? star : "5";
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getCollect() {
		return StringUtils.isNotBlank(collect) ? collect : "0";
	}

	public void setCollect(String collect) {
		this.collect = collect;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMjbCatalogName() {
		return mjbCatalogName;
	}

	public void setMjbCatalogName(String mjbCatalogName) {
		this.mjbCatalogName = mjbCatalogName;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
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

	public String getMjbBrandName() {
		return mjbBrandName;
	}

	public void setMjbBrandName(String mjbBrandName) {
		this.mjbBrandName = mjbBrandName;
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
}