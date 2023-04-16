package com.microservice.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.microservice.dto.CompraDto;
import com.microservice.dto.InfoForcedor;

public class CompraService {

	public void realizaCompra(CompraDto compra) {
		RestTemplate client = new RestTemplate();
		ResponseEntity<InfoForcedor> exchange = client.exchange("http://localhost:8081/info/"+ compra.getEndereco().getEstado(), 
				HttpMethod.GET, null,InfoForcedor.class);
	}

}
