package com.test.fooddeliverytest.dto;

public class CartMealInfoDTO {

    private MealInfoDTO mealInfo;
    private Integer count;

    public CartMealInfoDTO() {
        count = 1;
    }

    public MealInfoDTO getMealInfo() {
        return mealInfo;
    }

    public void setMealInfo(MealInfoDTO mealInfo) {
        this.mealInfo = mealInfo;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void incrementCount(){
        count++;
    }

    @Override
    public int hashCode() {
        return this.getMealInfo().getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this)
            return true;

        if (obj instanceof CartMealInfoDTO){
            CartMealInfoDTO that = (CartMealInfoDTO) obj;
            return that.getMealInfo().getId().equals(this.getMealInfo().getId());
        }

        return false;
    }
}
