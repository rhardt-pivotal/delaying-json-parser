package com.example.bigpayloaddelayer;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@SpringBootApplication
@Controller
public class BigPayloadDelayerApplication {

	Logger logger = LoggerFactory.getLogger(this.getClass().getName());


	public static void main(String[] args) {
		SpringApplication.run(BigPayloadDelayerApplication.class, args);
	}

	@PostMapping(path = "/postdelay/{delay}", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Make a POST request to upload the file",
			produces = "application/json", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "The POST call is Successful"),
			@ApiResponse(code = 500, message = "The POST call is Failed"),
			@ApiResponse(code = 404, message = "The API could not be found")
	})
	public ResponseEntity<String> uploadFile(@RequestBody Map<String, Object> request, @PathVariable int delay){

				try {
					logger.info(request.toString());
					logger.info("Sleeping {} seconds", delay);
					Thread.sleep(1000 * delay);
					logger.info("done");
					return ResponseEntity.ok("{\"response\": \"bueno!!!\"}");
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<String>("{\"response\": \"malo!!!\"", HttpStatus.INTERNAL_SERVER_ERROR);
				}
		}
}
