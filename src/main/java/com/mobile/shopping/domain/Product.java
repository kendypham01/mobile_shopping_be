package com.mobile.shopping.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "producer_code")
    private String producerCode;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Float price;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "create_name")
    private String createName;

    @Column(name = "update_date")
    private Instant updateDate;

    @Column(name = "update_name")
    private String updateName;

    public Long getId() {
        return this.id;
    }

    public Product id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public Product code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategoryCode() {
        return this.categoryCode;
    }

    public Product categoryCode(String categoryCode) {
        this.setCategoryCode(categoryCode);
        return this;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getProducerCode() {
        return this.producerCode;
    }

    public Product producerCode(String producerCode) {
        this.setProducerCode(producerCode);
        return this;
    }

    public void setProducerCode(String producerCode) {
        this.producerCode = producerCode;
    }

    public String getName() {
        return this.name;
    }

    public Product name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Product imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public Product quantity(Integer quantity) {
        this.setQuantity(quantity);
        return this;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getPrice() {
        return this.price;
    }

    public Product price(Float price) {
        this.setPrice(price);
        return this;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public Product status(Boolean status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Instant getCreateDate() {
        return this.createDate;
    }

    public Product createDate(Instant createDate) {
        this.setCreateDate(createDate);
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getCreateName() {
        return this.createName;
    }

    public Product createName(String createName) {
        this.setCreateName(createName);
        return this;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public Instant getUpdateDate() {
        return this.updateDate;
    }

    public Product updateDate(Instant updateDate) {
        this.setUpdateDate(updateDate);
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateName() {
        return this.updateName;
    }

    public Product updateName(String updateName) {
        this.setUpdateName(updateName);
        return this;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        return id != null && id.equals(((Product) o).id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", categoryCode='" + getCategoryCode() + "'" +
            ", producerCode='" + getProducerCode() + "'" +
            ", name='" + getName() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", quantity=" + getQuantity() +
            ", price=" + getPrice() +
            ", status='" + getStatus() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", createName='" + getCreateName() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", updateName='" + getUpdateName() + "'" +
            "}";
    }
}
