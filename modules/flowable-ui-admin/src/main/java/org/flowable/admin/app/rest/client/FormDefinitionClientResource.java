/* Licensed under the Apache License, Version 2.0 (the "License");
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
package org.flowable.admin.app.rest.client;

import org.flowable.admin.domain.EndpointType;
import org.flowable.admin.domain.ServerConfig;
import org.flowable.admin.service.engine.FormDefinitionService;
import org.flowable.admin.service.engine.exception.FlowableServiceException;
import org.flowable.app.service.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * @author Bassam Al-Sarori
 * @author Yvo Swillens
 */
@RestController
public class FormDefinitionClientResource extends AbstractClientResource {

    @Autowired
    protected FormDefinitionService clientService;

    @RequestMapping(value = "/rest/admin/form-definitions/{formDefinitionId}", method = RequestMethod.GET, produces = "application/json")
    public JsonNode getForm(@PathVariable String formDefinitionId) throws BadRequestException {

        ServerConfig serverConfig = retrieveServerConfig(EndpointType.FORM);
        try {
            return clientService.getForm(serverConfig, formDefinitionId);
        } catch (FlowableServiceException e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}