package br.com.template.curso.camunda.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component(value = "setarVariavelProcessoDelegate")
public class SetarVariavelProcessoDelegate implements JavaDelegate {
	
	private Logger logger = LoggerFactory.getLogger(SetarVariavelProcessoDelegate.class);
	
	@Override
	public void execute(DelegateExecution execution) {
		
		// Setando variável no processo
			execution.setVariable("nome", "Joao Teste");
		
		String nome = (String) execution.getVariable("nome");
		
		logger.info(nome);
		
	}

}
