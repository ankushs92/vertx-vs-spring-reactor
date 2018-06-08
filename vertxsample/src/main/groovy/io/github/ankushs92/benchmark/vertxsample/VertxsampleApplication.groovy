package io.github.ankushs92.benchmark.vertxsample

import groovy.util.logging.Slf4j
import io.github.ankushs92.benchmark.vertxsample.verticle.ApiVerticle
import io.vertx.core.DeploymentOptions
import io.vertx.core.Vertx
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import javax.annotation.PostConstruct

@SpringBootApplication
@Slf4j
class VertxsampleApplication {

	@Autowired
	private Vertx vertx

	@Autowired
	private SpringVerticleFactory springVerticleFactory

	static void main(String[] args) {
		SpringApplication.run VertxsampleApplication, args
	}

	@PostConstruct
	void deployVerticles() {
		vertx.registerVerticleFactory(springVerticleFactory)
		def cores = Runtime.runtime.availableProcessors()
		def threads = cores * 2
		log.info "Number of cores available {} ", cores
		log.info "Deploying {} Verticle instances ", cores
		def options = new DeploymentOptions().setInstances(threads)
		vertx.deployVerticle(springVerticleFactory.prefix() + ":" + ApiVerticle.class.name, options, { deployment ->
			if (deployment.succeeded()) {
				log.info "Deployment successful. Deployment Info {} ", deployment.result()
			} else {
				log.error "Deployment Failed with exception ", deployment.cause()
			}
		})
	}
}
