package com.jbk;

import java.util.Scanner;

import com.jbk.dao.ProductDao;
import com.jbk.entity.Product;
import com.jbk.utility.ProductUtility;

public class ProductController {

	public static void main(String[] args) {
		ProductDao dao = new ProductDao();
		Scanner scanner = new Scanner(System.in);
		int choice;
		char c;
		do {
			System.out.println("Press 1 For Save Product");
			System.out.println("Press 2 For get Product By Id");
			System.out.println("Press 3 for delete product");
			choice = scanner.nextInt();

			switch (choice) {
			case 1: {
				Product product = ProductUtility.prepareProductData(scanner);

				if (product != null) {
					String msg = dao.addProduct(product);
					System.out.println(msg);
				} else {
					System.out.println("Product Not Valid");
				}

				break;
			}

			case 2: {
				System.out.println("Enter Product Id");
				long productId = scanner.nextLong();
				Product product = dao.getProductById(productId);
				if (product != null) {
					System.out.println(product);
				} else {
					System.out.println("Product Not Found With Id =" + productId);
				}
				break;
			}

			case 3: {

				System.out.println("Enter Product Id");
				long productId = scanner.nextLong();
				String msg = dao.deleteProductById(productId);
				System.out.println(msg);
				break;
			}

			default:
				System.out.println("Invalid Choice");
				break;
			}
			System.out.println("Do you want to continue y/n ");
			c = scanner.next().charAt(0);

		} while (c == 'y' || c == 'Y');

		System.out.println("App Terminated");

	}

}
