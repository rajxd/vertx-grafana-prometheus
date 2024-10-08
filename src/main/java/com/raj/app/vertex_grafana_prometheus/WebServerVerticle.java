package com.raj.app.vertex_grafana_prometheus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;

public class WebServerVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {

		Router router = Router.router(vertx);
		router.get("/").handler(req -> {
			req.response().putHeader("content-type", "text/plain").end("Hello from Vert.x!");
		});
		router.get("/eventbus").handler(req -> {
			vertx.eventBus().request("greetings", "Hello").onSuccess(r->{
				req.response().putHeader("content-type", "text/plain").end(r.body().toString());
			});
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
