package com.xoriant.delivery.dbconfig;

public class DBquries {

	public static final String ADD_NEW_CATEGORY = "insert into categories values(?,?)";

	public static final String UPDATE_CATEGORY = "update categories set category_name=? where category_id=?";

	public static final String FETCH_ALL_CATEGORY = "select * from categories";

	public static final String FETCH_CATEGORY_BY_ID = "select * from categories where category_id=?";

	public static final String DELETE_CATEGORY = "delete from categories where category_id=?";

	public static final String ADD_NEW_BRAND = "insert into brands values(?,?,?)";

	public static final String UPDATE_BRAND = "update brands set brand_name=?,category_id=? where brand_id=?";

	public static final String FETCH_ALL_BRANDS = "select b.brand_id,b.brand_name,c.category_id,c.category_name from brands b inner join categories c on b.category_id=c.category_id";

	public static final String FETCH_BRAND_BRAND_ID = "select b.brand_id,b.brand_name,c.category_id,c.category_name from brands b inner join categories c on b.category_id=c.category_id where b.brand_id=?";

	public static final String DELETE_BRAND = "delete from brands where brand_id=?";

	public static final String ADD_NEW_PRODUCT = "insert into products values(?,?,?,?,?,?)";

	public static final String UPDATE_PRODUCT = "update products set product_name=?,price=?,description=?,brand_id=?,category_id=? where product_id=?";

	public static final String FETCH_ALL_PRODUCT = "select product_id,product_name,price,description,b.brand_id,b.brand_name,c.category_id,c.category_name from products p inner join brands b on p.brand_id=b.brand_id inner join categories c on p.category_id=c.category_id";

	public static final String FETCH_PRODUCT_BY_ID = "select product_id,product_name,price,description,b.brand_id,b.brand_name,c.category_id,c.category_name from products p inner join brands b on p.brand_id=b.brand_id inner join categories c on p.category_id=c.category_id where p.product_id=?";

	public static final String FETCH_PRODUCT_BY_NAME = "select product_id,product_name,price,description,b.brand_id,b.brand_name,c.category_id,c.category_name from products p inner join brands b on p.brand_id=b.brand_id inner join categories c on p.category_id=c.category_id where p.product_name=?";

	public static final String FETCH_PRODUCT_BY_BRAND_NAME = "select product_id,product_name,price,description,b.brand_id,b.brand_name,c.category_id,c.category_name from products p inner join brands b on p.brand_id=b.brand_id inner join categories c on p.category_id=c.category_id where b.brand_name=?";

	public static final String FETCH_PRODUCT_BY_CATEGORY_NAME = "select product_id,product_name,price,description,b.brand_id,b.brand_name,c.category_id,c.category_name from products p inner join brands b on p.brand_id=b.brand_id inner join categories c on p.category_id=c.category_id where c.category_name=?";

	public static final String FETCH_PRODUCT_IN_BETWEEN_PRICE_RANGE = "select product_id,product_name,price,description,b.brand_id,b.brand_name,c.category_id,c.category_name from products p inner join brands b on p.brand_id=b.brand_id inner join categories c on p.category_id=c.category_id where p.price>=? and p.price<=?";

	public static final String FETCH_PRODUCT_BELOW_THE_PRICE_RANGE = "select product_id,product_name,price,description,b.brand_id,b.brand_name,c.category_id,c.category_name from products p inner join brands b on p.brand_id=b.brand_id inner join categories c on p.category_id=c.category_id where p.price<=?";

	public static final String FETCH_PRODUCT_ABOVE_THE_PRICE_RANGE = "select product_id,product_name,price,description,b.brand_id,b.brand_name,c.category_id,c.category_name from products p inner join brands b on p.brand_id=b.brand_id inner join categories c on p.category_id=c.category_id where p.price>=?";
}
