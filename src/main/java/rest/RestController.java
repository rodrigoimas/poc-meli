package ar.com.ml.solar.rest;

import static spark.Spark.*;

public class RestController {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}
