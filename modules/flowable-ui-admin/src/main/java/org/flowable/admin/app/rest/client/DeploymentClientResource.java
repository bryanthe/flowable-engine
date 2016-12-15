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

import javax.servlet.http.HttpServletResponse;

import org.flowable.admin.domain.EndpointType;
import org.flowable.admin.domain.ServerConfig;
import org.flowable.admin.service.engine.DeploymentService;
import org.flowable.admin.service.engine.exception.FlowableServiceException;
import org.flowable.app.service.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * REST controller for managing the current user's account.
 */
@RestController
public class DeploymentClientResource extends AbstractClientResource {

	@Autowired
	protected DeploymentService clientService;

	/**
	 * GET /rest/authenticate -> check if the user is authenticated, and return its login.
	 */
	@RequestMapping(value = "/rest/admin/deployments/{deploymentId}", method = RequestMethod.GET, produces = "application/json")
	public JsonNode getDeployment(@PathVariable String deploymentId) throws BadRequestException {
		
		ServerConfig serverConfig = retrieveServerConfig(EndpointType.PROCESS);
		try {
			return clientService.getDeployment(serverConfig, deploymentId);
		} catch (FlowableServiceException e) {
			throw new BadRequestException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/rest/admin/deployments/{deploymentId}", method = RequestMethod.DELETE)
	public void deleteDeployment(@PathVariable String deploymentId, HttpServletResponse httpResponse) {
	    clientService.deleteDeployment(retrieveServerConfig(EndpointType.PROCESS), httpResponse, deploymentId);
	}
}
