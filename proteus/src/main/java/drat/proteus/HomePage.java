/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package drat.proteus;

import java.util.logging.Logger;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
  private static final long serialVersionUID = 1L;
  private FileUploadField fileUploadField;
  private static final Logger LOG = Logger.getLogger(HomePage.class.getName());

  public HomePage(final PageParameters params) {
    
    WebMarkupContainer alertDiv = new WebMarkupContainer("alert_div");
    alertDiv.setOutputMarkupId(true);
    
    if(!params.get("message").isEmpty() && !params.get("message").isNull()){
      String msg = params.get("message").toString();
      LOG.info("Alert message for Home Page: ["+msg+"]");
      alertDiv.add(new Label("alert_msg", Model.of(msg)).setVisible(true));
      alertDiv.setVisible(true);
    }
    else{
      alertDiv.add(new Label("alert_msg", Model.of("")).setVisible(false));
      alertDiv.setVisible(false);
    }
    add(alertDiv);
    
    fileUploadField = new FileUploadField("fileUploader");
    final TextField<String> path = new TextField<String>("path", Model.of(""));
    Form form = new DratStartForm("form", fileUploadField, path);
    add(form);

  }
}
