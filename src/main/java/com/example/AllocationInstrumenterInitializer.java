/*
 * Copyright 2023, 2023 IBM Corp. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.example;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.monitoring.runtime.instrumentation.AllocationRecorder;
import com.google.monitoring.runtime.instrumentation.Sampler;

public class AllocationInstrumenterInitializer implements ServletContextListener {

	public final static int THRESHOLD = Integer.getInteger("ALLOCATION_THRESHOLD", 1048576);

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("AllocationInstrumenterInitializer started");

		AllocationRecorder.addSampler(new Sampler() {
			public void sampleAllocation(int count, String desc, Object newObj, long size) {
				if (size >= THRESHOLD) {
					System.err.println("Allocation size of " + size + " exceeded threshold of " + THRESHOLD
							+ " with type " + desc + " and object " + newObj);
					Thread.dumpStack();
				}
			}
		});

		System.out.println("AllocationInstrumenterInitializer finished");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
	}
}
