package api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Spark;

/**
 * Class
 *
 * @author Scoowy
 * @version 2021.11.26.1209
 */
public class OptiCutPedidosAPI {

    public final static Logger logger = LoggerFactory.getLogger(OptiCutPedidosAPI.class);

    public static void main(String[] args) {
        Spark.port(getPort());

        Spark.get("/", (request, response) -> "Todo bien!");

        Spark.path("/api", () -> {
            Spark.path("/pedidos", () -> {
                Spark.get("", (req, res) -> "Get Planks");
                Spark.post("", (req, res) -> "Get Planks");
                Spark.delete("", (req, res) -> "Get Planks");

                Spark.path("/:pedidosId", () -> {
                    Spark.get("", (req, res) -> "Get Planks");
                    Spark.put("", (req, res) -> "Get Planks");
                    Spark.delete("", (req, res) -> "Get Planks");
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
