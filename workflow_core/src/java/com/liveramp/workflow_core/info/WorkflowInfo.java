package com.liveramp.workflow_core.info;

import java.util.Optional;

import com.liveramp.importer.generated.AppType;
import com.liveramp.java_support.alerts_handler.AlertsHandler;

public interface WorkflowInfo {

  long getExecutionId();

  long getAttemptId();

  Optional<AppType> getAppType();

  Optional<String> getScopeIdentifier();

  Optional<String> getDescription();

  Optional<String> getHostname();

  Optional<AlertsHandler> getAlertsHandler();

  String getPersistenceClassName();



}