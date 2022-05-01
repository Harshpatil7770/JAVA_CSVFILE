package com.xoriant.delivery.utility;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;

import com.xoriant.delivery.dao.EcartDaoImpl;
import com.xoriant.delivery.model.Brand;
import com.xoriant.delivery.model.Category;
import com.xoriant.delivery.model.Product;
import com.xoriant.delivery.validation.InputValidation;

public class EcartUtility {

	private static Scanner sc = new Scanner(System.in);

	public void addNewCategory() {

		System.out.println("Enter Category Name");
		String categoryName = InputValidation.inputStringValidation();

		Category category = new Category();
		category.setCategoryName(categoryName);

		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.addNewCategory(category);
	}

	public void updateCategory() {

		System.out.println("Enter category number ");
		int categoryId = InputValidation.inputIntegerValidation();

		System.out.println("Enter Category Name");
		String categoryName = InputValidation.inputStringValidation();

		Category category = new Category();
		category.setCategoryId(categoryId);
		category.setCategoryName(categoryName);

		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.updateCategory(category);
	}

	public void fetchCategoryById() throws FileNotFoundException {
		System.out.println("Enter category Id");
		int categoryId = InputValidation.inputIntegerValidation();

		Category category = new Category();
		category.setCategoryId(categoryId);
		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.fetchCategoryByCategoryID(categoryId);
	}

	public void deleteCategory() {
		System.out.println("Enter category Id");
		int categoryId = InputValidation.inputIntegerValidation();

		Category category = new Category();
		category.setCategoryId(categoryId);
		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.deleteCategory(categoryId);
	}

	public void addNewBrand() {
		Brand brand = new Brand();
		Category category = new Category();

		System.out.println("Enter brand name");
		String brandName = InputValidation.inputStringValidation();
		brand.setBrandName(brandName);

		System.out.println("Enter category Id");
		int categoryId = InputValidation.inputIntegerValidation();
		category.setCategoryId(categoryId);
		brand.setCategory(category);

		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.addNewBrand(brand);
	}

	public void updateBrand() throws SQLException {
		Brand brand = new Brand();
		Category category = new Category();

		System.out.println("Enter Brand Id");
		int brandId = InputValidation.inputIntegerValidation();
		brand.setBrandId(brandId);

		System.out.println("Enter brand name");
		String brandName = InputValidation.inputStringValidation();
		brand.setBrandName(brandName);

		System.out.println("Enter category Id");
		int categoryId = InputValidation.inputIntegerValidation();
		category.setCategoryId(categoryId);
		brand.setCategory(category);

		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.updateBrand(brand);
	}

	public void fetchBrandById() throws FileNotFoundException {
		System.out.println("Enter Brand Id");
		int brandId = InputValidation.inputIntegerValidation();

		Brand brand = new Brand();
		brand.setBrandId(brandId);
		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.fetchBrandByBrandID(brandId);
	}

	public void deleteBrand() {
		System.out.println("Enter brand Id");
		int brandId = InputValidation.inputIntegerValidation();

		Brand brand = new Brand();
		brand.setBrandId(brandId);
		;
		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.deleteBrand(brandId);
	}

	public void fetchProductById() throws FileNotFoundException {
		System.out.println("Enter Product Id");
		int prodId = InputValidation.inputIntegerValidation();

		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.fetchProductByID(prodId);
	}

	public void fetchProductByName() throws FileNotFoundException {
		System.out.println("Enter Product Name");
		String prodName = InputValidation.inputStringValidation();

		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.fetchProductByName(prodName);
	}

	public void fetchProductByBrandName() throws FileNotFoundException {
		System.out.println("Enter Brand Name");
		String brand_Name = InputValidation.inputStringValidation();

		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.fetchProductByBrandName(brand_Name);
	}

	public void fetchProductByCategoryName() throws FileNotFoundException {
		System.out.println("Enter Category Name");
		String category_Name = InputValidation.inputStringValidation();

		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.fetchProductByCategoryName(category_Name);
	}

	public void fetchProductInBetweenPriceRange() throws FileNotFoundException {
		System.out.println("Enter min price");
		double minPrice = InputValidation.inputDoubleValidation();

		System.out.println("Enter max price");
		double maxPrice = InputValidation.inputDoubleValidation();

		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.fetchProductInBetweenPriceRange(minPrice, maxPrice);
	}
	
	public void fetchProductInBelowThePriceRange() throws FileNotFoundException {
		System.out.println("Enter max price");
		double maxPrice = InputValidation.inputDoubleValidation();

		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.fetchProductBelowThePriceRange(maxPrice);
	}
	

	public void fetchProductAboveThePriceRange() throws FileNotFoundException {
		System.out.println("Enter min price");
		double minPrice = InputValidation.inputDoubleValidation();

		EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
		ecartDaoImpl.fetchProductAboveThePriceRange(minPrice);
	}

}
