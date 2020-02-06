package com.project.avtomoy.ui.info.fragments.Sales;

import java.util.ArrayList;

public class SalesClass {
    private ArrayList<Sale> sales;

    public SalesClass(ArrayList<Sale> sales) {
        this.sales = sales;
    }

    public ArrayList<Sale> getSales() {
        return sales;
    }

    public void setSales(ArrayList<Sale> sales) {
        this.sales = sales;
    }
}
