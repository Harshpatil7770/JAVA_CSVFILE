package com.xoriant.delivery.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.xoriant.delivery.dbconfig.DBConfig;
import com.xoriant.delivery.dbconfig.DBquries;
import com.xoriant.delivery.jaxb.CategoryJAXB;
import com.xoriant.delivery.model.Brand;
import com.xoriant.delivery.model.Category;
import com.xoriant.delivery.model.Product;
import com.xoriant.delivery.validation.InputValidation;

public class EcartDaoImpl {

	private static Scanner sc = new Scanner(System.in);

	public void addNewCategory(Category category) {
		Connection con = DBConfig.getConnection();
		String sql = DBquries.ADD_NEW_CATEGORY;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, category.getCategoryId());
			ps.setString(2, category.getCategoryName().toUpperCase());

			int rs = ps.executeUpdate();
			if (rs != 0) {
				System.out.println("========================================");
				System.out.println("===== New Category Added Succsfully ====");
				System.out.println("========================================");
			} else {
				System.out.println("========================================");
				System.out.println("========== Not able add Category =======");
				System.out.println("========================================");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void updateCategory(Category category) {
		Connection con = DBConfig.getConnection();
		String sql = DBquries.UPDATE_CATEGORY;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category.getCategoryName().toUpperCase());
			ps.setInt(2, category.getCategoryId());
			int res = ps.executeUpdate();
			if (res != 0) {
				System.out.println("========================================");
				System.out.println("===== Update Category Succesfully  =====");
				System.out.println("========================================");
			} else {
				System.out.println("========================================");
				System.out.println("====== Not able to Update Category =====");
				System.out.println("========================================");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public List<Category> fetchAllCategories() throws FileNotFoundException {
		String userDefPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
		File file = new File(userDefPath + "\\category.csv");
		PrintWriter out = new PrintWriter(file);

		List<Category> list = new ArrayList<Category>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Category ID");
			sb.append(",");
			sb.append("Category Name");
			sb.append("\n");
			out.write(sb.toString());

			Connection con = DBConfig.getConnection();
			Statement st = con.createStatement();
			String query = DBquries.FETCH_ALL_CATEGORY;
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Category category = new Category();
				category.setCategoryId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));

				int catId = category.getCategoryId();
				String catName = category.getCategoryName();

				out.println(catId + "," + catName);
				list.add(category);

				CategoryJAXB.marshal(catId, catName);
				CategoryJAXB.unmarshal(catId, catName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return list;
	}

	public Category fetchCategoryByCategoryID(int categoryId) throws FileNotFoundException {
		String userDefPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
		File file = new File(userDefPath + "\\category.csv");
		PrintWriter out = new PrintWriter(file);
		Category category = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Category ID");
			sb.append(",");
			sb.append("Category Name");
			sb.append("\n");
			out.write(sb.toString());

			Connection con = DBConfig.getConnection();
			String sql = DBquries.FETCH_CATEGORY_BY_ID;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				category = new Category();
				category.setCategoryId(rs.getInt(1));
				category.setCategoryName(rs.getString(2));

				int catId = category.getCategoryId();
				String catName = category.getCategoryName();

				out.println(catId + "," + catName);

				CategoryJAXB.marshal(catId, catName);
				CategoryJAXB.unmarshal(catId, catName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return category;
	}

	public void addListOfCategories(Category category) {
		Connection con = DBConfig.getConnection();
		try {
			String sql = DBquries.ADD_NEW_CATEGORY;
			PreparedStatement ps = con.prepareStatement(sql);
			Scanner sc = new Scanner(System.in);
			while (true) {
				ps.setInt(1, category.getCategoryId());
				System.out.println("Enter Category Name :: ");
				String categoryName = InputValidation.inputStringValidation();
				ps.setString(2, categoryName.toUpperCase());
				int result = ps.executeUpdate();
				if (result != 0) {
					System.out.println("=========== Added Succesfully ============");
					System.out.println("Are you want to insert more record? 1) yes 2) no");
					int res = InputValidation.inputIntegerValidation();
					if (res != 1) {
						break;
					}

				} else {
					System.out.println("Not able to Categories");
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void updateListOfCategories() {
		Connection con = DBConfig.getConnection();
		String sql = DBquries.UPDATE_CATEGORY;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			while (true) {
				System.out.println("Enter Category Id");
				int catId = InputValidation.inputIntegerValidation();
				System.out.println("Enter Category Name");
				String catName = InputValidation.inputStringValidation();
				ps.setString(1, catName.toUpperCase());
				ps.setInt(2, catId);
				int result = ps.executeUpdate();
				if (result != 0) {
					System.out.println("============ Updated Succesfully ==============");
					System.out.println("Do you want update more categories 1) yes 2) no");
					int res = InputValidation.inputIntegerValidation();
					if (res != 1) {
						break;
					}
				} else {
					System.out.println("==== Not able to update Categories ====");
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void deleteCategory(int categoryId) {
		Connection con = DBConfig.getConnection();
		String sql = DBquries.DELETE_CATEGORY;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, categoryId);

			int rs = ps.executeUpdate();
			if (rs != 0) {
				System.out.println("========================================");
				System.out.println("====== Delete Category Succesfully ======");
				System.out.println("========================================");
			} else {
				System.out.println("========================================");
				System.out.println("=== Not able to Delete the Category ====");
				System.out.println("========================================");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void addNewBrand(Brand brand) {
		Connection con = DBConfig.getConnection();
		String sql = DBquries.ADD_NEW_BRAND;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, brand.getBrandId());
			ps.setString(2, brand.getBrandName().toUpperCase());
			ps.setInt(3, brand.getCategory().getCategoryId());

			int rs = ps.executeUpdate();
			if (rs != 0) {
				System.out.println("========================================");
				System.out.println("===== Add New Brand Succesfully  =====");
				System.out.println("========================================");
			} else {
				System.out.println("========================================");
				System.out.println("====== Not able to add new Brand =====");
				System.out.println("========================================");
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void updateBrand(Brand brand) throws SQLException {
		Connection con = DBConfig.getConnection();
		String sql = DBquries.UPDATE_BRAND;
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setString(1, brand.getBrandName().toUpperCase());
		preparedStatement.setInt(2, brand.getCategory().getCategoryId());
		preparedStatement.setInt(3, brand.getBrandId());

		int res = preparedStatement.executeUpdate();
		if (res != 0) {
			System.out.println("========================================");
			System.out.println("===== Update  Brand Succesfully  =====");
			System.out.println("========================================");
		} else {
			System.out.println("========================================");
			System.out.println("====== Not able to add new Brand =====");
			System.out.println("========================================");
		}
	}

	public List<Brand> fetchAllBrands() throws FileNotFoundException {
		String userDefPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
		File file = new File(userDefPath + "\\brands.csv");
		PrintWriter out = new PrintWriter(file);

		List<Brand> list = new ArrayList<Brand>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Brand ID");
			sb.append(",");
			sb.append("Brand Name");
			sb.append(",");
			sb.append("Category ID");
			sb.append(",");
			sb.append("Category Name");
			sb.append("\n");
			out.write(sb.toString());

			Connection con = DBConfig.getConnection();
			Statement st = con.createStatement();
			String query = DBquries.FETCH_ALL_BRANDS;
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Category category = new Category();
				Brand brand = new Brand();
				brand.setBrandId(rs.getInt(1));
				brand.setBrandName(rs.getString(2));
				category.setCategoryId(rs.getInt(3));
				category.setCategoryName(rs.getString(4));
				brand.setCategory(category);

				int brandId = brand.getBrandId();
				String brandName = brand.getBrandName();
				int catId = category.getCategoryId();
				String catName = category.getCategoryName();

				out.println(brandId + "," + brandName + "," + catId + "," + catName);
				list.add(brand);

				CategoryJAXB.marshal(brandId, brandName, catId, catName);
				CategoryJAXB.unmarshal(brandId, brandName, catId, catName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return list;
	}

	public Brand fetchBrandByBrandID(int brandId) throws FileNotFoundException {
		String userDefPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
		File file = new File(userDefPath + "\\brands.csv");
		PrintWriter out = new PrintWriter(file);
		Brand brand = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Brand ID");
			sb.append(",");
			sb.append("Brand Name");
			sb.append(",");
			sb.append("Category ID");
			sb.append(",");
			sb.append("Category Name");
			sb.append("\n");
			out.write(sb.toString());

			Connection con = DBConfig.getConnection();
			String sql = DBquries.FETCH_BRAND_BRAND_ID;
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, brandId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				brand = new Brand();
				Category category = new Category();

				brand.setBrandId(rs.getInt(1));
				brand.setBrandName(rs.getString(2));
				category.setCategoryId(rs.getInt(3));
				category.setCategoryName(rs.getString(4));
				brand.setCategory(category);

				int brand_Id = brand.getBrandId();
				String brandName = brand.getBrandName();
				int catId = category.getCategoryId();
				String catName = category.getCategoryName();

				out.println(brand_Id + "," + brandName + "," + catId + "," + catName);

				CategoryJAXB.marshal(brandId, brandName, catId, catName);
				CategoryJAXB.unmarshal(brandId, brandName, catId, catName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return brand;
	}

	public void addListOfBrand(Brand brand) {
		Connection con = DBConfig.getConnection();
		String sql = DBquries.ADD_NEW_BRAND;
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			while (true) {
				preparedStatement.setInt(1, brand.getBrandId());
				System.out.println("Enter brand name");
				String brandName = InputValidation.inputStringValidation();
				preparedStatement.setString(2, brandName.toUpperCase());
				System.out.println("Enter category id");
				int categoryId = InputValidation.inputIntegerValidation();
				preparedStatement.setInt(3, categoryId);
				int res = preparedStatement.executeUpdate();
				if (res != 0) {
					System.out.println("========== Added Succesfully ===========");
					System.out.println("Do you want to add one more ? 1)yes 2)no");
					int result = InputValidation.inputIntegerValidation();
					if (result != 1) {
						break;
					}
				} else {
					System.out.println("============ Not able to add ============");
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void updateListOfBrands() {
		Connection con = DBConfig.getConnection();
		String sql = DBquries.UPDATE_BRAND;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			while (true) {
				System.out.println("Enter brand id");
				int brandId = InputValidation.inputIntegerValidation();
				System.out.println("Enter brand name");
				String brandName = InputValidation.inputStringValidation();
				System.out.println("Enter category id");
				int categoryId = InputValidation.inputIntegerValidation();
				ps.setString(1, brandName.toUpperCase());
				ps.setInt(2, categoryId);
				ps.setInt(3, brandId);

				int res = ps.executeUpdate();
				if (res != 0) {
					System.out.println("======= Updated Succesfully =======");
					System.out.println("Do you want to update more categories 1) yes 2) no");
					int response = InputValidation.inputIntegerValidation();
					if (response != 1) {
						break;
					}
				} else {
					System.out.println("======= Not able to update =========");
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void deleteBrand(int brandId) {
		Connection con = DBConfig.getConnection();
		String sql = DBquries.DELETE_BRAND;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, brandId);

			int rs = ps.executeUpdate();
			if (rs != 0) {
				System.out.println("========================================");
				System.out.println("====== Delete Brand Succesfully ======");
				System.out.println("========================================");
			} else {
				System.out.println("========================================");
				System.out.println("=== Not able to delete the Brand ====");
				System.out.println("========================================");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void addNewListOfProduct(Product product) {
		Connection con = DBConfig.getConnection();
		String sql = DBquries.ADD_NEW_PRODUCT;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			while (true) {
				ps.setInt(1, product.getProductId());
				System.out.println("Enter Product Name :: ");
				String productName = sc.nextLine();
				ps.setString(2, productName.toUpperCase());
				System.out.println("Enter Product Price :: ");
				double price = InputValidation.inputDoubleValidation();
				ps.setDouble(3, price);
				System.out.println("Enter Product Description :: ");
				String description = sc.nextLine();
				ps.setString(4, description);
				System.out.println("Enter Brand Id :: ");
				int brandId = InputValidation.inputIntegerValidation();
				ps.setInt(5, brandId);
				System.out.println("Enter Category Id :: ");
				int categoryId = InputValidation.inputIntegerValidation();
				ps.setInt(6, categoryId);

				int rs = ps.executeUpdate();
				if (rs != 0) {
					System.out.println("===== New Product Added Succsfully ====");
					System.out.println("Do you want to add more Product ? 1) yes 2) no");
					int response = InputValidation.inputIntegerValidation();
					if (response != 1) {
						break;
					}
				} else {
					System.out.println("========================================");
					System.out.println("========== Not able add the Product =======");
					System.out.println("========================================");
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void updateListOfProduct() {
		Connection con = DBConfig.getConnection();
		String sql = DBquries.UPDATE_PRODUCT;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			while (true) {
				System.out.println("Enter Product Id :: ");
				int productId = InputValidation.inputIntegerValidation();
				System.out.println("Enter Product Name :: ");
				String productName = sc.nextLine();
				ps.setString(1, productName.toUpperCase());
				System.out.println("Enter Product Price :: ");
				double price = InputValidation.inputDoubleValidation();
				ps.setDouble(2, price);
				System.out.println("Enter Product Description :: ");
				String description = sc.nextLine();
				ps.setString(3, description);
				System.out.println("Enter Brand Id :: ");
				int brandId = InputValidation.inputIntegerValidation();
				ps.setInt(4, brandId);
				System.out.println("Enter Category Id :: ");
				int categoryId = InputValidation.inputIntegerValidation();
				ps.setInt(5, categoryId);
				ps.setInt(6, productId);
				int rs = ps.executeUpdate();
				if (rs != 0) {
					System.out.println("===== Update Product Succsfully ====");
					System.out.println("Do you want to update more Product ? 1) yes 2) no");
					int response = InputValidation.inputIntegerValidation();
					if (response != 1) {
						break;
					}
				} else {
					System.out.println("========================================");
					System.out.println("=== Not able add update the  Product ===");
					System.out.println("========================================");
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public List<Product> fetchAllProduct() throws FileNotFoundException {
		String userDefPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
		File file = new File(userDefPath + "\\products.csv");
		PrintWriter out = new PrintWriter(file);

		List<Product> list = new ArrayList<Product>();
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Product ID");
			sb.append(",");
			sb.append("Product Name");
			sb.append(",");
			sb.append("Product Price");
			sb.append(",");
			sb.append("Product Description");
			sb.append(",");
			sb.append("Brand ID");
			sb.append(",");
			sb.append("Brand Name");
			sb.append(",");
			sb.append("Category ID");
			sb.append(",");
			sb.append("Category Name");
			sb.append("\n");
			out.write(sb.toString());

			Connection con = DBConfig.getConnection();
			Statement st = con.createStatement();
			String query = DBquries.FETCH_ALL_PRODUCT;
			ResultSet rs = st.executeQuery(query);
			while (rs.next()) {
				Product product = new Product();
				Brand brand = new Brand();
				Category category = new Category();

				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getDouble(3));
				product.setDescription(rs.getString(4));
				brand.setBrandId(rs.getInt(5));
				brand.setBrandName(rs.getString(6));
				category.setCategoryId(rs.getInt(7));
				category.setCategoryName(rs.getString(8));
				product.setBrand(brand);
				product.setCategory(category);

				int productId = rs.getInt(1);
				String productName = rs.getString(2);
				double price = rs.getDouble(3);
				String description = rs.getString(4);
				int brandId = rs.getInt(5);
				String brandName = rs.getString(6);
				int catId = rs.getInt(7);
				String catName = rs.getString(8);

				out.println(product.getProductId() + "," + product.getProductName() + "," + product.getPrice() + ","
						+ product.getDescription() + "," + product.getBrand().getBrandId() + ","
						+ product.getBrand().getBrandName() + "," + product.getCategory().getCategoryId() + ","
						+ product.getCategory().getCategoryName());

				CategoryJAXB.unmarshal(productId, productName, price, description, brandId, brandName, catId, catName);
				CategoryJAXB.marshal(productId, productName, price, description, brandId, brandName, catId, catName);
				list.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return list;
	}

	public Product fetchProductByID(int prodId) throws FileNotFoundException {
		String userDefPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
		File file = new File(userDefPath + "\\products.csv");
		PrintWriter out = new PrintWriter(file);

		Product product = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Product ID");
			sb.append(",");
			sb.append("Product Name");
			sb.append(",");
			sb.append("Product Price");
			sb.append(",");
			sb.append("Product Description");
			sb.append(",");
			sb.append("Brand ID");
			sb.append(",");
			sb.append("Brand Name");
			sb.append(",");
			sb.append("Category ID");
			sb.append(",");
			sb.append("Category Name");
			sb.append("\n");
			out.write(sb.toString());

			Connection con = DBConfig.getConnection();
			String query = DBquries.FETCH_PRODUCT_BY_ID;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, prodId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product = new Product();
				Brand brand = new Brand();
				Category category = new Category();

				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getDouble(3));
				product.setDescription(rs.getString(4));
				brand.setBrandId(rs.getInt(5));
				brand.setBrandName(rs.getString(6));
				category.setCategoryId(rs.getInt(7));
				category.setCategoryName(rs.getString(8));
				product.setBrand(brand);
				product.setCategory(category);

				int productId = rs.getInt(1);
				String productName = rs.getString(2);
				double price = rs.getDouble(3);
				String description = rs.getString(4);
				int brandId = rs.getInt(5);
				String brandName = rs.getString(6);
				int catId = rs.getInt(7);
				String catName = rs.getString(8);

				out.println(product.getProductId() + "," + product.getProductName() + "," + product.getPrice() + ","
						+ product.getDescription() + "," + product.getBrand().getBrandId() + ","
						+ product.getBrand().getBrandName() + "," + product.getCategory().getCategoryId() + ","
						+ product.getCategory().getCategoryName());

				CategoryJAXB.unmarshal(productId, productName, price, description, brandId, brandName, catId, catName);
				CategoryJAXB.marshal(productId, productName, price, description, brandId, brandName, catId, catName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return product;
	}

	public Product fetchProductByName(String prodName) throws FileNotFoundException {
		String userDefPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
		File file = new File(userDefPath + "\\products.csv");
		PrintWriter out = new PrintWriter(file);

		Product product = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Product ID");
			sb.append(",");
			sb.append("Product Name");
			sb.append(",");
			sb.append("Product Price");
			sb.append(",");
			sb.append("Product Description");
			sb.append(",");
			sb.append("Brand ID");
			sb.append(",");
			sb.append("Brand Name");
			sb.append(",");
			sb.append("Category ID");
			sb.append(",");
			sb.append("Category Name");
			sb.append("\n");
			out.write(sb.toString());

			Connection con = DBConfig.getConnection();
			String query = DBquries.FETCH_PRODUCT_BY_NAME;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, prodName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				product = new Product();
				Brand brand = new Brand();
				Category category = new Category();

				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getDouble(3));
				product.setDescription(rs.getString(4));
				brand.setBrandId(rs.getInt(5));
				brand.setBrandName(rs.getString(6));
				category.setCategoryId(rs.getInt(7));
				category.setCategoryName(rs.getString(8));
				product.setBrand(brand);
				product.setCategory(category);

				int productId = rs.getInt(1);
				String productName = rs.getString(2);
				double price = rs.getDouble(3);
				String description = rs.getString(4);
				int brandId = rs.getInt(5);
				String brandName = rs.getString(6);
				int catId = rs.getInt(7);
				String catName = rs.getString(8);

				out.println(product.getProductId() + "," + product.getProductName() + "," + product.getPrice() + ","
						+ product.getDescription() + "," + product.getBrand().getBrandId() + ","
						+ product.getBrand().getBrandName() + "," + product.getCategory().getCategoryId() + ","
						+ product.getCategory().getCategoryName());

				CategoryJAXB.unmarshal(productId, productName, price, description, brandId, brandName, catId, catName);
				CategoryJAXB.marshal(productId, productName, price, description, brandId, brandName, catId, catName);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return product;
	}

	public List<Product> fetchProductByBrandName(String brand_Name) throws FileNotFoundException {
		String userDefPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
		File file = new File(userDefPath + "\\products.csv");
		PrintWriter out = new PrintWriter(file);
		List<Product> list = new ArrayList<Product>();
		Product product = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Product ID");
			sb.append(",");
			sb.append("Product Name");
			sb.append(",");
			sb.append("Product Price");
			sb.append(",");
			sb.append("Product Description");
			sb.append(",");
			sb.append("Brand ID");
			sb.append(",");
			sb.append("Brand Name");
			sb.append(",");
			sb.append("Category ID");
			sb.append(",");
			sb.append("Category Name");
			sb.append("\n");
			out.write(sb.toString());

			Connection con = DBConfig.getConnection();
			String query = DBquries.FETCH_PRODUCT_BY_BRAND_NAME;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, brand_Name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product();
				Brand brand = new Brand();
				Category category = new Category();

				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getDouble(3));
				product.setDescription(rs.getString(4));
				brand.setBrandId(rs.getInt(5));
				brand.setBrandName(rs.getString(6));
				category.setCategoryId(rs.getInt(7));
				category.setCategoryName(rs.getString(8));
				product.setBrand(brand);
				product.setCategory(category);

				int productId = rs.getInt(1);
				String productName = rs.getString(2);
				double price = rs.getDouble(3);
				String description = rs.getString(4);
				int brandId = rs.getInt(5);
				String brandName = rs.getString(6);
				int catId = rs.getInt(7);
				String catName = rs.getString(8);

				out.println(product.getProductId() + "," + product.getProductName() + "," + product.getPrice() + ","
						+ product.getDescription() + "," + product.getBrand().getBrandId() + ","
						+ product.getBrand().getBrandName() + "," + product.getCategory().getCategoryId() + ","
						+ product.getCategory().getCategoryName());

				CategoryJAXB.unmarshal(productId, productName, price, description, brandId, brandName, catId, catName);
				CategoryJAXB.marshal(productId, productName, price, description, brandId, brandName, catId, catName);
				list.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return list;
	}

	public List<Product> fetchProductByCategoryName(String category_name) throws FileNotFoundException {
		String userDefPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
		File file = new File(userDefPath + "\\products.csv");
		PrintWriter out = new PrintWriter(file);
		List<Product> list = new ArrayList<Product>();
		Product product = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Product ID");
			sb.append(",");
			sb.append("Product Name");
			sb.append(",");
			sb.append("Product Price");
			sb.append(",");
			sb.append("Product Description");
			sb.append(",");
			sb.append("Brand ID");
			sb.append(",");
			sb.append("Brand Name");
			sb.append(",");
			sb.append("Category ID");
			sb.append(",");
			sb.append("Category Name");
			sb.append("\n");
			out.write(sb.toString());

			Connection con = DBConfig.getConnection();
			String query = DBquries.FETCH_PRODUCT_BY_CATEGORY_NAME;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, category_name);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product();
				Brand brand = new Brand();
				Category category = new Category();

				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getDouble(3));
				product.setDescription(rs.getString(4));
				brand.setBrandId(rs.getInt(5));
				brand.setBrandName(rs.getString(6));
				category.setCategoryId(rs.getInt(7));
				category.setCategoryName(rs.getString(8));
				product.setBrand(brand);
				product.setCategory(category);

				int productId = rs.getInt(1);
				String productName = rs.getString(2);
				double price = rs.getDouble(3);
				String description = rs.getString(4);
				int brandId = rs.getInt(5);
				String brandName = rs.getString(6);
				int catId = rs.getInt(7);
				String catName = rs.getString(8);

				out.println(product.getProductId() + "," + product.getProductName() + "," + product.getPrice() + ","
						+ product.getDescription() + "," + product.getBrand().getBrandId() + ","
						+ product.getBrand().getBrandName() + "," + product.getCategory().getCategoryId() + ","
						+ product.getCategory().getCategoryName());

				CategoryJAXB.unmarshal(productId, productName, price, description, brandId, brandName, catId, catName);
				CategoryJAXB.marshal(productId, productName, price, description, brandId, brandName, catId, catName);
				list.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return list;
	}

	public List<Product> fetchProductInBetweenPriceRange(double minPrice, double maxPrice)
			throws FileNotFoundException {
		String userDefPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
		File file = new File(userDefPath + "\\products.csv");
		PrintWriter out = new PrintWriter(file);
		List<Product> list = new ArrayList<Product>();
		Product product = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Product ID");
			sb.append(",");
			sb.append("Product Name");
			sb.append(",");
			sb.append("Product Price");
			sb.append(",");
			sb.append("Product Description");
			sb.append(",");
			sb.append("Brand ID");
			sb.append(",");
			sb.append("Brand Name");
			sb.append(",");
			sb.append("Category ID");
			sb.append(",");
			sb.append("Category Name");
			sb.append("\n");
			out.write(sb.toString());

			Connection con = DBConfig.getConnection();
			String query = DBquries.FETCH_PRODUCT_IN_BETWEEN_PRICE_RANGE;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDouble(1, minPrice);
			ps.setDouble(2, maxPrice);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product();
				Brand brand = new Brand();
				Category category = new Category();

				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getDouble(3));
				product.setDescription(rs.getString(4));
				brand.setBrandId(rs.getInt(5));
				brand.setBrandName(rs.getString(6));
				category.setCategoryId(rs.getInt(7));
				category.setCategoryName(rs.getString(8));
				product.setBrand(brand);
				product.setCategory(category);

				int productId = rs.getInt(1);
				String productName = rs.getString(2);
				double price = rs.getDouble(3);
				String description = rs.getString(4);
				int brandId = rs.getInt(5);
				String brandName = rs.getString(6);
				int catId = rs.getInt(7);
				String catName = rs.getString(8);

				out.println(product.getProductId() + "," + product.getProductName() + "," + product.getPrice() + ","
						+ product.getDescription() + "," + product.getBrand().getBrandId() + ","
						+ product.getBrand().getBrandName() + "," + product.getCategory().getCategoryId() + ","
						+ product.getCategory().getCategoryName());

				CategoryJAXB.unmarshal(productId, productName, price, description, brandId, brandName, catId, catName);
				CategoryJAXB.marshal(productId, productName, price, description, brandId, brandName, catId, catName);
				list.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return list;
	}

	public List<Product> fetchProductBelowThePriceRange(double maxPrice) throws FileNotFoundException {
		String userDefPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
		File file = new File(userDefPath + "\\products.csv");
		PrintWriter out = new PrintWriter(file);
		List<Product> list = new ArrayList<Product>();
		Product product = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Product ID");
			sb.append(",");
			sb.append("Product Name");
			sb.append(",");
			sb.append("Product Price");
			sb.append(",");
			sb.append("Product Description");
			sb.append(",");
			sb.append("Brand ID");
			sb.append(",");
			sb.append("Brand Name");
			sb.append(",");
			sb.append("Category ID");
			sb.append(",");
			sb.append("Category Name");
			sb.append("\n");
			out.write(sb.toString());

			Connection con = DBConfig.getConnection();
			String query = DBquries.FETCH_PRODUCT_BELOW_THE_PRICE_RANGE;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDouble(1, maxPrice);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product();
				Brand brand = new Brand();
				Category category = new Category();

				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getDouble(3));
				product.setDescription(rs.getString(4));
				brand.setBrandId(rs.getInt(5));
				brand.setBrandName(rs.getString(6));
				category.setCategoryId(rs.getInt(7));
				category.setCategoryName(rs.getString(8));
				product.setBrand(brand);
				product.setCategory(category);

				int productId = rs.getInt(1);
				String productName = rs.getString(2);
				double price = rs.getDouble(3);
				String description = rs.getString(4);
				int brandId = rs.getInt(5);
				String brandName = rs.getString(6);
				int catId = rs.getInt(7);
				String catName = rs.getString(8);

				out.println(product.getProductId() + "," + product.getProductName() + "," + product.getPrice() + ","
						+ product.getDescription() + "," + product.getBrand().getBrandId() + ","
						+ product.getBrand().getBrandName() + "," + product.getCategory().getCategoryId() + ","
						+ product.getCategory().getCategoryName());

				CategoryJAXB.unmarshal(productId, productName, price, description, brandId, brandName, catId, catName);
				CategoryJAXB.marshal(productId, productName, price, description, brandId, brandName, catId, catName);
				list.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return list;
	}

	public List<Product> fetchProductAboveThePriceRange(double minPrice) throws FileNotFoundException {
		String userDefPath = "C:\\Users\\" + System.getProperty("user.name") + "\\Desktop";
		File file = new File(userDefPath + "\\products.csv");
		PrintWriter out = new PrintWriter(file);
		List<Product> list = new ArrayList<Product>();
		Product product = null;
		try {
			StringBuilder sb = new StringBuilder();
			sb.append("Product ID");
			sb.append(",");
			sb.append("Product Name");
			sb.append(",");
			sb.append("Product Price");
			sb.append(",");
			sb.append("Product Description");
			sb.append(",");
			sb.append("Brand ID");
			sb.append(",");
			sb.append("Brand Name");
			sb.append(",");
			sb.append("Category ID");
			sb.append(",");
			sb.append("Category Name");
			sb.append("\n");
			out.write(sb.toString());

			Connection con = DBConfig.getConnection();
			String query = DBquries.FETCH_PRODUCT_ABOVE_THE_PRICE_RANGE;
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDouble(1, minPrice);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product = new Product();
				Brand brand = new Brand();
				Category category = new Category();

				product.setProductId(rs.getInt(1));
				product.setProductName(rs.getString(2));
				product.setPrice(rs.getDouble(3));
				product.setDescription(rs.getString(4));
				brand.setBrandId(rs.getInt(5));
				brand.setBrandName(rs.getString(6));
				category.setCategoryId(rs.getInt(7));
				category.setCategoryName(rs.getString(8));
				product.setBrand(brand);
				product.setCategory(category);

				int productId = rs.getInt(1);
				String productName = rs.getString(2);
				double price = rs.getDouble(3);
				String description = rs.getString(4);
				int brandId = rs.getInt(5);
				String brandName = rs.getString(6);
				int catId = rs.getInt(7);
				String catName = rs.getString(8);

				out.println(product.getProductId() + "," + product.getProductName() + "," + product.getPrice() + ","
						+ product.getDescription() + "," + product.getBrand().getBrandId() + ","
						+ product.getBrand().getBrandName() + "," + product.getCategory().getCategoryId() + ","
						+ product.getCategory().getCategoryName());

				CategoryJAXB.unmarshal(productId, productName, price, description, brandId, brandName, catId, catName);
				CategoryJAXB.marshal(productId, productName, price, description, brandId, brandName, catId, catName);
				list.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		return list;
	}
}
