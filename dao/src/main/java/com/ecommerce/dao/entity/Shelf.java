package com.ecommerce.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity(name = "shelf")
@Table(name = "shelf", indexes = {
        @Index(name = "shelf_id_index", columnList = "shelf_id", unique = true),
        @Index(name = "shelf_id_product_id_index", columnList = "shelf_id, shopper_product_id")
})
public class Shelf implements Serializable {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "shelf_id")
   private Long shelfId;

   @Column(name = "shopper_product_id")
   private String productId;

   @Column(name = "relevancy_score")
   private String relevancyScore;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "shopper_product_id", referencedColumnName = "product_id", insertable = false, updatable = false)
   private ProductMetadata productMetadata;

   public String getRelevancyScore() {
      return relevancyScore;
   }

   public void setRelevancyScore(String relevancyScore) {
      this.relevancyScore = relevancyScore;
   }

   public String getProductId() {
      return productId;
   }

   public void setProductId(String productId) {
      this.productId = productId;
   }

   public ProductMetadata getProductMetadata() {
      return productMetadata;
   }

   public void setProductMetadata(ProductMetadata productMetadata) {
      this.productMetadata = productMetadata;
   }
}
