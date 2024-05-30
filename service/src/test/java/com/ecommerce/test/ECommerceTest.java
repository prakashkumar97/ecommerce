package com.ecommerce.test;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class ECommerceTest {

    @Autowired
    ECommerceController eCommerceController;

    @Test
    public void testSaveProductMetadata() {
        ProductMetadata productMetadata = new ProductMetadata();
        productMetadata.setProductId("123");
        productMetadata.setCategory(Category.BABIES);
        productMetadata.setBrand("Test");

        ProductMetaData productMetaDataResponse = eCommerceController.saveProductMetadata(productMetadata);
        Assert.assertNotNull(productMetaDataResponse);
        Assert.assertEquals(productMetaDataResponse.getProductId(), productMetadata.getProductId());
        Assert.assertEquals(productMetaDataResponse.getBrand(), productMetadata.getBrand());
        Assert.assertEquals(productMetaDataResponse.getCategory(), productMetadata.getCategory());

    }

    @Test
    public void testSaveShopperDetails() {
        ShopperDetails shopperDetails = new ShopperDetails();
        shopperDetails.setShopperId("123");
        Shelf shelf = new Shelf();
        shelf.setProductId("123");
        shelf.setRelevancyScore("99.0000334");
        shopperDetails.setShelf(Arrays.asList(shelf));

        com.ecommerce.service.model.ShopperDetails
                shopperDetailsResponse = eCommerceController.saveShopperDetails(shopperDetails);
        Assert.assertNotNull(shopperDetailsResponse);
        Assert.assertEquals(shopperDetailsResponse.getShopperId(), shopperDetails.getShopperId());
        Assert.assertNotNull(shopperDetailsResponse.getShelfList());

    }

}
