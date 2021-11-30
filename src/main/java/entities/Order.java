package entities;

import org.bson.types.ObjectId;

import java.util.List;
import java.util.StringJoiner;

public class Order {
    private ObjectId id;
    private String client;
    private String plank;
    private List<Piece> pieces;
    private String status;

    public Order() {
    }

    public Order(ObjectId id) {
        this.id = id;
    }

    public Order(String client, String plank, List<Piece> pieces, String status) {
        this.client = client;
        this.plank = plank;
        this.pieces = pieces;
        this.status = status;
    }

    public Order(ObjectId id, String client, String plank, List<Piece> pieces, String status) {
        this.id = id;
        this.client = client;
        this.plank = plank;
        this.pieces = pieces;
        this.status = status;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getPlank() {
        return plank;
    }

    public void setPlank(String plank) {
        this.plank = plank;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Order.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("client='" + client + "'")
                .add("plank='" + plank + "'")
                .add("pieces=" + pieces)
                .add("status='" + status + "'")
                .toString();
    }
}