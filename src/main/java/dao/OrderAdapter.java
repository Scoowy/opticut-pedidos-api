package dao;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import entities.Order;
import entities.Piece;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class OrderAdapter implements JsonSerializer<Order>, JsonDeserializer<Order> {

    private static final String ID = "id";
    private static final String CLIENT = "client";
    private static final String PLANK = "plank";
    private static final String PIECES = "pieces";
    private static final String STATUS = "status";

    @Override
    public JsonElement serialize(Order order, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject orderJson = new JsonObject();

        orderJson.addProperty(ID, order.getId().toString());
        orderJson.addProperty(CLIENT, order.getClient());
        orderJson.addProperty(PLANK, order.getPlank());
        JsonElement pieces = jsonSerializationContext.serialize(order.getPieces());
        orderJson.add(PIECES, pieces);
        orderJson.addProperty(STATUS, order.getStatus());

        return orderJson;
    }

    @Override
    public Order deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String client = jsonObject.get(CLIENT).getAsString();
        String plank = jsonObject.get(PLANK).getAsString();

        Type listOfPieces = new TypeToken<ArrayList<Piece>>() {
        }.getType();

        List<Piece> pieces = jsonDeserializationContext.deserialize(jsonObject.get(PIECES).getAsJsonArray(), listOfPieces);
        String status = jsonObject.get(STATUS).getAsString();

        return new Order(client, plank, pieces, status);
    }

}