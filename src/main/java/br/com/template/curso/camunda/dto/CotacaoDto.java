package br.com.template.curso.camunda.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class CotacaoDto {

	private String nomeCliente;
	
	private String nomeSolicitante;

	private String tipoMercadoria;
	
	private Integer quantidadeMercadoria;
	
	private String valorMercadoria;
	
	private String idTarefa;

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getTipoMercadoria() {
		return tipoMercadoria;
	}

	public void setTipoMercadoria(String tipoMercadoria) {
		this.tipoMercadoria = tipoMercadoria;
	}

	public Integer getQuantidadeMercadoria() {
		return quantidadeMercadoria;
	}

	public void setQuantidadeMercadoria(Integer quantidadeMercadoria) {
		this.quantidadeMercadoria = quantidadeMercadoria;
	}

	public String getValorMercadoria() {
		return valorMercadoria;
	}

	public void setValorMercadoria(String valorMercadoria) {
		this.valorMercadoria = valorMercadoria;
	}

	public String getIdTarefa() {
		return idTarefa;
	}

	public void setIdTarefa(String idTarefa) {
		this.idTarefa = idTarefa;
	}
	
	
}
