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

package org.jbpm.deployment.util;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;

import org.jbpm.runtime.manager.impl.RuntimeEnvironmentBuilder;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.manager.RuntimeEnvironment;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.manager.RuntimeManagerFactory;
import org.kie.internal.io.ResourceFactory;

@ApplicationScoped
public class RewardsApplicationScopedProducer {

//    @Inject
//    private InjectableRegisterableItemsFactory factory;
//   
//    
   
   private RuntimeManager runtimeManager;
   
   @PersistenceUnit(unitName = "com.sample.rewards")
    private EntityManagerFactory emf;

    @PostConstruct
    public void produceEntityManagerFactory() {
    	System.out.println("public void produceEntityManagerFactory() called");
        if (this.emf == null) {
            this.emf = Persistence
                    .createEntityManagerFactory("com.sample.rewards");
        }
        
        RuntimeEnvironment environment = RuntimeEnvironmentBuilder
                .getDefault()
                .entityManagerFactory(emf)
                .addAsset(
                        ResourceFactory
                                .newClassPathResource("rewards-basic.bpmn"),
                        ResourceType.BPMN2).get();
        
        
       this.runtimeManager= RuntimeManagerFactory.Factory.get()
		.newSingletonRuntimeManager(environment, "com.sample:example:1.0");
        
        
    }

	public RuntimeManager getJBPMRuntimeManager() {
		return this.runtimeManager;
	}


    

}
