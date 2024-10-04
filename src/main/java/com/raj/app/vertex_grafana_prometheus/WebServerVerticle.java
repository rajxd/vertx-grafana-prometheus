package com.raj.app.vertex_grafana_prometheus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.example.micrometer.verticles.Greetings;
import io.vertx.ext.web.Router;

public class WebServerVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {

		Router router = Router.router(vertx);
		router.get("/").handler(req -> {
			req.response().putHeader("content-type", "text/plain").end("Hello from Vert.x!");
		});

		vertx.createHttpServer().requestHandler(router).listen(8888).onComplete(http -> {
			if (http.succeeded()) {
				startPromise.complete();
				System.out.println("HTTP server started on port 8888");
			} else {
				startPromise.fail(http.cause());
			}
		});
	}
}
