package com.raj.app.vertex_grafana_prometheus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.example.micrometer.verticles.Greetings;
import io.vertx.ext.web.Router;

public class EventBusProducerVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {

		vertx.setPeriodic(1000, x->{
//			vertx.eventBus().send("greetings", "Hello");
			
			vertx.eventBus().request("greetings", "Hello").onSuccess(r->{
				System.out.println(r.body().toString());
			});
		});
	}
}
