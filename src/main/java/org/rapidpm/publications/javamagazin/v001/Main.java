package org.rapidpm.publications.javamagazin.v001;


import org.rapidpm.proxybuilder.ProxyBuilder;
import org.rapidpm.proxybuilder.type.dymamic.DynamicProxyBuilder;

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
public class Main {


  public static void main(String[] args) {

    final boolean proxyNeeded = true; // dummy
    final boolean metricsNeeded = true; // dummy
    final boolean securityNeeded = true; // dummy

    final InnerDemoInterface instance = createInstance(
        new InnerDemoClass(),
        proxyNeeded,
        metricsNeeded,
        securityNeeded);

    final String hello = instance.workOn();
    System.out.println("hello = " + hello);

  }

  private static InnerDemoInterface createInstance(final InnerDemoClass original,
                                                   final boolean proxyNeeded,
                                                   final boolean securityNeeded,
                                                   final boolean metricsNeeded) {
    if (proxyNeeded) {

      DynamicProxyBuilder<InnerDemoInterface, InnerDemoClass> proxyBuilder = ProxyBuilder
          .newDynamicProxyBuilder(InnerDemoInterface.class, original);

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
