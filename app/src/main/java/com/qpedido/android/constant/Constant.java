package com.qpedido.android.constant;

import com.qpedido.android.model.Item;
import com.qpedido.android.model.Restaurant;

public abstract class Constant {
    public final static Restaurant[] PARTNERS = {
            new Restaurant("Rio Lanches", "\uD83C\uDF54", -27.052921, -49.539228),
            new Restaurant("Centro Pizzas", "\uD83C\uDF55", -27.060635, -49.520336),
            new Restaurant("Morro Restaurante", "\uD83C\uDF74", -27.044969, -49.527194),
    };

    public final static Item[] ITEMS = {
            new Item("", "", 10, 30),
    };
}
