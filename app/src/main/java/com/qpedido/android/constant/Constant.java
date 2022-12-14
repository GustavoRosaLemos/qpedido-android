package com.qpedido.android.constant;

import com.qpedido.android.model.Item;
import com.qpedido.android.model.Restaurant;

public abstract class Constant {
    public final static Restaurant[] PARTNERS = {
            new Restaurant("Rio Lanches", "\uD83C\uDF54", -27.052921, -49.539228),
            new Restaurant("Centro Pizzas", "\uD83C\uDF55", -27.060635, -49.520336),
            new Restaurant("Morro Restaurante", "\uD83C\uDF74", -27.044969, -49.527194),
    };

    public final static Item[] ITEMS_STARTER = {
            new Item("Salada e frango", "Salada c/ frango 200g", 30.0, 37),
            new Item("Bandeja de frios", "Bandeja com frios", 70.00, 15),
    };

    public final static Item[] ITEMS_PLATE = {
            new Item("Filé ao molho madeira", "Bife 200g c/ batatas rústicas", 53.33, 72),
            new Item("Filé ao molho 4 queijos", "Bife 200g c/ batatas rústicas", 70.00, 20),
    };

    public final static Item[] ITEMS_PIZZA = {
            new Item("Quatro queijos", "Queijo e orégano", 105.0, 143),
            new Item("Calabresa", "Pizza de calabresa", 105.00, 200),
    };

    public final static Item[] ITEMS_SANDWICH = {
            new Item("Sanduiche de atum", "Sanduiche c/ atum", 22.0, 60),
            new Item("Sanduiche de presunto", "Sanduiche c/ presunto", 22.00, 73),
    };

    public final static Item[] ITEMS_DESSERT = {
            new Item("Bolo de chocolate", "Fatia de bolo de chocolate", 43.0, 40),
            new Item("Pudim de leite", "Fatia de pudim de leite", 105.00, 200),
    };

    public final static Item[] ITEMS_DRINK = {
            new Item("Suco de manga", "Suco natural de manga", 15.0, 93),
            new Item("Suco de laranja", "Suco natural de laranja", 20.0, 80),
    };

    public final static String[] ATTENDANCE_CATEGORIES = {
            "Garçom",
            "Gerente"
    };

    public final static String[] ATTENDANCE_PRIORITY = {
            "Baixa",
            "Média",
            "Alta"
    };

    public final static String[] PAYMENT_TYPES = {
            "Cartão",
    };
}
