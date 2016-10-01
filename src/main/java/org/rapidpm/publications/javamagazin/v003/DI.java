package org.rapidpm.publications.javamagazin.v003;

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
  public static Service activateDI(Service service) {
    final Class<? extends Service> serviceClass = service.getClass();
    try {
      final Field declaredField = serviceClass.getDeclaredField("worker");
      declaredField.setAccessible(true);
      declaredField.set(service, new SubService());
    } catch (NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
    return service;
  }
}
