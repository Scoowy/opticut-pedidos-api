package controllers;

import api.OptiCutPedidosAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.OrderAdapter;
import entities.Order;
import org.bson.types.ObjectId;
import services.OrdersServices;
import spark.Request;
import spark.Response;
import utils.ErrorResponse;
import utils.OkResponse;
import utils.ResponseType;


public class OrdersController implements ICRUDController {

    Gson gson;
    GsonBuilder builder = new GsonBuilder();
    OrdersServices ordersServices = new OrdersServices();

    public OrdersController() {
        // Register adapters
        builder.registerTypeAdapter(Order.class, new OrderAdapter());
        gson = builder.create();
    }

    @Override
    public String getAll(Request req, Response res) {
        res.type(ResponseType.JSON.toString());
        return gson.toJson(ordersServices.getOrders());
    }

    @Override
    public String deleteAll(Request req, Response res) {
        res.type(ResponseType.JSON.toString());

        int rows = ordersServices.deleteOrders();

        if (rows != 0) {
            res.status(200);
            return gson.toJson(new OkResponse(200, "Deleted %d orders".formatted(rows)));
        } else {
            res.status(404);
            return gson.toJson(new ErrorResponse(404, "Orders not deleted"));
        }
    }

    @Override
    public String addNew(Request req, Response res) {
        res.type(ResponseType.JSON.toString());


        String body = req.body();
        Order order = gson.fromJson(body, Order.class);

        OptiCutPedidosAPI.logger.info("[New Order]: {}", order);

        int rows = ordersServices.addNewOrder(order);

        if (rows != 0) {
            res.status(201);
            return gson.toJson(new OkResponse(200, "Order created"));
        } else {
            res.status(404);
            return gson.toJson(new ErrorResponse(404, "Order not created"));
        }
    }

    @Override
    public String getOne(Request req, Response res) {
        res.type(ResponseType.JSON.toString());

        String orderId = req.params("orderId");
        Order order = new Order(new ObjectId(orderId));

        Order selected = ordersServices.getOrder(order);

        if (selected != null) {
            res.status(200);
            return gson.toJson(selected);
        } else {
            res.status(404);
            return gson.toJson(new ErrorResponse(404, "Order %s not found".formatted(order.getId().toString())));
        }
    }

    @Override
    public String updateOne(Request req, Response res) {
        res.type(ResponseType.JSON.toString());

        String orderId = req.params("orderId");
        String body = req.body();
        Order order = gson.fromJson(body, Order.class);

        order.setId(new ObjectId(orderId));

        int rows = ordersServices.updateOrder(order);

        if (rows != 0) {
            res.status(200);
            return gson.toJson(new OkResponse(200, "Order modified"));
        } else {
            res.status(404);
            return gson.toJson(new ErrorResponse(404, "Order not modified"));
        }
    }

    @Override
    public String deleteOne(Request req, Response res) {
        res.type(ResponseType.JSON.toString());

        String orderId = req.params("orderId");
        Order order = new Order(new ObjectId(orderId));

        int rows = ordersServices.deleteOrder(order);

        if (rows != 0) {
            res.status(201);
            return gson.toJson(new OkResponse(201, "Order deleted"));
        } else {
            res.status(404);
            return gson.toJson(new ErrorResponse(404, "Order not deleted"));
        }
    }

    public String changeStatus(Request req, Response res) {
        res.type(ResponseType.JSON.toString());

        String orderId = req.params("orderId");
        String newStatus = req.params("newStatus");

        Order order = new Order(new ObjectId(orderId));
        order.setStatus(newStatus);

        int rows = ordersServices.chageStatus(order);

        if (rows != 0) {
            res.status(200);
            return gson.toJson(new OkResponse(200, "Order status changed"));
        } else {
            res.status(404);
            return gson.toJson(new ErrorResponse(404, "Order not found"));
        }
    }
}