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
package org.trustedanalytics.metricsprovider.config;

import org.trustedanalytics.cloud.auth.OAuth2TokenRetriever;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.client.AsyncClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.AsyncRestOperations;
import org.springframework.web.client.AsyncRestTemplate;

@Configuration
@Profile({"default", "cloud"})
public class CloudConfig {
    @Autowired
    private int connectTimeout;

    @Autowired
    private int readTimeout;

    @Bean
    protected OAuth2TokenRetriever tokenRetriever() {
        return new OAuth2TokenRetriever();
    }

    @Bean
    protected AsyncRestOperations asyncTemplate() {
        return new AsyncRestTemplate(asyncHttpRequestFactory(connectTimeout, readTimeout));
    }

    private AsyncClientHttpRequestFactory asyncHttpRequestFactory(int connectTimeout,
        int readTimeout) {

        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setTaskExecutor(new SimpleAsyncTaskExecutor());
        factory.setConnectTimeout(connectTimeout);
        factory.setReadTimeout(readTimeout);
        return factory;
    }
}
