package ru.practicum.ShoppingCart.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ShoppingCart.model.Order;
import ru.practicum.ShoppingCart.model.enums.OrderStatus;
import ru.practicum.ShoppingCart.service.OrderService;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Api(tags = "Order Rest Controller")
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ApiOperation(value = "Создать заказ", notes = "Создание заказа")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        log.info("Запрос на создание заказа: {}", order);
        Order createdOrder = orderService.createOrder(order);
        log.info("Создан заказ: {}", createdOrder);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Удалить заказ", notes = "Удаление определенного заказа")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        log.info("Запрос на удаление заказа с id: {}", id);
        orderService.deleteOrder(id);
        log.info("Заказ с id {} успешно удален", id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    @ApiOperation(value = "Поменять статус заказа", notes = "Получение всех товаров, у которых поле InStock = true")
    public ResponseEntity<Order> updateOrderStatus(@PathVariable Long id, @RequestParam("status") String status) {
        log.info("Запрос на изменение статуса заказа с id: {}", id);
        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());
        Order updatedOrder = orderService.changeOrderStatus(id, orderStatus);
        log.info("Статус заказа с id {} изменен на {}", id, orderStatus);
        return ResponseEntity.ok(updatedOrder);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Отобразить заказ по id", notes = "Отображение заказа по его id")
    public Order getOrderById(@PathVariable Long id) {
        log.info("Запрос на получение заказа с id: {}", id);
        Order order = orderService.getOrderById(id);
        log.info("Заказ с id {} успешно получен", id);
        return order;
    }
}
