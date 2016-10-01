package org.rapidpm.publications.javamagazin.v004;

import org.rapidpm.proxybuilder.ProxyBuilder;
import org.rapidpm.proxybuilder.type.dymamic.DynamicProxyBuilder;

import java.lang.reflect.Field;

/**
 * Copyright (C) 2010 RapidPM
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by RapidPM - Team on 01.10.16.
 */
public class DI {


  public static boolean proxyNeeded;
  public static boolean securityNeeded;
  public static boolean metricsNeeded;

  public static Service activateDI(Class<ServiceImpl> service) {

    try {
      //Service result = createService(service.newInstance());
      final SubService subService = createSubService(SubServiceImpl.class.newInstance());
      final ServiceImpl rawService = service.newInstance();

      final Field declaredField = service.getDeclaredField("worker");
      declaredField.setAccessible(true);
      declaredField.set(rawService, subService);
      return createService(rawService);
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
    throw new RuntimeException(".... ");
  }

  private static Service createService(final ServiceImpl original) {
    if (proxyNeeded) {

      DynamicProxyBuilder<Service, ServiceImpl> proxyBuilder = ProxyBuilder
          .newDynamicProxyBuilder(Service.class, original);

      proxyBuilder = ((securityNeeded) ?
          proxyBuilder
              .addSecurityRule(() -> true)
              .addSecurityRule(() -> true)
          : proxyBuilder);

      proxyBuilder = ((metricsNeeded) ? proxyBuilder.addMetrics() : proxyBuilder);

      return proxyBuilder.build();
    } else {
      return original;
    }
  }

  private static SubService createSubService(final SubServiceImpl original) {
    if (proxyNeeded) {

      DynamicProxyBuilder<SubService, SubServiceImpl> proxyBuilder = ProxyBuilder
          .newDynamicProxyBuilder(SubService.class, original);

      proxyBuilder = ((securityNeeded) ?
          proxyBuilder
              .addSecurityRule(() -> true)
              .addSecurityRule(() -> true)
          : proxyBuilder);

      proxyBuilder = ((metricsNeeded) ? proxyBuilder.addMetrics() : proxyBuilder);

      return proxyBuilder.build();
    } else {
      return original;
    }
  }


}
