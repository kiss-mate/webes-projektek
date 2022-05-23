package hu.kissmt.webesrestapi.controllers;

import hu.kissmt.webesrestapi.dataaccess.IOrderDAO;
import hu.kissmt.webesrestapi.dataaccess.IProductDAO;
import hu.kissmt.webesrestapi.entities.Order;
import hu.kissmt.webesrestapi.entities.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/orders")
@CrossOrigin
public class OrderController {
    private final IOrderDAO _orderDAO;
    private final IProductDAO _productDAO;

    public OrderController(IOrderDAO orderDAO, IProductDAO productDAO) {
        _orderDAO = orderDAO;
        _productDAO = productDAO;
    }

    @GetMapping("/get/{id}") //localhost:8080/api/v1/orders/get/1
    public ResponseEntity<Order> getOrder(@PathVariable long id) {
        var order = _orderDAO.findById(id).orElse(null);
        if (order == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(order,HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrders() {
        var orders = _orderDAO.findAll();
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @RequestMapping(value = "/add")
    @CrossOrigin
    public String addOrder(@RequestBody Map<String, String> basket) {
        basket = basket
                .entrySet()
                .stream()
                .filter(e -> !e.getValue().equals("0"))
                .collect(Collectors.toMap(e -> e.getKey(), e-> e.getValue()));

        Map<String, String> finalBasket = basket;

        if (finalBasket.isEmpty())
            return "Please select at least 1 piece of product";

        System.out.println(finalBasket);
        var orderedProducts = _productDAO
                .findAll()
                .stream()
                .filter(p -> finalBasket.keySet().contains(p.getProductName()))
                .collect(Collectors.toList());
        double netTotal;
        double grossTotal;

        netTotal = orderedProducts.stream().map(p -> p.getNetUnitPrice() * Integer.parseInt(finalBasket.get(p.getProductName()))).reduce(0.0, Double::sum);
        grossTotal = orderedProducts.stream().map(p -> p.getGrossUnitPrice() * Integer.parseInt(finalBasket.get(p.getProductName()))).reduce(0.0, Double::sum);

        var order = new Order(
                netTotal,
                grossTotal,
                finalBasket
        );
        var savedOrder = _orderDAO.save(order);

        return "Order #" + savedOrder.getId() + " saved successfully";
    }

    @RequestMapping(value = "/delete/{orderId}")
    @CrossOrigin
    public String deleteOrder(@PathVariable String orderId) {
        var id = Long.parseLong(orderId);
        if (id < 1)
            return "Invalid id provided";

        _orderDAO.deleteById(id);

        return "Order #" + id + " deleted successfully";
    }
}
