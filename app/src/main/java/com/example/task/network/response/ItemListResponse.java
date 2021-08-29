package com.example.task.network.response;

import java.util.List;

public class ItemListResponse {
    boolean status;
    String message;
    List<Items> items;


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public class Items{
        int id;
        int store_id;
        String name;
        int price;
        Category category;
        String image_url;
        String images;
        int available;
        String description;
        int discounted_price;
        String variants;
        int variants_count;

        int quantity;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getStore_id() {
            return store_id;
        }

        public void setStore_id(int store_id) {
            this.store_id = store_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public Category getCategory() {
            return category;
        }

        public void setCategory(Category category) {
            this.category = category;
        }

        public String getImage_url() {
            return image_url;
        }

        public void setImage_url(String image_url) {
            this.image_url = image_url;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getAvailable() {
            return available;
        }

        public void setAvailable(int available) {
            this.available = available;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getDiscounted_price() {
            return discounted_price;
        }

        public void setDiscounted_price(int discounted_price) {
            this.discounted_price = discounted_price;
        }

        public String getVariants() {
            return variants;
        }

        public void setVariants(String variants) {
            this.variants = variants;
        }

        public int getVariants_count() {
            return variants_count;
        }

        public void setVariants_count(int variants_count) {
            this.variants_count = variants_count;
        }
    }

    public class Category{
        String id;
        String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
