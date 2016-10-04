package org.rapidpm.publications.javamagazin.v005;

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

    DI.metricsNeeded = true;
    DI.securityNeeded = false;
    DI.proxyNeeded = true;

    final Service service = DI.activateDI(ServiceImpl.class);
    System.out.println("service.getClass() = " + service.getClass());
    System.out.println("service = " + service.workOn("Hello"));
  }
}
