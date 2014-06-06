/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2006, Red Hat Middleware LLC, and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.ws.common.deployment;

import org.jboss.ws.common.configuration.ConfigHelper;
import org.jboss.ws.common.integration.AbstractDeploymentAspect;
import org.jboss.wsf.spi.SPIProvider;
import org.jboss.wsf.spi.deployment.Deployment;
import org.jboss.wsf.spi.deployment.Endpoint;
import org.jboss.wsf.spi.management.EndpointMetrics;
import org.jboss.wsf.spi.management.EndpointMetricsFactory;
import org.jboss.wsf.spi.management.ServerConfig;
import org.jboss.wsf.spi.management.ServerConfigFactory;
import org.jboss.wsf.spi.metadata.config.EndpointConfig;

/**
 * A deployer that assigns the metrics to the Endpoint 
 *
 * @author Thomas.Diesler@jboss.org
 * @since 20-Jun-2007
 */
public class EndpointMetricsDeploymentAspect extends AbstractDeploymentAspect
{
   @Override
   public void start(Deployment dep)
   {
      EndpointMetricsFactory factory = SPIProvider.getInstance().getSPI(EndpointMetricsFactory.class);
      ServerConfigFactory scf = SPIProvider.getInstance().getSPI(ServerConfigFactory.class);
      EndpointConfig defaultConfig = scf.getServerConfig().getEndpointConfig(EndpointConfig.STANDARD_ENDPOINT_CONFIG);
      boolean enabled = Boolean.valueOf(defaultConfig.getProperty(EndpointConfig.STATISTICS_ENABLED));
      if (enabled)
      {
         for (Endpoint ep : dep.getService().getEndpoints())
         {

            EndpointMetrics metrics = factory.newEndpointMetrics();
            ep.setEndpointMetrics(metrics);
         }
      }
   }
}
