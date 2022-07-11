package com.example.workflow;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class PrepareToBattle implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        int warriors = (int) delegateExecution.getVariable("warriors");

        if (warriors < 0 || warriors > 100) {
            throw new BpmnError("warriorsError");
        }

        int enemyWarriors = (int) (Math.random() * 100);
        String battleStatus = "Undefined";

        boolean isWin;

        if (warriors > enemyWarriors) {
            isWin = true;
            battleStatus = "Victory";
        } else {
            isWin = false;
            battleStatus = "Defeat";
        }

        delegateExecution.setVariable("enemyWarriors", enemyWarriors);
        delegateExecution.setVariable("battleStatus", battleStatus);
        delegateExecution.setVariable("isWin", isWin);
        delegateExecution.setVariable("warriors", warriors);


    }
}
