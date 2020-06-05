package br.com.template.curso.camunda.api;

import java.util.List;

import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.template.curso.camunda.dto.CotacaoDto;
import br.com.template.curso.camunda.enumeration.DecisaoEnum;
import br.com.template.curso.camunda.enumeration.TarefaEnum;
import br.com.template.curso.camunda.service.ProcessService;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/v1/process")
public class ProcessApi {
	
	private final ProcessService processService;
	private Logger logger = LoggerFactory.getLogger(ProcessApi.class);
	
	public ProcessApi(ProcessService processService) {
		this.processService = processService;
	}
	
    @PostMapping
    @ApiOperation(value = "Inicia um processo do camunda.", notes = "Inicia um processo do camunda.", response = Void.class)
    public void start(@RequestBody CotacaoDto cotacao) {

    	processService.start(cotacao);
    }
    
    @GetMapping(path = "/tarefas/cotacao")
    @ApiOperation(value = "Busca tarefas cotacao.", notes = "Busca tarefas cotacao.", response = List.class)
    public List<CotacaoDto> buscarTarefasCotacao() {
    	
    	return processService.buscarTarefasCotacao(TarefaEnum.taskEnviarSolicitacao);
    }
    
    @GetMapping(path = "/tarefas/solicitacao")
    @ApiOperation(value = "Busca tarefas solicitacao.", notes = "Busca tarefas solicitacao.", response = List.class)
    public List<CotacaoDto> buscarTarefasSolicitacao() {
    	
    	return processService.buscarTarefasCotacao(TarefaEnum.taskReceberSolicitacao);
    }
    
    @GetMapping(path = "/tarefa/{idTarefa}")
    @ApiOperation(value = "Busca tarefa.", notes = "Busca tarefa.", response = List.class)
    public CotacaoDto buscarTarefasCotacao(@PathVariable(name = "idTarefa") String idTarefa) {
    	
    	return processService.buscarTarefa(idTarefa);
    }
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "/tarefa/cotacao/{decisao}")
    @ApiOperation(value = "Completar tarefa cotacao.", notes = "Completar tarefa cotacao.", response = Void.class)
    public void completarTarefaCotacao(@RequestBody CotacaoDto cotacao,
    								  @PathVariable(name = "decisao") DecisaoEnum decisao) {
    	
    	processService.completarTarefaCotacao(cotacao, decisao);
    }
    
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(path = "/tarefa/solicitacao")
    @ApiOperation(value = "Completar tarefa solicitacao.", notes = "Completar tarefa solicitacao.", response = Void.class)
    public void completarTarefaSolicitacao(@RequestBody CotacaoDto cotacao) {
    	
    	processService.completarTarefaSolicitacao(cotacao);
    }
	

}
