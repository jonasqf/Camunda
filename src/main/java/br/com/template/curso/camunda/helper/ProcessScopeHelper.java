package br.com.template.curso.camunda.helper;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Component;

import br.com.template.curso.camunda.dto.CotacaoDto;
import br.com.template.curso.camunda.enumeration.DecisaoEnum;

@Component
public class ProcessScopeHelper {

	private final RuntimeService runtimeService;
	
	public ProcessScopeHelper(RuntimeService runtimeService) {
		this.runtimeService = runtimeService;
	}
	
	private static final String COTACAO_DTO = "cotacao";
	private static final String DECISAO = "decisao";

	public CotacaoDto getCotacao(String processId) {
		return (CotacaoDto) runtimeService.getVariable(processId, COTACAO_DTO);
	}
	
	public void setCotacao(String processId, CotacaoDto cotacao) {
		runtimeService.setVariable(processId, COTACAO_DTO, cotacao);
	}
	
	public void setDecisao(String processId, DecisaoEnum decisao) {
		runtimeService.setVariable(processId, DECISAO, decisao);
	}
	
}
