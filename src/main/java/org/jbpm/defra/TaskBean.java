/**
 * Copyright 2014 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jbpm.defra;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.jbpm.deployment.util.RewardsApplicationScopedProducer;
import org.jbpm.services.task.exception.PermissionDeniedException;
import org.kie.api.task.TaskService;
import org.kie.api.task.model.TaskSummary;
import org.kie.internal.runtime.manager.context.EmptyContext;

@Stateless
public class TaskBean {


    @Inject
    private RewardsApplicationScopedProducer applicationScopedProducer;  
    
    private TaskService taskService;
    
    @PostConstruct
    private void configure(){
    	taskService = applicationScopedProducer.getJBPMRuntimeManager().getRuntimeEngine(EmptyContext.get()).getTaskService();
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<TaskSummary> retrieveTaskList(String actorId) throws Exception {

        
        List<TaskSummary> list = null;
        
            list = taskService.getTasksAssignedAsPotentialOwner(actorId, "en-UK");

        System.out.println("retrieveTaskList by " + actorId);
        for (TaskSummary task : list) {
            System.out.println(" task.getId() = " + task.getId());
        }

        return list;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void approveTask(String actorId, long taskId) throws Exception {
 
        try {
            System.out.println("approveTask (taskId = " + taskId + ") by " + actorId);
            taskService.start(taskId, actorId);
            taskService.complete(taskId, actorId, null);

            //Thread.sleep(10000); // To test OptimisticLockException

         
        } catch (PermissionDeniedException e) {
            e.printStackTrace();
            // Transaction might be already rolled back by TaskServiceSession
            
            // Probably the task has already been started by other users
            throw new ProcessOperationException("The task (id = " + taskId
                    + ") has likely been started by other users ", e);
        } catch (Exception e) {
            e.printStackTrace();
            // Transaction might be already rolled back by TaskServiceSession

            throw new RuntimeException(e);
        } 
    }

}
