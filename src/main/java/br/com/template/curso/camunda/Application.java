package br.com.template.curso.camunda;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.camunda.bpm.spring.boot.starter.SpringBootProcessApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class Application extends SpringBootProcessApplication {
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostDeploy
    public void onDeploymentFinished(ProcessEngine processEngine) {
        Logger logHelper = LoggerFactory.getLogger(this.getClass());

        try {
            SpringProcessEngineConfiguration processEngineConfiguration = (SpringProcessEngineConfiguration) processEngine.getProcessEngineConfiguration();
            processEngineConfiguration.setDefaultSerializationFormat("application/json");
        } catch (Throwable e) {
            logHelper.error("onDeploymentFinished", e);
            throw e;
        }
    }


    @Override
    public ExecutionListener getExecutionListener() {
        return new ExecutionListener() {
            Logger  LOGGER = LoggerFactory.getLogger(ExecutionListener.class.getName());

            @Override
            public void notify(DelegateExecution execution) throws Exception {
                String key = new StringBuilder()
                        .append(execution.getProcessEngineServices().getRepositoryService()
                                .getProcessDefinition(execution.getProcessDefinitionId()).getKey())
                        .append(":").append(execution.getCurrentActivityId()).toString();
                LOGGER.info("GlobalExecutionListener >> " + execution.getEventName() + " == " + key);
            }
        };
    }
}
