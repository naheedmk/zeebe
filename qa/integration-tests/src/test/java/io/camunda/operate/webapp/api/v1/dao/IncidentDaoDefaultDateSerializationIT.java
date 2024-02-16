/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a proprietary license.
 * See the License.txt file for more information. You may not use this file
 * except in compliance with the proprietary license.
 */
package io.camunda.operate.webapp.api.v1.dao;

import io.camunda.operate.data.OperateDateTimeFormatter;
import io.camunda.operate.entities.ErrorType;
import io.camunda.operate.entities.IncidentEntity;
import io.camunda.operate.entities.IncidentState;
import io.camunda.operate.property.OperateProperties;
import io.camunda.operate.schema.templates.IncidentTemplate;
import io.camunda.operate.util.TestApplication;
import io.camunda.operate.util.j5templates.OperateSearchAbstractIT;
import io.camunda.operate.webapp.api.v1.entities.Incident;
import io.camunda.operate.webapp.api.v1.entities.Query;
import io.camunda.operate.webapp.api.v1.entities.Results;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static io.camunda.operate.schema.indices.IndexDescriptor.DEFAULT_TENANT_ID;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
    classes = {TestApplication.class},
    properties = {OperateProperties.PREFIX + ".importer.startLoadingDataOnStartup = false",
        OperateProperties.PREFIX + ".archiver.rolloverEnabled = false",
        "spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER",
        OperateProperties.PREFIX + ".multiTenancy.enabled = false",
        OperateProperties.PREFIX + ".rfc3339ApiDateFormat = false"})
public class IncidentDaoDefaultDateSerializationIT extends OperateSearchAbstractIT {
  @Autowired
  private IncidentDao dao;

  @Autowired
  private IncidentTemplate incidentIndex;

  @Autowired
  private OperateDateTimeFormatter dateTimeFormatter;

  private final String firstIncidentCreationTime = "2024-02-15T22:40:10.834+0000";
  private final String secondIncidentCreationTime = "2024-02-15T22:41:10.834+0000";

  private final String thirdIncidentCreationTime = "2024-01-15T22:40:10.834+0000";
  @Override
  protected void runAdditionalBeforeAllSetup() throws Exception {
    testSearchRepository.createOrUpdateDocumentFromObject(incidentIndex.getFullQualifiedName(),
        new IncidentEntity().setKey(7147483647L).setProcessDefinitionKey(5147483647L).setProcessInstanceKey(6147483647L)
            .setErrorType(ErrorType.JOB_NO_RETRIES).setState(IncidentState.ACTIVE).setErrorMessage("Some error")
            .setTenantId(DEFAULT_TENANT_ID)
            .setCreationTime(dateTimeFormatter.parseGeneralDateTime(firstIncidentCreationTime)).setJobKey(2251799813685260L));

    testSearchRepository.createOrUpdateDocumentFromObject(incidentIndex.getFullQualifiedName(),
        new IncidentEntity().setKey(7147483648L).setProcessDefinitionKey(5147483648L).setProcessInstanceKey(6147483648L)
            .setErrorType(ErrorType.JOB_NO_RETRIES).setState(IncidentState.ACTIVE).setErrorMessage("Another error")
            .setTenantId(DEFAULT_TENANT_ID).setCreationTime(dateTimeFormatter.parseGeneralDateTime(secondIncidentCreationTime))
            .setJobKey(3251799813685260L));

    testSearchRepository.createOrUpdateDocumentFromObject(incidentIndex.getFullQualifiedName(),
        new IncidentEntity().setKey(7147483649L).setProcessDefinitionKey(5147483649L).setProcessInstanceKey(6147483649L)
            .setErrorType(ErrorType.JOB_NO_RETRIES).setState(IncidentState.ACTIVE).setErrorMessage("Third error")
            .setTenantId(DEFAULT_TENANT_ID).setCreationTime(dateTimeFormatter.parseGeneralDateTime(thirdIncidentCreationTime))
            .setJobKey(3251799813685261L));

    searchContainerManager.refreshIndices("*operate-incident*");
  }

  @Test
  public void shouldFilterByCreationDate() {
    Results<Incident> flowNodeInstanceResults = dao.search(new Query<Incident>()
        .setFilter(new Incident().setCreationTime(firstIncidentCreationTime)));

    assertThat(flowNodeInstanceResults.getTotal()).isEqualTo(1L);
    assertThat(flowNodeInstanceResults.getItems().get(0).getCreationTime()).isEqualTo(firstIncidentCreationTime);
    assertThat(flowNodeInstanceResults.getItems().get(0).getMessage()).isEqualTo("Some error");
  }

  @Test
  public void shouldFilterByCreationDateWithDateMath() {
    Results<Incident> incidentResults = dao.search(new Query<Incident>()
        .setFilter(new Incident().setCreationTime(firstIncidentCreationTime + "||/d")));

    assertThat(incidentResults.getTotal()).isEqualTo(2L);

    Incident checkIncident = incidentResults.getItems().stream().filter(
            item -> "Some error".equals(item.getMessage()))
        .findFirst().orElse(null);
    assertThat(checkIncident).extracting("creationTime", "message")
        .containsExactly(firstIncidentCreationTime, "Some error");

    checkIncident = incidentResults.getItems().stream().filter(
            item -> "Another error".equals(item.getMessage()))
        .findFirst().orElse(null);
    assertThat(checkIncident).extracting("creationTime", "message")
        .containsExactly(secondIncidentCreationTime, "Another error");
  }
}
