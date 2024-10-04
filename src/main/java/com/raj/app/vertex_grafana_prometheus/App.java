package com.raj.app.vertex_grafana_prometheus;

import io.vertx.core.Vertx;

public class App {

	public static void main(final String... args) {
	    Vertx vertx = Vertx.vertx();
	    vertx.deployVerticle(new WebServerVerticle());
	}

}
