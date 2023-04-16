package com.microservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.client.FornecedorClient;
import com.microservice.controller.dto.CompraDTO;
import com.microservice.controller.dto.InfoFornecedorDTO;
import com.microservice.dto.InfoPedidoDTO;
import com.microservice.model.Compra;

@Service
public class CompraService {

	private static final Logger LOG = LoggerFactory.getLogger(CompraService.class);
	
//	@Autowired
//	private RestTemplate client;
	
//	@Autowired
//	private DiscoveryClient eurekaClient;

	@Autowired
	private FornecedorClient fornecedorClient;
	
	public Compra realizaCompra(CompraDTO compra) {
			
		final String estado = compra.getEndereco().getEstado();
		
		LOG.info("Buscando Informações dos fornecedores de {}", estado);
		InfoFornecedorDTO info = fornecedorClient.getInfoPorEstado(estado);
		
		LOG.info("Realizando um pedido");
		InfoPedidoDTO pedido = fornecedorClient.realziarPedido(compra.getItens());
		
//		//RestTemplate client = new RestTemplate();
//		
//		ResponseEntity<InfoFornecedorDTO> exchange = 
//				client.exchange("http://fornecedor/info/"+compra.getEndereco().getEstado(),
//				HttpMethod.GET, null, InfoFornecedorDTO.class);
//		
//		eurekaClient.getInstances("fornecedor").stream()
//		.forEach(fornecedor -> {
//			System.out.println("localhost: "+ fornecedor.getPort());
//		});

		
		System.out.println(info.getEndereco());
		
		Compra compraSalva = new Compra();
		compraSalva.setPedidoId(pedido.getId());		
		compraSalva.setTempoDePreparo(pedido.getTempoDePreparo());		
		compraSalva.setEnderecoDestino(compra.getEndereco().toString());		
		return compraSalva;
		
	}
	
}
