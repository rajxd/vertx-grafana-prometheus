package com.raj.app.vertex_grafana_prometheus;

import java.util.EnumSet;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.micrometer.Label;
import io.vertx.micrometer.MicrometerMetricsOptions;
import io.vertx.micrometer.VertxPrometheusOptions;

public class App {

	public static void main(final String... args) {

		MicrometerMetricsOptions options = new MicrometerMetricsOptions()
				.setPrometheusOptions(new VertxPrometheusOptions().setStartEmbeddedServer(true)
						.setEmbeddedServerOptions(new HttpServerOptions().setPort(8887))
						.setEnabled(true))
				.setLabels(EnumSet.allOf(Label.class)).setEnabled(true);

	    Vertx vertx = Vertx.vertx(new VertxOptions().setMetricsOptions(options));
		vertx.deployVerticle(new WebServerVerticle());
		vertx.deployVerticle(new EventBusConsumerVerticle());
		vertx.deployVerticle(new EventBusProducerVerticle());
	}

}
