package com.xoriant.delivery.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.xoriant.delivery.model.Brand;
import com.xoriant.delivery.model.Category;
import com.xoriant.delivery.model.Product;

public class CategoryJAXB {

	public static void marshal(int catId, String catName) {
		try {

			Category category = new Category(catId, catName);
			System.out.println("=================================================");
			System.out.println("======= Marshal ( Java object XML Format) =======");
			System.out.println("=================================================");
			JAXBContext jaxbContext = JAXBContext.newInstance(Category.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(category, System.out);
			marshaller.marshal(category, new File("src\\com\\xoriant\\delivery\\data\\category.xml"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void unmarshal(int catId, String catName) {
		try {
			JAXBContext jc = JAXBContext.newInstance(Category.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			Category category = (Category) unmarshaller
					.unmarshal(new File("src\\com\\xoriant\\delivery\\data\\category.xml"));
			System.out.println("================================================================");
			System.out.println(" ============ Unmarshal Format( XML to JAVA Object) ============");
			System.out.println("================================================================");
			System.out.println("ID :: " + category.getCategoryId());
			System.out.println("Name :: " + category.getCategoryName());

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static void marshal(int brandId, String brandName, int catId, String catName) {
		try {
			Category category = new Category(catId, catName);
			Brand brand = new Brand(brandId, brandName, category);
			System.out.println("=================================================");
			System.out.println("======= Marshal ( Java object XML Format) =======");
			System.out.println("=================================================");
			JAXBContext jaxbContext = JAXBContext.newInstance(Brand.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(brand, System.out);
			marshaller.marshal(brand, new File("src\\com\\xoriant\\delivery\\data\\brands.xml"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void unmarshal(int brandId, String brandName, int catId, String catName) {
		try {
			JAXBContext jc = JAXBContext.newInstance(Brand.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			Brand brand = (Brand) unmarshaller.unmarshal(new File("src\\com\\xoriant\\delivery\\data\\brands.xml"));
			System.out.println("================================================================");
			System.out.println(" ============ Unmarshal Format( XML to JAVA Object) ============");
			System.out.println("================================================================");
			System.out.println("BRAND ID :: " + brand.getBrandId());
			System.out.println("BRAND Name :: " + brand.getBrandName());
			System.out.println("Category ID :: " + brand.getCategory().getCategoryId());
			System.out.println("Category Name :: " + brand.getCategory().getCategoryName());

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static void unmarshal(int productId, String productName, double price, String description, int brandId,
			String brandName, int catId, String catName) {
		try {
			JAXBContext jc = JAXBContext.newInstance(Product.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			Product product = (Product) unmarshaller
					.unmarshal(new File("src\\com\\xoriant\\delivery\\data\\products.xml"));
			System.out.println("================================================================");
			System.out.println(" ============ Unmarshal Format( XML to JAVA Object) ============");
			System.out.println("================================================================");
			System.out.println("PRODUCT ID :: " + product.getProductId());
			System.out.println("PRODUCT Name :: " + product.getProductName());
			System.out.println("PRODUCT PRICE :: " + product.getPrice());
			System.out.println("PRODUCT DESCRIPTION :: " + product.getDescription());
			System.out.println("BRAND ID :: " + product.getBrand().getBrandId());
			System.out.println("BRAND NAME :: " + product.getBrand().getBrandName());
			System.out.println("CATEGORY ID :: " + product.getCategory().getCategoryId());
			System.out.println("CATEGORY NAME :: " + product.getCategory().getCategoryName());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	public static void marshal(int productId, String productName, double price, String description, int brandId,
			String brandName, int catId, String catName) {
		try {
			Category category = new Category(catId, catName);
			Brand brand = new Brand(brandId, brandName, category);
			Product product = new Product(productId, productName, price, description, brand, category);
			System.out.println("=================================================");
			System.out.println("======= Marshal ( Java object XML Format) =======");
			System.out.println("=================================================");
			JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(product, System.out);
			marshaller.marshal(product, new File("src\\com\\xoriant\\delivery\\data\\products.xml"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
