package com.manorbuttys.api.menu.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.manorbuttys.api.menu.model.Item;
import com.manorbuttys.api.menu.model.MenuSection;
import com.manorbuttys.api.menu.repository.MenuRepository;
import com.stripe.Stripe;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MenuController {

    private final MenuRepository menuRepository;
    private final Gson gson = new Gson();

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping(value = "/menu", produces = "application/json")
    public Iterable<MenuSection> getMenu() {
        List<MenuSection> menu = (List<MenuSection>) menuRepository.findAll();
        Stream<MenuSection> sorted = menu.stream()
                .sorted();
        return sorted.collect(Collectors.toList());
    }

    @PostMapping(value = "/checkout", consumes = "application/json")
    public String checkout(@RequestBody String orderJson) throws Exception {
        Stripe.apiKey = "sk_test_bhw6uvZaZGiyxGhEi6IwuKa1";

        Type targetClassType = new TypeToken<ArrayList<MenuSection>>() {
        }.getType();
        Collection<MenuSection> order = new Gson().fromJson(orderJson, targetClassType);

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl("http://localhost:4200/")
                        .setCancelUrl("http://localhost:4200/")
                        .addAllLineItem(buildLineItemList(order))
                        .build();

        Session session = Session.create(params);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("id", session.getId());

        return gson.toJson(responseData);
    }

    private List<SessionCreateParams.LineItem> buildLineItemList(Collection<MenuSection> order) {
        ArrayList<SessionCreateParams.LineItem> lineItems = new ArrayList<>();
        for (MenuSection section : order) {
            for (Item item : section.getItems()) {
                if (item.getCount() > 0) {
                    BigDecimal rigged_price = item.getPrice().multiply(new BigDecimal("100.0"));
                    SessionCreateParams.LineItem line = SessionCreateParams.LineItem.builder()
                            .setQuantity((long) item.getCount())
                            .setPriceData(
                                    SessionCreateParams.LineItem.PriceData.builder()
                                            .setCurrency("gbp")
                                            .setUnitAmount(rigged_price.longValue())
                                            .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                    .setName(item.getName())
                                                    .build())
                                            .build())
                            .build();
                    lineItems.add(line);
                }
            }
        }
        return lineItems;
    }
}