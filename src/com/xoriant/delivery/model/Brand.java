package com.xoriant.delivery.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Brand")
public class Brand {

	private int brandId;

	private String brandName;

	private Category category;

	public Brand() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Brand(int brandId, String brandName, Category category) {
		super();
		this.brandId = brandId;
		this.brandName = brandName;
		this.category = category;
	}

	@XmlElement
	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	@XmlElement
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@XmlElement
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Brand [brandId=" + brandId + ", brandName=" + brandName + ", category=" + category + "]";
	}

}
