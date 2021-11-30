package com.opticortes.controllers;

import spark.Request;
import spark.Response;

public interface ICRUDController {
//    Gson gson = new Gson();

    String getAll(Request req, Response res);

    String deleteAll(Request req, Response res);

    String addNew(Request req, Response res);

    String getOne(Request req, Response res);

    String updateOne(Request req, Response res);

    String deleteOne(Request req, Response res);
}