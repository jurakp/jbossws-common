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
package org.jboss.ws.common;

import java.util.Hashtable;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

/**
 * A simple factory for creating safe object names.
 *
 * @author Thomas.Diesler@jboss.org
 * @since 08-May-2006
 */
public class ObjectNameFactory
{
   public static ObjectName create(String name)
   {
      try
      {
         return new ObjectName(name);
      }
      catch (MalformedObjectNameException e)
      {
         throw Messages.MESSAGES.invalidObjectName(e, name);
      }
   }

   public static ObjectName create(String domain, String key, String value)
   {
      try
      {
         return new ObjectName(domain, key, value);
      }
      catch (MalformedObjectNameException e)
      {
         throw Messages.MESSAGES.invalidObjectName(e, domain + "," + key + "," + value);
      }
   }

   public static ObjectName create(String domain, Hashtable<String, String> table)
   {
      try
      {
         return new ObjectName(domain, table);
      }
      catch (MalformedObjectNameException e)
      {
         throw Messages.MESSAGES.invalidObjectName(e, domain + "," + table);
      }
   }
}
