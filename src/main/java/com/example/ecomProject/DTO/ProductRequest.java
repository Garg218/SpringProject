package com.example.ecomProject.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Date;

public class ProductRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String brand;
    @Positive
    private BigDecimal price;

    @NotBlank
    private String category;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
    private Date releaseDate;

    @Positive
    private int quantity;

    @NotNull
    private Boolean available;

//    private String imageName;
//    private String imageType;
//    @Lob
//    private byte[] imageDesc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

//    public String getImageName() {
//        return imageName;
//    }
//
//    public void setImageName(String imageName) {
//        this.imageName = imageName;
//    }
//
//    public String getImageType() {
//        return imageType;
//    }
//
//    public void setImageType(String imageType) {
//        this.imageType = imageType;
//    }
//
//    public byte[] getImageDesc() {
//        return imageDesc;
//    }
//
//    public void setImageDesc(byte[] imageDesc) {
//        this.imageDesc = imageDesc;
//    }


}
