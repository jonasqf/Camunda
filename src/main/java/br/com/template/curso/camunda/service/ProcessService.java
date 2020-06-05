package br.com.template.curso.camunda.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import br.com.template.curso.camunda.dto.CotacaoDto;
import br.com.template.curso.camunda.enumeration.DecisaoEnum;
import br.com.template.curso.camunda.enumeration.TarefaEnum;
import br.com.template.curso.camunda.helper.ProcessScopeHelper;

@Service
public class ProcessService {
	
	private static final String PROCESSO_COTACAO = "processoCotacao";
	
	private final RuntimeService runtimeService;
	private final TaskService taskService;
	private final ProcessScopeHelper processScope;
	
	public ProcessService(RuntimeService runtimeService, TaskService taskService, ProcessScopeHelper processScope) {
		this.runtimeService = runtimeService;
		this.taskService = taskService;
		this.processScope = processScope;
	}

	public void start(CotacaoDto cotacao) {
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("cotacao", cotacao);
        
        
		runtimeService.startProcessInstanceByKey(PROCESSO_COTACAO, cotacao.getNomeSolicitante(), variables);
    }
	
	public List<CotacaoDto> buscarTarefasCotacao(TarefaEnum tarefaCode) {
		List<Task> tasks = taskService.createTaskQuery()
				.taskDefinitionKey(tarefaCode.toString())
				.list();
		
		if (tasks != null && tasks.size() == 0) {
			return new ArrayList<CotacaoDto>();
		}
		
		return tasks.stream().map(task -> {
			CotacaoDto cotacao = processScope.getCotacao(task.getProcessInstanceId());
			cotacao.setIdTarefa(task.getId());
			
			return cotacao;
		}).collect(Collectors.toList());
	}
	
	public CotacaoDto buscarTarefa(String idTarefa) {
		Task task = taskService.createTaskQuery()
				.taskId(idTarefa)
				.singleResult();
		
		CotacaoDto cotacao = processScope.getCotacao(task.getProcessInstanceId());
		cotacao.setIdTarefa(task.getId());
		
		return cotacao;
	}

	public void completarTarefaCotacao(CotacaoDto cotacao, DecisaoEnum decisao) {
		Task task = taskService.createTaskQuery()
							.taskId(cotacao.getIdTarefa())
							.singleResult();

		String processId = task.getProcessInstanceId();
		
		processScope.setDecisao(processId, decisao);
		processScope.setCotacao(processId, cotacao);
		
		taskService.complete(task.getId());
    }
	
	public void completarTarefaSolicitacao(CotacaoDto cotacao) {
		taskService.complete(cotacao.getIdTarefa());
    }
	
}
