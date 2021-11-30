package com.opticortes.api;

import com.opticortes.controllers.OrdersController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

public class OptiCutPedidosAPI {

    public final static Logger logger = LoggerFactory.getLogger(OptiCutPedidosAPI.class);

    public static void main(String[] args) {
        Spark.port(getPort());

        OrdersController ordersCtrl = new OrdersController();

        Spark.get("/", (request, response) -> "Todo bien!");

        Spark.path("/api", () -> {
            Spark.path("/orders", () -> {
                Spark.get("", ordersCtrl::getAll);
                Spark.post("", ordersCtrl::addNew);
                Spark.delete("", ordersCtrl::deleteAll);

                Spark.path("/:orderId", () -> {
                    Spark.get("", ordersCtrl::getOne);
                    Spark.put("", ordersCtrl::updateOne);
                    Spark.delete("", ordersCtrl::deleteOne);
                    Spark.get("/:newStatus", ordersCtrl::changeStatus);
                });
            });
        });
    }

    static int getPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8080;
    }
}