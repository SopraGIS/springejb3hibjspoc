/*
 * This file is part of websocktets-gl - simple WebSocket example
 * Copyright (C) 2012  Burt Parkers
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jbpm.defra;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jbpm.deployment.util.RewardsApplicationScopedProducer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.RuntimeEngine;
import org.kie.api.runtime.manager.RuntimeManager;
import org.kie.api.runtime.process.ProcessInstance;
import org.kie.internal.runtime.manager.context.EmptyContext;

@Stateless
//@Interceptors(SpringBeanAutowiringInterceptor.class)
public class ProcessBean implements ProcessLocal{

    /** The EntityManager. */
    @PersistenceContext(unitName = "com.sample.rewards")
    private EntityManager entityManager;
    
//    @Autowired
//    private RuntimeEnvironmentFactoryBean  environmentFactoryBean;
    @Inject
    private RewardsApplicationScopedProducer applicationScopedProducer;  
    
    
    private RuntimeManager singletonManager;

    
    @PostConstruct
    public void configure() {
        // use toString to make sure CDI initializes the bean
        // this makes sure that RuntimeManager is started asap,
        // otherwise after server restart complete task won't move process forward 
        //singletonManager.toString();
    	System.out.println("Post construct called");
    	System.out.println((applicationScopedProducer==null)?"applicationScopedProducer is null": "applicationScopedProducer is not null");
    	if(applicationScopedProducer != null )singletonManager = applicationScopedProducer.getJBPMRuntimeManager();
    		System.out.println((singletonManager == null)? "singleton is null ":singletonManager.toString());
    		
    }
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long startProcess(String recipient) throws Exception {
	System.out.println("starting a process now ");

        RuntimeEngine runtime = singletonManager.getRuntimeEngine(EmptyContext
                .get());
        KieSession ksession = runtime.getKieSession();

        long processInstanceId = -1;
 
            // start a new process instance
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("recipient", recipient);
            ProcessInstance processInstance = ksession.startProcess(
                    "com.sample.rewards-basic", params);

            processInstanceId = processInstance.getId();

            System.out.println("Process started ... : processInstanceId = "
                    + processInstanceId);

         

        return processInstanceId;
	
    }
}
