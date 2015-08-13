/**
 * Copyright (c) 2015 Intel Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.trustedanalytics.metricsprovider.integrationtests.utils;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.AsyncRestOperations;

public class AsyncRestMockBuilder<T> {

    private final AsyncRestOperations restOps;
    private final Class<T> typeToReturn;

    public AsyncRestMockBuilder(AsyncRestOperations restOps, Class<T> typeToReturn) {
        this.restOps = restOps;
        this.typeToReturn = typeToReturn;
    }

    public void thenReturn(T valueToReturn) {
        when(restOps
            .exchange(any(String.class), eq(HttpMethod.GET), any(HttpEntity.class),
                eq(typeToReturn), any(Object.class)))
            .thenReturn(new TestListenableFuture<>(valueToReturn));
    }
}
