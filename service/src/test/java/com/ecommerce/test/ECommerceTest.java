package com.ecommerce.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.Assert;

import com.ecommerce.common.Category;
import com.ecommerce.dao.entity.ProductMetadata;
import com.ecommerce.dao.entity.Shelf;
import com.ecommerce.dao.entity.ShopperDetails;
import com.ecommerce.service.controller.ECommerceController;
import com.ecommerce.service.model.ProductMetaData;


@SpringBootTest(classes = ECommerceTestConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ECommerceTest {

    private static final String ID_MUST_BE_PRESENT = "ids for this class must be manually assigned before calling save()";

    @Autowired
    ECommerceController eCommerceController;

    @Test
    @Order(1)
    public void testSaveProductMetadata() {

        List<ProductMetadata> productMetadataList = new ArrayList<>();

        ProductMetadata productMetadata = new ProductMetadata();
        productMetadata.setProductId("201");
        productMetadata.setCategory(Category.BABIES);
        productMetadata.setBrand("Test");

        ProductMetadata productMetadata1 = new ProductMetadata();
        productMetadata1.setProductId("202");
        productMetadata1.setCategory(Category.BABIES);
        productMetadata1.setBrand("Test");

        ProductMetadata productMetadata2 = new ProductMetadata();
        productMetadata2.setProductId("203");
        productMetadata2.setCategory(Category.BABIES);
        productMetadata2.setBrand("Test1");

        ProductMetadata productMetadata3 = new ProductMetadata();
        productMetadata3.setProductId("204");
        productMetadata3.setCategory(Category.BABIES);
        productMetadata3.setBrand("Test2");

        ProductMetadata productMetadata4 = new ProductMetadata();
        productMetadata4.setProductId("205");
        productMetadata4.setCategory(Category.BABIES);
        productMetadata4.setBrand("Test3");

        productMetadataList.addAll(Arrays.asList(productMetadata1, productMetadata2, productMetadata3, productMetadata4));

        for(ProductMetadata product : productMetadataList) {
            ProductMetaData productMetaDataResponse = eCommerceController.saveProductMetadata(product);
            Assert.assertNotNull(productMetaDataResponse);
            Assert.assertEquals(productMetaDataResponse.getProductId(), product.getProductId());
            Assert.assertEquals(productMetaDataResponse.getBrand(), product.getBrand());
            Assert.assertEquals(productMetaDataResponse.getCategory(), product.getCategory());
        }

    }

    @Test
    @Order(2)
    public void testSaveProductMetadataFailure() {

        ProductMetadata productMetadata = new ProductMetadata();
        productMetadata.setCategory(Category.BABIES);
        productMetadata.setBrand("Test");

        try {
            eCommerceController.saveProductMetadata(productMetadata);
        }
        catch (Exception e) {
            Assert.assertTrue(e instanceof JpaSystemException);
            Assert.assertTrue(e.getMessage().contains(ID_MUST_BE_PRESENT));
        }
    }

    @Test
    @Order(3)
    public void testSaveShopperDetails() {
        ShopperDetails shopperDetails = new ShopperDetails();
        shopperDetails.setShopperId("200");

        Shelf shelf = new Shelf();
        shelf.setProductId("201");
        shelf.setRelevancyScore("99.0000334");

        Shelf shelf1 = new Shelf();
        shelf1.setProductId("202");
        shelf1.setRelevancyScore("91.0000334");

        Shelf shelf2 = new Shelf();
        shelf2.setProductId("203");
        shelf2.setRelevancyScore("12.0000334");

        Shelf shelf3 = new Shelf();
        shelf3.setProductId("204");
        shelf3.setRelevancyScore("79.0000334");

        Shelf shelf4 = new Shelf();
        shelf4.setProductId("205");
        shelf4.setRelevancyScore("30.0000334");

        shopperDetails.setShelf(Arrays.asList(shelf, shelf1, shelf2, shelf3, shelf4));

        com.ecommerce.service.model.ShopperDetails
                shopperDetailsResponse = eCommerceController.saveShopperDetails(shopperDetails);
        Assert.assertNotNull(shopperDetailsResponse);
        Assert.assertEquals(shopperDetailsResponse.getShopperId(), shopperDetails.getShopperId());
        Assert.assertNotNull(shopperDetailsResponse.getShelfList());

    }

    @Test
    @Order(4)
    public void testFilterProducts() {
        List<ProductMetaData>
                productMetadataResponse = eCommerceController.getProductsByShopper("123", Category.BABIES, "Test", 2);
        for(ProductMetaData productMetaData : productMetadataResponse) {
            Assert.assertNotNull(productMetaData);
            Assert.assertEquals(productMetaData.getCategory().name(), Category.BABIES.name());
            Assert.assertNotNull(productMetaData.getBrand(), "Test");
        }

    }

    @Test
    @Order(5)
    public void testFilterProductsByBrand() {
        List<ProductMetaData>
                productMetadataResponse = eCommerceController.getProductsByShopper("123", null, "Test2", 2);
        for(ProductMetaData productMetaData : productMetadataResponse) {
            Assert.assertNotNull(productMetaData);
            Assert.assertEquals(productMetaData.getCategory().name(), Category.BABIES.name());
            Assert.assertEquals(productMetaData.getBrand(), "Test2");
        }

    }

    @Test
    @Order(6)
    public void testLimitFilteredProducts() {
        List<ProductMetaData>
                productMetadataResponse = eCommerceController.getProductsByShopper("200", Category.BABIES, null, 2);
        System.out.println(productMetadataResponse.size());
        Assert.assertTrue(productMetadataResponse.size() == 2);
    }

    @Test
    @Order(7)
    public void testFilterProductsWithNoShopperIdFailure() {
        try {
            eCommerceController.getProductsByShopper(null, Category.BABIES, "Test", 2);
            Assert.assertTrue(false, "Expected to throw exception since shopper id is null");
        }
        catch(Exception e) {

        }
    }

}
