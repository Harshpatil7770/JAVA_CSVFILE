package com.xoriant.delivery.main;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.*;

import com.xoriant.delivery.dao.EcartDaoImpl;
import com.xoriant.delivery.model.Brand;
import com.xoriant.delivery.model.Category;
import com.xoriant.delivery.model.Product;
import com.xoriant.delivery.utility.EcartUtility;
import com.xoriant.delivery.validation.InputValidation;

public class Main {

	private static Scanner sc = new Scanner(System.in);

	public void showMainMenu() throws FileNotFoundException, SQLException {

		int number = 1;
		do {

			System.out.println("=========== Welcome To Ecart =========");
			System.out.println("1) Add New Cateory");
			System.out.println("2) Update Category");
			System.out.println("3) Fetch All Category and Write it CSV FILE");
			System.out.println("4) Fetch Category by Id and Write it CSV FILE");
			System.out.println("5) Add List of Categories");
			System.out.println("6) Update List of Categories");
			System.out.println("7) Delete Category");
			System.out.println();
			System.out.println("11) Add New Brand");
			System.out.println("12) Update Brand");
			System.out.println("13) Fetch All Brands and Write it CSV FILE");
			System.out.println("14) Fetch Brand by Id and Write it CSV FILE");
			System.out.println("15) Add List of Brand");
			System.out.println("16) Update List of Brand");
			System.out.println("17) Delete Brand");
			System.out.println();
			System.out.println("21) Add New List Of Product ");
			System.out.println("22) Update List Of Product");
			System.out.println("23) Fetch All Product and Write it CSV FILE");
			System.out.println("24) Fetch Product by Id and Write it CSV FILE");
			System.out.println("25) Fetch Product by Name and Write it CSV FILE");
			System.out.println("26) Fetch Product by Brand Name and Write it CSV FILE");
			System.out.println("27) Fetch Product by Category Name and Write it CSV FILE");
			System.out.println("28) Fetch Product In between Price Range and Write it CSV FILE");
			System.out.println("29) Fetch Product above Price Range and Write it CSV FILE");
			System.out.println("30) Fetch Product below Price Range and Write it CSV FILE");

			System.out.println("\n Enter Your Choice :: ");
			int ch = InputValidation.inputIntegerValidation();

			EcartUtility ecartUtility = new EcartUtility();
			EcartDaoImpl ecartDaoImpl = new EcartDaoImpl();
			Category category = new Category();
			Brand brand = new Brand();
			Product product = new Product();
			switch (ch) {
			case 1:
				System.out.println(" You have selected  1) Add New Cateory");
				ecartUtility.addNewCategory();
				break;
			case 2:
				System.out.println(" You have selected  2) Update Cateory ");
				ecartUtility.updateCategory();
				break;
			case 3:
				System.out.println(" You have selected  3) Fetch All Category and Write it CSV FILE ");
				ecartDaoImpl.fetchAllCategories().forEach(System.out::println);
				break;
			case 4:
				System.out.println("You have selected  4) Fetch Category by Category Id and Write it CSV FILE");
				ecartUtility.fetchCategoryById();
				break;
			case 5:
				System.out.println("You have selected  5)  Add List of Category");
				ecartDaoImpl.addListOfCategories(category);
				break;
			case 6:
				System.out.println("You have selected  6)  Update List of Category");
				ecartDaoImpl.updateListOfCategories();
				break;
			case 7:
				System.out.println("You have selected  7)  Delete Category");
				ecartUtility.deleteCategory();
				break;
			case 11:
				System.out.println(" You have selected  11)  Add New Brand");
				ecartUtility.addNewBrand();
				break;
			case 12:
				System.out.println(" You have selected  12) Update Brand ");
				ecartUtility.updateBrand();
				break;
			case 13:
				System.out.println(" You have selected  13) Fetch All Brand and Write it CSV FILE ");
				ecartDaoImpl.fetchAllBrands().forEach(System.out::println);
				break;
			case 14:
				System.out.println("You have selected  14) Fetch Brand by Brand Id and Write it CSV FILE");
				ecartUtility.fetchBrandById();
				break;
			case 15:
				System.out.println("You have selected  15)  Add List of Brand");
				ecartDaoImpl.addListOfBrand(brand);
				break;
			case 16:
				System.out.println("You have selected  16)  Update List of Brands");
				ecartDaoImpl.updateListOfBrands();
				break;
			case 17:
				System.out.println("You have selected  17)  Delete brand");
				ecartUtility.deleteBrand();
				break;
			case 21:
				System.out.println(" You have selected  21)  Add New List Of Product");
				ecartDaoImpl.addNewListOfProduct(product);
				break;
			case 22:
				System.out.println(" You have selected  22) Update List Of Product ");
				ecartDaoImpl.updateListOfProduct();
				break;
			case 23:
				System.out.println(" You have selected  23) Fetch All Products and Write it CSV FILE ");
				ecartDaoImpl.fetchAllProduct().forEach(System.out::println);
				;
				break;
			case 24:
				System.out.println("You have selected   24) Fetch Product by Id and Write it CSV FILE");
				ecartUtility.fetchProductById();
				break;
			case 25:
				System.out.println("You have selected   25) Fetch Product by Name and Write it CSV FILE");
				ecartUtility.fetchProductByName();
				break;
			case 26:
				System.out.println("You have selected  26)  Fetch Product by Brand Name and Write it CSV FILE");
				ecartUtility.fetchProductByName();
				break;
			case 27:
				System.out.println("You have selected  27)  Fetch Product by Category Name and Write it CSV FILE");
				ecartUtility.fetchProductByCategoryName();
				break;
			case 28:
				System.out
						.println("You have selected  28)  Fetch Product In between Price Range and Write it CSV FILE");
				ecartUtility.fetchProductInBetweenPriceRange();
				break;
			case 29:
				System.out.println("You have selected  29)  Fetch Product below The Price Range and Write it CSV FILE");
				ecartUtility.fetchProductInBelowThePriceRange();
				break;
			case 30:
				System.out.println("You have selected  30)  Fetch Product Above The Price Range and Write it CSV FILE");
				ecartUtility.fetchProductAboveThePriceRange();
				break;
			default:
				System.out.println("==============================================");
				System.out.println("==== Please enter available choice number ==== ");
				System.out.println("==============================================");
			}

			System.out.println("Do you want to continue? 1)yes 2)no");
			number = InputValidation.inputIntegerValidation();
		} while (number == 1);
	}
}
